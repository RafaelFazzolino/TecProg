package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public class PartidoDao extends ConnectionFactory {

	private final int UM = 1;
	private final int DOIS = 2;

	public PartidoDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}

	/**
	 * This method is to add all parties of the table.
	 * @param lista is a Array containing all parties.
	 * @throws SQLException is for case some SQL exceptions happen.
	 */
	public void adicionarPartidoNaTable (ArrayList<Party> lista)
			throws SQLException {
		String sql = "insert into partido(sigla, nomePartido)values(?, ?)";

		PreparedStatement stmt = getConexao().prepareStatement(sql);

		for( int i = 0; i < lista.size(); i++ ) {
			stmt.setString(UM, lista.get(i).getAcronyn());
			stmt.setString(DOIS, lista.get(i).getNameParty());

			stmt.execute();
		}

		stmt.close();
	}
	
	/**
	 * This method is to get a specific party in database.
	 * @return listaPassar what is an Array with all the desired parties.
	 * @throws SQLException is for case some SQL exceptions happen.
	 */
	public ArrayList<ArrayList<String>> pegarPartidos() throws SQLException {
		
		ArrayList<ArrayList<String>> listaPassar = new ArrayList<ArrayList<String>>();
		
		String sql = "Select * from partido";
		
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while( rs.next() ) {
			ArrayList<String> adicionar = new ArrayList<String>();
			adicionar.add(rs.getString("sigla"));
			adicionar.add(rs.getString("nomePartido"));
			listaPassar.add(adicionar);
		}
		
		return listaPassar;
	}
	
	
}
