package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.model.StatisticParty;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public class TesteEstatisticaPartido {

	StatisticParty estatisticaPartido;
	
	@Before
	public void setUp()
	{
		estatisticaPartido = new StatisticParty();
		
	}
	
	@Test
	public void testGetPartido() {
		Party partido = new Party();
		estatisticaPartido.setpoliticalParty(partido);
		assertTrue(estatisticaPartido.getpoliticalParty().equals(partido));
	}

	@Test
	public void testSetPartido() {
		Party partido = new Party();
		estatisticaPartido.setpoliticalParty(partido);
		assertTrue(estatisticaPartido.getpoliticalParty().equals(partido));
	}

	@Test
	public void testGetQuantidadeDeSessoes() {
		int testQuantidade = 9;
		estatisticaPartido.setnumberOfSessions(testQuantidade);
		assertTrue(estatisticaPartido.getnumberOfSessions() == testQuantidade);
	}

	@Test
	public void testSetQuantidadeDeSessoes() {
		int testQuantidade = 9;
		estatisticaPartido.setnumberOfSessions(testQuantidade);
		assertTrue(estatisticaPartido.getnumberOfSessions() == testQuantidade);
		
	}

	@Test
	public void testGetSessoesAssistidas() {
		int testQuantidade = 1;
		estatisticaPartido.setassistedSessions(testQuantidade);
		assertTrue(estatisticaPartido.getassistedSessions() == testQuantidade);
	}

	@Test
	public void testSetSessoesAssistidas() {
		estatisticaPartido.setassistedSessions(9);
		assertTrue(estatisticaPartido.getassistedSessions() == 9);
	}

	@Test
	public void testGetPorcentagem() {
		String testString = "teste";
		estatisticaPartido.setpercentage(testString);
		assertTrue(estatisticaPartido.getpercentage().equalsIgnoreCase(testString));
	}

	@Test
	public void testSetPorcentagem() {
		String testString = "teste";
		estatisticaPartido.setpercentage(testString);
		assertTrue(estatisticaPartido.getpercentage().equalsIgnoreCase(testString));
	}

}
