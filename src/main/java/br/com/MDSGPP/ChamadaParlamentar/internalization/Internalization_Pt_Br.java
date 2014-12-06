/**
 * Class: Internalization_Pt_Br
 * 
 * This class is the class that will translate the site for Portuguese from Brazil.
 */

package br.com.MDSGPP.ChamadaParlamentar.internalization;

import java.util.ListResourceBundle;

public class Internalization_Pt_Br extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return contents;
	}

	static final Object[][] contents = {
	/**
	 * translates for header.
	 */
	{ "menu.Home", "Página Inicial" },

	{ "menu.Deputys", "Deputados" },
			{ "menu.TrackDeputy", "Procurar deputado" },

			{ "menu.Partido", "Partido" },
			{ "menu.TrackParty", "Procurar partido" },

			{ "menu.Sessões", "Sessões" },
			{ "menu.TrackSession", "Track Session" },

			{ "menu.Ranking", "Ranking" },
			{ "menu.RankingTop5", "5 melhores do Ranking" },
			{ "menu.CompleteRanking", "Ranking Completo" },

	};
}
