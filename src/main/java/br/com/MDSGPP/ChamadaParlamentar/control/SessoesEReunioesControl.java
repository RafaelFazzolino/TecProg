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
		SessoesEReunioesDao dao;/*
								 * Variable that create the connection with
								 * dataBase.
								 */
		dao = new SessoesEReunioesDao();

		ArrayList<String> list;/* Variable that contains the list of sessions. */
		list = dao.procurarSessao(descricao);

		SessoesEReunioes pass;/* Variable that contains the final list. */
		pass = new SessoesEReunioes();
		pass.setDeputadosPresentes(list);
		pass.setDescricao(descricao);

		return pass;
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
		ArrayList<String> passList;/* Variable that contains the final list. */
		passList = new ArrayList<String>();

		for (int i = 0; i < deputadosPorPagina; i++) {
			if (pagina == 0) {
				passList.add(deputadosPresentes.get(i));
			} else {
				if (i + (pagina * deputadosPorPagina) < deputadosPresentes
						.size()) {
					passList.add(deputadosPresentes.get(i
							+ (pagina * deputadosPorPagina)));
				}
			}
		}

		return passList;
	}
}
