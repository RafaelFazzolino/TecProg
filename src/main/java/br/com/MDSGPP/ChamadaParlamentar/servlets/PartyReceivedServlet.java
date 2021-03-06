/**
 * Class: partyReceivedServlet
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This is the class that will receive the parties of the servlet
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

import br.com.MDSGPP.ChamadaParlamentar.control.StatisticsPoliticalPartyControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.ExceptionSqlInjection;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;
import br.com.MDSGPP.ChamadaParlamentar.model.StatisticParty;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public class PartyReceivedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is to get the political party.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = null;

		String nameParty;
		nameParty = request.getParameter("nome");

		if (ExceptionSqlInjection.testeSqlInjection(nameParty)) {
			try {
				/**
				 * This variable will receive statistical party
				 * */
				StatisticParty statistic = StatisticsPoliticalPartyControl
						.gerarEstatisticaPartido(nameParty);
				/**
				 * This Array will receive all deputy with no data
				 * */
				ArrayList<Statistic> noData = statistic.getpoliticalParty()
						.getDeputiesWithoutData();

				/**
				 * This variable will receive party
				 * */
				Party party = statistic.getpoliticalParty();

				/**
				 * This variable used to know how many deputy whitout data
				 * */

				int howManyNoData = party.getDeputiesWithoutData().size();

				request.setAttribute("numeroSemDados", howManyNoData);
				request.setAttribute("semDados", noData);
				request.setAttribute("estatisticaPartido", statistic);
				request.setAttribute("partido", party);
				rd = request.getRequestDispatcher("MostrarPartido.jsp");

			} catch (ClassNotFoundException e) {
				rd = request.getRequestDispatcher("Erro.jp");
			} catch (SQLException e) {
				rd = request.getRequestDispatcher("Erro.jp");
			} catch (NullPointerException e) {
				rd = request.getRequestDispatcher("PartidoNaoEncontrado.jsp");
			} catch (EmptyListException e) {
				rd = request.getRequestDispatcher("PartidoNaoEncontrado.jsp");
				e.printStackTrace();
			}
		} else {
			rd = request.getRequestDispatcher("SqlDetectado.jsp");
		}

		rd.forward(request, response);
	}
}
