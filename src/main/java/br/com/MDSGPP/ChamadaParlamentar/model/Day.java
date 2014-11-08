/**
 * Class: UpdateDataBase
 * 
 * This class is the class that will hold all the attributes of Dia.
 */

package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

import org.apache.xml.utils.WrongParserException;

import br.com.MDSGPP.ChamadaParlamentar.util.DateVerification;

public class Day {
	private String date;
	private ArrayList<SessionAndMeetings> listOfSessions = new ArrayList<SessionAndMeetings>();
	private int numberOfSessions;

	/**
	 * This method is to get the date.
	 * 
	 * @return date.
	 */
	public String getData() {
		return date;
	}

	/**
	 * This method is to change the value of the variable data.
	 * 
	 * @param data
	 *            is to new value to be added.
	 * @throws DataFormatoErradoException
	 *             if the date is in formed unknown.
	 */
	public void setData(String date) throws WrongParserException {
		if (!DateVerification.dateVerification(date)) {
			throw new WrongParserException(date);
		}

		this.date = date;
	}

	/**
	 * This method is to get the Array listaSessoes containing all sessions at
	 * this day.
	 * 
	 * @return listaSessoes what is an array containing all sessions at this
	 *         day.
	 */
	public ArrayList<SessionAndMeetings> getListSessions() {
		return listOfSessions;
	}

	/**
	 * This method is to change the array listaSessoes.
	 * 
	 * @param listaSessoes
	 *            is an array containing all sessions at this day.
	 */
	public void setListSesssions(ArrayList<SessionAndMeetings> listOfSessions) {
		this.listOfSessions = listOfSessions;
		if (listOfSessions != null) {
			this.numberOfSessions = listOfSessions.size();
		} else {
			this.numberOfSessions = 0;
		}
	}

	/**
	 * This method is to get the number of sessions.
	 * 
	 * @return numeroSessoes what is a variable containing the number of
	 *         sessions.
	 */
	public int getNumberSessions() {
		return numberOfSessions;
	}
}
