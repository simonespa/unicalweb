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

public class RegistraEsame extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			if( !registraEsame(request, response, session) )
				response.sendRedirect("/unicalweb/area-utenti/docente/calendario-esami.jsp");
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
	
	private boolean registraEsame(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		boolean exception = false;
		Connection connection = (Connection) session.getAttribute("connessione");
		String update = "update esame set registrato = 'S' where matricola = ?";
		String matricola = request.getParameter("matricola");
		PreparedStatement prepared = null;
		try {
			prepared = connection.prepareStatement(update);
			prepared.setString(1, matricola);
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
