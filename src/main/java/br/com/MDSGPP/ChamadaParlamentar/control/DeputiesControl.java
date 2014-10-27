package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.dao.DeputiesDao;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;

public final class DeputiesControl {
	/**
	 * This method is to get deputies.
	 * 
	 * @return deputado.getNomesDeputados what is the name of all deputies.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<String> getDeputados()
			throws ClassNotFoundException, SQLException {

		DeputiesDao deputy;/* Variable that contains the deputies. */
		deputy = new DeputiesDao();

		ArrayList<String> nameDeputy; /*
									 * Variable that will contains the name of
									 * all deputies.
									 */
		nameDeputy = deputy.getNamesDeputies();

		return nameDeputy;
	}

	/**
	 * This method is to check the deputy.
	 * 
	 * @param nome
	 *            is a variable what contains the name of deputy.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static Deputies verificaExistencia(String nome)
			throws ClassNotFoundException, SQLException {
		Deputies deputy;/* Variable that contains the deputies. */
		deputy = null;

		DeputiesDao deputadoDao;/*
								 * Variable that create connection with dataBase
								 * to get deputies.
								 */
		deputadoDao = new DeputiesDao();
		ArrayList<Deputies> list;/* Variable that contains all deputies. */
		list = deputadoDao.getDeputies();

		int sizeList;/* Variable that contains the size of the list. */
		sizeList = list.size();

		for (int countingAux = 0; countingAux < sizeList; countingAux++) {

			if ((list.get(countingAux).getNameCivilCongressman()
					.equalsIgnoreCase(nome))
					|| list.get(countingAux).getNameTreatmentCongressman()
							.equalsIgnoreCase(nome)) {

				deputy = list.get(countingAux);
			}
		}

		return deputy;
	}
}
