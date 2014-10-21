package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao extends ConnectionFactory {
	
	public Dao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}
	/**
	 * Method to create the connection to the database with the tables Ranking,
	 * sessoes and datas. 
	 * @throws SQLException case the dataBase is off.
	 * @throws ClassNotFoundException case the class is not found.
	 */
	public static void truncateTables() throws SQLException, ClassNotFoundException{
		
		String sql1 = "truncate table datas";
		String sql2 = "truncate table ranking";
		String sql3 = "truncate table sessao";
		
		PreparedStatement stmt1;/*This variable received the connection.*/
		stmt1 = ConnectionFactory.getConexao().prepareStatement(sql1);
		
		PreparedStatement stmt2;/*This variable received the connection.*/
		stmt2 = ConnectionFactory.getConexao().prepareStatement(sql2);
		
		PreparedStatement stmt3;/*This variable received the connection.*/
		stmt3 = ConnectionFactory.getConexao().prepareStatement(sql3);
	
		stmt1.execute();
		stmt2.execute();
		stmt3.execute();
	}
}
