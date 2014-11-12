/**
 * Class: DayControl
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Class to interact with DAO and view and process the data to provide the functionalities of the system. 
 */

package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.xml.utils.WrongParserException;

import br.com.MDSGPP.ChamadaParlamentar.dao.DayDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessionsAndMeetingsDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;

public final class DayControl {

	/**
	 * This method creates a list and returns the days that the deputy was in a
	 * session.
	 * 
	 * @return listInverter what is the new list of days.
	 * @throws ClassNotFoundException
	 *             if the class is not found.
	 * @throws SQLException
	 *             if an error occurs with dataBase.
	 * @throws DateWrongFormatException
	 *             case to come up with wrong date format.
	 */
	public static ArrayList<Day> getDias() throws ClassNotFoundException,
			SQLException, WrongParserException {
		ArrayList<Day> list;// Variable that contains the days.
		DayDao dayDao;/*
					 * Variable that create the connection with dataBase to get
					 * days.
					 */
		dayDao = new DayDao();

		list = dayDao.searchAllDescription();

		ArrayList<Day> listInverter;/*
									 * Variable that contains the list of the
									 * days.
									 */
		listInverter = new ArrayList<Day>();

		listInverter = ReverseList(listInverter, list);

		return listInverter;
	}

	/**
	 * This method creates the inverted list.
	 * 
	 * @param listaInverter
	 *            is an Array that contains the clear list.
	 * @param list
	 *            variable that contains the list.
	 * @return listInverter that is the inverted list.
	 */
	public static ArrayList<Day> ReverseList(ArrayList<Day> listInverter,
			ArrayList<Day> list) {
		listInverter = new ArrayList<Day>();

		int sizeList;// Variable that contains the size of the list.
		sizeList = list.size();

		for (int countList = 0; countList < sizeList; countList++) {
			listInverter.add(list.get(sizeList - 1 - countList));
		}
		return listInverter;
	}

	/**
	 * This method creates a list based on the days and dates per pages and
	 * returns a list.
	 * 
	 * @param page
	 *            is the number of pages.
	 * @param datesPerPage
	 *            is a variable that contains the number of dates for page.
	 * @param day
	 *            is the day that is being analyzed.
	 * @return listaPassar is the arrayList contains all days.
	 */

	public static ArrayList<Day> getListaCerta(int page, int datesPerPage,
			ArrayList<Day> day) {
		ArrayList<Day> listPass;// Variable that contains all days.
		listPass = new ArrayList<Day>();

		for (int countListPage = 0; countListPage < datesPerPage; countListPage++) {
			if (page == 0) {
				listPass.add(day.get(countListPage));
			} else {
				int sizeDay;// Variable that contains the size of the Day.
				sizeDay = day.size();

				if (countListPage + (page * datesPerPage) < sizeDay) {
					listPass.add(day.get(countListPage + (page * datesPerPage)));
				}
			}
		}

		return listPass;
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
	public static Day passDate(String date) throws ClassNotFoundException,
			SQLException, WrongParserException, EmptyListException {

		Day day;// Variable that contains the day.
		day = null;
		day = new SessionsAndMeetingsDao().searchDay(date);
		day.setData(date);

		int sizeListOfSessions;/*
								 * Variable that contains the size of the list
								 * of sessions.
								 */
		sizeListOfSessions = day.getListSessions().size();

		if (sizeListOfSessions == 0) {
			throw new EmptyListException();
		}

		return day;
	}
}
