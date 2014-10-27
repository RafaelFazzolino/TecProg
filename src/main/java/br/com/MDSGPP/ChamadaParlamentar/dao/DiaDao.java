package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;
import br.com.MDSGPP.ChamadaParlamentar.model.SessionAndMeetings;

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

	public ArrayList<Day> buscarTodasDescricoes() throws SQLException,
			DataFormatoErradoException {
		ArrayList<Day> list;/* Variable that contains all days. */
		list = new ArrayList<Day>();

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
	public static ArrayList<Day> popularListaDia(ResultSet rs,
			ArrayList<Day> list) throws SQLException {
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

			SessionAndMeetings passar;/*
									 * Variable that received all features of
									 * the sessions.
									 */
			passar = new SessionAndMeetings();
			passar.setDescription(descricao);
			passar.setFullDescription(descAux);
			list.get(cont).getListSessions().add(passar);
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

	public static ArrayList<Day> criarDias(ResultSet rs) throws SQLException,
			DataFormatoErradoException {
		ArrayList<Day> list;/* Is an array that received the days. */
		list = new ArrayList<Day>();

		int controle;/* This variable is to control the while. */
		controle = 0;

		while (rs.next()) {
			if (list.size() == 0) {
				Day dia;/* Variable that contains the day. */
				dia = new Day();
				dia.setData(rs.getString(DATAS));
				list.add(dia);
			} else {
				boolean teste = list.get(controle).getData()
						.equals(rs.getString(DATAS));
				if (!teste) {
					Day diaAux;/* Temporary variable that contains the day. */
					diaAux = new Day();
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
