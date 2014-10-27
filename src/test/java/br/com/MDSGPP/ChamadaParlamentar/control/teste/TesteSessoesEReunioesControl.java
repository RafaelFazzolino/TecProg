package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.control.SessionsAndMeetingsControl;
import br.com.MDSGPP.ChamadaParlamentar.model.SessionAndMeetings;

public class TesteSessoesEReunioesControl {
	SessionsAndMeetingsControl sessoes;

	@Before
	public void setUp() {
		sessoes = new SessionsAndMeetingsControl();
	}

	@Test
	public void testSessoesEReunioesControl() {
		assertNotNull(sessoes);
	}

	@Test
	public void testPassarSessao() throws ClassNotFoundException, SQLException {
		SessionAndMeetings teste = SessionsAndMeetingsControl
				.nextSession("teste");
		assertNotNull(teste.getDeputiesPresence());
		assertTrue(teste.getDeputiesPresence().size() == 0);

		SessionAndMeetings teste2 = SessionsAndMeetingsControl
				.nextSession("ORDINÁRIA Nº 313 - 20/11/2012");
		assertNotNull(teste2.getDeputiesPresence());
		assertTrue(teste2.getDeputiesPresence().size() > 0);
	}

	@Test
	public void testArrumarListaDeputados() {
		ArrayList<String> listaParaTeste = new ArrayList<String>();

		for (int i = 0; i < 50; i++) {
			listaParaTeste.add("numero " + i);
		}

		ArrayList<String> listaRecebida1 = SessionsAndMeetingsControl
				.organizeListDeputy(0, 10, listaParaTeste);
		ArrayList<String> listaRecebida2 = SessionsAndMeetingsControl
				.organizeListDeputy(5, 9, listaParaTeste);

		assertTrue(listaRecebida1.size() <= 10);
		assertTrue(listaRecebida2.size() > 0);
		assertTrue(listaRecebida2.size() < 10);

	}

}
