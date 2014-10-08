package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

	private static Connection connection;

	public ConnectionFactory() {

	}
	
	/**
	 * Method that checks if the database connection exists, if not it creates
	 * the connection and returns it.
	 * 
	 * @returns the connection with the database.
	 * @throws ClassNotFoundException
	 *             if the database is off.
	 * @throws SQLException
	 *             if miss spelled SQL is entered.
	 */

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if( ConnectionFactory.connection == null ) {
			Class.forName("com.mysql.jdbc.Driver");
			ConnectionFactory.connection = DriverManager.getConnection
					("jdbc:mysql://localhost/chamada", "root", "12345");
		}
		return ConnectionFactory.connection;
	}
	
	/**
	 * Getter of connection, <b>deprecated</b>.
	 * 
	 * @return connection
	 */

	public static Connection getConexao() {
		return connection;
	}	
}
