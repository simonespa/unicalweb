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

public class ModificaGruppo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if( session.getAttribute("user") != null ) {
			boolean exception = false;
			Connection connection = (Connection) session.getAttribute("connessione");
			PreparedStatement prepared = null;
			String update = "update gruppo_di_progetto set voto_progetto = ?, commento = ? where username_gruppo = ?";
			String voto = request.getParameter("voto");
			String commento = request.getParameter("commento");
			String user = (String) session.getAttribute("attribute");
			try {
				prepared = connection.prepareStatement(update);
				prepared.setString(1, voto);
				prepared.setString(2, commento);
				prepared.setString(3, user);
				prepared.executeUpdate();
			} catch (SQLException e) {
				exception = true;
				response.sendRedirect("/unicalweb/area-utenti/error-page.jsp?message="+e.getMessage());
			} finally {
				try {
					prepared.close();
				} catch (SQLException e) {
					if( !exception ) {
						response.sendRedirect("/unicalweb/area-utenti/error-page.jsp");
					}
				}
			}
			if( !exception ) {
				response.sendRedirect("/unicalweb/area-utenti/docente/dettagli-gruppo.jsp");
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}

}
