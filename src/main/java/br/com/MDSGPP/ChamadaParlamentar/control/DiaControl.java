package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.collections.iterators.ReverseListIterator;

import br.com.MDSGPP.ChamadaParlamentar.dao.DiaDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessoesEReunioesDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Dia;

public final class DiaControl {
	
	/**
	 * This method creates a list and returns the days that the deputy 
	 * was in a session.
	 * @return listaInverter what is the new list of days.
	 * @throws ClassNotFoundException if the class is not found.
	 * @throws SQLException if an error occurs with dataBase.
	 * @throws DataFormatoErradoException case to come up with wrong date format.
	 */
	public static ArrayList<Dia> getDias() 
			throws ClassNotFoundException, SQLException, DataFormatoErradoException {
		ArrayList<Dia> list;/*Variable that contains the days.*/
		DiaDao diaDao;/*Variable that create the connection with dataBase to get days.*/
		diaDao = new DiaDao();

		list = diaDao.buscarTodasDescricoes();

		ArrayList<Dia> listaInverter;/*Variable that contains the list of the days.*/
		listaInverter = new ArrayList<Dia>();

		listaInverter = ReverseList(listaInverter, list);

		return listaInverter;
	}
	
	/**
	 * This method creates the inverted list.
	 * @param listaInverter is an Array that contains the clear list.
	 * @param list variable that contains the list.
	 * @return listaInverter that is the inverted list.
	 */
	public static ArrayList<Dia> ReverseList(ArrayList<Dia> listaInverter, ArrayList<Dia> list ){
		listaInverter = new ArrayList<Dia>();
		
		int sizeList;/*Variable that contains the size of the list.*/
		sizeList = list.size();
		
		for(int i = 0; i < sizeList; i++) {
			listaInverter.add(list.get(sizeList-1-i));
		}
		return listaInverter;
	}

	/**
	 * This method creates a list based on the days and dates per pages
	 * and returns a list.
	 * @param pagina is the number of pages.
	 * @param datasPorPagina is a variable that contains the number of dates for page.
	 * @param dia is the day that is being analyzed.
	 * @return listaPassar is the arrayList contains all days.
	 */
	
	public static ArrayList<Dia> getListaCerta(int pagina, int datasPorPagina,
			ArrayList<Dia> dia) {
		ArrayList<Dia> listaPassar;/*Variable that contains all days.*/
		listaPassar = new ArrayList<Dia>();

		for(int i = 0 ; i < datasPorPagina ; i++) {
			if(pagina == 0) {
				listaPassar.add(dia.get(i));
			}
			else {
				int sizeDay;/*Variable that contains the size of the Day.*/
				sizeDay = dia.size();
				
				if(i+(pagina*datasPorPagina) < sizeDay) {
					listaPassar.add(dia.get(i+ (pagina*datasPorPagina)));
				}
			}
		}

		return listaPassar;
	}
	/**
	 * This method is to pass the date.
	 * @param data is a variable what contains the date.
	 * @return dia what is a variable what contains all features of the date.
	 * @throws ClassNotFoundException is case occur an error.
	 * @throws SQLException is case occurs an error with dataBase.
	 * @throws DataFormatoErradoException is case occurs an error with the date.
	 * @throws ListaVaziaException is case the list came empty.
	 */
	public static Dia passarData(String data) 
			throws ClassNotFoundException, SQLException, 
			DataFormatoErradoException, ListaVaziaException{
		
		Dia dia;/*Variable that contains the day.*/
		dia = null;
		dia = new SessoesEReunioesDao().procuraDia(data);
		dia.setData(data);
		
		int sizeListOfSessions;/*Variable that contains the size of the list of sessions.*/
		sizeListOfSessions = dia.getListaSessoes().size();
		
		if(sizeListOfSessions == 0){
			throw new ListaVaziaException();
		}

		return dia;
	}
}
