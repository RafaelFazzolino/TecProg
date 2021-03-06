package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.control.PoliticalPartyControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public class TestePartidoControl {

	@Test
	public void testPassarListaPartidos() throws ClassNotFoundException,
			SQLException {
		assertNotNull(PoliticalPartyControl.passarListaPartidos());
	}

	@Test
	public void testVerificaExistenciaRetornaNulo()
			throws ClassNotFoundException, SQLException {
		assertTrue(PoliticalPartyControl.verificaExistencia("ronaldo") == null);
	}

	@Test
	public void testVerificaExistenciaSigla() throws ClassNotFoundException,
			SQLException {
		ArrayList<ArrayList<String>> listaComDados = PoliticalPartyControl
				.passarListaPartidos();
		String sigla = "PT";

		for (int i = 0; i < listaComDados.size(); i++) {
			if (listaComDados.get(i).get(1).equalsIgnoreCase(sigla)) {
				assertTrue(PoliticalPartyControl.verificaExistencia(sigla) == listaComDados
						.get(i));
			}
		}
	}

	@Test
	public void testVerificaExistenciaNomePartido()
			throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> listaComDados = PoliticalPartyControl
				.passarListaPartidos();
		String partido = "Partido dos Trabalhadores";

		for (int i = 0; i < listaComDados.size(); i++) {
			if (listaComDados.get(i).get(0).equalsIgnoreCase(partido)) {
				assertTrue(PoliticalPartyControl.verificaExistencia(partido) == listaComDados
						.get(i));
			}
		}
	}

	@Test
	public void testPassarPartidoCerto() throws ClassNotFoundException,
			SQLException {
		PoliticalPartyControl control = new PoliticalPartyControl();
		String nomePartido = "PT";

		ArrayList<String> nomePartidoCerto = PoliticalPartyControl
				.verificaExistencia(nomePartido);

		assertNotNull(PoliticalPartyControl.passarPartido(nomePartido));
	}

	@Test
	public void testPassarPartidoIncorreto() throws ClassNotFoundException,
			SQLException {
		String nomePartido = "erro";

		assertNotNull(PoliticalPartyControl.passarPartido(nomePartido));
	}

	@Test
	public void testGerarEstatisticasdoPartidoCerto()
			throws ClassNotFoundException, SQLException, EmptyListException {
		String nomePartido = "pt";
		assertNotNull(PoliticalPartyControl
				.gerarEstatisticaDoPartido(nomePartido));
	}

	@Test(expected = EmptyListException.class)
	public void testGerarEstatisticasdoPartidoCertoListaVazia()
			throws ClassNotFoundException, SQLException, EmptyListException {
		String nomePartido = "NaoEPraPassar";
		PoliticalPartyControl.gerarEstatisticaDoPartido(nomePartido);
	}

	@Test
	public void testPassarPartidoComDadosCompletos()
			throws ClassNotFoundException, SQLException, EmptyListException {
		String nomePartido1 = "pt";
		String nomePartido2 = "PT";
		String nomePartido3 = "partido dos trabalhadores";

		Party partido1 = PoliticalPartyControl
				.passarPartidoComDadosCompletos(nomePartido1);
		Party partido2 = PoliticalPartyControl
				.passarPartidoComDadosCompletos(nomePartido2);
		Party partido3 = PoliticalPartyControl
				.passarPartidoComDadosCompletos(nomePartido3);

		assertNotNull(partido1);
		assertTrue(partido1.getDeputiesParty().size() > 0);

		assertNotNull(partido2);
		assertTrue(partido2.getDeputiesParty().size() > 0);

		assertTrue(partido1.getAcronyn()
				.equalsIgnoreCase(partido2.getAcronyn()));

		assertNotNull(partido3);

		assertTrue(partido1.getAcronyn()
				.equalsIgnoreCase(partido3.getAcronyn()));
	}

	@Test(expected = EmptyListException.class)
	public void testPassarPartidoComDadosCompletosListaVazia()
			throws ClassNotFoundException, SQLException, EmptyListException {
		Party partido1 = PoliticalPartyControl
				.passarPartidoComDadosCompletos("nao pode passar");
	}
}