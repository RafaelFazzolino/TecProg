/**
 * Class: Internalization_En
 * 
 * This class is the class that will translate the site for English.
 */

package br.com.MDSGPP.ChamadaParlamentar.internalization;

import java.util.ListResourceBundle;

public class Internalization_En extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return contents;
	}

	static final Object[][] contents = {
			/**
			 * translates for header.
			 */
			{ "menu.Home", "Home" },

			{ "menu.Deputys", "Deputies" },
			{ "menu.TrackDeputy", "Track Deputies" },

			{ "menu.Party", "Party" },
			{ "menu.TrackParty", "Track Party" },

			{ "menu.Sessions", "Sessions" },
			{ "menu.TrackSession", "Track Session" },

			{ "menu.Ranking", "Ranking" },
			{ "menu.RankingTop5", "Top 5 Ranking" },
			{ "menu.CompleteRanking", "Complete Ranking" },

			
	};

}