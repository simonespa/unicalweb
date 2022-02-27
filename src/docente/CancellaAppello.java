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

@SuppressWarnings("serial")
public class CancellaAppello extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if( session.getAttribute("user") != null) {
			cancellaAppello(request, response, session);
			response.sendRedirect("/unicalweb/area-utenti/docente/calendario-appelli.jsp");
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}
	
	private void cancellaAppello(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Connection connection = (Connection) session.getAttribute("connessione");
		String deleteEsami = "delete from esame where codice_appello = ?";
		String deleteAppello = "delete from appello where codice_appello = ?";
		String appello = request.getParameter("appello");
		PreparedStatement prepared = null;
		try {
			// inizia la transazione
			connection.setAutoCommit(false);
			
			// cancella tutti gli esami relativi all'appello selezionato
			prepared = connection.prepareStatement(deleteEsami);
			prepared.setString(1, appello);
			prepared.executeUpdate();
			
			// infine cancella l'appello selezionato
			prepared = connection.prepareStatement(deleteAppello);
			prepared.setString(1, appello);
			prepared.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// effettua il rollback della transazione
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// abiilità nuovamente l'autocommit mode
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// chiude lo statement
			try {
				prepared.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
