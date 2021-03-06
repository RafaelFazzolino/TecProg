/**
 * Class: UpdateDataBase
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is the class that is going to update the data base with
 * the data provided from the deputy chamber, every day at 6 AM.
 */

package br.com.MDSGPP.ChamadaParlamentar.atualizacaoBanco;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.rpc.ServiceException;

import br.com.MDSGPP.ChamadaParlamentar.classesDeConexao.ConnectionWithWsSessions;
import br.com.MDSGPP.ChamadaParlamentar.control.RankingControl;
import br.com.MDSGPP.ChamadaParlamentar.dao.Dao;
import br.com.MDSGPP.ChamadaParlamentar.dao.DeputiesDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.RankingDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessionsAndMeetingsDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaRankingException;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;

public class UpdateDataBase {
	Timer timer;// Variable used to control the time of updating the database.

	/**
	 * Method to start the execution of tasks. From this method will update the
	 * database with new information from the WebService.
	 */
	public UpdateDataBase() {

		timer = new Timer();
		// Executes the task every day 6am.
		System.out.println("ENTROU INICIAR");
		timer.schedule(new tarefasDiarias(), getAmanha6Am(), delayDiario);
	}

	private final static long delayDiario = 1000 * 60 * 60 * 24;
	private final static int fONE_DAY = 0;/*
										 * Variable used to store the value 0,
										 * constant.
										 */
	private final static int fFOUR_AM = 18;/*
											 * Variable used to store the value
											 * 18, constant.
											 */
	private final static int fZERO_MINUTES = 17;/*
												 * Variable used to store the
												 * value 17, constant.
												 */

	private static Date getAmanha6Am() {
		System.out.println("entrou");
		Calendar tomorrow = new GregorianCalendar();/*
													 * Variable used to save the
													 * day that the update
													 * should be done.
													 */
		tomorrow.add(Calendar.DATE, fONE_DAY);
		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
				tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE),
				fFOUR_AM, fZERO_MINUTES);

		Date time = result.getTime();/*
									 * Saves the time that the method should
									 * update the system.
									 */

		return time;
	}

	public static void main(String[] args) {
		new UpdateDataBase();
	}

	// Method to stop the execution of tasks.

	public void stop() {
		timer.cancel();
	}

	// Method that contains the scheduled tasks that will be performed.

	class tarefasDiarias extends TimerTask {
		public void run() {
			try {
				System.out.println("teste");
				Dao.truncateTables();

				SessionsAndMeetingsDao sessionsDao;/*
													 * This variable controls
													 * the connection to the
													 * database, specific to the
													 * table sessions and
													 * meetings.
													 */
				sessionsDao = new SessionsAndMeetingsDao();

				sessionsDao.addDateInTable(ConnectionWithWsSessions
						.adcionarDataNaTable("20/11/2011", "440"));
				sessionsDao.addSessionInTable(ConnectionWithWsSessions
						.adcionarSessaoNaTable("20/11/2011"));

				RankingDao rankingDao = new RankingDao();/*
														 * This variable
														 * controls the
														 * connection to the
														 * database, specific to
														 * the ranking table.
														 */
				Ranking ranking = RankingControl.createRanking(RankingControl
						.createListEstatistic(new DeputiesDao().getDeputies()));

				rankingDao.addRankingInTable(ranking);/*
													 * This variable is the
													 * ranking done.
													 */

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ListaRankingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
