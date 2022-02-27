import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ORACLE_USER = "gestione_esami";
	private static final String ORACLE_PASSWORD = "basididati";
	private static final int SESSION_EXPIRES_TIME = 900;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName(ORACLE_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String utente = null;
		if( (utente = (String) session.getAttribute("tipo")) == null ) {
			String tipo = request.getParameter("tipo");
			Connection connection = null;
			CallableStatement callable = null;
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			String call = null;
			String result = null;
			if( tipo.equals("docente") ){
				call = "{ call ? := login_docente(?, ?) }";
				try {
					connection = DriverManager.getConnection(ORACLE_URL, ORACLE_USER, ORACLE_PASSWORD);
					callable = connection.prepareCall(call);
					callable.registerOutParameter(1, Types.VARCHAR);
					callable.setString(2, user);
					callable.setString(3, password);
					callable.execute();
					result = callable.getString(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (result != null ) {
					String[] results = result.split("&");
					// descrive il tipo di utente
					session.setAttribute("tipo", "docente");
					// questi 4 attributi indicano lo storico della pag€ina visualizzata per ogni rispettivo link
					// indicato nell'intestazione. Questo evita che ad ogni refresh della pagina venga caricata
					// quella di default
					session.setAttribute("body-home", "profilo-utente.jsp");
					session.setAttribute("body-gruppo", "elenco-gruppi.jsp");
					session.setAttribute("body-progetto", "elenco-progetti.jsp");
					session.setAttribute("body-statistiche", "analisi-statistiche-body.jsp");
					// questi quattro attributi si commentano da soli
					session.setAttribute("user", user);
					session.setAttribute("nome", results[0]);
					session.setAttribute("cognome", results[1]);
					session.setAttribute("connessione", connection);
					// setta l'intervallo massimo per la sessione, scaduto il quale la sessione viene invalidata
					session.setMaxInactiveInterval(SESSION_EXPIRES_TIME);
					// redirezione l'utente alla pagina principale
					response.sendRedirect("/unicalweb/area-utenti/docente/homepage.jsp");
				} else {
					response.sendRedirect("/unicalweb/errore-login.jsp");
				}
			} else {
				call = "{ call ? := login_gruppo_progetto(?, ?) }";
				try {
					connection = DriverManager.getConnection(ORACLE_URL, ORACLE_USER, ORACLE_PASSWORD);
					callable = connection.prepareCall(call);
					callable.registerOutParameter(1, Types.VARCHAR);
					callable.setString(2, user);
					callable.setString(3, password);
					callable.execute();
					result = callable.getString(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (result != null ) {
					session.setAttribute("tipo", "gruppo-progetto");
					session.setAttribute("user", result);
					session.setAttribute("connessione", connection);
					session.setAttribute("body-home", "profilo-utente.jsp");
					session.setMaxInactiveInterval(SESSION_EXPIRES_TIME);
					response.sendRedirect("/unicalweb/area-utenti/gruppo-progetto/homepage.jsp");
				} else {
					response.sendRedirect("/unicalweb/errore-login.jsp");
				}
			}
		} else {
			response.sendRedirect("/unicalweb/area-utenti/"+utente+"/homepage.jsp");
		}

	}

}