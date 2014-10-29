package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.DayControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;

public class SessionsAndMeetingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is to get the sessions.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		RequestDispatcher rd = null;

		try {
			int page = 1;
			int datesByPage = 10;

			if (request.getParameter("pagina") != null) {
				page = Integer.parseInt(request.getParameter("pagina"));
			}

			ArrayList<Day> day = DayControl.getDias();

			int numberDate = day.size();

			int numberPages = ((int) Math.ceil(numberDate * 1.0 / datesByPage)) - 1;

			ArrayList<Day> dayNext = DayControl.getListaCerta(page - 1,
					datesByPage, day);

			request.setAttribute("noDePaginas", numberPages);
			request.setAttribute("paginaAtual", page);
			request.setAttribute("dias", dayNext);
			request.setAttribute("diasAuto", day);

			rd = request.getRequestDispatcher("AcompanharSessao.jsp");
		} catch (ClassNotFoundException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		} catch (SQLException e) {
			rd = request.getRequestDispatcher("Erro.jsp");
		} catch (DataFormatoErradoException e) {
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
