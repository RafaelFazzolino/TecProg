package br.com.MDSGPP.ChamadaParlamentar.util;

import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public final class LimparLista {

	/**
	 * This method is to clear the list of statistics of the deputies.
	 * @param lista is an Array contains all deputies.
	 * @return passar what is the clean list.
	 */
	public static ArrayList<ArrayList<Statistic>> limparLista(ArrayList<Statistic> lista){
		ArrayList<ArrayList<Statistic>> passar = new ArrayList<ArrayList<Statistic>>();
		ArrayList<Statistic> removidos = new ArrayList<Statistic>();
		for( int i = 0; i < lista.size(); i++ ) {
			try {
				Integer.parseInt(lista.get(i).getNumberSession());
			} catch (NumberFormatException e) {
				removidos.add(lista.get(i));
				lista.remove(i);
				i--;
			}
		}

		passar.add(lista);
		passar.add(removidos);
		return passar;
	}
}
