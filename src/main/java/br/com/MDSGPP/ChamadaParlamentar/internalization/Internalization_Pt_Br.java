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

			{ "TrackDeputy.InsertDeputysName", "Digite o nome do deputado" },

			{ "TrackParty.InsertPartysName", "Digite o nome do partido" },

			{ "DataNotAvailable.SearchOtherDeputys",
					"Procurar outros deputados" },
			{ "DataNotAvailable.click", "Clique" },
			{ "DataNotAvailable.Here", "aqui" },
			{ "DataNotAvailable.NoData",
					"Desculpe, os dados para este deputado não estão disponíveis." },

			{ "TrackSession.InsertDay", "Digite o dia" },
			{ "TrackSession.Page", "Página" },
			{ "TrackSession.Date", "Data" },

			{ "DateNotFound.DateNotFound", "Data não encontrada." },
			{ "DateNotFound.TryAgain", "Deseja tentar novamente?" },

			{ "DeputyNotFound.DeputyNotFound", "Deputado não encontrado!" },
			{ "DeputyNotFound.TryAgain", "Deseja tentar novamente?" },

			{ "Error.CorrectedError", "Desculpe, esse erro será corrigido." },

			{ "Footer.WhoWeAre", "Quem nós somos" },
			{ "Error.CorrectedError", "Desculpe, esse erro será corrigido." },

			{ "WrongFormat.WrongDate",
					"Desculpe, mas a data que você digitou está incorreta." },

			{ "ShowComparisonDeputy.Name", "Nome:" },
			{ "ShowComparisonDeputy.SessionsWhatched", "Sessões Assistidas:" },
			{ "ShowComparisonDeputy.TotalNumberSessions", "Número de sessões:" },
			{ "ShowComparisonDeputy.Percent", "Percentual:" },
			{ "ShowComparisonDeputy.Sessions", "Sessões:" },

	};
}
