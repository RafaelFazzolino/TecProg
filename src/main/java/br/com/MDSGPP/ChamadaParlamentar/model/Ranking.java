package br.com.MDSGPP.ChamadaParlamentar.model;

import java.util.ArrayList;

public class Ranking {

	private ArrayList<Estatistica> lista = new ArrayList<Estatistica>();
	private ArrayList<Estatistica> removidos = new ArrayList<Estatistica>();
	private ArrayList<Estatistica> melhores = new ArrayList<Estatistica>();
	private ArrayList<Estatistica> piores = new ArrayList<Estatistica>();
	
	public Ranking() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method is to get the statistic of deturies.
	 */
	public ArrayList<Estatistica> getLista() {
		return lista;
	}
	/**
	 * This method is to change the variable lista.
	 */
	public void setLista(ArrayList<Estatistica> lista) {
		this.lista = lista;
	}
	/**
	 * This method is to get all deputies deleted.
	 */
	public ArrayList<Estatistica> getRemovidos() {
		return removidos;
	}
	/**
	 * This method is to change the variable removidos.
	 */
	public void setRemovidos(ArrayList<Estatistica> removidos) {
		this.removidos = removidos;
	}
	/**
	 * This method is to get the bests deputies.
	 */
	public ArrayList<Estatistica> getMelhores() {
		return melhores;
	}
	/**
	 * This method is to change the variable melhores.
	 */
	public void setMelhores(ArrayList<Estatistica> melhores) {
		this.melhores = melhores;
	}
	/**
	 * This method is to get the worst deputies.
	 */
	public ArrayList<Estatistica> getPiores() {
		return piores;
	}
	/**
	 * This method is to change the variable piores.
	 */
	public void setPiores(ArrayList<Estatistica> piores) {
		this.piores = piores;
	}
}
