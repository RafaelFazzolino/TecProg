package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;

public class DeputadoDao extends ConnectionFactory {

	private static final int ONE = 1;/* Constant which takes the value 1. */
	private static final int TWO = 2;/* Constant which takes the value 2. */
	private static final int THREE = 3;/* Constant which takes the value 3. */
	private static final int FOUR = 4;/* Constant which takes the value 4. */
	private static final int FIVE = 5;/* Constant which takes the value 5. */
	private static final int SIX = 6;/* Constant which takes the value 6. */
	private static final int SEVEN = 7;/* Constant which takes the value 7. */
	private static final int EIGHT = 8;/* Constant which takes the value 8. */
	private static final int NINE = 9;/* Constant which takes the value 9. */
	private static final int TEN = 10;/* Constant which takes the value 10. */
	private static final int ELEVEN = 11;/* Constant which takes the value 11. */
	private static final int TWELVE = 12;/* Constant which takes the value 12. */

	/**
	 * This method is to create the instance of ConnectionFactory.
	 * 
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case occurs an error with database.
	 */
	public DeputadoDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}

	/**
	 * This method add a deputy in the list Deputies.
	 * 
	 * @param deputados
	 *            is an ArrayList contains all deputies.
	 * @throws SQLException
	 *             case the dataBase is off.
	 */

	public void adicionaDeputado(ArrayList<Deputies> deputies)
			throws SQLException {
		String sql;/*
					 * Variable that received the code to add deputies on
					 * database.
					 */
		sql = "insert into deputado(idParlamentar, matricula, ideCadastro, "
				+ "nomeCivil, nomeDeTratamento, sexo, uf, partido"
				+ ", numeroDoGabinete, anexo, telefone, email)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt;/*
								 * Variable that create the connection with
								 * database.
								 */
		stmt = getConexao().prepareStatement(sql);

		int sizeDeputies;/* Variable that contains the size of deputies. */
		sizeDeputies = deputies.size();

		for (int i = 0; i < sizeDeputies; i++) {

			stmt.setInt(ONE, deputies.get(i).getIdDeputy());
			stmt.setInt(TWO, deputies.get(i).getResgitry());
			stmt.setInt(THREE, deputies.get(i).getIdRegister());
			stmt.setString(FOUR, deputies.get(i).getNameCivilCongressman());
			stmt.setString(FIVE, deputies.get(i)
					.getNameTreatmentCongressman());
			stmt.setString(SIX, deputies.get(i).getSex());
			stmt.setString(SEVEN, deputies.get(i).getFederativeUnit());
			stmt.setString(EIGHT, deputies.get(i).getParty());
			stmt.setString(NINE, deputies.get(i).getNumberCabinet());
			stmt.setString(TEN, deputies.get(i).getAnnexx());
			stmt.setString(ELEVEN, deputies.get(i).getTelephone());
			stmt.setString(TWELVE, deputies.get(i).getEmail());

			stmt.execute();

		}
		stmt.close();
	}

	/**
	 * This method is used in the auto-complete in the search of a name.
	 * 
	 * @return list contains the name of all deputies.
	 * @throws SQLException
	 *             case the dataBase is off.
	 */

	public ArrayList<String> getNomesDeputados() throws SQLException {
		String sql;/* Variable that contains the code to database. */
		sql = "Select * from deputado";

		ArrayList<String> list;/* This is an Array that contains all deputies. */
		list = new ArrayList<String>();

		PreparedStatement stmt;/*
								 * Variable that create the connection with
								 * database.
								 */
		stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		ResultSet rs;/* Variable that received the connection. */
		rs = stmt.executeQuery();

		while (rs.next()) {

			String civilName;/*
							 * Variable that contains the original name of
							 * deputy.
							 */
			civilName = rs.getString("nomeCivil");

			String tratamentName;/*
								 * Variable that contains the Treatment Name of
								 * deputy.
								 */
			tratamentName = rs.getString("nomeDeTratamento");

			list.add(civilName);
			list.add(tratamentName);
		}

		rs.close();
		stmt.close();

		return list;

	}

	/**
	 * This method is to get the enrolls deputy.
	 * 
	 * @return list what is the Array contains all enrolls of deputies.
	 * @throws SQLException
	 *             is case occurs an error with dataBase.
	 */
	public ArrayList<Integer> getMatriculaDeputados() throws SQLException {

		/**
		 * Creates the SQL command search how to look for an specific line.
		 */

		String sql;/* Variable that received the code of database. */
		sql = "Select * from deputado";

		ArrayList<Integer> list;/* This is an array that contains all deputies. */
		list = new ArrayList<Integer>();

		/**
		 * Creates the prepared statement that is what is going to connect with
		 * the DB.
		 */

		PreparedStatement stmt = ConnectionFactory.getConexao()
				.prepareStatement(sql);

		/**
		 * Execute the STMT to search the data.
		 */

		ResultSet rs;/* Variable that received the connection. */
		rs = stmt.executeQuery();

		while (rs.next()) {
			list.add(rs.getInt("matricula"));
		}

		rs.close();
		return list;
	}

	/**
	 * This method is to get all deputies in dataBase.
	 * 
	 * @return lista what is an array contains all deputies.
	 * @throws SQLException
	 *             is case occurs an error with dataBase.
	 */

	public ArrayList<Deputies> getDeputados() throws SQLException {
		String sql;/* Variable that received the code of database. */
		sql = "Select * from deputado";

		ArrayList<Deputies> list;/* This is an array that contains all deputies. */
		list = new ArrayList<Deputies>();

		PreparedStatement stmt;/* Variable that create the connection. */
		stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		ResultSet rs;/* Variable that received the connection. */
		rs = stmt.executeQuery();

		while (rs.next()) {
			Deputies deputy;/*
							 * Variable that received all features of the
							 * deputy.
							 */
			deputy = new Deputies();

			deputy.setIdDeputy(rs.getInt("idParlamentar"));
			deputy.setRegsitry(rs.getInt("matricula"));
			deputy.setIdRegister(rs.getInt("ideCadastro"));
			deputy.setNameCivilCongressman(rs.getString("nomeCivil"));
			deputy.setNameTreatmentCongressman(rs
					.getString("nomeDeTratamento"));
			deputy.setSex(rs.getString("sexo"));
			deputy.setFederativeUnit(rs.getString("uf"));
			deputy.setParty(rs.getString("partido"));
			deputy.setNumberCabinet(rs.getString("numeroDoGabinete"));
			deputy.setAnnexx(rs.getString("anexo"));
			deputy.setTelephone(rs.getString("telefone"));
			deputy.setEmail(rs.getString("email"));

			list.add(deputy);
		}

		rs.close();
		return list;
	}
}
