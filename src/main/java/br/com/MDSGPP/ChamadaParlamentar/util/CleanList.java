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

		for (int count = 0; count < sizeList; count++) {

			count = anlyseOneItem(list, pass, removed, count);

		}

		pass.add(list);
		pass.add(removed);
		return pass;
	}

	/**
	 * This method analyze to see if one item must go to removed or to the list.
	 * 
	 * @param lista
	 *            it is the list with all the statistics.
	 * @param pass
	 *            it is the list of the ones that are ok.
	 * @param removed
	 *            it is the list of the ones that has no data.
	 * @param i
	 *            it is the place where to search the statistic.
	 * @return returns the i to control where it is.
	 */
	private static int anlyseOneItem(ArrayList<Statistic> list,
			ArrayList<ArrayList<Statistic>> pass, ArrayList<Statistic> removed,
			int count) {
		try {
			Integer.parseInt(list.get(count).getNumberSession());
		} catch (NumberFormatException e) {
			removed.add(list.get(count));
			list.remove(count);
			count--;
		}
		return count;

	}
}
