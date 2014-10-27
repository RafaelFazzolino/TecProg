package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

public class Ranking {

	private ArrayList<Statistic> list = new ArrayList<Statistic>();
	private ArrayList<Statistic> removed = new ArrayList<Statistic>();
	private ArrayList<Statistic> better = new ArrayList<Statistic>();
	private ArrayList<Statistic> worst = new ArrayList<Statistic>();

	public Ranking() {
	}

	/**
	 * This method is to get the statistic of deturies.
	 */
	public ArrayList<Statistic> getList() {
		return list;
	}

	/**
	 * This method is to change the variable lista.
	 */
	public void setList(ArrayList<Statistic> lista) {
		this.list = lista;
	}

	/**
	 * This method is to get all deputies deleted.
	 */
	public ArrayList<Statistic> getRemoved() {
		return removed;
	}

	/**
	 * This method is to change the variable removidos.
	 */
	public void setRemoved(ArrayList<Statistic> removidos) {
		this.removed = removidos;
	}

	/**
	 * This method is to get the bests deputies.
	 */
	public ArrayList<Statistic> getBetter() {
		return better;
	}

	/**
	 * This method is to change the variable melhores.
	 */
	public void setBetter(ArrayList<Statistic> melhores) {
		this.better = melhores;
	}

	/**
	 * This method is to get the worst deputies.
	 */
	public ArrayList<Statistic> getWorst() {
		return worst;
	}

	/**
	 * This method is to change the variable piores.
	 */
	public void setWorst(ArrayList<Statistic> piores) {
		this.worst = piores;
	}
}
