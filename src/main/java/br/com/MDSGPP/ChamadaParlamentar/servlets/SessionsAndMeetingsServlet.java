/**
 * Class: SessionsAndMeetingsServlet
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is the servlet that is going call for all days and return then
 * on a {@link ArrayList} of {@link Dia} to the jsp page or it is going to
 * trigger the error page.
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

import org.apache.xml.utils.WrongParserException;

import br.com.MDSGPP.ChamadaParlamentar.control.DayControl;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;

public class SessionsAndMeetingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is to get the sessions.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		RequestDispatcher rd;
		rd = null;

		try {
			int page = 1;
			int datesByPage = 10;

			if (request.getParameter("pagina") != null) {
				page = Integer.parseInt(request.getParameter("pagina"));
			}

			ArrayList<Day> day;/* This is an Array that contains all days. */
			day = DayControl.getDias();

			int numberDate;/* This variable contains the number of dates. */
			numberDate = day.size();

			int numberPages;/* This variable contains the number of pages. */
			numberPages = ((int) Math.ceil(numberDate * 1.0 / datesByPage)) - 1;

			ArrayList<Day> dayNext;/* Variable that contains the next day. */
			dayNext = DayControl.getListaCerta(page - 1, datesByPage, day);

			request.setAttribute("noDePaginas", numberPages);
			request.setAttribute("paginaAtual", page);
			request.setAttribute("dias", dayNext);
			request.setAttribute("diasAuto", day);

			rd = request.getRequestDispatcher("AcompanharSessao.jsp");
		} catch (ClassNotFoundException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		} catch (SQLException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		} catch (WrongParserException e) {
			rd = request.getRequestDispatcher("FormatoErrado.jsp");
		}

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
