package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import br.com.MDSGPP.ChamadaParlamentar.control.DeputiesControl;

public class TesteDeputadosControl {
	DeputiesControl deputies;

	@Before
	public void setUp() {
		deputies = new DeputiesControl();
	}

	@Test
	/* Test that verify the object deputies. */
	public void testDeputadosControl() {
		assertNotNull(deputies);
	}

	@Test
	public void testGetDeputados() throws ClassNotFoundException, SQLException {
		assertTrue(DeputiesControl.getDeputados().size() != 0);
	}

	@Test
	/* Test that verify the existence of deputy. */
	public void testVerificaExistencia() throws ClassNotFoundException,
			SQLException {
		String nome1 = "naoVaiPassar";
		String nome2 = "tiririca";
		String nome3 = "JOSÉ AUGUSTO MAIA";

		assertTrue(DeputiesControl.verificaExistencia(nome1) == null);
		assertNotNull(DeputiesControl.verificaExistencia(nome2));
		assertNotNull(DeputiesControl.verificaExistencia(nome3));

	}
}
