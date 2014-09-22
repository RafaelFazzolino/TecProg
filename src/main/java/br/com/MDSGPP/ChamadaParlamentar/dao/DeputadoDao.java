package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;

public class DeputadoDao extends ConnectionFactory {

	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;
	private static final int TEN = 10;
	private static final int ELEVEN = 11;
	private static final int TWELVE = 12;
		
	public DeputadoDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}
	
	/**
	 * This method add a deputy in the list Deputados.
	 * @param deputados is an ArrayList contains all deputies.
	 * @throws SQLException case the dataBase is off.
	 */
	
	public void adicionaDeputado(ArrayList<Deputados> deputys) throws SQLException {
		String sql = "insert into deputado(idParlamentar, matricula, ideCadastro, "
				+ "nomeCivil, nomeDeTratamento, sexo, uf, partido"
				+ ", numeroDoGabinete, anexo, telefone, email)" +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = getConexao().prepareStatement(sql);
		for( int i = 0; i < deputys.size(); i++ ) {

			stmt.setInt(ONE, deputys.get(i).getIdDoParlamentar());
			stmt.setInt(TWO, deputys.get(i).getMatricula());
			stmt.setInt(THREE, deputys.get(i).getIdeCadastro());
			stmt.setString(FOUR, deputys.get(i).getNomeCivilDoParlamentar());
			stmt.setString(FIVE, deputys.get(i).getNomeDeTratamentoDoParlamentar());
			stmt.setString(SIX, deputys.get(i).getSexo());
			stmt.setString(SEVEN, deputys.get(i).getUf());
			stmt.setString(EIGHT, deputys.get(i).getPartido());
			stmt.setString(NINE, deputys.get(i).getNumeroDoGabinete());
			stmt.setString(TEN, deputys.get(i).getAnexo());
			stmt.setString(ELEVEN, deputys.get(i).getTelefone());
			stmt.setString(TwELVE, deputys.get(i).getEmail());

			stmt.execute();

		}
		stmt.close();	
	}
	
	/**
	 * This method is used in the auto-complete in the search of a name.
	 * @return lista contains the name of all deputies.
	 * @throws SQLException case the dataBase is off.
	 */
	
	public ArrayList<String> getNomesDeputados() throws SQLException {
		String sql = "Select * from deputado"; 

		ArrayList<String> list= = new ArrayList<String>();

		PreparedStatement stmt= ConnectionFactory.getConexao().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while( rs.next() ) {
			String civilName = rs.getString("nomeCivil");
			String tratamentName = rs.getString("nomeDeTratamento");
			list.add(civilName);
			list.add(tratamentName;
		}

		rs.close();
		stmt.close();

		
		return list;

	}
	/**
	 * This method is to get the enrolls deputy.
	 * @return lista what is the Array contains all enrolls of deputies.
	 * @throws SQLException is case occurs an error with dataBase.
	 */
	public ArrayList<Integer> getMatriculaDeputados() throws SQLException {
		
		/**
		 * Creates the SQL command search how to look for an specific line.
		 */
		
		String sql = "Select * from deputado";

		ArrayList<Integer> list = new ArrayList<Integer>();

		/**
		 * Creates the prepared statement that is what is going to connect with the DB.
		 */
		
		PreparedStatement stmt= ConnectionFactory.getConexao().prepareStatement(sql);
		
		/**
		 * Execute the STMT to search the data.
		 */
		
		ResultSet rs = stmt.executeQuery();

		while( rs.next() ) {
			lista.add(rs.getInt("matricula"));
		}


		rs.close();
		return list;
	}
	
	/**
	 * This method is to get all deputies in dataBase.
	 * @return lista what is an array contains all deputies.
	 * @throws SQLException is case occurs an error with dataBase.
	 */

	public ArrayList<Deputados> getDeputados() throws SQLException {
		String sql = "Select * from deputado";
		ArrayList<Deputados> list = new ArrayList<Deputados>();

		PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while( rs.next() ) {
			Deputados deputy = new Deputados();

			deputy.setIdDoParlamentar(rs.getInt("idParlamentar"));
			deputy.setMatricula(rs.getInt("matricula"));
			deputy.setIdeCadastro(rs.getInt("ideCadastro"));
			deputy.setNomeCivilDoParlamentar(rs.getString("nomeCivil"));
			deputy.setNomeDeTratamentoDoParlamentar(rs.getString("nomeDeTratamento"));
			deputy.setSexo(rs.getString("sexo"));		
			deputy.setUf(rs.getString("uf"));
			deputy.setPartido(rs.getString("partido"));
			deputy.setNumeroDoGabinete(rs.getString("numeroDoGabinete"));
			deputy.setAnexo(rs.getString("anexo"));
			deputy.setTelefone(rs.getString("telefone"));
			deputy.setEmail(rs.getString("email"));

			listy.add(deputy);
		}

		rs.close();
		return list;
	}
}
