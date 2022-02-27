package gruppoProgetto;

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

public class ModificaProfilo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("user") != null) {
			Connection connection = (Connection) session.getAttribute("connessione");
			PreparedStatement state = null;
			ResultSet result = null;
			String query = null;
			// è la password che viene inserita nel form e che deve coincidere
			// con quella memorizzata nella base di dati
			String password = request.getParameter("oldPassword");
			String user = (String) session.getAttribute("user");
			String commento = request.getParameter("commento");
			try {
				// controlla che la password inserita nel form sia corretta
				query = "select password_gruppo from gruppo_di_progetto where username_gruppo = ?";
				state = connection.prepareStatement(query);
				state.setString(1, user);
				result = state.executeQuery();
				result.next();
				String passwordFromDB = result.getString(1);
				// se la password inserita nel form è corretta allora modifica
				// il profilo
				if (passwordFromDB.equals(password)) {
					// acquisisce i parametri da settare per la modifica del
					// profilo
					if ((password = request.getParameter("newPassword")).equals("")) {
						password = passwordFromDB;
					}
					// prepara la query e la esegue
					query = "update gruppo_di_progetto set password_gruppo = ?, commento = ? where username_gruppo = ?";
					state = connection.prepareStatement(query);
					state.setString(1, password);
					state.setString(2, commento);
					state.setString(3, user);
					state.executeUpdate();
					response.sendRedirect("/unicalweb/area-utenti/gruppo-progetto/profilo-utente.jsp");
				} else {
					// altrimenti richiama la pagina di modifica profilo con
					// errore
					response.sendRedirect("/unicalweb/area-utenti/gruppo-progetto/modifica-profilo-psw-errata.jsp");
				}
			} catch(SQLException e) {
				response.sendRedirect("/unicalweb/area-utenti/error-page.jsp?message="+e.getMessage());
			} finally {
				try {
					result.close();
				} catch (SQLException e) {
				}
				try {
					state.close();
				} catch (SQLException e) {
				}
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}

	}
}