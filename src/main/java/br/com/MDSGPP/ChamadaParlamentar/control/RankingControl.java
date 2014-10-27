/**
 * Class: RankingControl
 * 
 * Date: march 26 2014.
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

import br.com.MDSGPP.ChamadaParlamentar.dao.RankingDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaRankingException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;
import br.com.MDSGPP.ChamadaParlamentar.util.LimparLista;

public final class RankingControl {
	private static final int SIZE_RANKINGS = 5;/*
												 * Variable used to store the
												 * value of the number of
												 * members of the ranking.
												 */

	/**
	 * This method is to generate the ranking.
	 * 
	 * @param lista
	 *            {@link ArrayList} of {@link Statistic}, is the list of the
	 *            statistics of all the deputies, having data or not.
	 * @return returns the ranking.
	 * @throws ListaRankingException
	 *             case an error occurs with the list.
	 */

	public static Ranking createRanking(ArrayList<Statistic> list)
			throws ListaRankingException {

		Ranking ranking;/* Variable that contains all features of the ranking. */
		ranking = new Ranking();
		try {
			ArrayList<ArrayList<Statistic>> received;/*
														 * It is an array that
														 * receives all the
														 * statistics for each
														 * deputy.
														 */
			received = LimparLista.limparLista(list);

			ArrayList<Statistic> deleted;/*
											 * It is an array that receives all
											 * Members removed by mistake in
											 * webService.
											 */
			deleted = received.get(1);

			ArrayList<Statistic> bests;/*
										 * It is an array that receives the best
										 * MPs taking into account their
										 * statistical presence.
										 */
			bests = new ArrayList<Statistic>();

			ArrayList<Statistic> worst;/*
										 * It is an array that receives the
										 * worst MPs taking into account their
										 * statistical presence.
										 */
			worst = new ArrayList<Statistic>();

			ArrayList<Statistic> receivedList;/*
												 * It is an array that receives
												 * the list of statistics.
												 */
			receivedList = received.get(0);

			int sizeReceived;/* Variable that contains the size of the received. */
			sizeReceived = receivedList.size();

			if (sizeReceived == 0) {
				throw new ListaRankingException();
			}
			ArrayList<Statistic> orderedList;/*
												 * Um array is to receive all
												 * the sorted list of
												 * statistics.
												 */
			orderedList = ordenation(receivedList);

			for (int i = 0; i < SIZE_RANKINGS; i++) {
				bests.add(list.get(i));
				worst.add(list.get(list.size() - 1 - i));
			}

			ranking.setBetter(bests);
			ranking.setWorst(worst);
			ranking.setList(orderedList);
			ranking.setRemoved(deleted);

		} catch (NullPointerException e) {
			throw new ListaRankingException();
		}

		return ranking;
	}

	/**
	 * This method is to get an {@link ArrayList} of {@link Deputies} and
	 * calculate the statistics to all of then, if there is no data, it marks
	 * it.
	 * 
	 * @param lista
	 *            {@link ArrayList} of {@link Deputies} its the list with all
	 *            the deputies.
	 * @return returns an {@link ArrayList} of {@link Statistic} from de
	 *         calculations.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 * @throws ListaRankingException
	 *             case an error occurs with the list.
	 * @throws ListaVaziaException
	 *             case an error occurs with the list.
	 */

	public static ArrayList<Statistic> createListEstatistic(
			ArrayList<Deputies> lista) throws ClassNotFoundException,
			SQLException, ListaRankingException, ListaVaziaException {

		try {
			ArrayList<Statistic> devolver;/*
											 * Variable that will return the
											 * result of the method.
											 */
			devolver = new ArrayList<Statistic>();

			String name;/* Variable that contains the name of the deputy. */
			name = EstatisticaControl.arrumarNomePesquisa(lista.get(0));

			devolver.add(EstatisticaControl.gerarEstatisticas(name));

			int allSession;/* Variable that contains the number of sessions. */
			allSession = Integer.parseInt(devolver.get(0).getTotalSession());

			int sizeList;/* Variable that contains the size of the list. */
			sizeList = lista.size();

			for (int i = 0; i < sizeList; i++) {
				name = EstatisticaControl.arrumarNomePesquisa(lista.get(i));

				try {
					devolver.add(EstatisticaControl.gerarEstatisticas(name,
							allSession));
				} catch (ListaVaziaException e) {
					Statistic estatistica;/*
											 * Variable that contains all
											 * features of the statistics.
											 */
					estatistica = new Statistic();

					estatistica.setName(name);
					devolver.add(estatistica);
				}
			}
			return devolver;
		} catch (IndexOutOfBoundsException e2) {
			throw new ListaRankingException();
		}
	}

	/**
	 * This method generates the ranking top 5, taking the best and the worst.
	 * 
	 * @return returns an {@link Ranking} with the 5 top and 5 worst.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with database.
	 */

	public static Ranking nextRankingTop5() throws ClassNotFoundException,
			SQLException {
		RankingDao rankingDao;/*
							 * Variable that will connect to the database, the
							 * Ranking table.
							 */
		rankingDao = new RankingDao();

		Ranking ranking;/* Variable that contains all features of the ranking. */
		ranking = rankingDao.returnRanking();

		ArrayList<Statistic> bests;/*
									 * Variable used to store the best deputies,
									 * taking into account their statistical
									 * presence.
									 */
		bests = new ArrayList<Statistic>();

		ArrayList<Statistic> worst;/*
									 * Variable used to store the worst
									 * deputies, taking into account their
									 * statistical presence.
									 */
		worst = new ArrayList<Statistic>();

		ranking.setList(ordenation(ranking.getList()));

		for (int i = 0; i < SIZE_RANKINGS; i++) {
			bests.add(ranking.getList().get(i));
			worst.add(ranking.getList().get(ranking.getList().size() - 1 - i));
		}
		ranking.setBetter(bests);
		ranking.setWorst(worst);
		return ranking;
	}

	/**
	 * This method is suposed to sort the ranking, we are using here
	 * <b>insertion sort</b>.
	 * 
	 * @param lista
	 *            {@link ArrayList} of {@link Statistic}, unsorted list.
	 * @return returns an {@link ArrayList} of {@link Statistic} with the
	 *         sorted list.
	 */

	public static ArrayList<Statistic> ordenation(ArrayList<Statistic> list) {
		/* Insertion Sort. */

		int i = 1, j = 1;

		int sizeList;/* Variable that contains the size of the list. */
		sizeList = list.size();

		if (sizeList > 0) {

			while (j < sizeList) {

				i = j;

				while (i > 0) {

					int first;/*
							 * Variable that stores the fewest for the
							 * ordination.
							 */
					first = Integer.parseInt(list.get(i - 1).getNumberSession());
					int second;/*
								 * Variable that stores the highest number for
								 * the ordination.
								 */
					second = Integer.parseInt(list.get(i).getNumberSession());

					if (first < second) {
						Statistic temp;

						temp = list.get(i - 1);

						list.set(i - 1, list.get(i));
						list.set(i, temp);
						i--;
					} else {
						break;
					}
				}
				j++;
			}
		}
		return list;
	}

	/**
	 * This method generates the full ranking.
	 * 
	 * @return returns a {@link Ranking} object having all the deputies.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 */

	public static Ranking nextRankingComplete() throws SQLException,
			ClassNotFoundException {
		RankingDao rankingDao;/*
							 * Variable that create the connection with the
							 * dataBase.
							 */
		rankingDao = new RankingDao();
		Ranking ranking;/* Variable that contains all features of the ranking. */
		ranking = rankingDao.returnRanking();

		ranking.setList(ordenation(ranking.getList()));

		return ranking;
	}
}
