/**
 * Class: Party
 * 
 * This class is the class that will hold all the attributes of the
 *political parties.
 */

package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

public class Party {
	private String acronyn;
	private String nameParty;
	private ArrayList<Deputies> deputiesParty;
	private ArrayList<Statistic> statisticDeputies;
	private ArrayList<Statistic> deputiesWithoutData;

	public String getNameParty() {
		return nameParty;
	}

	public void setNameParty(String nomePartido) {
		this.nameParty = nomePartido;
	}

	public String getAcronyn() {
		return acronyn;
	}

	public void setAcronyn(String sigla) {
		this.acronyn = sigla;
	}

	public ArrayList<Deputies> getDeputiesParty() {
		return deputiesParty;
	}

	public void setDeputiesParty(ArrayList<Deputies> deputadosDoPartido) {
		this.deputiesParty = deputadosDoPartido;
	}

	public ArrayList<Statistic> getStatisticDeputies() {
		return statisticDeputies;
	}

	public void setStatisticDeputies(
			ArrayList<Statistic> estatisticaDosDeputados) {
		this.statisticDeputies = estatisticaDosDeputados;
	}

	public ArrayList<Statistic> getDeputiesWithoutData() {
		return deputiesWithoutData;
	}

	public void setDeputiesWithoutData(ArrayList<Statistic> deputadosSemDados) {
		this.deputiesWithoutData = deputadosSemDados;
	}

}
