/**
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is the connection factory, it is responsable to initiate connectivity
 * from the system and the database.
 * 
 */

package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;/*
										 * Variable that create the connection
										 * with DataBase.
										 */

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

	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		if (ConnectionFactory.connection == null) {
			Class.forName("com.mysql.jdbc.Driver");
			ConnectionFactory.connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/chamada", "root", "12345");
		}
		return ConnectionFactory.connection;
	}

	/**
	 * Getter of connection, <b>deprecated</b>.
	 * 
	 * @return connection that is a variable that create a connection with
	 *         database.
	 */

	public static Connection getConexao() {
		return connection;
	}
}
