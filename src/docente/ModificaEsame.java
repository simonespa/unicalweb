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

import utils.DateUtils;

public class ModificaEsame extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			if( !modificaEsame(request, response, session) ) {
				String appello = request.getParameter("appello");
				response.sendRedirect("/unicalweb/area-utenti/docente/calendario-esami.jsp?appello="+appello);
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
	
	private boolean modificaEsame(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		boolean exception = false;
		Connection connection = (Connection) session.getAttribute("connessione");
		String update = "update esame set voto_prova_scritta = ?, data_prova_orale = ?, voto_prova_orale = ?, voto_finale = ? " +
						"where matricola = ?";
		String matricola = request.getParameter("matricola");
		String votoScritto = request.getParameter("votoScritto");
		String giorno = request.getParameter("giorno");
		String mese = request.getParameter("mese");
		String anno = request.getParameter("anno");
		String data = DateUtils.getDate(giorno, mese, anno);
		String votoOrale = request.getParameter("votoOrale");
		String votoFinale = request.getParameter("votoFinale");
		PreparedStatement prepared = null;
		try {
			prepared = connection.prepareStatement(update);
			prepared.setString(1, votoScritto);
			prepared.setString(2, data);
			prepared.setString(3, votoOrale);
			prepared.setString(4, votoFinale);
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
		return exception;
	}

}