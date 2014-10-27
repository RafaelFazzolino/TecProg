package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;

public class TesteDeputados {
	Deputies deputado;
	Deputies deputado2;
	
	@Before
	public void setUp() {
		deputado = new Deputies();
		deputado2 = new Deputies
				(123, 124, 125, "pedro", "pedrin", "masculino", 
						"DF", "PT", "123", "2",
						"123456789", "alguem@algumacoisa");
	}
	
	@Test
	public void testDeputados() {
		assertNotNull(deputado);
	}

	@Test
	public void testDeputadosIntIntIntStringStringStringStringStringStringStringStringString() {
		Deputies deputadoTeste = new Deputies
				(123, 124, 125, "pedro", "pedrin", "masculino", 
						"DF", "PT", "123", "2",
						"123456789", "alguem@algumacoisa");
	
		assertNotNull(deputadoTeste);
		assertTrue(deputadoTeste.getIdDeputy() == 123);
		assertTrue(deputadoTeste.getResgitry() == 124);
		assertTrue(deputadoTeste.getIdRegister()== 125);
		assertTrue(deputadoTeste.getNameCivilCongressman().equals("pedro"));
		assertTrue(deputadoTeste.getNameTreatmentCongressman().equals("pedrin"));
		assertTrue(deputadoTeste.getSex().equals("masculino"));
		assertTrue(deputadoTeste.getFederativeUnit().equals("DF"));
		assertTrue(deputadoTeste.getParty().equals("PT"));
		assertTrue(deputadoTeste.getNumberCabinet().equals("123"));
		assertTrue(deputadoTeste.getAnnexx().equals("2"));
		assertTrue(deputadoTeste.getTelephone().equals("123456789"));
		assertTrue(deputadoTeste.getEmail().equals("alguem@algumacoisa"));
		
	}

	@Test
	public void testGetIdDoParlamentar() {
		assertNotNull(deputado2.getIdDeputy());
	}

	@Test
	public void testSetIdDoParlamentar() {
		deputado.setIdDeputy(123);
		assertNotNull(deputado.getIdDeputy());
	}

	@Test
	public void testGetNomeCivilDoParlamentar() {
		assertNotNull(deputado2.getNameCivilCongressman());
	}

	@Test
	public void testSetNomeCivilDoParlamentar() {
		deputado.setNameCivilCongressman("teste");
		assertNotNull(deputado.getNameCivilCongressman());
	}

	@Test
	public void testGetNomeDeTratamentoDoParlamentar() {
		assertNotNull(deputado2.getNameTreatmentCongressman());
	}

	@Test
	public void testSetNomeDeTratamentoDoParlamentar() {
		deputado.setNameTreatmentCongressman("teste");;
		assertNotNull(deputado.getNameTreatmentCongressman());
	}

	@Test
	public void testGetSexo() {
		assertNotNull(deputado2.getSex());
	}

	@Test
	public void testSetSexo() {
		deputado.setSex("masculino");
		assertNotNull(deputado.getSex());
	}

	@Test
	public void testGetUf() {
		assertNotNull(deputado2.getFederativeUnit());
	}

	@Test
	public void testSetUf() {
		deputado.setFederativeUnit("df");
		assertNotNull(deputado.getFederativeUnit());
	}

	@Test
	public void testGetPartido() {
		assertNotNull(deputado2.getParty());
	}

	@Test
	public void testSetPartido() {
		deputado.setParty("PT");
		assertNotNull(deputado.getParty());
	}

	@Test
	public void testGetNumeroDoGabinete() {
		assertNotNull(deputado2.getNumberCabinet());
	}

	@Test
	public void testSetNumeroDoGabinete() {
		deputado.setNumberCabinet("123456");
		assertNotNull(deputado.getNumberCabinet());
	}

	@Test
	public void testGetAnexo() {
		assertNotNull(deputado2.getAnnexx());
	}

	@Test
	public void testSetAnexo() {
		deputado.setAnnexx("1");
		assertNotNull(deputado.getAnnexx());
	}

	@Test
	public void testGetTelefone() {
		assertNotNull(deputado2.getTelephone());
	}

	@Test
	public void testSetTelefone() {
		deputado.setTelephone("123456");
		assertNotNull(deputado.getTelephone());
	}

	@Test
	public void testGetEmail() {
		assertNotNull(deputado2.getEmail());
	}

	@Test
	public void testSetEmail() {
		deputado.setEmail("teste");;
		assertNotNull(deputado.getEmail());
	}

	@Test
	public void testGetMatricula() {
		assertNotNull(deputado2.getResgitry());
	}

	@Test
	public void testSetMatricula() {
		deputado.setRegsitry(123);
		assertNotNull(deputado.getResgitry());
	}

	@Test
	public void testGetIdeCadastro() {
		assertNotNull(deputado2.getIdRegister());
	}

	@Test
	public void testSetIdeCadastro() {
		deputado.setIdRegister(123);
		assertNotNull(deputado.getIdRegister());
	}

}
