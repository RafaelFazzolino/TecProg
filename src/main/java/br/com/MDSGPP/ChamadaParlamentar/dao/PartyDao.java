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
	 * @param lista is a Array containing all parties.
	 * @throws SQLException is for case some SQL exceptions happen.
	 */
	public void addPartyInTable (ArrayList<Party> list)
			throws SQLException {
		String sql = "insert into partido(sigla, nomePartido)values(?, ?)";

		PreparedStatement stmt;
		stmt = getConexao().prepareStatement(sql);

		int sizeList;/*Variable that contains the size of list.*/
		sizeList = list.size();
		
		for( int i = 0; i < sizeList; i++ ) {
			stmt.setString(UM, list.get(i).getAcronyn());
			stmt.setString(DOIS, list.get(i).getNameParty());

			stmt.execute();
		}

		stmt.close();
	}
	
	/**
	 * This method is to get a specific party in database.
	 * @return listaPassar what is an Array with all the desired parties.
	 * @throws SQLException is for case some SQL exceptions happen.
	 */
	public ArrayList<ArrayList<String>> catchParty() throws SQLException {
		
		ArrayList<ArrayList<String>> listPass;
		listPass = new ArrayList<ArrayList<String>>();
		
		String sql = "Select * from partido";
		
		PreparedStatement stmt;
		stmt = getConexao().prepareStatement(sql);
		
		ResultSet rs;
		rs = stmt.executeQuery();
		
		while( rs.next() ) {
			ArrayList<String> adding = new ArrayList<String>();
			adding.add(rs.getString("sigla"));
			adding.add(rs.getString("nomePartido"));
			listPass.add(adding);
		}
		
		return listPass;
	}
	
	
}
