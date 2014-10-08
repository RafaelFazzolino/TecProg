package br.com.MDSGPP.ChamadaParlamentar.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.util.VerificarData;

public class SessoesEReunioes {

	private String data;
	private String description;
	private String fullDescription;
	private ArrayList<String> presentDeputies = new ArrayList<String>();

	private ArrayList<Deputados> deputies = new ArrayList<Deputados>();

	public SessoesEReunioes(String data, 
			String description, ArrayList<Deputados> deputies,
			String fullDescription) throws DataFormatoErradoException {

		if( !VerificarData.verificaData(data) ) {
			throw new DataFormatoErradoException();
		}


		this.data = data;
		this.description = description;
		this.deputies = deputies;
		this.fullDescription = fullDescription;

	}

	public SessoesEReunioes() {

	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws DataFormatoErradoException {
		if(!VerificarData.verificaData(data)) {
			throw new DataFormatoErradoException();
		}

		this.data = data;
	}

	public String getDescricao() {
		return description;
	}

	public void setDescricao(String description) {
		this.description = description;
	}

	public ArrayList<Deputados> getDeputados() {
		return deputies;
	}

	public void setDeputados(ArrayList<Deputados> deputies) {
		this.deputies = deputies;
	}

	public String getDescricaoCompleta() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public ArrayList<String> getDeputadosPresentes() {
		return presentDeputies;
	}

	public void setDeputadosPresentes(ArrayList<String> presentDeputies) {
		this.presentDeputies = presentDeputies;
	}
}
