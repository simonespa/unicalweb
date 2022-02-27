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

public class ModificaProfilo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("user") != null) {
			Connection connection = (Connection) session
					.getAttribute("connessione");
			PreparedStatement state = null;
			ResultSet result = null;
			String query = null;
			// è la password che viene inserita nel form e che deve coincidere
			// con quella memorizzata
			// nella base di dati
			String password = request.getParameter("oldPassword");
			String user = (String) session.getAttribute("user");
			String nome = null;
			String cognome = null;
			String email = null;
			try {
				// controlla che la password inserita nel form sia corretta
				query = "select password_docente from docente where username_docente = ?";
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
					if ((password = request.getParameter("newPassword"))
							.equals("")) {
						password = passwordFromDB;
					}
					if ((nome = request.getParameter("nome")) == null) {
						nome = (String) session.getAttribute("nome");
					}
					if ((cognome = request.getParameter("cognome")) == null) {
						cognome = (String) session.getAttribute("cognome");
					}
					if ((email = request.getParameter("email")) == null) {
						email = (String) session.getAttribute("email");
					}
					// prepara la query e la esegue
					query = "update docente set " + "password_docente = ?, "
							+ "nome = ?, " + "cognome = ?, " + "email = ? "
							+ "where username_docente = ?";
					state = connection.prepareStatement(query);
					state.setString(1, password);
					state.setString(2, nome);
					state.setString(3, cognome);
					state.setString(4, email);
					state.setString(5, user);
					state.executeUpdate();
					session.setAttribute("nome", nome);
					session.setAttribute("cognome", cognome);
					session.setAttribute("body-home", "profilo-utente.jsp");
					response
							.sendRedirect("/unicalweb/area-utenti/docente/profilo-utente.jsp");
				} else {
					// altrimenti richiama la pagina di modifica profilo con
					// errore
					response
							.sendRedirect("/unicalweb/area-utenti/docente/modifica-profilo-psw-errata.jsp");
				}
			} catch (SQLException e) {
			} finally {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			response.sendRedirect("/unicalweb/sessione-scaduta.jsp");
		}
	}

}