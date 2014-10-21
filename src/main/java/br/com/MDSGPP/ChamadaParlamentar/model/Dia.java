package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.util.VerificarData;

public class Dia {
	private String date;
	private ArrayList<SessoesEReunioes> listOfSessions = new ArrayList<SessoesEReunioes>();
	private int numberOfSessions;

	/**
	 * This method is to get the date.
	 * @return date.
	 */
	public String getData() {
		return date;
	}
	
	/**
	 * This method is to change the value of the variable data.
	 * @param data is to new value to be added.
	 * @throws DataFormatoErradoException if the date is in formed unknown.
	 */
	public void setData(String date) throws DataFormatoErradoException {
		if( !VerificarData.verificaData(date) ) {
			throw new DataFormatoErradoException();
		}
		
		this.date = date;
	}
	/**
	 * This method is to get the Array listaSessoes containing all sessions at this day.
	 * @return listaSessoes what is an array containing all sessions at this day.
	 */
	public ArrayList<SessoesEReunioes> getListaSessoes() {
		return listOfSessions;
	}
	/**
	 * This method is to change the array listaSessoes.
	 * @param listaSessoes is an array containing all sessions at this day.
	 */
	public void setListaSessoes(ArrayList<SessoesEReunioes> listOfSessions) {
		this.listOfSessions = listOfSessions;
		if( listOfSessions != null ) {
			this.numberOfSessions = listOfSessions.size();
		}
		else {
			this.numberOfSessions = 0;
		}
	}
	/**
	 * This method is to get the number of sessions.
	 * @return numeroSessoes what is a variable containing the number of sessions.
	 */
	public int getNumeroSessoes() {
		return numberOfSessions;
	}
}
