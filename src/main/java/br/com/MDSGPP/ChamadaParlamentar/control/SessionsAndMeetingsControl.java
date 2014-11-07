/**
 * Class: SessionsAndMeetingsControl
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

import br.com.MDSGPP.ChamadaParlamentar.dao.SessionsAndMeetingsDao;
import br.com.MDSGPP.ChamadaParlamentar.model.SessionAndMeetings;

public final class SessionsAndMeetingsControl {

	/**
	 * Method that bridges the gap between the Dao and Model about the sessions.
	 * 
	 * @param descricao
	 *            is the parameter that used for search the session.
	 * @return pass, is the object of the model SessoesEReunioes.
	 * @throws ClassNotFoundException
	 *             if the database is off.
	 * @throws SQLException
	 *             if miss spelled SQL is entered.
	 */

	public static SessionAndMeetings nextSession(String description)
			throws ClassNotFoundException, SQLException {
		SessionsAndMeetingsDao dao;/*
									 * Variable that create the connection with
									 * dataBase.
									 */
		dao = new SessionsAndMeetingsDao();

		ArrayList<String> list;/* Variable that contains the list of sessions. */
		list = dao.searchSession(description);

		SessionAndMeetings pass;/* Variable that contains the final list. */
		pass = new SessionAndMeetings();
		pass.setDeputiesPresence(list);
		pass.setDescription(description);

		return pass;
	}

	/**
	 * Method that organizes how many deputies will be displayed per page.
	 * 
	 * @param pagina
	 * @param deputadosPorPagina
	 * @param deputadosPresentes
	 * @return passList with all the deputies that are in the page.
	 */

	public static ArrayList<String> organizeListDeputy(int page,
			int deputiesForPage, ArrayList<String> presentDeputies) {
		ArrayList<String> passList;/* Variable that contains the final list. */
		passList = new ArrayList<String>();

		for (int i = 0; i < deputiesForPage; i++) {
			if (page == 0) {
				passList.add(presentDeputies.get(i));
			} else {

				int numberOfPresentsDeputy;/*
											 * Variable that contains the number
											 * of present deputies.
											 */
				numberOfPresentsDeputy = presentDeputies.size();

				if (i + (page * deputiesForPage) < numberOfPresentsDeputy) {
					passList.add(presentDeputies.get(i
							+ (page * deputiesForPage)));
				}
			}
		}

		return passList;
	}
}
