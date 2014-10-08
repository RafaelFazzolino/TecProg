package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Estatistica;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class RankingDao extends ConnectionFactory {

	public RankingDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}
	
	/**
	 * This method add the Ranking in the DB table.
	 * @param ranking is a Ranking contains all features of the ranking.
	 * @throws SQLException case the dataBase is off.
	 */
	
	public void adicionarRankingNaTable(Ranking ranking) throws SQLException {
		String sql =  "insert into ranking(nomeParlamentar, porcentagem, numeroSessoes)values (?, ?, ?)";
		PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		for( int i = 0; i < ranking.getLista().size(); i++ ) {
			try {
				stmt.setString(1, ranking.getLista().get(i).getNome());
				stmt.setString(2, ranking.getLista().get(i).getPorcentagem());
				stmt.setString(3, ranking.getLista().get(i).getNumeroSessao());
				
				stmt.execute();
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println(ranking.getLista().get(i).getNome());
			}
		}
		for( int i = 0; i < ranking.getRemovidos().size(); i++ ) {
			stmt.setString(1, ranking.getRemovidos().get(i).getNome());
			stmt.setString(2, "semDados");
			stmt.execute();
		}

		stmt.close();
	}
	
	/**
	 * This method returns a refreshed status of the Ranking
	 * @return ranking complete.
	 * @throws SQLException case the dataBase is off.
	 */
	public Ranking retornaRanking () throws SQLException {
		Ranking ranking = new Ranking();
		ArrayList<Estatistica> deleted = new ArrayList<Estatistica>();
		ArrayList<Estatistica> list = new ArrayList<Estatistica>();
		
		String sql = "Select * from ranking";
		
		PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while( rs.next() ) {
			Estatistica statistic = new Estatistica();
			statistic.setNome(rs.getString("nomeParlamentar"));
			if( rs.getString("porcentagem").equalsIgnoreCase("semDados") ) {
				deleted.add(statistic);
			} 
			else {
				statistic.setPorcentagem(rs.getString("porcentagem"));
				statistic.setNumeroSessao(rs.getString("numeroSessoes"));
				list.add(statistic);
			}
		}
		
		ranking.setLista(list);
		ranking.setRemovidos(deleted);
		return ranking;
	}


}
