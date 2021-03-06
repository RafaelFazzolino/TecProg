package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.xml.utils.WrongParserException;
import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.model.Day;
import br.com.MDSGPP.ChamadaParlamentar.model.SessionAndMeetings;

public class TesteDia {
	Day dia;
	Day dia2;

	@Before
	public void setUp() throws WrongParserException {
		dia = new Day();
		dia.setData("10/10/2010");
		ArrayList<SessionAndMeetings> lista = new ArrayList<SessionAndMeetings>();
		SessionAndMeetings sessao = new SessionAndMeetings();
		lista.add(sessao);
		dia.setListSesssions(lista);

		dia2 = new Day();
	}

	@Test
	public void testGetData() {
		assertNotNull(dia.getData());
		assertTrue(dia.getData().equalsIgnoreCase("10/10/2010"));
	}

	@Test
	public void testSetData() throws WrongParserException {
		dia2.setData("10/10/2010");
		assertNotNull(dia2.getData());
	}

	@Test(expected = WrongParserException.class)
	public void testSetDataFormatoErrado() throws WrongParserException {
		dia2.setData("naoVaiPassar");
	}

	@Test
	public void testGetListaSessoes() {
		assertNotNull(dia.getListSessions());
	}

	@Test
	public void testSetListaSessoes() {
		dia2.setListSesssions(null);
		assertTrue(dia2.getListSessions() == null);

		dia2.setListSesssions(dia.getListSessions());

		assertNotNull(dia2.getListSessions());
	}

	@Test
	public void testGetNumeroSessoes() {
		assertTrue(dia2.getListSessions().size() == dia2.getNumberSessions());
	}
}
