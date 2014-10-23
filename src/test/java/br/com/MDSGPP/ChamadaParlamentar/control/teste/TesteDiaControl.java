package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.control.DayControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Dia;

public class TesteDiaControl {
	DayControl control;
	
	
	@Before
	public void setUp() {
		control = new DayControl();
	}
	
	public ArrayList<Dia> criarListaDia() {
		ArrayList<Dia> lista = new ArrayList<Dia>();
		
		for(int i = 0; i<50; i++) {
			Dia dia = new Dia();
			lista.add(dia);
		}
				
		return lista;
	}
	
	@Test
	public void testDiaControl() {
		assertNotNull(control);
	}
	
	@Test
	public void testGetDias() 
			throws ClassNotFoundException, SQLException,
			DataFormatoErradoException {
		ArrayList<Dia> dias = DayControl.getDias();
		
		assertNotNull(dias);
		assertTrue(dias.size() != 0);
	}
	
	@Test
	public void testReverseList() 
			throws ClassNotFoundException, SQLException,
			DataFormatoErradoException {
		ArrayList<Dia> listaInverter = new ArrayList<Dia>();
		assertNotNull(listaInverter);
	}

	@Test
	public void testGetListaCerta() throws ClassNotFoundException, SQLException {
		ArrayList<Dia> dias = criarListaDia();
		
		int pagina = 1;
		int pagina2 = 2;
		int datasPorPagina = 5;
		int paginaFinal =(int) (dias.size()/datasPorPagina);
		
		ArrayList<Dia> diasTeste = DayControl.getListaCerta(pagina-1, datasPorPagina, dias);
		ArrayList<Dia> diasTeste2 = DayControl.getListaCerta(pagina2-1, datasPorPagina, dias);
		ArrayList<Dia> diasTesteFinal = DayControl.getListaCerta(paginaFinal-1, datasPorPagina, dias);
		ArrayList<Dia> diasTesteFinalReal = DayControl.getListaCerta(paginaFinal, datasPorPagina, dias);
		assertNotNull(diasTeste);
		
		assertTrue(diasTeste.size() == datasPorPagina);
		assertTrue(diasTeste2.size() == datasPorPagina);
		assertNotNull(diasTesteFinal.size());
		assertNotNull(diasTesteFinalReal);
		
		for(int i =0; i<diasTeste.size(); i++) {
			assertTrue(diasTeste.get(i).equals(dias.get(i+((pagina-1)*datasPorPagina))));
			assertTrue(diasTeste2.get(i).equals(dias.get(i+((pagina2-1)*datasPorPagina))));
			if(i < diasTesteFinal.size()) {
				assertTrue(diasTesteFinal.get(i).equals(dias.get(i+((paginaFinal-1)*datasPorPagina))));
			}
		}
	
	}
	
	@Test(expected=ListaVaziaException.class)
	public void testPassarDataListaVazia() throws ClassNotFoundException, 
	SQLException, DataFormatoErradoException, ListaVaziaException{
		Dia dia1 = DayControl.passarData("20/11/2011");
	}
	
	@Test
	public void testePassarDataNormal() 
			throws ClassNotFoundException, SQLException, 
			DataFormatoErradoException, ListaVaziaException {
		Dia dia2 = DayControl.passarData("22/11/2011");
		assertTrue(dia2.getListaSessoes().size() > 0);
	}
	
	@Test(expected=DataFormatoErradoException.class)
	public void testePassarDataFormatoErrado() 
			throws ClassNotFoundException, SQLException,
			DataFormatoErradoException, ListaVaziaException {
		Dia dia3 = DayControl.passarData("nao deve passar"); //esse espera uma data null
	}
}
