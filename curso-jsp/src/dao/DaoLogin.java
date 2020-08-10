package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {

	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception {
		String sql = "select * from usuario where login ='" +login+ "' and senha = '" +senha+ "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(); //executando a seleção
		
		if(resultSet.next()) {
			return true; //quando possui usuário válido entra nessa condição
		}else {
			return false; //usuário não validado
		}
		
	}
	
	
	
	
}