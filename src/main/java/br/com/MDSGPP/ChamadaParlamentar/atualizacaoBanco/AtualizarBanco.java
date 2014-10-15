package br.com.MDSGPP.ChamadaParlamentar.atualizacaoBanco;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.rpc.ServiceException;

import br.com.MDSGPP.ChamadaParlamentar.classesDeConexao.ConexaoComWsSessoesEReunioes;
import br.com.MDSGPP.ChamadaParlamentar.control.RankingControl;
import br.com.MDSGPP.ChamadaParlamentar.dao.Dao;
import br.com.MDSGPP.ChamadaParlamentar.dao.DeputadoDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.RankingDao;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessoesEReunioesDao;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaRankingException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;

public class AtualizarBanco {
	Timer timer;/*Variable used to control the time of updating the database.*/
	/**
	 * Method to start the execution of tasks.
	 * From this method will update the database with new information from the WebService.
	 */
	public AtualizarBanco() {

		timer = new Timer();
		/*Executes the task every day 6am.*/
		System.out.println("ENTROU INICIAR");
		timer.schedule(new tarefasDiarias(), getAmanha6Am(), delayDiario);
}
	
	private final static long delayDiario = 1000*60*60*24;
	private final static int fONE_DAY = 0;/*Variable used to store the value 0, constant.*/
	private final static int fFOUR_AM = 18;/*Variable used to store the value 18, constant.*/
	private final static int fZERO_MINUTES = 17;/*Variable used to store the value 17, constant.*/

	private static Date getAmanha6Am() {
		System.out.println("entrou");
	    Calendar tomorrow;/*Variable used to save the day that the update should be done.*/
	    tomorrow = new GregorianCalendar();
	    tomorrow.add(Calendar.DATE, fONE_DAY);
	    Calendar result;/*Saves the time that the method should update the system.*/
	    result = new GregorianCalendar(
	    tomorrow.get(Calendar.YEAR),
	    tomorrow.get(Calendar.MONTH),
	    tomorrow.get(Calendar.DATE),
	    fFOUR_AM,
	    fZERO_MINUTES
	  );
	  return result.getTime();
	}
	
	public static void main(String[] args) {
        new AtualizarBanco();
    }
	
	/**
	 * Method to stop the execution of tasks.
	 */
	public void parar() {
		timer.cancel();
	}
	/**
	 * Method that contains the scheduled tasks that will be performed.
	 */
	class tarefasDiarias extends TimerTask {
		public void run() {
			try {
				System.out.println("teste");
				Dao.truncateTables();
				
				SessoesEReunioesDao sessionsDao;/*This variable controls the connection to the database, specific to the table sessions and meetings.*/
				sessionsDao = new SessoesEReunioesDao();
				
				sessionsDao.adcionarDataNaTable(ConexaoComWsSessoesEReunioes.adcionarDataNaTable("20/11/2011", "440"));
				sessionsDao.adcionarSessaoNaTable(ConexaoComWsSessoesEReunioes.adcionarSessaoNaTable("20/11/2011"));
				
				RankingDao rankingDao;/*This variable controls the connection to the database, specific to the ranking table.*/
				rankingDao = new RankingDao();
				
				Ranking ranking;/*This variable is the ranking done.*/
				ranking =  RankingControl.gerarRanking
						(RankingControl.gerarListaEstatistica(new DeputadoDao().getDeputados()));
		
				rankingDao.adicionarRankingNaTable(ranking);
				
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
			} catch (ListaVaziaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
