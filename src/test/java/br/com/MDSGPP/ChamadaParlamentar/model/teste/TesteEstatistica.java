package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;

import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public class TesteEstatistica {
	Statistic test = new Statistic();

	@Before
	public void setUp() throws Exception {
		test.setName("Rafael");
		test.setNumberSession("123");
		test.setTotalSession("100");
		test.setPercentagem("98");
		ArrayList<String> lista = new ArrayList<String>();
		for (int i = 0; i < 30; i++)
			lista.add("" + i);
		test.setLista(lista);

	}

	@Test
	public void testEstatistica() {// Testando instância de estatistica
		assertNotNull(test);
	}

	@Test
	public void testGetNumeroSessao() {
		assertNotNull(test.getNumberSession());
	}

	@Test
	public void testSetNumeroSessao() {
		assertTrue(test.getNumberSession().equals("123"));
	}

	@Test
	public void testGetTotalSessao() {
		assertNotNull(test.getTotalSession());
	}

	@Test
	public void testSetTotalSessao() {
		assertTrue(test.getTotalSession().equals("100"));
	}

	@Test
	public void testGetNome() {
		assertNotNull(test.getName());
	}

	@Test
	public void testSetNome() {
		assertTrue(test.getName().equals("Rafael"));
	}

	@Test
	public void testGetPorcentagem() {
		assertNotNull(test.getPercentagem());
	}

	@Test
	public void testSetPorcentagem() {
		assertTrue(test.getPercentagem().equals("98"));
	}

	@Test
	public void testGetLista() {
		assertNotNull(test.getLista());
	}

	@Test
	public void testSetLista() {
		for (int i = 0; i < 30; i++)
			assertTrue(test.getLista().get(i).equals("" + i));
	}
}
