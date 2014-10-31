/**
 * Class: StatisticsControl
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
import java.text.DecimalFormat;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.dao.StatisticDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessionsAndMeetingsDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public final class EstatisticaControl {

	public static final double PASS_PERCENTAGE = 100.0;

	/**
	 * This method is to generate statistics based on the name of the deputy.
	 * 
	 * @param nome
	 *            String, its the name of deputy.
	 * @return returns the {@link Statistic} of the {@link Deputies}
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 * @throws ListaVaziaException
	 *             is case an error occurs with the list.
	 */

	public static Statistic gerarEstatisticas(String nome)
			throws ClassNotFoundException, SQLException, EmptyListException {

		Statistic estatistica;/* Variable that contains the statistic. */
		estatistica = new Statistic();

		StatisticDao dao;/* Variable that create the connection with dataBase. */
		dao = new StatisticDao();

		SessionsAndMeetingsDao sessions;/*
										 * Variable that create the connection
										 * with dataBase.
										 */
		sessions = new SessionsAndMeetingsDao();

		int numeroTotalSessao;/*
							 * Variable that contains the number of all
							 * sessions.
							 */
		numeroTotalSessao = sessions.nextNumberSession();

		estatistica.setLista(dao.getStatisticDeputies(nome));

		estatistica.setName(nome);

		estatistica = calcularEstatistica(estatistica, sessions,
				numeroTotalSessao);
		estatistica.setTotalSession(Integer.toString(numeroTotalSessao));

		return estatistica;
	}

	/**
	 * This method generates the statistics based on the name and the total
	 * number of sessions.
	 * 
	 * @param nome
	 *            String, name of the {@link Deputies}.
	 * @param numeroTotalSessao
	 *            Integer, total number of sessions.
	 * @return returns {@link Statistic}.
	 * @throws ClassNotFoundException
	 *             case the class is not found.
	 * @throws SQLException
	 *             case an error occurs with dataBase.
	 * @throws ListaVaziaException
	 *             is case an error occurs with the list.
	 */

	public static Statistic gerarEstatisticas(String nome, int numeroTotalSessao)
			throws ClassNotFoundException, SQLException, EmptyListException {

		Statistic estatistica;/* Variable that contains the statistic. */
		estatistica = new Statistic();

		StatisticDao dao;/* Variable that create the connection with dataBase. */
		dao = new StatisticDao();

		SessionsAndMeetingsDao sessions;/*
										 * Variable that create the connection
										 * with dataBase.
										 */
		sessions = new SessionsAndMeetingsDao();

		estatistica.setLista(dao.getStatisticDeputies(nome));

		estatistica.setName(nome);

		estatistica = calcularEstatistica(estatistica, sessions,
				numeroTotalSessao);

		estatistica.setTotalSession(Integer.toString(numeroTotalSessao));

		return estatistica;

	}

	/**
	 * This method actualy calculate the {@link Statistic}.
	 * 
	 * @param estatistica
	 *            {@link Statistic}, its the {@link Statistic} but without the
	 *            numbers, have only data of the number of sessions.
	 * @param sessoes
	 *            {@link SessionsAndMeetingsDao}, its the connection to the data
	 *            base.
	 * @param numeroTotalSessao
	 *            Integer, its the total number of sessions.
	 * @return returns an {@link Statistic} but now with actual numbers.
	 * @throws ListaVaziaException
	 */

	public static Statistic calcularEstatistica(Statistic estatistica,
			SessionsAndMeetingsDao sessoes, int numeroTotalSessao)
			throws EmptyListException {

		int sizeList;/* Variable that contains the size of List. */
		sizeList = estatistica.getLista().size();

		if (sizeList == 0) {
			throw new EmptyListException();
		}
		estatistica.setNumberSession(Integer.toString(sizeList));

		DecimalFormat df;/*
						 * Variable that contains the decimal format of the
						 * number.
						 */
		df = new DecimalFormat("###.00");

		estatistica.setPercentagem(df
				.format((((double) sizeList) / ((double) numeroTotalSessao))
						* PASS_PERCENTAGE)
				+ "%");

		return estatistica;
	}

	/**
	 * This method puts the name of the deputy on the right format for search on
	 * the database.
	 * 
	 * @param deputado
	 *            {@link Deputies}, its the deputy object.
	 * @return returns a String with the name based on the data base pattern.
	 */

	public static String arrumarNomePesquisa(Deputies deputado) {

		String montar;/* Variable that contains the new name. */
		montar = deputado.getNameTreatmentCongressman() + "-"
				+ deputado.getParty() + "/" + deputado.getFederativeUnit();
		String montarFinal;/* Variable that contains the final name. */
		montarFinal = montar.toUpperCase();

		return montarFinal;
	}

	/**
	 * This method is to control the number of sessions that is going to be
	 * shown at the screen.
	 * 
	 * @param pagina
	 *            Integer, its the number of the actual page.
	 * @param sessoesPorPagina
	 *            Integer, its the total number of sessions for page.
	 * @param listaPassada
	 *            {@link ArrayList} of {@link String} containing all the
	 *            sessions.
	 * @return returns an {@link ArrayList} of {@link String} containing the
	 *         sessions that must be on the page.
	 */

	public static ArrayList<String> passarListaCerta(int pagina,
			int sessoesPorPagina, ArrayList<String> listaPassada) {
		ArrayList<String> listPass;/*
									 * Variable that contains the final list of
									 * statistics.
									 */
		listPass = new ArrayList<String>();
		ArrayList<String> list;/*
								 * Variable that contains the list of
								 * statistics.
								 */
		list = ordenarLista(listaPassada);

		int sizeList;/* Variable that contains the size of list. */
		sizeList = list.size();

		if (sizeList == 0) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < sessoesPorPagina; i++) {
			if (pagina == 0) {
				listPass.add(list.get(i));
			} else {
				if (i + (pagina * sessoesPorPagina) < sizeList) {
					listPass.add(list.get(i + (pagina * sessoesPorPagina)));
				}
			}
		}
		return listPass;
	}

	/**
	 * This method put the list on the order from most recent to older.
	 * 
	 * @param lista
	 *            {@link ArrayList} of {@link String} its the list containing
	 *            all the sessions, but it must be sorted.
	 * @return {@link ArrayList} of {@link String} contains the list on the
	 *         correct order.
	 */

	public static ArrayList<String> ordenarLista(ArrayList<String> lista) {
		ArrayList<String> ordenada = new ArrayList<String>();

		int sizeList;/* Variable that contains the size of list. */
		sizeList = lista.size();

		for (int i = 0; i < sizeList; i++) {
			ordenada.add(lista.get(sizeList - 1 - i));
		}

		return ordenada;
	}
}