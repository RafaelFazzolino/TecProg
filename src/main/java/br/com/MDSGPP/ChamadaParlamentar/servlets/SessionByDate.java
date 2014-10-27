package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.DayControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.exception.DataNaoEncontradaException;
import br.com.MDSGPP.ChamadaParlamentar.exception.ExceptionSqlInjection;
import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;

public class SessionByDate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * This method is to create a link between the view and the control of sessoePorDia.
	 */
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;


		String date = request.getParameter("datas");
		if( ExceptionSqlInjection.testeSqlInjection(date) ) {
			try {
				new DayControl();
				Day day = DayControl.passarData(date);

				try {
					day.getListSessions().size();
				} catch (NullPointerException e1) {
					throw new DataNaoEncontradaException();
				}


				request.setAttribute("dia", day);
				rd = request.getRequestDispatcher("/MostrarDia.jsp");


			} catch (ClassNotFoundException e) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (SQLException e) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (DataFormatoErradoException e) {
				rd = request.getRequestDispatcher("/FormatoErrado.jsp");
			} catch (DataNaoEncontradaException e) {
				rd = request.getRequestDispatcher("/DataNaoEncontrada.jsp");
			} catch (ListaVaziaException e) {
				rd = request.getRequestDispatcher("/DataNaoEncontrada.jsp");
			}
		}
		else {
			rd = request.getRequestDispatcher("SqlDetectado.jsp");
		}

		rd.forward(request, response);

	}
}