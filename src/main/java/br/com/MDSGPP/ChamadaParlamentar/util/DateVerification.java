/**
 * Class: DateVerification.
 *  
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * Class that validates the date.
 */

package br.com.MDSGPP.ChamadaParlamentar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateVerification {

	/**
	 * This method is to validate a date, if it's not in the in the format it
	 * should be, it returns false, otherwise it returns true.
	 * 
	 * @param data
	 *            is a String contains the date to be verified.
	 * @return false if give parse error in, and true if not.
	 */

	public static boolean dateVerification(String data) {
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("dd/MM/YYYY");
		sdf.setLenient(false);

		try {
			sdf.parse(data);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
