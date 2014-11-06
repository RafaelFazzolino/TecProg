package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class RankingDao extends ConnectionFactory {

	public RankingDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}

	/**
	 * This method add the Ranking in the DB table.
	 * 
	 * @param ranking
	 *            is a Ranking contains all features of the ranking.
	 * @throws SQLException
	 *             case the dataBase is off.
	 */

	public void addRankingInTable(Ranking ranking) throws SQLException {
		String sql;
		sql = "insert into ranking(nomeParlamentar, porcentagem, numeroSessoes)values (?, ?, ?)";
		PreparedStatement stmt;
		stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		int sizeRanking;/* Variable that contains the size of ranking. */
		sizeRanking = ranking.getList().size();

		for (int i = 0; i < sizeRanking; i++) {
			try {
				stmt.setString(1, ranking.getList().get(i).getName());
				stmt.setString(2, ranking.getList().get(i).getPercentagem());
				stmt.setString(3, ranking.getList().get(i).getNumberSession());

				stmt.execute();
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println(ranking.getList().get(i).getName());
			}
		}
		for (int i = 0; i < ranking.getRemoved().size(); i++) {
			stmt.setString(1, ranking.getRemoved().get(i).getName());
			stmt.setString(2, "semDados");
			stmt.execute();
		}

		stmt.close();
	}

	/**
	 * This method returns a refreshed status of the Ranking
	 * 
	 * @return ranking complete.
	 * @throws SQLException
	 *             case the dataBase is off.
	 */
	public Ranking returnRanking() throws SQLException {

		Ranking ranking;/* Variable that contains the ranking. */
		ranking = new Ranking();

		ArrayList<Statistic> deleted;/*
									 * Variable that contains all deputies
									 * deleted.
									 */
		deleted = new ArrayList<Statistic>();

		ArrayList<Statistic> list;/* Variable that contains all deputies. */
		list = new ArrayList<Statistic>();

		String sql = "Select * from ranking";

		PreparedStatement stmt;
		stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		ResultSet rs;
		rs = stmt.executeQuery();

		while (rs.next()) {
			Statistic statistic;
			statistic = new Statistic();

			statistic.setName(rs.getString("nomeParlamentar"));
			if (rs.getString("porcentagem").equalsIgnoreCase("semDados")) {
				deleted.add(statistic);
			} else {
				statistic.setPercentagem(rs.getString("porcentagem"));
				statistic.setNumberSession(rs.getString("numeroSessoes"));
				list.add(statistic);
			}
		}

		ranking.setList(list);
		ranking.setRemoved(deleted);
		return ranking;
	}

}
