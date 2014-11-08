package br.com.MDSGPP.ChamadaParlamentar.util.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.util.DateVerification;

public class TesteVerificarData {

	@Test
	public void testVerificarData() {
		assertNotNull(new DateVerification());
	}

	@Test
	public void testVerificaData() {
		boolean resposta = DateVerification.dateVerification("12/12/2012");
		boolean resposta2 = DateVerification
				.dateVerification("nao deve passar");

		assertTrue(resposta);
		assertTrue(resposta2 == false);
	}

}
