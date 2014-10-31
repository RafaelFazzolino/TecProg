package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.control.PoliticalPartyControl;
import br.com.MDSGPP.ChamadaParlamentar.control.StatisticsPoliticalPartyControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.StatisticParty;

public class TesteEstatisticaPartidoControl {
	PoliticalPartyControl estatistica;

	@Before
	public void setUp() {
		estatistica = new PoliticalPartyControl();
	}

	@Test
	public void testEstatisticaPartidoControl() {
		assertNotNull(estatistica);
	}

	@Test
	public void testGerarEstatisticaPartido() throws ClassNotFoundException,
			SQLException, EmptyListException {
		String nomePassar = "PT";

		StatisticParty estatistica = StatisticsPoliticalPartyControl
				.gerarEstatisticaPartido(nomePassar);

		assertNotNull(estatistica);
	}

	@Test(expected = EmptyListException.class)
	public void testGerarEstatisticaPartidoListaVazia()
			throws ClassNotFoundException, SQLException, EmptyListException {
		String nomePassar = "nao deve passar";
		StatisticParty estatistica = StatisticsPoliticalPartyControl
				.gerarEstatisticaPartido(nomePassar);
	}

	@Test
	public void testFormatarNumeroDouble() {
		double numero = 12.1335413;
		double numero2 = 12.148090;

		String numeroPassado = StatisticsPoliticalPartyControl
				.formatarNumeroDouble(numero);
		String numeroPassado2 = StatisticsPoliticalPartyControl
				.formatarNumeroDouble(numero2);

		assertTrue(numeroPassado.equalsIgnoreCase("12.13")
				|| numeroPassado.equalsIgnoreCase("12,13"));

		assertTrue(numeroPassado2.equalsIgnoreCase("12.15")
				|| numeroPassado2.equalsIgnoreCase("12,15"));
	}

}
