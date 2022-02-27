package gruppoProgetto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RitiraStudente extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			boolean exception = false;
			Connection connection = (Connection) session.getAttribute("connessione");
			PreparedStatement prepared = null;
			String update = "update studente set ritirato = 'S' where matricola = ?";
			String matricola = request.getParameter("matricola");
			try {
				prepared = connection.prepareStatement(update);
				prepared.setString(1, matricola);
				prepared.executeUpdate();
			} catch (SQLException e) {
				exception = true;
				response.sendRedirect("/unicalweb/area-utenti/error-page.jsp?message="+e.getMessage());
			} finally {
				try {
					prepared.close();
				} catch (SQLException e) {}
			}
			if( !exception )
				response.sendRedirect("/unicalweb/area-utenti/gruppo-progetto/componenti-gruppo.jsp");
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}

	}

}