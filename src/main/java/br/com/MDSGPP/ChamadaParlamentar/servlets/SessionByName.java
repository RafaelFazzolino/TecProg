/**
 * Class: SessionsByNameServlet.
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is the servlet that is going call for a specific session that
 * matches with the name given, and then redirects to the right jsp page.
 */

package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.SessionsAndMeetingsControl;
import br.com.MDSGPP.ChamadaParlamentar.model.SessionAndMeetings;

public class SessionByName extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is the only method of the servlet, it's responsible to get
	 * the session asked for the user and send the request to the right jsp
	 * page.
	 */

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		SessionAndMeetings session;
		session = new SessionAndMeetings();

		String description;
		description = request.getParameter("descricao");
		try {
			int page = 1;
			int deputiesForPage = 20;

			if (request.getParameter("pagina") != null) {
				page = Integer.parseInt(request.getParameter("pagina"));
			}
			session = SessionsAndMeetingsControl.nextSession(description);

			if (session.getDeputiesPresence().size() != 0) {

				int numberDeputies;
				numberDeputies = session.getDeputiesPresence().size();

				int numberPages;
				numberPages = ((int) Math.ceil(numberDeputies * 1.0
						/ deputiesForPage)) - 1;

				session.setDeputiesPresence(SessionsAndMeetingsControl
						.organizeListDeputy(page - 1, deputiesForPage,
								session.getDeputiesPresence()));

				request.setAttribute("quantosDeputados", numberDeputies);
				request.setAttribute("noDePaginas", numberPages);
				request.setAttribute("paginaAtual", page);
				request.setAttribute("sessao", session);
				rd = request.getRequestDispatcher("SessaoPorNome.jsp");
			} else {
				rd = request.getRequestDispatcher("SessaoNaoEncontrada.jsp");
			}
		} catch (ClassNotFoundException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		} catch (SQLException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		}

		rd.forward(request, response);

	}
}
