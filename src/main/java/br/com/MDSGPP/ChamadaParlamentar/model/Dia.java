package br.com.MDSGPP.ChamadaParlamentar.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.util.VerificarData;

public class Dia {
	private String data;
	private ArrayList<SessoesEReunioes> listaSessoes = new ArrayList<SessoesEReunioes>();
	private int numeroSessoes;

	/**
	 * This method is to get the date.
	 * @return date.
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * this method is to change the value of the variable data.
	 * @param data is to new value to be added.
	 * @throws DataFormatoErradoException if the date is in formed unknown.
	 */
	public void setData(String data) throws DataFormatoErradoException {
		if(!VerificarData.verificaData(data)) {
			throw new DataFormatoErradoException();
		}
		
		this.data = data;
	}
	/**
	 * This method is to get the Array listaSessoes containing all sessions at this day.
	 * @return listaSessoes what is an array containing all sessions at this day.
	 */
	public ArrayList<SessoesEReunioes> getListaSessoes() {
		return listaSessoes;
	}
	/**
	 * This method is to change the array listaSessoes.
	 * @param listaSessoes is an array containing all sessions at this day.
	 */
	public void setListaSessoes(ArrayList<SessoesEReunioes> listaSessoes) {
		this.listaSessoes = listaSessoes;
		if(listaSessoes != null) {
			this.numeroSessoes = listaSessoes.size();
		}
		else {
			this.numeroSessoes = 0;
		}
	}
	/**
	 * this method is to get the number of sessions.
	 * @return numeroSessoes what is a variable containing the number of sessions.
	 */
	public int getNumeroSessoes() {
		return numeroSessoes;
	}
}
