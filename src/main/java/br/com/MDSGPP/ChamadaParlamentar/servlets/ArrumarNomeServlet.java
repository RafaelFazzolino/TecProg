package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArrumarNomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * This is the only method of the servlet, its suppose to correct the name,
	 * so it can be used to make search on the database.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String name = request.getParameter("nome");
		name = name.split("-")[0];
		
		rd = request.getRequestDispatcher("parlamentarRecebido?nome="+ name);
		rd.forward(request, response);
	}
}
