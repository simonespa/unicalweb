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

public class ChiudiAppello extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			Connection connection = (Connection) session.getAttribute("connessione");
			PreparedStatement state = null;
			String update = "update appello set chiuso = ? where codice_appello = ?";
			String appello = request.getParameter("appello");
			try {
				state = connection.prepareStatement(update);
				state.setString(1, "S");
				state.setString(2, appello);
				state.executeUpdate();
			} catch (SQLException e) {
			} finally {
				try {
					state.close();
				} catch (SQLException e) {}
				response.sendRedirect("/unicalweb/area-utenti/docente/calendario-appelli.jsp");
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}

}
