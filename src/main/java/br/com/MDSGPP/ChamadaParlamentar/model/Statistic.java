package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

public class Statistic {

	private String name;
	private String numberOfSession;
	private String totalSession;
	private String percentage;
	private ArrayList<String> list = new ArrayList<String>();

	public Statistic() {
	}

	public String getNumberSession() {
		return numberOfSession;
	}

	public void setNumberSession(String numberOfSession) {
		this.numberOfSession = numberOfSession;
	}

	public String getTotalSession() {
		return totalSession;
	}

	public void setTotalSession(String totalSessao) {
		this.totalSession = totalSessao;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPercentagem() {
		return percentage;
	}

	public void setPercentagem(String percentage) {
		this.percentage = percentage;
	}

	public ArrayList<String> getLista() {
		return list;
	}

	public void setLista(ArrayList<String> lista) {
		this.list = lista;
	}
}
