package br.com.MDSGPP.ChamadaParlamentar.control;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.MDSGPP.ChamadaParlamentar.dao.DeputyDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.PartidoDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;
import br.com.MDSGPP.ChamadaParlamentar.model.Estatistica;
import br.com.MDSGPP.ChamadaParlamentar.model.Partidos;
import br.com.MDSGPP.ChamadaParlamentar.util.LimparLista;

public final class PartidoControl {
	/**
	 * This method is to pass the list contains all parties.
	 * @return lista contains all parties.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 */
	public static ArrayList<ArrayList<String>> passarListaPartidos() 
			throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> lista = new PartidoDao().pegarPartidos();	
		return lista;
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

		ArrayList<ArrayList<String>> listaComDados = passarListaPartidos();

		for(int i = 0; i < listaComDados.size(); i++) {
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
		Partidos partido = new Partidos();
		partido.setDeputadosDoPartido(null);

		ArrayList<String> nomePartidoCerto = verificaExistencia(nomePartido);

		ArrayList<Deputados> todosDeputados = new DeputyDao().getDeputados();
		ArrayList<Deputados> deputadosDoPartido = new ArrayList<Deputados>();

		if(nomePartidoCerto != null) {			
			for(int i = 0; i < todosDeputados.size(); i++) {
				if(nomePartidoCerto.get(0).equalsIgnoreCase(
						todosDeputados.get(i).getPartido())) {
					deputadosDoPartido.add(todosDeputados.get(i));
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

		ArrayList<Estatistica> estatisticas = new ArrayList<Estatistica>();

		try {
			for(int i = 0; i < partido.getDeputadosDoPartido().size(); i++) {
				Estatistica estatistica = new Estatistica();
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

		Partidos partido;
		partido = gerarEstatisticaDoPartido(nome);


		ArrayList<ArrayList<Estatistica>> listaRecebida = LimparLista.
				limparLista(partido.getEstatisticaDosDeputados());

		partido.setEstatisticaDosDeputados(listaRecebida.get(0));
		partido.setDeputadosSemDados(listaRecebida.get(1));

		return partido;
	}	

	


}
