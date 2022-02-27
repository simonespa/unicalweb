package gruppoProgetto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registrazione extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ORACLE_USER = "gestione_esami";
	private static final String ORACLE_PASSWORD = "basididati";
	
	private boolean exception = false;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName(ORACLE_DRIVER);
		} catch (ClassNotFoundException e) {}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dichiarazione delle variabili
		Connection connection = null;
		Statement state = null;
		ResultSet result = null;
		int numeroProgetti = 0;
		
		try {
			// Stabilisce una connessione verso il DBMS
			connection = DriverManager.getConnection(ORACLE_URL, ORACLE_USER, ORACLE_PASSWORD);

			// Disabilita l'auto commit
			connection.setAutoCommit(false);
			
			// Restituisce il numero dei progetti e i codici presenti nel sistema
			state = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "select codice_progetto from progetto";
			result = state.executeQuery(query);
			result.last();
			numeroProgetti = result.getRow();
			
			if( numeroProgetti > 0 ) {	// se c'è più di un progetto allora procede alla registrazione
				String progetto = selezionaProgetto(connection, result, numeroProgetti);
				Date dataRegistrazione = selezionaDataDiSistema(connection);
				registraGruppo(request, response, connection, progetto, dataRegistrazione);
				registraComponentiGruppo(request, response, connection);
			} else {	// altrimenti notifica che non ci sono progetti nel sistema
				response.sendRedirect("/unicalweb/esito-registrazione.jsp?result=no&message=Nel sistema non ci sono progetti da assegnare al gruppo");
			}
			connection.commit();
		} catch(SQLException e) {
			// Effettua il rollback per invalidare le modifiche della transazione
			try {
				connection.rollback();
			} catch (SQLException e1) {}
			// Notifica l'eccezione con una pagina di errore generica se già non è stata gestita
			if( !exception ) {
				// Mette a true il flag che segnala il verificarsi di una eccezione
				exception = true;
				response.sendRedirect("/unicalweb/esito-registrazione.jsp?result=no&message=Errore della transazione durante la fase di registrazione <"+e.getMessage()+">");
			}
		} finally {
			// Abilita nuovamente l'auto commit mode
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {}
			
			// Chiude tutte le risorse allocate dal DBMS
			try {
				result.close();
			} catch (SQLException e) {}
			try {
				state.close();
			} catch (SQLException e) {}
			try {
				connection.close();
			} catch (SQLException e) {}
			// Notifica l'avvenuta registrazione se non si è verificata nessuna eccezione
			if( ! exception ) {
				response.sendRedirect("/unicalweb/esito-registrazione.jsp?result=yes&message=Registrazione effettuata correttamente");
			}
			exception = false;
		}
	}
	
	private String selezionaProgetto(Connection conn, ResultSet result, int numeroProgetti) throws SQLException {
		// Genera un numero pseudo casuale
		new Random(System.currentTimeMillis());
		int index = ((int) (Math.random() * numeroProgetti)) + 1;
		// Porta il cursore sull'indice scelto
		result.absolute(index);
		// Seleziona il relativo progetto che si trova all'indice corrispondente
		String progetto = result.getString(1);
		// Restituisce la stringa che identifica il codice del progetto
		return progetto;
	}
	
	private Date selezionaDataDiSistema(Connection conn) throws SQLException {
		// acquisisce la data di sistema nel formato aaaa-mm-gg
		return new Date(System.currentTimeMillis());
	}
	
	private void registraGruppo(HttpServletRequest request, HttpServletResponse response, Connection conn, String progetto, Date dataRegistrazione) throws SQLException, IOException {
		// Imposta la query da sottomettere al DBMS
		String insert = "insert into gruppo_di_progetto(username_gruppo, password_gruppo, data_registrazione, codice_progetto, commento) values(?, ?, ?, ?, ?)";
		
		// Acquisisce i parametri dal form
		PreparedStatement prepared = null;
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		
		// prepara la query e la esegue
		try {
			prepared = conn.prepareStatement(insert);
			prepared.setString(1, user);
			prepared.setString(2, password);
			prepared.setDate(3, dataRegistrazione);
			prepared.setString(4, progetto);
			prepared.setString(5, "");
			prepared.executeUpdate();
		} catch (SQLException e) {
			// Notifica l'avvenuta gestione dell'eccezione con il flag posto a "true"
			exception = true;
			// Notifica l'eccezione con una pagina di errore e delega la gestione al metodo chiamante
			response.sendRedirect("/unicalweb/esito-registrazione.jsp?result=no&message=Il gruppo "+user+" è già presente nel sistema");
			// Lancia l'eccezione al livello superiore
			throw new SQLException();
		} finally {
			prepared.close();
		}
	}
	
	private void registraComponentiGruppo(HttpServletRequest request, HttpServletResponse response, Connection conn) throws SQLException, IOException {
		// Dichiara le variabili
		PreparedStatement prepared = null;
		String matricola = null;
		String nome = null;
		String cognome = null;
		String email = null;

		// Acquisisce la username del gruppo dal form
		String user = request.getParameter("username");
		
		// Prepara la query
		String query = "insert into studente values(?, ?, ?, ?, ?, ?, ?)";
		try {
			// Setta la query nello statement
			prepared = conn.prepareStatement(query);
			for( int i = 1; i <= 4; i++) {	// Per ogni studente
				// Se il relativo campo non è vuoto, procede alla registrazione dei dati
				if( ! request.getParameter("matricola"+i).equals("") ) {
					matricola = request.getParameter("matricola"+i);
					nome = request.getParameter("nome"+i);
					cognome = request.getParameter("cognome"+i);
					email = request.getParameter("email"+i);
					prepared.setString(1, matricola);
					prepared.setString(2, nome);
					prepared.setString(3, cognome);
					prepared.setString(4, email);
					prepared.setString(5, user);
					prepared.setString(6, "N");
					prepared.setString(7, "nessuna nota");
					prepared.executeUpdate();
				}
			}
		} catch(SQLException e) {
			// Notifica l'avvenuta gestione dell'eccezione con il flag posto a "true"
			exception = true;
			// Notifica il sistema con una pagina di errore
			response.sendRedirect("/unicalweb/esito-registrazione.jsp?result=no&message=Si è verificato un'errore mentre si stava tentando di registrare lo studente "+nome+" "+cognome+". Nel sistema esiste già uno studente con la matricola "+matricola);
			// Lancia l'eccezione al livello superiore
			throw new SQLException();
		} finally {
			prepared.close();
		}
	}
}