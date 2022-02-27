package docente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificaProgetto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			if( !modificaProgetto(request, response, session) ) {
				response.sendRedirect("/unicalweb/area-utenti/docente/elenco-progetti.jsp");
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
	
	private boolean modificaProgetto(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		boolean exception = false;
		Connection connection = (Connection) session.getAttribute("connessione");
		String update = "update progetto set titolo = ?, descrizione = ?, url_file_specifiche = ?, " +
						"grado_difficolta = ?, codice_insegnamento = ? " +
						"where codice_progetto = ?";
		String progetto = request.getParameter("progetto");
		String titolo = request.getParameter("titolo");
		String descrizione = request.getParameter("descrizione");
		String urlSpecifiche = request.getParameter("urlSpecifiche");
		String difficolta = request.getParameter("difficolta");
		String insegnamento = request.getParameter("insegnamento");
		PreparedStatement prepared = null;
		try {
			prepared = connection.prepareStatement(update);
			prepared.setString(1, titolo);
			prepared.setString(2, descrizione);
			prepared.setString(3, urlSpecifiche);
			prepared.setString(4, difficolta);
			prepared.setString(5, insegnamento);
			prepared.setString(6, progetto);
			prepared.executeUpdate();			
		} catch (SQLException e) {
			response.sendRedirect("/unicalweb/area-utenti/error-page.jsp?message="+e.getMessage());
			exception = true;
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
