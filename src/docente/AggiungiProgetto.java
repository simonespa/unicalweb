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

public class AggiungiProgetto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			if( !aggiungiProgetto(request, response, session) ) {
				response.sendRedirect("/unicalweb/area-utenti/docente/elenco-progetti.jsp");
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
	
	private boolean aggiungiProgetto(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		boolean exception = false;
		Connection connection = (Connection) session.getAttribute("connessione");
		String update = "insert into progetto values(?, ?, ?, ?, ?, ?)";
		String codiceProgetto = request.getParameter("codiceProgetto");
		String titolo = request.getParameter("titolo");
		String descrizione = request.getParameter("descrizione");
		String urlSpecifiche = (String) session.getAttribute("attribute"); 
		String difficolta = request.getParameter("difficolta");
		String insegnamento = request.getParameter("insegnamento");
		PreparedStatement prepared = null;
		try {
			prepared = connection.prepareStatement(update);
			prepared.setString(1, codiceProgetto);
			prepared.setString(2, titolo);
			prepared.setString(3, descrizione);
			prepared.setString(4, urlSpecifiche);
			prepared.setString(5, difficolta);
			prepared.setString(6, insegnamento);
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
