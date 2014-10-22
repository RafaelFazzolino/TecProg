package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

public class Estatistica {

	private String name;
	private String numberOfSession;
	private String totalSessao;
	private String percentage;
	private ArrayList<String> lista = new ArrayList<String>();

	public Estatistica() {
	}

	public String getNumeroSessao() {
		return numberOfSession;
	}

	public void setNumeroSessao(String numberOfSession) {
		this.numberOfSession = numberOfSession;
	}

	public String getTotalSessao() {
		return totalSessao;
	}

	public void setTotalSessao(String totalSessao) {
		this.totalSessao = totalSessao;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public String getPorcentagem() {
		return percentage;
	}

	public void setPorcentagem(String percentage) {
		this.percentage = percentage;
	}

	public ArrayList<String> getLista() {
		return lista;
	}

	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}
}
