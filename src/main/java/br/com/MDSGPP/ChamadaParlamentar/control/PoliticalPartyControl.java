/**
 * Class: PoliticalPartyControl
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
import br.com.MDSGPP.ChamadaParlamentar.dao.PartyDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;
import br.com.MDSGPP.ChamadaParlamentar.util.CleanList;

public final class PoliticalPartyControl {
	/**
	 * This method is to pass the list contains all parties.
	 * 
	 * @return lista contains all parties.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 */
	public static ArrayList<ArrayList<String>> passarListaPartidos()
			throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> list;/*
										 * ArrayList that contains all political
										 * parties.
										 */
		list = new PartyDao().catchParty();

		return list;
	}

	/**
	 * This method is to check the party.
	 * 
	 * @param partido
	 *            is a String contains the name of the party.
	 * @return listaComDados contains the features of the party.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 */
	public static ArrayList<String> verificaExistencia(String party)
			throws ClassNotFoundException, SQLException {

		ArrayList<ArrayList<String>> listWithDatas;/*
													 * Variable that contains
													 * all data of the parties.
													 */
		listWithDatas = passarListaPartidos();

		int sizeListWithData;// Variable that contains the size of the list.
		sizeListWithData = listWithDatas.size();

		for (int countDate = 0; countDate < sizeListWithData; countDate++) {

			if (listWithDatas.get(countDate).get(0).equalsIgnoreCase(party)
					|| listWithDatas.get(countDate).get(1)
							.equalsIgnoreCase(party)) {

				return listWithDatas.get(countDate);
			}
		}
		return null;
	}

	/**
	 * This method is to pass all parties already with all Members of each
	 * party.
	 * 
	 * @param nomePartido
	 *            is a String contains the name of the party.
	 * @return partido contains all features of the party.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 */

	public static Party passarPartido(String nameParty)
			throws ClassNotFoundException, SQLException {

		Party party;// Variable that contains all features of the party.
		party = new Party();
		party.setDeputiesParty(null);

		ArrayList<String> politicalPartyName;/*
											 * Variable that contains the right
											 * political party.
											 */
		politicalPartyName = verificaExistencia(nameParty);

		ArrayList<Deputies> allDeputies;// Array that contains all deputies.
		allDeputies = new DeputiesDao().getDeputies();

		ArrayList<Deputies> deputiesOfThePoliticalParty;/*
														 * ArrayList that
														 * contains all deputies
														 * of the party.
														 */
		deputiesOfThePoliticalParty = new ArrayList<Deputies>();

		if (politicalPartyName != null) {

			int sizeAllDeputies;/*
								 * Variable that contains the size of the list
								 * with all deputies.
								 */
			sizeAllDeputies = allDeputies.size();

			for (int countDeputies = 0; countDeputies < sizeAllDeputies; countDeputies++) {
				if (politicalPartyName.get(0).equalsIgnoreCase(
						allDeputies.get(countDeputies).getParty())) {
					deputiesOfThePoliticalParty.add(allDeputies
							.get(countDeputies));
				}
			}

			party.setAcronyn(politicalPartyName.get(0));
			party.setNameParty(politicalPartyName.get(1));
			party.setDeputiesParty(deputiesOfThePoliticalParty);
		}

		return party;
	}

	/**
	 * This method is to generate the statistical presence of each party,
	 * analyzing all the Members present in each party.
	 * 
	 * @param nome
	 *            is a String contains the name of the party.
	 * @return partido contains all features of the party.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 * @throws ListaVaziaException
	 *             case an error occurs with the list.
	 */

	public static Party gerarEstatisticaDoPartido(String name)
			throws ClassNotFoundException, SQLException, EmptyListException {
		Party party;
		party = passarPartido(name);

		ArrayList<Statistic> statistics;/*
										 * Variable that contains the
										 * statistics.
										 */
		statistics = new ArrayList<Statistic>();

		int sizeDeputiesOfParty;/*
								 * Variable that contains the size of list with
								 * all deputies of the party.
								 */
		sizeDeputiesOfParty = party.getDeputiesParty().size();

		try {
			for (int countParty = 0; countParty < sizeDeputiesOfParty; countParty++) {
				Statistic statistic;/*
									 * Variable that contains all features of
									 * statistics.
									 */
				statistic = new Statistic();
				try {
					statistic = EstatisticaControl
							.gerarEstatisticas(EstatisticaControl
									.arrumarNomePesquisa(party
											.getDeputiesParty().get(countParty)));
				} catch (EmptyListException e) {
					statistic.setName(EstatisticaControl
							.arrumarNomePesquisa(party.getDeputiesParty().get(
									countParty)));
				}
				statistics.add(statistic);
			}
		} catch (NullPointerException e) {
			throw new EmptyListException();
		}

		party.setStatisticDeputies(statistics);

		return party;
	}

	/**
	 * This method is to pass only the parties with complete data.
	 * 
	 * @param nome
	 *            is a String contains the name of the party.
	 * @return partido contains all features of the party.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with database.
	 * @throws ListaVaziaException
	 *             case an error occurs with the list.
	 */
	public static Party passarPartidoComDadosCompletos(String name)
			throws ClassNotFoundException, SQLException, EmptyListException {

		Party party;/*
					 * Variable that contains all features of the political
					 * party.
					 */
		party = gerarEstatisticaDoPartido(name);

		ArrayList<ArrayList<Statistic>> receivedList;/*
													 * Variable that contains
													 * the received list.
													 */
		receivedList = CleanList.cleanList(party.getStatisticDeputies());

		party.setStatisticDeputies(receivedList.get(0));
		party.setDeputiesWithoutData(receivedList.get(1));

		return party;
	}
}
