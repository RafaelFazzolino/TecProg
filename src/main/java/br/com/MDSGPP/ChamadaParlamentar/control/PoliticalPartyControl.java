package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.dao.DeputadoDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.PartidoDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;
import br.com.MDSGPP.ChamadaParlamentar.model.Estatistica;
import br.com.MDSGPP.ChamadaParlamentar.model.Partidos;
import br.com.MDSGPP.ChamadaParlamentar.util.LimparLista;

public final class PoliticalPartyControl {
	/**
	 * This method is to pass the list contains all parties.
	 * @return lista contains all parties.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 */
	public static ArrayList<ArrayList<String>> passarListaPartidos() 
			throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> list;/*ArrayList that contains all political parties.*/
		list = new PartidoDao().pegarPartidos();	
		
		return list;
	}
	/**
	 * This method is to check the party.
	 * @param partido is a String contains the name of the party.
	 * @return listaComDados contains the features of the party.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 */
	public static ArrayList<String> verificaExistencia(String partido)
			throws ClassNotFoundException, SQLException {

		ArrayList<ArrayList<String>> listaComDados;/*Variable that contains all data of the parties.*/
		listaComDados = passarListaPartidos();

		int sizeListWithData;/*Variable that contains the size of the list.*/
		sizeListWithData = listaComDados.size();
		
		for(int i = 0 ; i < sizeListWithData ; i++) {
			
			if(listaComDados.get(i).get(0).equalsIgnoreCase(partido)
					||listaComDados.get(i).get(1).equalsIgnoreCase(partido)) {
				
				return listaComDados.get(i);
			}
		}
		return null;
	}

	/**
	 * This method is to pass all parties already with all Members of each party.
	 * @param nomePartido is a String contains the name of the party.
	 * @return partido contains all features of the party.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 */
	 
	public static Partidos passarPartido(String nomePartido) 
			throws ClassNotFoundException, SQLException {
		Partidos partido;/*Variable that contains all features of the party.*/
		partido = new Partidos();
		partido.setDeputadosDoPartido(null);

		ArrayList<String> nomePartidoCerto;/*Variable that contains the right political party.*/
		nomePartidoCerto = verificaExistencia(nomePartido);

		ArrayList<Deputados> allDeputies;/*Array that contains all deputies.*/
		allDeputies = new DeputadoDao().getDeputados();
		
		ArrayList<Deputados> deputadosDoPartido;/*ArrayList that contains all deputies of the party.*/
		deputadosDoPartido = new ArrayList<Deputados>();

		if(nomePartidoCerto != null) {			
			
			int sizeAllDeputies;/*Variable that contains the size of the list with all deputies.*/
			sizeAllDeputies = allDeputies.size();
			
			for(int i = 0 ; i < sizeAllDeputies ; i++) {
				if(nomePartidoCerto.get(0).equalsIgnoreCase(
						allDeputies.get(i).getPartido())) {
					deputadosDoPartido.add(allDeputies.get(i));
				}
			}

			partido.setSigla(nomePartidoCerto.get(0));
			partido.setNomePartido(nomePartidoCerto.get(1));
			partido.setDeputadosDoPartido(deputadosDoPartido);	
		}

		return partido;
	}

	/**
	 * This method is to generate the statistical presence of each party,
	 * analyzing all the Members present in each party.
	 * @param nome is a String contains the name of the party.
	 * @return partido contains all features of the party.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 * @throws ListaVaziaException case an error occurs with the list.
	 */
	 
	public static Partidos gerarEstatisticaDoPartido(String nome) 
			throws ClassNotFoundException, SQLException, ListaVaziaException {
		Partidos partido = passarPartido(nome);

		ArrayList<Estatistica> estatisticas;/*Variable that contains the statistics.*/
		estatisticas = new ArrayList<Estatistica>();

		int sizeDeputiesOfParty;/*Variable that contains the size of list with all deputies of the party.*/
		sizeDeputiesOfParty = partido.getDeputadosDoPartido().size();
		
		try {
			for(int i = 0 ; i < sizeDeputiesOfParty ; i++) {
				Estatistica estatistica;/*Variable that contains all features of statistics.*/
				estatistica = new Estatistica();
				try {
					estatistica = EstatisticaControl.gerarEstatisticas(
							EstatisticaControl.arrumarNomePesquisa(partido.getDeputadosDoPartido().get(i)));
				} catch (ListaVaziaException e) {
					estatistica.setNome(EstatisticaControl.arrumarNomePesquisa(partido.getDeputadosDoPartido().get(i)));
				}
				estatisticas.add(estatistica);
			}
		} catch (NullPointerException e) {
			throw new ListaVaziaException();
		}

		partido.setEstatisticaDosDeputados(estatisticas);

		return partido;		
	}

	/**
	 * This method is to pass only the parties with complete data.
	 * @param nome is a String contains the name of the party.
	 * @return partido contains all features of the party.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with database.
	 * @throws ListaVaziaException case an error occurs with the list.
	 */
	public static Partidos passarPartidoComDadosCompletos(String nome) 
			throws ClassNotFoundException, SQLException, ListaVaziaException {

		Partidos partido;/*Variable that contains all features of the political party.*/
		partido = gerarEstatisticaDoPartido(nome);


		ArrayList<ArrayList<Estatistica>> listaRecebida;/*Variable that contains the received list.*/
		listaRecebida = LimparLista.limparLista(partido.getEstatisticaDosDeputados());

		partido.setEstatisticaDosDeputados(listaRecebida.get(0));
		partido.setDeputadosSemDados(listaRecebida.get(1));

		return partido;
	}	

	


}