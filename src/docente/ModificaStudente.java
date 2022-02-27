package docente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificaStudente extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String gruppo = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			if( !modificaStudente(request, response, session) ) {
				response.sendRedirect("/unicalweb/area-utenti/docente/dettagli-gruppo.jsp?gruppo="+gruppo);
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
	
	private boolean modificaStudente(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		boolean exception = false;
		Connection connection = (Connection) session.getAttribute("connessione");
		String update = "update studente set nome = ?, cognome = ?, email = ?, nota = ? where matricola = ?";
		String matricola = request.getParameter("matricola");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String nota = request.getParameter("nota");
		PreparedStatement prepared = null;
		try {
			prepared = connection.prepareStatement(update);
			prepared.setString(1, nome);
			prepared.setString(2, cognome);
			prepared.setString(3, email);
			prepared.setString(4, nota);
			prepared.setString(5, matricola);
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
		gruppo = getGruppo(connection, matricola);
		return exception;
	}

	private String getGruppo(Connection connection, String matricola) {
		PreparedStatement prepared = null;
		ResultSet result = null;
		String query = "select username_gruppo from studente where matricola = ?";
		String gruppoProgetto = null;
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1, matricola);
			result = prepared.executeQuery();
			result.next();
			gruppoProgetto = result.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				prepared.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return gruppoProgetto;
	}

}