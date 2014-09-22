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
import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;
import br.com.MDSGPP.ChamadaParlamentar.model.Estatistica;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;
import br.com.MDSGPP.ChamadaParlamentar.util.LimparLista;

public final class RankingControl {
	private static final int SIZE_RANKINGS = 5;
	
	/**	
	 * This method is to generate the ranking.
	 * 
	 * @param lista
	 *            {@link ArrayList} of {@link Estatistica}, is the list of the
	 *            statistics of all the deputies, having data or not.
	 * @return returns the ranking.
	 * @throws ListaRankingException case an error occurs with the list.
	 */

	public static Ranking gerarRanking(ArrayList<Estatistica> lista) 
			throws ListaRankingException {

		Ranking ranking = new Ranking();
		try {
			ArrayList<ArrayList<Estatistica>> received = 
					LimparLista.limparLista(lista);
			ArrayList<Estatistica> deleted = received.get(1);
			ArrayList<Estatistica> bests = new ArrayList<Estatistica>();
			ArrayList<Estatistica> worst = new ArrayList<Estatistica>();

			ArrayList<Estatistica> listaRecebida = received.get(0);

			if(receivedList.size() == 0) {
				throw new ListaRankingException();
			}
			ArrayList<Estatistica> orderedList = ordenacao(listaRecebida);

			for(int i = 0; i < SIZE_RANKINGS; i++) {
				bests.add(list.get(i));
				worst.add(list.get(list.size() -1 -i));
			}

			ranking.setMelhores(bests);
			ranking.setPiores(worsts);
			ranking.setLista(orderedList);
			ranking.setRemovidos(deleted);

		} catch (NullPointerException e) {
			throw new ListaRankingException();
		}

		return ranking;
	}

	/**
	 * This method is to get an {@link ArrayList} of {@link Deputados} and
	 * calculate the statistics to all of then, if there is no data, it marks
	 * it.
	 * 
	 * @param lista
	 *            {@link ArrayList} of {@link Deputados} its the list with all
	 *            the deputies.
	 * @return returns an {@link ArrayList} of {@link Estatistica} from de
	 *         calculations.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 * @throws ListaRankingException case an error occurs with the list.
	 * @throws ListaVaziaException case an error occurs with the list.
	 */
	
	public static ArrayList<Estatistica> gerarListaEstatistica(ArrayList<Deputados> lista)
			throws ClassNotFoundException, SQLException, 
			ListaRankingException, ListaVaziaException {

		try {
			ArrayList<Estatistica> devolver = new ArrayList<Estatistica>();
			String name = EstatisticaControl.arrumarNomePesquisa(lista.get(0));

			devolver.add(EstatisticaControl.gerarEstatisticas(nome));


			int allSession = Integer.parseInt(devolver.get(0).getTotalSessao());

			for(int i = 0; i < lista.size(); i++) {
				name = EstatisticaControl.arrumarNomePesquisa(lista.get(i));

				try {
					devolver.add(EstatisticaControl.gerarEstatisticas(nome, 
							totalSessao));
				} catch (ListaVaziaException e) {
					Estatistica estatistica = new Estatistica();
					estatistica.setNome(nome);
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
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with database.
	 */
	
	
	public static Ranking passarRankingTop5() throws ClassNotFoundException, SQLException {
		RankingDao rankingDao = new RankingDao();
		Ranking ranking = rankingDao.retornaRanking();
		ArrayList<Estatistica> bests = new ArrayList<Estatistica>();
		ArrayList<Estatistica> worst = new ArrayList<Estatistica>();
		
		ranking.setLista(ordenacao(ranking.getLista()));
		
		for(int i = 0; i < 5; i++) {
			bests.add(ranking.getLista().get(i));
			worst.add(ranking.getLista().get(ranking.getLista().size() -1 -i));
		}
		ranking.setMelhores(bests);
		ranking.setPiores(worst);
		return ranking;
	}

	/**
	 * This method is suposed to sort the ranking, we are using here
	 * <b>insertion sort</b>.
	 * 
	 * @param lista
	 *            {@link ArrayList} of {@link Estatistica}, unsorted list.
	 * @return returns an {@link ArrayList} of {@link Estatistica} with the
	 *         sorted list.
	 */
	
	public static ArrayList<Estatistica> ordenacao(ArrayList<Estatistica> list) {
		/*Insertion Sort.*/
		
		int i = 1, j = 1;
		if(list.size() > 0)
		{
			while( j < lista.size() )
			{
				i=j;
				while( i > 0 )
				{
					int first = Integer.parseInt(lista.get(i-1).getNumeroSessao());
					int second = Integer.parseInt(lista.get(i).getNumeroSessao());

					if ( first < second ) {
						Estatistica temp;

						temp = list.get(i-1);

						list.set(i-1, list.get(i));
						list.set(i, temp);
						i--;
					}
					else
					{
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
	 * @throws SQLException case an error occurs with dataBase.
	 * @throws ClassNotFoundException case the class is not found.
	 */
	
	public static Ranking passarRankingCompleto() 
			throws SQLException, ClassNotFoundException {
		RankingDao rankingDao = new RankingDao();
		Ranking ranking = rankingDao.retornaRanking();
		
		ranking.setLista(ordenacao(ranking.getLista()));
		
		return ranking;
	}
}
