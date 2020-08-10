package connection;

/**
 * Responsável pela conexão com o banco de dados
 *@author Rayane 
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

public class SingleConnection {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;

	// chamada stática para o método conectar
	static {
		conectar();
	}

	// construtor
	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
