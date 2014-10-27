package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.dao.DayDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessoesEReunioesDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;


public final class DayControl {

	/**
	 * This method creates a list and returns the days that the deputy was in a
	 * session.
	 * 
	 * @return listaInverter what is the new list of days.
	 * @throws ClassNotFoundException
	 *             if the class is not found.
	 * @throws SQLException
	 *             if an error occurs with dataBase.
	 * @throws DataFormatoErradoException
	 *             case to come up with wrong date format.
	 */
	public static ArrayList<Day> getDias() throws ClassNotFoundException,
			SQLException, DataFormatoErradoException {
		ArrayList<Day> list;/* Variable that contains the days. */
		DayDao diaDao;/*
					 * Variable that create the connection with dataBase to get
					 * days.
					 */
		diaDao = new DayDao();

		list = diaDao.searchAllDescription();

		ArrayList<Day> listaInverter;/*
									 * Variable that contains the list of the
									 * days.
									 */
		listaInverter = new ArrayList<Day>();

		listaInverter = ReverseList(listaInverter, list);

		return listaInverter;
	}

	/**
	 * This method creates the inverted list.
	 * 
	 * @param listaInverter
	 *            is an Array that contains the clear list.
	 * @param list
	 *            variable that contains the list.
	 * @return listaInverter that is the inverted list.
	 */
	public static ArrayList<Day> ReverseList(ArrayList<Day> listaInverter,
			ArrayList<Day> list) {
		listaInverter = new ArrayList<Day>();

		int sizeList;/* Variable that contains the size of the list. */
		sizeList = list.size();

		for (int i = 0; i < sizeList; i++) {
			listaInverter.add(list.get(sizeList - 1 - i));
		}
		return listaInverter;
	}

	/**
	 * This method creates a list based on the days and dates per pages and
	 * returns a list.
	 * 
	 * @param pagina
	 *            is the number of pages.
	 * @param datasPorPagina
	 *            is a variable that contains the number of dates for page.
	 * @param dia
	 *            is the day that is being analyzed.
	 * @return listaPassar is the arrayList contains all days.
	 */

	public static ArrayList<Day> getListaCerta(int pagina, int datasPorPagina,
			ArrayList<Day> dia) {
		ArrayList<Day> listaPassar;/* Variable that contains all days. */
		listaPassar = new ArrayList<Day>();

		for (int i = 0; i < datasPorPagina; i++) {
			if (pagina == 0) {
				listaPassar.add(dia.get(i));
			} else {
				int sizeDay;/* Variable that contains the size of the Day. */
				sizeDay = dia.size();

				if (i + (pagina * datasPorPagina) < sizeDay) {
					listaPassar.add(dia.get(i + (pagina * datasPorPagina)));
				}
			}
		}

		return listaPassar;
	}

	/**
	 * This method is to pass the date.
	 * 
	 * @param data
	 *            is a variable what contains the date.
	 * @return dia what is a variable what contains all features of the date.
	 * @throws ClassNotFoundException
	 *             is case occur an error.
	 * @throws SQLException
	 *             is case occurs an error with dataBase.
	 * @throws DataFormatoErradoException
	 *             is case occurs an error with the date.
	 * @throws ListaVaziaException
	 *             is case the list came empty.
	 */
	public static Day passarData(String data) throws ClassNotFoundException,
			SQLException, DataFormatoErradoException, ListaVaziaException {

		Day dia;/* Variable that contains the day. */
		dia = null;
		dia = new SessoesEReunioesDao().procuraDia(data);
		dia.setData(data);

		int sizeListOfSessions;/*
								 * Variable that contains the size of the list
								 * of sessions.
								 */
		sizeListOfSessions = dia.getListSessions().size();

		if (sizeListOfSessions == 0) {
			throw new ListaVaziaException();
		}

		return dia;
	}
}
