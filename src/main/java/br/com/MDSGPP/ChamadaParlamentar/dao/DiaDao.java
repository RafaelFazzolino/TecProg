package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.model.Dia;
import br.com.MDSGPP.ChamadaParlamentar.model.SessoesEReunioes;

public class DiaDao extends ConnectionFactory {

	private static final String DATAS = "datas";

	public DiaDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}
	
	/**
	 * This method search all the descriptions based on a day.
	 * @return list contains all days.
	 * @throws SQLException case the dataBase is off.
	 * @throws DataFormatoErradoException case the date is wrong.
	 */

	public ArrayList<Dia> buscarTodasDescricoes() throws SQLException, DataFormatoErradoException {
		ArrayList<Dia> list = new ArrayList<Dia>();
		String sql = "Select * from datas";

		PreparedStatement stmt= ConnectionFactory.getConexao().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		list = criarDias(rs);

		ResultSet rs2 = stmt.executeQuery();

		list = popularListaDia(rs2, list);

		rs.close();
		return list;
	}

	/**
	 * This method populates the DB based on a day.
	 * @param rs
	 * @param lista
	 * @return list contains all days.
	 * @throws SQLException case the dataBase is off.
	 */
	public static ArrayList<Dia> popularListaDia(ResultSet rs, 
			ArrayList<Dia> list) throws SQLException {
		int cont = 0;

		while( rs.next() ) {
			String descricao = rs.getString("sessao");
			String descAux = descricao;
			descricao = descricao.split(" -")[0];

			boolean teste = list.get(cont).getData().equalsIgnoreCase(rs.getString(DATAS));

			if( !teste ) {
				cont++;
			}
			SessoesEReunioes passar = new SessoesEReunioes();
			passar.setDescricao(descricao);
			passar.setFullDescription(descAux);
			list.get(cont).getListaSessoes().add(passar);
		}

		rs.close();
		return list;
	}

	/**
	 * This method create the Dia in the DB.
	 * @param rs
	 * @return lista contains the new days.
	 * @throws SQLException case the dataBase is off.
	 * @throws DataFormatoErradoException case the date is wrong.
	 */
	
	public static ArrayList<Dia> criarDias(ResultSet rs) 
			throws SQLException, DataFormatoErradoException {
		ArrayList<Dia> lista = new ArrayList<Dia>();

		int controle = 0;

		while( rs.next() ) {
			if( lista.size() == 0 ) {
				Dia dia = new Dia();
				dia.setData(rs.getString(DATAS));
				lista.add(dia);
			}
			else {
				boolean teste = lista.get(controle).getData().equals(rs.getString(DATAS));
				if( !teste ) {
					Dia diaAux = new Dia();
					diaAux.setData(rs.getString(DATAS));
					lista.add(diaAux);
					controle++;
				}
			}
		}

		rs.close();
		return lista;
	}

}
