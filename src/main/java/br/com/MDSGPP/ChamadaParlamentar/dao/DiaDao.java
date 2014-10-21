package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.model.Dia;
import br.com.MDSGPP.ChamadaParlamentar.model.SessoesEReunioes;

public class DiaDao extends ConnectionFactory {

	private static final String DATAS = "datas";/*
												 * This is a constant that
												 * contains the string "datas".
												 */

	/**
	 * This method is to create the connection.
	 * 
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case the database is off.
	 */
	public DiaDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}

	/**
	 * This method search all the descriptions based on a day.
	 * 
	 * @return list contains all days.
	 * @throws SQLException
	 *             case the dataBase is off.
	 * @throws DataFormatoErradoException
	 *             case the date is wrong.
	 */

	public ArrayList<Dia> buscarTodasDescricoes() throws SQLException,
			DataFormatoErradoException {
		ArrayList<Dia> list;/* Variable that contains all days. */
		list = new ArrayList<Dia>();

		String sql;/* Variable that contains the code of database. */
		sql = "Select * from datas";

		PreparedStatement stmt;/* Variable that create the connection. */
		stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		ResultSet rs;/* Variable that received the connection. */
		rs = stmt.executeQuery();

		list = criarDias(rs);

		ResultSet rs2;/* Variable that received the connection. */
		rs2 = stmt.executeQuery();

		list = popularListaDia(rs2, list);

		rs.close();
		return list;
	}

	/**
	 * This method populates the DB based on a day.
	 * 
	 * @param rs
	 * @param lista
	 * @return list contains all days.
	 * @throws SQLException
	 *             case the dataBase is off.
	 */
	public static ArrayList<Dia> popularListaDia(ResultSet rs,
			ArrayList<Dia> list) throws SQLException {
		int cont;/* Variable to use on while. */
		cont = 0;

		while (rs.next()) {
			String descricao;/* Variable that received the description. */
			descricao = rs.getString("sessao");

			String descAux;/* Temporary variable that received the description. */
			descAux = descricao;
			descricao = descricao.split(" -")[0];

			boolean teste;/* Variable for test the table of database. */
			teste = list.get(cont).getData()
					.equalsIgnoreCase(rs.getString(DATAS));

			if (!teste) {
				cont++;
			}

			SessoesEReunioes passar;/*
									 * Variable that received all features of
									 * the sessions.
									 */
			passar = new SessoesEReunioes();
			passar.setDescricao(descricao);
			passar.setFullDescription(descAux);
			list.get(cont).getListaSessoes().add(passar);
		}

		rs.close();
		return list;
	}

	/**
	 * This method create the Dia in the DB.
	 * 
	 * @param rs
	 * @return list contains the new days.
	 * @throws SQLException
	 *             case the dataBase is off.
	 * @throws DataFormatoErradoException
	 *             case the date is wrong.
	 */

	public static ArrayList<Dia> criarDias(ResultSet rs) throws SQLException,
			DataFormatoErradoException {
		ArrayList<Dia> list;/* Is an array that received the days. */
		list = new ArrayList<Dia>();

		int controle;/* This variable is to control the while. */
		controle = 0;

		while (rs.next()) {
			if (list.size() == 0) {
				Dia dia;/* Variable that contains the day. */
				dia = new Dia();
				dia.setData(rs.getString(DATAS));
				list.add(dia);
			} else {
				boolean teste = list.get(controle).getData()
						.equals(rs.getString(DATAS));
				if (!teste) {
					Dia diaAux;/* Temporary variable that contains the day. */
					diaAux = new Dia();
					diaAux.setData(rs.getString(DATAS));
					list.add(diaAux);
					controle++;
				}
			}
		}

		rs.close();
		return list;
	}

}
