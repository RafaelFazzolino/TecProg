package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

public class Partidos {
	private String sigla;
	private String nomePartido;
	private ArrayList<Deputies> deputadosDoPartido;
	private ArrayList<Statistic> estatisticaDosDeputados;
	private ArrayList<Statistic> deputadosSemDados;

	public String getNomePartido() {
		return nomePartido;
	}

	public void setNomePartido(String nomePartido) {
		this.nomePartido = nomePartido;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public ArrayList<Deputies> getDeputadosDoPartido() {
		return deputadosDoPartido;
	}

	public void setDeputadosDoPartido(ArrayList<Deputies> deputadosDoPartido) {
		this.deputadosDoPartido = deputadosDoPartido;
	}

	public ArrayList<Statistic> getEstatisticaDosDeputados() {
		return estatisticaDosDeputados;
	}

	public void setEstatisticaDosDeputados(
			ArrayList<Statistic> estatisticaDosDeputados) {
		this.estatisticaDosDeputados = estatisticaDosDeputados;
	}

	public ArrayList<Statistic> getDeputadosSemDados() {
		return deputadosSemDados;
	}

	public void setDeputadosSemDados(ArrayList<Statistic> deputadosSemDados) {
		this.deputadosSemDados = deputadosSemDados;
	}

}
