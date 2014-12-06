package br.com.MDSGPP.ChamadaParlamentar.util;

public class Language {

	private static String language = "br.com.MDSGPP.ChamadaParlamentar.internalization.Internalization_En";
	private final static String BASE_CLASS = "br.com.MDSGPP.ChamadaParlamentar.internalization.Internalization_";

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		if (language.equals("english")) {
			Language.language = BASE_CLASS + "En";
		}
		if (language.equals("portuguese")) {
			Language.language = BASE_CLASS + "Pt";
		}

	}

}
