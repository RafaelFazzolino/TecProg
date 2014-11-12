/**
 * Class: DeputyControl
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Class to interact with DAO and view and process the data to provide the functionalities of the system. 
 */

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

		DeputiesDao deputy;// Variable that contains the deputies.
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
		Deputies deputy;// Variable that contains the deputies.
		deputy = null;

		DeputiesDao deputyDao;/*
							 * Variable that create connection with dataBase to
							 * get deputies.
							 */
		deputyDao = new DeputiesDao();
		ArrayList<Deputies> list;// Variable that contains all deputies.
		list = deputyDao.getDeputies();

		int sizeList;// Variable that contains the size of the list.
		sizeList = list.size();

		for (int countAux = 0; countAux < sizeList; countAux++) {

			if ((list.get(countAux).getNameCivilCongressman()
					.equalsIgnoreCase(nome))
					|| list.get(countAux).getNameTreatmentCongressman()
							.equalsIgnoreCase(nome)) {

				deputy = list.get(countAux);
			}
		}

		return deputy;
	}
}
