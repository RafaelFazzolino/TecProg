package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.control.EstatisticaControl;
import br.com.MDSGPP.ChamadaParlamentar.control.RankingControl;
import br.com.MDSGPP.ChamadaParlamentar.dao.DeputiesDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaRankingException;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public class TesteRankingControl {
	RankingControl rankingControl;

	@Before
	public void setUp() {
		rankingControl = new RankingControl();
	}

	@Test
	public void testRankingControl() {
		assertNotNull(rankingControl);
	}

	@Test
	public void testGerarRanking() throws ClassNotFoundException, SQLException,
			ListaRankingException, EmptyListException {
		Ranking ranking = RankingControl.createRanking(RankingControl
				.createListEstatistic(new DeputiesDao().getDeputies()));

		assertNotNull(ranking.getList());
		assertNotNull(ranking.getBetter());
		assertNotNull(ranking.getWorst());
		assertNotNull(ranking.getRemoved());
	}

	@Test(expected = ListaRankingException.class)
	public void testGerarRankingListaRankingException()
			throws ListaRankingException {
		ArrayList<Statistic> teste = new ArrayList<Statistic>();
		Ranking ranking2 = RankingControl.createRanking(teste);
	}

	@Test(expected = ListaRankingException.class)
	public void testGerarRankingListaComParametroNull()
			throws ListaRankingException {

		Ranking ranking3 = RankingControl.createRanking(null);
	}

	@Test
	public void testGerarListaEstatistica() throws ClassNotFoundException,
			SQLException, ListaRankingException, EmptyListException {
		ArrayList<Statistic> lista1 = RankingControl
				.createListEstatistic(new DeputiesDao().getDeputies());
		assertNotNull(lista1);
	}

	@Test(expected = ListaRankingException.class)
	public void testGerarListaEstatisticaException()
			throws ClassNotFoundException, SQLException, ListaRankingException,
			EmptyListException {
		ArrayList<Deputies> listaParaParametro = new ArrayList<Deputies>();
		ArrayList<Statistic> lista2 = RankingControl
				.createListEstatistic(listaParaParametro);
	}

	@Test
	public void testOrdenacao() {
		ArrayList<Statistic> lista = new ArrayList<Statistic>();

		Statistic primeiro = new Statistic();
		Statistic segundo = new Statistic();
		Statistic terceiro = new Statistic();
		primeiro.setNumberSession("10");
		segundo.setNumberSession("20");
		terceiro.setNumberSession("15");

		lista.add(primeiro);
		lista.add(segundo);
		lista.add(terceiro);
		lista = RankingControl.ordenation(lista);

		for (int i = 0; i < lista.size() - 1; i++) {
			assertTrue(Integer.parseInt(lista.get(i).getNumberSession()) > Integer
					.parseInt(lista.get(i + 1).getNumberSession()));
		}
	}

	@Test
	public void testMergeFirstFull() {
		ArrayList<EstatisticaControl> firstList = new ArrayList<EstatisticaControl>();
		ArrayList<EstatisticaControl> secondList = new ArrayList<EstatisticaControl>();

		for (int i = 10; i > 0; i--) {
			EstatisticaControl estatistica = new EstatisticaControl();
			estatistica.setNumberSession(Integer.toString(i));
			firstList.add(estatistica);
		}

		ArrayList<EstatisticaControl> listToTest = RankingControl.merge(
				firstList, secondList);

		assertNotNull(listToTest);
	}

	@Test
	public void testMergeSecondFull() {
		ArrayList<EstatisticaControl> firstList = new ArrayList<EstatisticaControl>();
		ArrayList<EstatisticaControl> secondList = new ArrayList<EstatisticaControl>();

		for (int i = 10; i > 0; i--) {
			EstatisticaControl estatistica = new EstatisticaControl();
			estatistica.setNumberSession(Integer.toString(i));
			secondList.add(estatistica);
		}

		ArrayList<EstatisticaControl> listToTest = RankingControl.merge(
				firstList, secondList);

		assertNotNull(listToTest);
	}

	@Test
	public void testMergeBothFull() {
		ArrayList<EstatisticaControl> firstList = new ArrayList<EstatisticaControl>();
		ArrayList<EstatisticaControl> secondList = new ArrayList<EstatisticaControl>();

		for (int i = 10; i > 0; i--) {
			EstatisticaControl estatistica = new EstatisticaControl();
			estatistica.setNumberSession(Integer.toString(i));
			firstList.add(estatistica);
		}

		for (int i = 20; i < 30; i++) {
			EstatisticaControl estatistica = new EstatisticaControl();
			estatistica.setNumberSession(Integer.toString(i));
			secondList.add(estatistica);
		}

		ArrayList<EstatisticaControl> listToTest = RankingControl.merge(
				firstList, secondList);

		for (int i = 1; i < listToTest.size(); i++) {
			int first = Integer.parseInt(listToTest.get(i - 1)
					.getNumberSession());
			int second = Integer.parseInt(listToTest.get(i).getNumberSession());
			System.out.println(first);
		}
	}

	@Test
	public void testOrdenacaoListaNull() {
		ArrayList<Statistic> lista = new ArrayList<Statistic>();
		lista = RankingControl.ordenation(lista);
		assertTrue(lista.size() == 0);
	}

}
