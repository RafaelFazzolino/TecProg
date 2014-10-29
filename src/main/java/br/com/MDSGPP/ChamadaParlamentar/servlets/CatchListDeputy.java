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

public class CatchListDeputy extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the only method on the servlet, its responsibility is to retrieve
	 * all the deputies name and sends it to the jsp page on an
	 * {@link ArrayList} of {@link String}, so there can be an auto complete on
	 * it.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			ArrayList<String> list = DeputiesControl.getDeputados();
			request.setAttribute("lista", list);
			rd = request.getRequestDispatcher("/AcompanharParlamentar.jsp");
		} catch (ClassNotFoundException e) {
			rd = request.getRequestDispatcher("/Erro.jsp");
		} catch (SQLException e) {
			rd = request.getRequestDispatcher("/Erro.jsp");
		}

		rd.forward(request, response);
	}
}
