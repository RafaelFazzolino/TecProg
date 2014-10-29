package br.com.MDSGPP.ChamadaParlamentar.util;

import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public final class LimparLista {

	/**
	 * This method is to clear the list of statistics of the deputies.
	 * 
	 * @param lista
	 *            is an Array contains all deputies.
	 * @return passar what is the clean list.
	 */
	public static ArrayList<ArrayList<Statistic>> limparLista(
			ArrayList<Statistic> list) {
		ArrayList<ArrayList<Statistic>> pass = new ArrayList<ArrayList<Statistic>>();
		ArrayList<Statistic> removidos = new ArrayList<Statistic>();
		for (int i = 0; i < list.size(); i++) {
			try {
				Integer.parseInt(list.get(i).getNumberSession());
			} catch (NumberFormatException e) {
				removidos.add(list.get(i));
				list.remove(i);
				i--;
			}
		}

		pass.add(list);
		pass.add(removidos);
		return pass;
	}
}
