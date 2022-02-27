package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

	private static Connection connection;
	
	public static void setConnection(Connection conn) {
		connection = conn;
	}
	
	public static String getInsegnamento(String insegnamento) {
		String query = "select nome from insegnamento where codice_insegnamento = ?";
		PreparedStatement prepared = null;
		ResultSet result = null;
		String nomeInsegnamento = null;
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1, insegnamento);
			result = prepared.executeQuery();
			result.next();
			nomeInsegnamento = result.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				prepared.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nomeInsegnamento;
	}
	
	public static String getAppello(String appello) {
		String query = "select data_prova_scritta from appello where codice_appello = ?";
		PreparedStatement prepared = null;
		ResultSet result = null;
		String dataAppello = null;
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1, appello);
			result = prepared.executeQuery();
			result.next();
			dataAppello = result.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				prepared.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DateUtils.formatDate(dataAppello);
	}
	
}
