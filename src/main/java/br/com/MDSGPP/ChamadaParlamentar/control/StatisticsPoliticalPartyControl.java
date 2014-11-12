/**
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Class to interact with DAO and view and process the data to provide the functionalities of the system. 
 */

package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.StatisticParty;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public final class StatisticsPoliticalPartyControl {

	/**
	 * This method is to generate the statistics of one political party.
	 * 
	 * @param nome
	 *            String, name of the political party.
	 * @return statistical what is the statistical of the political party.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 * @throws ListaVaziaException
	 *             is case an error occurs with the list.
	 */

	public static StatisticParty gerarEstatisticaPartido(String name)
			throws ClassNotFoundException, SQLException, EmptyListException {
		StatisticParty statistical;/*
									 * Variable that contains the statistical of
									 * the political party.
									 */
		statistical = new StatisticParty();

		Party party;// Variable that contains the political party.
		party = PoliticalPartyControl.passarPartidoComDadosCompletos(name);
		statistical.setpoliticalParty(party);

		int numberOfSessions = 0;/*
								 * Variable that contains the number of
								 * sessions.
								 */
		int numberOfSessionsAttended = 0;/*
										 * Variable that contains the number of
										 * attended sessions.
										 */

		int sizeStatisticalParty;/*
								 * Variable that contains the size of
								 * statistical party.
								 */
		sizeStatisticalParty = party.getStatisticDeputies().size();

		for (int countStatisticalParty = 0; countStatisticalParty < sizeStatisticalParty; countStatisticalParty++) {

			numberOfSessions = numberOfSessions
					+ Integer.parseInt(party.getStatisticDeputies()
							.get(countStatisticalParty).getTotalSession());
			numberOfSessionsAttended = numberOfSessionsAttended
					+ Integer.parseInt(party.getStatisticDeputies()
							.get(countStatisticalParty).getNumberSession());
		}

		double percentage;/*
						 * Variable that contains the percentage of attended
						 * sessions.
						 */
		percentage = (((double) (numberOfSessionsAttended)) / ((double) numberOfSessions)) * 100.0;

		String passPercentage;// Variable that contains the final percentage.
		passPercentage = formatarNumeroDouble(percentage);

		statistical.setpercentage(passPercentage);
		statistical.setnumberOfSessions(numberOfSessions);
		statistical.setassistedSessions(numberOfSessionsAttended);

		return statistical;
	}

	/**
	 * this method is only to format a number on the pattern 0.00.
	 * 
	 * @param numero
	 *            Double, number to be formated.
	 * @return formatardoubleReturn that is a string with the formated number.
	 */

	public static String formatarNumeroDouble(double number) {
		NumberFormat formatardouble;/*
									 * Variable of contains the double format of
									 * the number.
									 */
		formatardouble = new DecimalFormat("0.00");

		String formatardoubleReturn;/*
									 * Variable that contains the return of
									 * double format.
									 */

		formatardoubleReturn = formatardouble.format(number);

		return formatardoubleReturn;
	}
}
