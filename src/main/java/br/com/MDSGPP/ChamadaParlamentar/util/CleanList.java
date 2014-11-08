/**
 * Class: CleanList
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is the based to wipe the deputies that have no data of how many
 * sessions have attended to. 
 */

package br.com.MDSGPP.ChamadaParlamentar.util;

import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public final class CleanList {

	/**
	 * This method is to clear the list of statistics of the deputies.
	 * 
	 * @param lista
	 *            is an Array contains all deputies.
	 * @return passar what is the clean list.
	 */
	public static ArrayList<ArrayList<Statistic>> cleanList(
			ArrayList<Statistic> list) {
		ArrayList<ArrayList<Statistic>> pass;
		pass = new ArrayList<ArrayList<Statistic>>();

		ArrayList<Statistic> removed;/*
									 * This is an Array that contains all
									 * deputies removed.
									 */
		removed = new ArrayList<Statistic>();

		int sizeList;/* Variable that contains the size of list. */
		sizeList = list.size();

		for (int i = 0; i < sizeList; i++) {
			try {
				Integer.parseInt(list.get(i).getNumberSession());
			} catch (NumberFormatException e) {
				removed.add(list.get(i));
				list.remove(i);
				i--;
			}
		}

		pass.add(list);
		pass.add(removed);
		return pass;
	}
}
