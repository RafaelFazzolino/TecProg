/**
 * Class: PartyAutoCompleteServlet
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Class that generates the auto complete party.
 */

package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.PoliticalPartyControl;

public class PartyAutoCompleteServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is to create the auto-complete of parties.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		try {
			ArrayList<ArrayList<String>> listAutoComplete = PoliticalPartyControl
					.passarListaPartidos();

			request.setAttribute("lista", listAutoComplete);

			rd = request.getRequestDispatcher("AcompanharPartido.jsp");

		} catch (ClassNotFoundException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		} catch (SQLException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		}

		rd.forward(request, response);
	}
}
