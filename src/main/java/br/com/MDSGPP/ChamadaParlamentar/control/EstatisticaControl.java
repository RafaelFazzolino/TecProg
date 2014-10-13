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

import br.com.MDSGPP.ChamadaParlamentar.dao.EstatisticaDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessoesEReunioesDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;
import br.com.MDSGPP.ChamadaParlamentar.model.Estatistica;

public final class EstatisticaControl {

	public static final double PASSAR_PORCENTAGEM = 100.0;

    	/**
	 * This method is to generate statistics based on the name of the deputy.
	 * 
	 * @param nome
	 *            String, its the name of deputy.
	 * @return returns the {@link Estatistica} of the {@link Deputados}
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 * @throws ListaVaziaException is case an error occurs with the list.
	 */
    
	public static Estatistica gerarEstatisticas(String nome)
			throws ClassNotFoundException, SQLException, ListaVaziaException {
		Estatistica estatistica = new Estatistica();
		EstatisticaDao dao = new EstatisticaDao();	
		SessoesEReunioesDao sessoes = new SessoesEReunioesDao();
		int numeroTotalSessao;
		numeroTotalSessao = sessoes.passarNumeroDeSessoes();

		estatistica.setLista(dao.getEstatisticaDeputados(nome));

		estatistica.setNome(nome);

		estatistica = calcularEstatistica(estatistica, sessoes, numeroTotalSessao);
		estatistica.setTotalSessao(Integer.toString(numeroTotalSessao));


		return estatistica;
	}

/**
	 * This method generates the statistics based on the name and the total
	 * number of sessions.
	 * 
	 * @param nome
	 *            String, name of the {@link Deputados}.
	 * @param numeroTotalSessao
	 *            Integer, total number of sessions.
	 * @return returns {@link Estatistica}.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 * @throws ListaVaziaException is case an error occurs with the list.
	 */

	public static Estatistica gerarEstatisticas(String nome, int numeroTotalSessao) 
			throws ClassNotFoundException, SQLException, ListaVaziaException {

		Estatistica estatistica = new Estatistica();
		EstatisticaDao dao = new EstatisticaDao();	
		SessoesEReunioesDao sessoes = new SessoesEReunioesDao();

		estatistica.setLista(dao.getEstatisticaDeputados(nome));

		estatistica.setNome(nome);

		estatistica = calcularEstatistica(estatistica, sessoes, numeroTotalSessao);

		estatistica.setTotalSessao(Integer.toString(numeroTotalSessao));


		return estatistica;

	}

	/**
	 * This method actualy calculate the {@link Estatistica}.
	 * 
	 * @param estatistica
	 *            {@link Estatistica}, its the {@link Estatistica} but without
	 *            the numbers, have only data of the number of sessions.
	 * @param sessoes
	 *            {@link SessoesEReunioesDao}, its the connection to the data
	 *            base.
	 * @param numeroTotalSessao
	 *            Integer, its the total number of sessions.
	 * @return returns an {@link Estatistica} but now with actual numbers.
	 * @throws ListaVaziaException
	 */


	public static Estatistica calcularEstatistica
	(Estatistica estatistica, SessoesEReunioesDao sessoes, 
			int numeroTotalSessao) throws ListaVaziaException {
		
		int sizeList;
		sizeList = estatistica.getLista().size();
		
		if(sizeList == 0){
			throw new ListaVaziaException();
		}
		estatistica.setNumeroSessao(Integer.toString(sizeList));

		DecimalFormat df = new DecimalFormat("###.00");  
		estatistica.setPorcentagem(df.format(
				(((double)sizeList)/
						((double)numeroTotalSessao))*PASSAR_PORCENTAGEM) + "%");


		return estatistica;
	}

/**
	 * This method puts the name of the deputy on the right format for search on
	 * the database.
	 * 
	 * @param deputado
	 *            {@link Deputados}, its the deputy object.
	 * @return returns a String with the name based on the data base pattern.
	 */

	public static String arrumarNomePesquisa(Deputados deputado) {
		String montar;
		montar = deputado.getNomeDeTratamentoDoParlamentar() +
				"-" + deputado.getPartido() + "/" + deputado.getUf();
		String montarFinal;
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

	public static ArrayList<String> passarListaCerta(int pagina, int sessoesPorPagina, ArrayList<String> listaPassada ) {
		ArrayList<String> listaPassar = new ArrayList<String>();
		ArrayList<String> lista = ordenarLista(listaPassada);
		
		int sizeList;
		sizeList = lista.size();
		
		if(sizeList == 0) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0 ; i < sessoesPorPagina ; i++) {
			if(pagina == 0) {
				listaPassar.add(lista.get(i));
			}
			else {
				if(i+(pagina*sessoesPorPagina) < sizeList) {
					listaPassar.add(lista.get(i+ (pagina*sessoesPorPagina)));
				}
			}
		}		
		return listaPassar;
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

		int sizeList;
		sizeList = lista.size();
		
		for(int i = 0 ; i < sizeList ; i++) {
			ordenada.add(lista.get(sizeList -1 - i));
		}

		return ordenada;
	}
}