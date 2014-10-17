package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.DeputadosControl;
import br.com.MDSGPP.ChamadaParlamentar.control.EstatisticaControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.ExceptionSqlInjection;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;
import br.com.MDSGPP.ChamadaParlamentar.model.Estatistica;

public class SecondCongressmanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is to get the second deputy.
	 */
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/**
		 * This variable will receive the name of first congressman
		 * */
		String nameFirstCongressman = request.getParameter("primeiroParlamentar");
		nameFirstCongressman = nameFirstCongressman.split("-")[0];

		/**
		 * This variable will receive thw name of second Congressman 
		 * */
		String nameSecondCongressman = request.getParameter("nome");
		/**
		 * This variable will receive feature of first congressman
		 * 
		 * */
		
		Deputados firstCongressman = null;
		/**
		 * This variable will receive feature of second congressman
		 * 
		 * */
		
		Deputados secondCongressman = null;
		RequestDispatcher rd = null;

		if( ExceptionSqlInjection.testeSqlInjection(nameSecondCongressman) ) {
			try {
				firstCongressman = DeputadosControl.verificaExistencia(nameFirstCongressman);
				secondCongressman = DeputadosControl.verificaExistencia(nameSecondCongressman);


				if( secondCongressman != null ) {
					/**
					 * This variable will receive statistic of first congressman
					 * 
					 * */
					Estatistica statisticFirst = EstatisticaControl.
							gerarEstatisticas(EstatisticaControl.
									arrumarNomePesquisa(firstCongressman));
					/**
					 * This variable will receive statistic of second congressman
					 * 
					 * */	
					Estatistica statisticSecond = EstatisticaControl.
							gerarEstatisticas(EstatisticaControl.
									arrumarNomePesquisa(secondCongressman));
					ArrayList<Estatistica> lista = new ArrayList<Estatistica>();
					lista.add(statisticFirst);
					lista.add(statisticSecond);
					
					/**
					 * This variable will receive percent of miss of first congressman
					 * 
					 * */
					double presenceFirst = Math.ceil(((Double.parseDouble(statisticFirst.getNumeroSessao())) / (Double.parseDouble(statisticFirst.getTotalSessao())))*100);
					
					/**
					 * This variable will receive percent of miss of first congressman in format string
					 * 
					 * */
					String presenceNext1 = Double.toString(presenceFirst);
					
					
					/**
					 * This variable will receive percent of miss of second congressman
					 * 
					 * */
					double presenceSecond = Math.ceil(((Double.parseDouble(statisticSecond.getNumeroSessao())) / (Double.parseDouble(statisticSecond.getTotalSessao())))*100);
					/**
					 * This variable will receive percent of miss of first congressman in format string
					 * 
					 * */
					String presenceNext2 = Double.toString(presenceSecond);
					
					
					/**
					 * List of names of congressman
					 * 
					 * 
					 * */
					ArrayList<String> listNames = new ArrayList<String>();
					listNames.add(statisticFirst.getNome());
					listNames.add(statisticSecond.getNome());
					listNames.add("Total");
					
					request.setAttribute("lista", listNames);
					request.setAttribute("nomePrimeiro", statisticFirst.getNome());
					request.setAttribute("presencaPrimeiro", presenceNext1);
					request.setAttribute("presencaSegundo", presenceNext2);
					request.setAttribute("estatisticaPrimeiro", statisticFirst);
					request.setAttribute("estatisticaSegundo", statisticSecond);
					
					rd = request.getRequestDispatcher("/MostrarComparacaoDeputados.jsp");
				}
				else {
					rd = request.getRequestDispatcher("/DeputadoNaoEncontrado.jsp");
				}
			} catch (ClassNotFoundException e1) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (SQLException e) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (NumberFormatException e) {
				rd = request.getRequestDispatcher("/DadosNaoDisponiveis.jsp");
			} catch (ListaVaziaException e) {
				rd = request.getRequestDispatcher("/DadosNaoDisponiveis.jsp");
			}
		}
		else {
			rd = request.getRequestDispatcher("SqlDetectado.jsp");
		}

		rd.forward(request, response);

	}
}