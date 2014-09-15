package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;

public class DeputadoDao extends ConnectionFactory {

	private static final int UM = 1;
	private static final int DOIS = 2;
	private static final int TRES = 3;
	private static final int QUATRO = 4;
	private static final int CINCO = 5;
	private static final int SEIS = 6;
	private static final int SETE = 7;
	private static final int OITO = 8;
	private static final int NOVE = 9;
	private static final int DEZ = 10;
	private static final int ONZE = 11;
	private static final int DOZE = 12;
		
	public DeputadoDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}
	
	/**
	 * This method add a deputy in the list Deputados.
	 * @param deputados is an ArrayList contains all deputies.
	 * @throws SQLException case the dataBase is off.
	 */
	
	public void adicionaDeputado(ArrayList<Deputados> deputados) throws SQLException {
		String sql = "insert into deputado(idParlamentar, matricula, ideCadastro, "
				+ "nomeCivil, nomeDeTratamento, sexo, uf, partido"
				+ ", numeroDoGabinete, anexo, telefone, email)" +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = getConexao().prepareStatement(sql);
		for( int i = 0; i < deputados.size(); i++ ) {

			stmt.setInt(UM, deputados.get(i).getIdDoParlamentar());
			stmt.setInt(DOIS, deputados.get(i).getMatricula());
			stmt.setInt(TRES, deputados.get(i).getIdeCadastro());
			stmt.setString(QUATRO, deputados.get(i).getNomeCivilDoParlamentar());
			stmt.setString(CINCO, deputados.get(i).getNomeDeTratamentoDoParlamentar());
			stmt.setString(SEIS, deputados.get(i).getSexo());
			stmt.setString(SETE, deputados.get(i).getUf());
			stmt.setString(OITO, deputados.get(i).getPartido());
			stmt.setString(NOVE, deputados.get(i).getNumeroDoGabinete());
			stmt.setString(DEZ, deputados.get(i).getAnexo());
			stmt.setString(ONZE, deputados.get(i).getTelefone());
			stmt.setString(DOZE, deputados.get(i).getEmail());

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

		ArrayList<String> lista = new ArrayList<String>();

		PreparedStatement stmt= ConnectionFactory.getConexao().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while( rs.next() ) {
			String nomeCivil = rs.getString("nomeCivil");
			String nomeTratamento = rs.getString("nomeDeTratamento");
			lista.add(nomeCivil);
			lista.add(nomeTratamento);
		}

		rs.close();
		stmt.close();

		
		return lista;

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

		ArrayList<Integer> lista = new ArrayList<Integer>();

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
		return lista;
	}
	
	/**
	 * This method is to get all deputies in dataBase.
	 * @return lista what is an array contains all deputies.
	 * @throws SQLException is case occurs an error with dataBase.
	 */

	public ArrayList<Deputados> getDeputados() throws SQLException {
		String sql = "Select * from deputado";
		ArrayList<Deputados> lista = new ArrayList<Deputados>();

		PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while( rs.next() ) {
			Deputados deputado = new Deputados();

			deputado.setIdDoParlamentar(rs.getInt("idParlamentar"));
			deputado.setMatricula(rs.getInt("matricula"));
			deputado.setIdeCadastro(rs.getInt("ideCadastro"));
			deputado.setNomeCivilDoParlamentar(rs.getString("nomeCivil"));
			deputado.setNomeDeTratamentoDoParlamentar(rs.getString("nomeDeTratamento"));
			deputado.setSexo(rs.getString("sexo"));		
			deputado.setUf(rs.getString("uf"));
			deputado.setPartido(rs.getString("partido"));
			deputado.setNumeroDoGabinete(rs.getString("numeroDoGabinete"));
			deputado.setAnexo(rs.getString("anexo"));
			deputado.setTelefone(rs.getString("telefone"));
			deputado.setEmail(rs.getString("email"));

			lista.add(deputado);
		}

		rs.close();
		return lista;
	}
}
