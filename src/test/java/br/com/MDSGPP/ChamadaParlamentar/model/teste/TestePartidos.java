package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public class TestePartidos {

	Party partido;
	
	@Before
	public void setUp() {
		ArrayList<Deputies> lista = new ArrayList<Deputies>();
		partido = new Party();
		partido.setNameParty("teste");
		partido.setAcronyn("df");
		partido.setDeputiesParty(lista);
		
		ArrayList<Statistic> array = new ArrayList<Statistic>();
		partido.setStatisticDeputies(array);
		partido.setDeputiesWithoutData(array);
		
		
	}
	@Test
	public void testGetNomePartido() {
		assertTrue(partido.getNameParty().equalsIgnoreCase("teste"));
	}

	@Test
	public void testSetNomePartido() {
		partido.setNameParty("testeSet");
		assertTrue(partido.getNameParty().equalsIgnoreCase("testeSet"));
	}

	@Test
	public void testGetSigla() {
		assertTrue(partido.getAcronyn().equalsIgnoreCase("df"));
	}

	@Test
	public void testSetSigla() {
		partido.setAcronyn("OF");
		assertTrue(partido.getAcronyn().equalsIgnoreCase("OF"));
	}

	@Test
	public void testGetDeputadosDoPartido() {
		assertTrue(partido.getDeputiesParty().size() == 0);
		
	}

	@Test
	public void testSetDeputadosDoPartido() {
		Deputies deputado = new Deputies();
		 partido.getDeputiesParty().add(deputado);
		 
		 assertTrue(partido.getDeputiesParty().size() == 1);
	}

	@Test
	public void testSetEstatisticaDosDeputados() {
		Statistic estatistica = new Statistic();
		partido.getStatisticDeputies().add(estatistica);
		assertTrue(partido.getStatisticDeputies().size() == 1);
	}

	@Test
	public void testsetDeputadosSemDados() {
		Statistic estatistica = new Statistic();
		partido.getDeputiesWithoutData().add(estatistica);
		 assertTrue(partido.getDeputiesWithoutData().size() == 1);
	}
	
}
