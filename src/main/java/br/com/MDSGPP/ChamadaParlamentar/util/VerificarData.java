package br.com.MDSGPP.ChamadaParlamentar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public final class VerificarData {
	
	/**
	 * This method is to validate a date, if it's not in the in the format it
	 * should be, it returns false, otherwise it returns true.
	 * @param data is a String contains the date to be verified.
	 * @return false if give parse error in, and true if not.
	 */

	public static boolean verificaData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		sdf.setLenient(false);

		try {
			sdf.parse(data);
		} catch (ParseException e) {
			return false;
		  }	
		  return true;
	}
}
