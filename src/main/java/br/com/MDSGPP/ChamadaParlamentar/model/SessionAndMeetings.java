package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.util.VerificarData;

public class SessionAndMeetings {

	private String data;
	private String description;
	private String fullDescription;
	private ArrayList<String> presentDeputies = new ArrayList<String>();

	private ArrayList<Deputies> deputies = new ArrayList<Deputies>();

	public SessionAndMeetings(String data, String description,
			ArrayList<Deputies> deputies, String fullDescription)
			throws DataFormatoErradoException {

		if (!VerificarData.verificaData(data)) {
			throw new DataFormatoErradoException();
		}

		this.data = data;
		this.description = description;
		this.deputies = deputies;
		this.fullDescription = fullDescription;

	}

	public SessionAndMeetings() {

	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws DataFormatoErradoException {
		if (!VerificarData.verificaData(data)) {
			throw new DataFormatoErradoException();
		}

		this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Deputies> getDeputies() {
		return deputies;
	}

	public void setDeputies(ArrayList<Deputies> deputies) {
		this.deputies = deputies;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public ArrayList<String> getDeputiesPresence() {
		return presentDeputies;
	}

	public void setDeputiesPresence(ArrayList<String> presentDeputies) {
		this.presentDeputies = presentDeputies;
	}
}
