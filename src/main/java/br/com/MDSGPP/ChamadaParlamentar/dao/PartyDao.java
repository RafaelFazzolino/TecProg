/**
 * Class: PartyDao
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is to connect the system with the table party of the database. 
 */

package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public class PartyDao extends ConnectionFactory {

	private final int UM = 1;
	private final int DOIS = 2;

	public PartyDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}

	/**
	 * This method is to add all parties of the table.
	 * 
	 * @param lista
	 *            is a Array containing all parties.
	 * @throws SQLException
	 *             is for case some SQL exceptions happen.
	 */
	public void addPartyInTable(ArrayList<Party> list) throws SQLException {
		String sql = "insert into partido(sigla, nomePartido)values(?, ?)";

		PreparedStatement stmt;
		stmt = getConexao().prepareStatement(sql);

		int sizeList;// Variable that contains the size of list.
		sizeList = list.size();

		for (int i = 0; i < sizeList; i++) {
			stmt.setString(UM, list.get(i).getAcronyn());
			stmt.setString(DOIS, list.get(i).getNameParty());

			stmt.execute();
		}

		stmt.close();
	}

	/**
	 * This method is to get a specific party in database.
	 * 
	 * @return listaPassar what is an Array with all the desired parties.
	 * @throws SQLException
	 *             is for case some SQL exceptions happen.
	 */
	public ArrayList<ArrayList<String>> catchParty() throws SQLException {

		ArrayList<ArrayList<String>> listPass;
		listPass = new ArrayList<ArrayList<String>>();

		String sql = "Select * from partido";

		PreparedStatement stmt;
		stmt = getConexao().prepareStatement(sql);

		ResultSet rs;
		rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> adding = new ArrayList<String>();
			adding.add(rs.getString("sigla"));
			adding.add(rs.getString("nomePartido"));
			listPass.add(adding);
		}

		return listPass;
	}

}
