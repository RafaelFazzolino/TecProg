/**
 * Class: SecondCongressmanServlet.
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is the servlet responsible to gather information for the user to compare
 * two different deputies, the first comes from the last jsp page, and the other
 * the system got to calculate using the name.
 */

package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.DeputiesControl;
import br.com.MDSGPP.ChamadaParlamentar.control.EstatisticaControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.ExceptionSqlInjection;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public class SecondCongressmanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is to get the second deputy.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This variable will receive the name of first congressman
		 * */
		String nameFirstCongressman;
		nameFirstCongressman = request.getParameter("primeiroParlamentar");
		nameFirstCongressman = nameFirstCongressman.split("-")[0];

		/**
		 * This variable will receive thw name of second Congressman
		 * */
		String nameSecondCongressman;
		nameSecondCongressman = request.getParameter("nome");
		/**
		 * This variable will receive feature of first congressman
		 * 
		 * */

		Deputies firstCongressman;
		firstCongressman = null;
		/**
		 * This variable will receive feature of second congressman
		 * 
		 * */

		Deputies secondCongressman;
		secondCongressman = null;

		RequestDispatcher rd;
		rd = null;

		if (ExceptionSqlInjection.testeSqlInjection(nameSecondCongressman)) {
			try {
				firstCongressman = DeputiesControl
						.verificaExistencia(nameFirstCongressman);
				secondCongressman = DeputiesControl
						.verificaExistencia(nameSecondCongressman);

				if (secondCongressman != null) {
					/**
					 * This variable will receive statistic of first congressman
					 * 
					 * */
					Statistic statisticFirst;
					statisticFirst = EstatisticaControl
							.gerarEstatisticas(EstatisticaControl
									.arrumarNomePesquisa(firstCongressman));
					/**
					 * This variable will receive statistic of second
					 * congressman
					 * 
					 * */
					Statistic statisticSecond;
					statisticSecond = EstatisticaControl
							.gerarEstatisticas(EstatisticaControl
									.arrumarNomePesquisa(secondCongressman));
					ArrayList<Statistic> list;
					list = new ArrayList<Statistic>();
					list.add(statisticFirst);
					list.add(statisticSecond);

					/**
					 * This variable will receive percent of miss of first
					 * congressman
					 * 
					 * */
					double presenceFirst;
					presenceFirst = Math
							.ceil(((Double.parseDouble(statisticFirst
									.getNumberSession())) / (Double
									.parseDouble(statisticFirst
											.getTotalSession()))) * 100);

					/**
					 * This variable will receive percent of miss of first
					 * congressman in format string
					 * 
					 * */
					String presenceNext1;
					presenceNext1 = Double.toString(presenceFirst);

					/**
					 * This variable will receive percent of miss of second
					 * congressman
					 * 
					 * */
					double presenceSecond;
					presenceSecond = Math
							.ceil(((Double.parseDouble(statisticSecond
									.getNumberSession())) / (Double
									.parseDouble(statisticSecond
											.getTotalSession()))) * 100);
					/**
					 * This variable will receive percent of miss of first
					 * congressman in format string
					 * 
					 * */
					String presenceNext2;
					presenceNext2 = Double.toString(presenceSecond);

					/**
					 * List of names of congressman
					 * 
					 * 
					 * */
					ArrayList<String> listNames;
					listNames = new ArrayList<String>();

					listNames.add(statisticFirst.getName());
					listNames.add(statisticSecond.getName());
					listNames.add("Total");

					request.setAttribute("lista", listNames);
					request.setAttribute("nomePrimeiro",
							statisticFirst.getName());
					request.setAttribute("presencaPrimeiro", presenceNext1);
					request.setAttribute("presencaSegundo", presenceNext2);
					request.setAttribute("estatisticaPrimeiro", statisticFirst);
					request.setAttribute("estatisticaSegundo", statisticSecond);

					rd = request
							.getRequestDispatcher("/MostrarComparacaoDeputados.jsp");
				} else {
					rd = request
							.getRequestDispatcher("/DeputadoNaoEncontrado.jsp");
				}
			} catch (ClassNotFoundException e1) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (SQLException e) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (NumberFormatException e) {
				rd = request.getRequestDispatcher("/DadosNaoDisponiveis.jsp");
			} catch (EmptyListException e) {
				rd = request.getRequestDispatcher("/DadosNaoDisponiveis.jsp");
			}
		} else {
			rd = request.getRequestDispatcher("SqlDetectado.jsp");
		}

		rd.forward(request, response);

	}
}
