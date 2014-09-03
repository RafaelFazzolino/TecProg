package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.dao.DiaDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessoesEReunioesDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Dia;

public final class DiaControl {
	
	/**
	 * This method creates a list and returns the days that the deputy 
	 * was in a session
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataFormatoErradoException
	 */

	public static ArrayList<Dia> getDias() 
			throws ClassNotFoundException, SQLException, DataFormatoErradoException {
		ArrayList<Dia> lista;
		DiaDao diaDao = new DiaDao();

		lista = diaDao.buscarTodasDescricoes();

		ArrayList<Dia> listaInverter = new ArrayList<Dia>();

		for(int i = 0; i<lista.size(); i++) {
			listaInverter.add(lista.get(lista.size()-1-i));
		}

		return listaInverter;
	}

	/**
	 * This method creates a list based on the days and dates per pages
	 * and returns a list
	 * @param pagina
	 * @param datasPorPagina
	 * @param dia
	 * @return
	 */
	
	public static ArrayList<Dia> getListaCerta(int pagina, int datasPorPagina,
			ArrayList<Dia> dia) {
		ArrayList<Dia> listaPassar = new ArrayList<Dia>();

		for(int i = 0; i< datasPorPagina; i++) {
			if(pagina == 0) {
				listaPassar.add(dia.get(i));
			}
			else {
				if(i+(pagina*datasPorPagina) < dia.size()) {
					listaPassar.add(dia.get(i+ (pagina*datasPorPagina)));
				}
			}
		}

		return listaPassar;
	}

	public static Dia passarData(String data) 
			throws ClassNotFoundException, SQLException, 
			DataFormatoErradoException, ListaVaziaException{
		
		Dia dia = null;
		dia = new SessoesEReunioesDao().procuraDia(data);
		dia.setData(data);
		
		if(dia.getListaSessoes().size() == 0){
			throw new ListaVaziaException();
		}

		return dia;
	}
}
