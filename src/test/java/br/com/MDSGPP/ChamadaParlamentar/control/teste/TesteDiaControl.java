package br.com.MDSGPP.ChamadaParlamentar.control.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.xml.utils.WrongParserException;
import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.control.DayControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;

public class TesteDiaControl {
	DayControl control;

	@Before
	public void setUp() {
		control = new DayControl();
	}

	public ArrayList<Day> criarListaDia() {
		ArrayList<Day> lista = new ArrayList<Day>();

		for (int i = 0; i < 50; i++) {
			Day dia = new Day();
			lista.add(dia);
		}

		return lista;
	}

	@Test
	public void testDiaControl() {
		assertNotNull(control);
	}

	@Test
	public void testGetDias() throws ClassNotFoundException, SQLException,
			WrongParserException {
		ArrayList<Day> dias = DayControl.getDias();

		assertNotNull(dias);
		assertTrue(dias.size() != 0);
	}

	@Test
	public void testReverseList() throws ClassNotFoundException, SQLException,
			WrongParserException {
		ArrayList<Day> listaInverter = new ArrayList<Day>();
		assertNotNull(listaInverter);
	}

	@Test
	public void testGetListaCerta() throws ClassNotFoundException, SQLException {
		ArrayList<Day> dias = criarListaDia();

		int pagina = 1;
		int pagina2 = 2;
		int datasPorPagina = 5;
		int paginaFinal = (int) (dias.size() / datasPorPagina);

		ArrayList<Day> diasTeste = DayControl.getListaCerta(pagina - 1,
				datasPorPagina, dias);
		ArrayList<Day> diasTeste2 = DayControl.getListaCerta(pagina2 - 1,
				datasPorPagina, dias);
		ArrayList<Day> diasTesteFinal = DayControl.getListaCerta(
				paginaFinal - 1, datasPorPagina, dias);
		ArrayList<Day> diasTesteFinalReal = DayControl.getListaCerta(
				paginaFinal, datasPorPagina, dias);
		assertNotNull(diasTeste);

		assertTrue(diasTeste.size() == datasPorPagina);
		assertTrue(diasTeste2.size() == datasPorPagina);
		assertNotNull(diasTesteFinal.size());
		assertNotNull(diasTesteFinalReal);

		for (int i = 0; i < diasTeste.size(); i++) {
			assertTrue(diasTeste.get(i).equals(
					dias.get(i + ((pagina - 1) * datasPorPagina))));
			assertTrue(diasTeste2.get(i).equals(
					dias.get(i + ((pagina2 - 1) * datasPorPagina))));
			if (i < diasTesteFinal.size()) {
				assertTrue(diasTesteFinal.get(i).equals(
						dias.get(i + ((paginaFinal - 1) * datasPorPagina))));
			}
		}

	}

	@Test(expected = ListaVaziaException.class)
	public void testPassarDataListaVazia() throws ClassNotFoundException,
			SQLException, WrongParserException, ListaVaziaException {
		Day dia1 = DayControl.passarData("20/11/2011");
	}

	@Test
	public void testePassarDataNormal() throws ClassNotFoundException,
			SQLException, WrongParserException, ListaVaziaException {
		Day dia2 = DayControl.passarData("22/11/2011");
		assertTrue(dia2.getListSessions().size() > 0);
	}

	@Test(expected = WrongParserException.class)
	public void testePassarDataFormatoErrado() throws ClassNotFoundException,
			SQLException, WrongParserException, ListaVaziaException {
		Day dia3 = DayControl.passarData("nao deve passar"); // esse espera uma
																// data null
	}
}
