/**
 * Class: RankingCompleteServlet
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is going to call the complete ranking with all the
 * deputies.
 */

package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.RankingControl;
import br.com.MDSGPP.ChamadaParlamentar.model.Ranking;

public class RankingComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method is to get the complete ranking.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Ranking ranking;
		ranking = null;

		RequestDispatcher rd;
		rd = null;

		try {
			ranking = RankingControl.nextRankingComplete();
			request.setAttribute("ranking", ranking);
			rd = request.getRequestDispatcher("/RankingCompleto.jsp");

		} catch (ClassNotFoundException e) {
			rd = request.getRequestDispatcher("/Erro.jsp");
			e.printStackTrace();
		} catch (SQLException e) {
			rd = request.getRequestDispatcher("/Erro.jsp");
			e.printStackTrace();
		}

		rd.forward(request, response);
	}
}
