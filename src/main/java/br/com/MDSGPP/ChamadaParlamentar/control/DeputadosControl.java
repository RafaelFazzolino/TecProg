package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.dao.DeputadoDao;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;

public final class DeputadosControl {
	/**
	 * This method is to get deputies. 
	 * @return deputado.getNomesDeputados what is the name of all deputies.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<String> getDeputados() throws ClassNotFoundException, SQLException {

		DeputadoDao deputy;
		deputy = new DeputadoDao();

		return deputy.getNomesDeputados();
	}
	/**
	 * This method is to check the deputy.
	 * @param nome is a variable what contains the name of deputy.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	 
	public static Deputados verificaExistencia(String nome) throws ClassNotFoundException, SQLException {
		Deputados deputy = null;

		DeputadoDao deputadoDao = new DeputadoDao();			
		ArrayList<Deputados> list = deputadoDao.getDeputados();

		for( int countingAux = 0; countingAux < list.size(); countingAux++ ) {
			if(( list.get(countingAux).getNomeCivilDoParlamentar().
					equalsIgnoreCase(nome)) ||
					list.get(countingAux).getNomeDeTratamentoDoParlamentar()
					.equalsIgnoreCase(nome) ) {

				deputy = list.get(countingAux);
			}
		}

		return deputy;
	}
}
