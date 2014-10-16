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

		DeputadoDao deputy;/*Variable that contains the deputies.*/
		deputy = new DeputadoDao();
		
		ArrayList<String> nameDeputy; /*Variable that will contains the name of all deputies.*/
		nameDeputy = deputy.getNomesDeputados();
		
		return nameDeputy;
	}
	/**
	 * This method is to check the deputy.
	 * @param nome is a variable what contains the name of deputy.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	 
	public static Deputados verificaExistencia(String nome) throws ClassNotFoundException, SQLException {
		Deputados deputy;/*Variable that contains the deputies.*/
		deputy = null;

		DeputadoDao deputadoDao;/*Variable that create connection with dataBase to get deputies.*/
		deputadoDao = new DeputadoDao();			
		ArrayList<Deputados> list;/*Variable that contains all deputies.*/
		list = deputadoDao.getDeputados();

		int sizeList;/*Variable that contains the size of the list.*/
		sizeList = list.size();
		
		for( int countingAux = 0 ; countingAux < sizeList ; countingAux++ ) {
			
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
