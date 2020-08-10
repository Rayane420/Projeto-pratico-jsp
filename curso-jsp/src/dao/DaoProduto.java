package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Authenticator.Result;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void Salvar(BeanProduto produto) {
		try {
			String sql = "insert into produto(nome, quantidade, valor) values (?, ?, ?)";
			PreparedStatement insertP = connection.prepareStatement(sql);
			insertP.setString(1, produto.getNome());
			insertP.setString(2, produto.getQuantidade());
			insertP.setString(3, produto.getValor());
			insertP.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanProduto> listar() throws Exception {
		List<BeanProduto> listar = new ArrayList<BeanProduto>();

		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getString("quantidade"));
			beanProduto.setValor(resultSet.getString("valor"));

			listar.add(beanProduto);
		}

		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from produto where id = '" + id + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	public BeanProduto consultar(String id) throws Exception {
		String sql = "select * from produto where id='" + id + "' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getString("quantidade"));
			beanProduto.setValor(resultSet.getString("valor"));

			return beanProduto;
		}

		return null;
	}
	
	public void atualizar (BeanProduto produto) {
		try {
			String sql = "update produto set nome = ?, quantidade = ?, valor = ? where id =" + produto.getId();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getQuantidade());
			preparedStatement.setString(3, produto.getValor());
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	public boolean validarProduto(String nome) throws Exception{
		String sql = "select count(1) as qtd from produto where nome='" + nome + "' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		
		return false;
	}
	
	

}