package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

public class Ranking {

	private ArrayList<Statistic> lista = new ArrayList<Statistic>();
	private ArrayList<Statistic> removidos = new ArrayList<Statistic>();
	private ArrayList<Statistic> melhores = new ArrayList<Statistic>();
	private ArrayList<Statistic> piores = new ArrayList<Statistic>();

	public Ranking() {
	}

	/**
	 * This method is to get the statistic of deturies.
	 */
	public ArrayList<Statistic> getLista() {
		return lista;
	}

	/**
	 * This method is to change the variable lista.
	 */
	public void setLista(ArrayList<Statistic> lista) {
		this.lista = lista;
	}

	/**
	 * This method is to get all deputies deleted.
	 */
	public ArrayList<Statistic> getRemovidos() {
		return removidos;
	}

	/**
	 * This method is to change the variable removidos.
	 */
	public void setRemovidos(ArrayList<Statistic> removidos) {
		this.removidos = removidos;
	}

	/**
	 * This method is to get the bests deputies.
	 */
	public ArrayList<Statistic> getMelhores() {
		return melhores;
	}

	/**
	 * This method is to change the variable melhores.
	 */
	public void setMelhores(ArrayList<Statistic> melhores) {
		this.melhores = melhores;
	}

	/**
	 * This method is to get the worst deputies.
	 */
	public ArrayList<Statistic> getPiores() {
		return piores;
	}

	/**
	 * This method is to change the variable piores.
	 */
	public void setPiores(ArrayList<Statistic> piores) {
		this.piores = piores;
	}
}
