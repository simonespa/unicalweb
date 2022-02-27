package docente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

public class UploadSpecifiche extends HttpServlet {
	
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
				File fileOutput = new File("D:\\progetti\\Java\\BDE\\unicalweb\\files\\specifiche\\", fileInputName);
				FileInputStream input = new FileInputStream(fileInput);
				FileOutputStream output = new FileOutputStream(fileOutput);
				while( input.available() > 0 ) {
					output.write(input.read());
				}
				input.close();
				output.close();
				session.setAttribute("attribute", "/unicalweb/files/specifiche/"+fileInputName);
				response.sendRedirect("/unicalweb/area-utenti/docente/aggiungi-progetto.jsp");
			} else {
				response.sendRedirect("/unicalweb/area-utenti/docente/upload-failed.jsp");
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
}