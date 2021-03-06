/**
 * Class: Dao
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is to basicaly drop some tables to the system be able to update then.
 * 
 */

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
	 * 
	 * @throws SQLException
	 *             case the dataBase is off.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 */
	@SuppressWarnings("unused")
	public static void truncateTables() throws SQLException,
			ClassNotFoundException {

		truncateATable("datas");
		truncateATable("ranking");
		truncateATable("sessao");

	}

	/**
	 * This method truncate a single table.
	 * 
	 * @param table
	 *            it is the table to be truncated
	 * @throws SQLException
	 */
	private static void truncateATable(String table) throws SQLException {
		String sql = "truncate table " + table;

		PreparedStatement stmt = ConnectionFactory.getConexao()
				.prepareStatement(sql);

		stmt.execute();

	}
}
