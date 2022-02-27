package gruppoProgetto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.security.x509.Extension;

import com.oreilly.servlet.MultipartRequest;

public class UploadProgetto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("user") != null) {
			MultipartRequest multi = new MultipartRequest(request, ".");
			// crea l'handler per il file da caricare sul server
			File fileInput = multi.getFile("file");
			// acquisisce il nome del file
			String fileInputName = multi.getFilesystemName("file");
			if( fileInput != null ) {
				// crea l'handler per il file da copiare sul filesystem
				File fileOutput = new File("D:\\progetti\\Java\\BDE\\unicalweb\\files\\progetti\\", fileInputName);
				FileInputStream input = new FileInputStream(fileInput);
				FileOutputStream output = new FileOutputStream(fileOutput);
				while( input.available() > 0 ) {
					output.write(input.read());
				}
				input.close();
				output.close();
				// rende persistente sul DB il percorso della risorsa caricata sul server
				if( !aggiornaDatabase(response, session, fileInputName) ) {
					response.sendRedirect("/unicalweb/area-utenti/gruppo-progetto/profilo-utente.jsp");
				}
			} else {
				response.sendRedirect("/unicalweb/area-utenti/gruppo-progetto/upload-failed.jsp");
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
	
	private boolean aggiornaDatabase(HttpServletResponse response, HttpSession session, String fileInputName) throws IOException {
		boolean exception = false;
		Connection connection = (Connection) session.getAttribute("connessione");
		String username = (String) session.getAttribute("user");
		PreparedStatement prepared = null;
		String update = "update gruppo_di_progetto set data_consegna = ?, url_file_progetto_consegnato = ? where username_gruppo = ?";
		String url = "/unicalweb/files/progetti/" + fileInputName;
		Date date = new Date(System.currentTimeMillis());
		try {
			prepared = connection.prepareStatement(update);
			prepared.setDate(1, date);
			prepared.setString(2, url);
			prepared.setString(3, username);
			prepared.executeUpdate();
		} catch (SQLException e) {
			exception = true;
			response.sendRedirect("/unicalweb/area-utenti/error-page.jsp?message="+e.getMessage());
		} finally {
			try {
				prepared.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exception;
	}

}