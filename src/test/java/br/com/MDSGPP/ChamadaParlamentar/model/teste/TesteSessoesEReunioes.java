package br.com.MDSGPP.ChamadaParlamentar.model.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.SessionAndMeetings;

public class TesteSessoesEReunioes {


	SessionAndMeetings sessoes;
	SessionAndMeetings sessoesTeste; 

	@Before
	public void setUp() throws DataFormatoErradoException {
		ArrayList<Deputies> listaTeste = new ArrayList<Deputies>();
		sessoes = new SessionAndMeetings();
		sessoesTeste = new 
				SessionAndMeetings("11/12/2012", "descricaoDeTeste", listaTeste, "descricaoTeste");
	}

	@Test
	public void testSessoesEReunioesStringStringArrayListOfDeputados() 
			throws DataFormatoErradoException {
		ArrayList<Deputies> lista = new ArrayList<Deputies>();
		SessionAndMeetings sessoes2 = new 
				SessionAndMeetings("11/12/2012", "descricaoDeTeste", lista,
						"descricaoTeste");
		

		assertNotNull(sessoes2);
	}
	
	@Test(expected=DataFormatoErradoException.class)
	public void testSessoesEReunioesComException() throws DataFormatoErradoException {
		ArrayList<Deputies> lista = new ArrayList<Deputies>();
		SessionAndMeetings sessoes3 = new 
				SessionAndMeetings("oioi", "não pode passar", lista, "descricaoTeste");
	}

	@Test
	public void testSessoesEReunioes() {
		assertNotNull(sessoes);
	}

	@Test
	public void testGetData() {
		assertTrue(sessoesTeste.getData().equals("11/12/2012"));
	}

	@Test
	public void testSetData() throws DataFormatoErradoException {
		sessoes.setData("11/12/2012");
		assertTrue(sessoes.getData().equals("11/12/2012"));
	}
	
	@Test(expected=DataFormatoErradoException.class)
	public void testSetDataFormatoErrado() throws DataFormatoErradoException {
		sessoes.setData("nao deve passar");
	}

	@Test
	public void testGetDescricao() {
		assertTrue(sessoesTeste.getDescription().equals("descricaoDeTeste"));
	}

	@Test
	public void testSetDescricao() {
		sessoes.setDescription("descricaoDeTeste");
		assertTrue(sessoes.getDescription().equals("descricaoDeTeste"));
	}

	@Test
	public void testGetDeputados() {
		assertNotNull(sessoesTeste.getDeputies());
	}

	@Test
	public void testSetDeputados() {
		Deputies deputado = new Deputies();
		deputado.setNameTreatmentCongressman("teste");
		ArrayList<Deputies> lista = new ArrayList<Deputies>();
		lista.add(deputado);
		sessoes.setDeputies(lista);

		assertTrue(sessoes.getDeputies()
				.get(0).getNameTreatmentCongressman()
				.equals("teste"));
	}
	
	@Test
	public void testGetDescricaoCompleta() {
		assertNotNull(sessoesTeste.getFullDescription());
	}
	
	@Test
	public void testSetDescricaoCompleta() {
		sessoes.setFullDescription("teste");
		
		assertTrue(sessoes.getFullDescription().equalsIgnoreCase("teste"));
	}
	
	@Test
	public void testGetDeputadosPresentes() {
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("teste");
		lista.add("teste2");
		
		sessoes.setDeputiesPresence(lista);
		assertTrue(sessoes.getDeputiesPresence().size() == 2);
		assertNotNull(sessoes.getDeputiesPresence().get(0));
		assertNotNull(sessoes.getDeputiesPresence().get(1));
	}
	
	@Test
	public void testSetDeputadosPresentes() {
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("teste");
		lista.add("teste2");
		sessoes.setDeputiesPresence(null);
		
		assertTrue(sessoes.getDeputiesPresence() == null);
		sessoes.setDeputiesPresence(lista);
		assertTrue(sessoes.getDeputiesPresence().size() == 2);
		assertNotNull(sessoes.getDeputiesPresence().get(0));
		assertNotNull(sessoes.getDeputiesPresence().get(1));
	}
	

}
