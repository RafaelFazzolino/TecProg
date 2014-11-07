/**
 * Class: SessionAndMeeting
 * 
 * This class is the class that will hold all the attributes of Sessions
 * and meetings.
 */

package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

import org.apache.xml.utils.WrongParserException;

import br.com.MDSGPP.ChamadaParlamentar.util.VerificarData;

public class SessionAndMeetings {

	private String date;
	private String description;
	private String fullDescription;
	private ArrayList<String> presentDeputies = new ArrayList<String>();

	private ArrayList<Deputies> deputies = new ArrayList<Deputies>();

	public SessionAndMeetings(String date, String description,
			ArrayList<Deputies> deputies, String fullDescription)
			throws WrongParserException {

		if (!VerificarData.verificaData(date)) {
			throw new WrongParserException(fullDescription);
		}

		this.date = date;
		this.description = description;
		this.deputies = deputies;
		this.fullDescription = fullDescription;

	}

	public SessionAndMeetings() {

	}

	/*
	 * This method is to get the date.
	 */
	public String getData() {
		return date;
	}

	/*
	 * This method is to change the date.
	 */
	public void setData(String data) throws WrongParserException {
		if (!VerificarData.verificaData(data)) {
			throw new WrongParserException(data);
		}

		this.date = data;
	}

	/*
	 * This method is to get the description.
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * This method is to change the description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * This method is to get the deputies.
	 */
	public ArrayList<Deputies> getDeputies() {
		return deputies;
	}

	/*
	 * This method is to change the deputies.
	 */
	public void setDeputies(ArrayList<Deputies> deputies) {
		this.deputies = deputies;
	}

	/*
	 * This method is to get the description.
	 */
	public String getFullDescription() {
		return fullDescription;
	}

	/*
	 * This method is to change the description.
	 */
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	/*
	 * This method is to get all presents deputies.
	 */
	public ArrayList<String> getDeputiesPresence() {
		return presentDeputies;
	}

	/*
	 * This method is to change the presents deputies.
	 */
	public void setDeputiesPresence(ArrayList<String> presentDeputies) {
		this.presentDeputies = presentDeputies;
	}
}
