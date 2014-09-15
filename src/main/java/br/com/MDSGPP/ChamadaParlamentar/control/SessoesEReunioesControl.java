package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.dao.SessoesEReunioesDao;
import br.com.MDSGPP.ChamadaParlamentar.model.SessoesEReunioes;

public final class SessoesEReunioesControl {
	
	/**
	 * Method that bridges the gap between the Dao and Model about the sessions.
	 * 
	 * @param descricao
	 *            is the parameter that used for search the session.
	 * @return passar, is the object of the model SessoesEReunioes.
	 * @throws ClassNotFoundException
	 *             if the database is off.
	 * @throws SQLException
	 *             if miss spelled SQL is entered.
	 */

	public static SessoesEReunioes passarSessao(String descricao) 
			throws ClassNotFoundException, SQLException {
		SessoesEReunioesDao dao = new SessoesEReunioesDao();

		ArrayList<String> lista = dao.procurarSessao(descricao);

		SessoesEReunioes passar = new SessoesEReunioes();
		passar.setDeputadosPresentes(lista);
		passar.setDescricao(descricao);

		return passar;		
	}
	
	/**
	 * Method that organizes how many deputies will be displayed per page.
	 * 
	 * @param pagina
	 * @param deputadosPorPagina
	 * @param deputadosPresentes
	 * @return 'listaPassar' with all the deputies that are in the page.
	 */

	public static ArrayList<String> arrumarListaDeputados(int pagina, 
			int deputadosPorPagina, ArrayList<String> deputadosPresentes) {
		ArrayList<String> listaPassar = new ArrayList<String>();

		for(int i = 0; i < deputadosPorPagina; i++) {
			if( pagina == 0 ) {
				listaPassar.add(deputadosPresentes.get(i));
			}
			else {
				if( i + (pagina*deputadosPorPagina) < deputadosPresentes.size() ) {
					listaPassar.add(deputadosPresentes.get(i+ (pagina*deputadosPorPagina)));
				}
			}
		}
		
		return listaPassar;
	}
}
