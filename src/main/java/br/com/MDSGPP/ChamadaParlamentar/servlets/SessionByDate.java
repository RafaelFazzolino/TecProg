package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xml.utils.WrongParserException;

import br.com.MDSGPP.ChamadaParlamentar.control.DayControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.ExceptionSqlInjection;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Day;

public class SessionByDate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is to create a link between the view and the control of
	 * sessoePorDia.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		String date = request.getParameter("datas");
		if (ExceptionSqlInjection.testeSqlInjection(date)) {
			try {
				new DayControl();
				Day day = DayControl.passarData(date);

				try {
					day.getListSessions().size();
				} catch (NullPointerException e1) {
					try {
						throw new Exception();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				request.setAttribute("dia", day);
				rd = request.getRequestDispatcher("/MostrarDia.jsp");

			} catch (ClassNotFoundException e) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (SQLException e) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (WrongParserException e) {
				rd = request.getRequestDispatcher("/FormatoErrado.jsp");
			} catch (EmptyListException e) {
				rd = request.getRequestDispatcher("/DataNaoEncontrada.jsp");
			}
		} else {
			rd = request.getRequestDispatcher("SqlDetectado.jsp");
		}

		rd.forward(request, response);

	}
}
