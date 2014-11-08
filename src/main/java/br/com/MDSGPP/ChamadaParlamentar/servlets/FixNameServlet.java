/**
 * Class: FixNameServlet
 * 
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class solves the problem of incorrect name.
 */

package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FixNameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the only method of the servlet, its suppose to correct the name,
	 * so it can be used to make search on the database.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		/**
		 * This variable received name Deputy and fix possible wrong
		 * */
		String name;
		name = request.getParameter("nome");

		name = name.split("-")[0];

		rd = request.getRequestDispatcher("parlamentarRecebido?nome=" + name);
		rd.forward(request, response);
	}
}
