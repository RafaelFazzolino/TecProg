package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;

public class TesteRanking {
	Ranking ranking1 = new Ranking();
	Ranking ranking2 = new Ranking();
	ArrayList<Statistic> lista;

	@Before
	public void setUp() throws Exception {
		lista = new ArrayList<Statistic>();
		ranking1.setList(lista);
		ranking1.setBetter(lista);
		ranking1.setWorst(lista);
		ranking1.setRemoved(lista);
	}

	@Test
	public void testRanking() {
		assertNotNull(ranking1);
		assertNotNull(ranking2);
	}

	@Test
	public void testGetLista() {
		assertNotNull(ranking1.getList());
	}

	@Test
	public void testSetLista() {
		ranking2.setList(lista);
		assertNotNull(ranking2.getList());
	}

	@Test
	public void testGetRemovidos() {
		assertNotNull(ranking1.getRemoved());
	}

	@Test
	public void testSetRemovidos() {
		ranking2.setRemoved(lista);
		assertNotNull(ranking2.getRemoved());
	}

	@Test
	public void testGetMelhores() {
		assertNotNull(ranking1.getBetter());
	}

	@Test
	public void testSetMelhores() {
		ranking2.setBetter(lista);
		assertNotNull(ranking2.getBetter());
	}

	@Test
	public void testGetPiores() {
		assertNotNull(ranking1.getWorst());
	}

	@Test
	public void testSetPiores() {
		ranking2.setWorst(lista);
		assertNotNull(ranking2.getWorst());
	}

}
