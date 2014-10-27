package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.control.RankingControl;
import br.com.MDSGPP.ChamadaParlamentar.dao.DeputadoDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaRankingException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
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
			ListaRankingException, ListaVaziaException {
		Ranking ranking = RankingControl.gerarRanking(RankingControl
				.gerarListaEstatistica(new DeputadoDao().getDeputados()));

		assertNotNull(ranking.getList());
		assertNotNull(ranking.getBetter());
		assertNotNull(ranking.getWorst());
		assertNotNull(ranking.getRemoved());
	}

	@Test(expected = ListaRankingException.class)
	public void testGerarRankingListaRankingException()
			throws ListaRankingException {
		ArrayList<Statistic> teste = new ArrayList<Statistic>();
		Ranking ranking2 = RankingControl.gerarRanking(teste);
	}

	@Test(expected = ListaRankingException.class)
	public void testGerarRankingListaComParametroNull()
			throws ListaRankingException {

		Ranking ranking3 = RankingControl.gerarRanking(null);
	}

	@Test
	public void testGerarListaEstatistica() throws ClassNotFoundException,
			SQLException, ListaRankingException, ListaVaziaException {
		ArrayList<Statistic> lista1 = RankingControl
				.gerarListaEstatistica(new DeputadoDao().getDeputados());
		assertNotNull(lista1);
	}

	@Test(expected = ListaRankingException.class)
	public void testGerarListaEstatisticaException()
			throws ClassNotFoundException, SQLException, ListaRankingException,
			ListaVaziaException {
		ArrayList<Deputies> listaParaParametro = new ArrayList<Deputies>();
		ArrayList<Statistic> lista2 = RankingControl
				.gerarListaEstatistica(listaParaParametro);
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
		lista = RankingControl.ordenacao(lista);

		for (int i = 0; i < lista.size() - 1; i++) {
			assertTrue(Integer.parseInt(lista.get(i).getNumberSession()) > Integer
					.parseInt(lista.get(i + 1).getNumberSession()));
		}
	}

	@Test
	public void testOrdenacaoListaNull() {
		ArrayList<Statistic> lista = new ArrayList<Statistic>();
		lista = RankingControl.ordenacao(lista);
		assertTrue(lista.size() == 0);
	}

}
