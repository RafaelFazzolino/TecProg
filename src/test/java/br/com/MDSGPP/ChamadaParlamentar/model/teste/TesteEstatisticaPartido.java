package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.model.EstatisticaPartido;
import br.com.MDSGPP.ChamadaParlamentar.model.Partidos;

public class TesteEstatisticaPartido {

	EstatisticaPartido estatisticaPartido;
	
	@Before
	public void setUp()
	{
		estatisticaPartido = new EstatisticaPartido();
		
	}
	
	@Test
	public void testGetPartido() {
		Partidos partido = new Partidos();
		estatisticaPartido.setpoliticalParty(partido);
		assertTrue(estatisticaPartido.getpoliticalParty().equals(partido));
	}

	@Test
	public void testSetPartido() {
		Partidos partido = new Partidos();
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
