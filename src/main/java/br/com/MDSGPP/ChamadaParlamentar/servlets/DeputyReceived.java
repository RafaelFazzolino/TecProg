package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.DeputiesControl;
import br.com.MDSGPP.ChamadaParlamentar.control.EstatisticaControl;
import br.com.MDSGPP.ChamadaParlamentar.exception.ExceptionSqlInjection;
import br.com.MDSGPP.ChamadaParlamentar.exception.EmptyListException;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;

public class DeputyReceived extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method is to get the deputy.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name;
		name = request.getParameter("nome");

		Deputies deputy;
		deputy = null;

		RequestDispatcher rd;
		rd = null;

		if (ExceptionSqlInjection.testeSqlInjection(name)) {
			try {
				int page = 1;
				int sessionForPage = 15;

				if (request.getParameter("pagina") != null) {
					page = Integer.parseInt(request.getParameter("pagina"));
					name = name.split("-")[0];
				}
				deputy = DeputiesControl.verificaExistencia(name);

				if (deputy != null) {
					ArrayList<String> list = DeputiesControl.getDeputados();
					Statistic statistic = EstatisticaControl
							.gerarEstatisticas(EstatisticaControl
									.arrumarNomePesquisa(deputy));

					int numberOfSessions = statistic.getLista().size();
					int numberOfPage = ((int) Math.ceil(numberOfSessions * 1.0
							/ sessionForPage)) - 1;

					double presence = Math.ceil(((Double.parseDouble(statistic
							.getNumberSession())) / (Double
							.parseDouble(statistic.getTotalSession()))) * 100);
					String presenceNext;
					presenceNext = Double.toString(presence);

					statistic.setLista(EstatisticaControl.passarListaCerta(
							page - 1, sessionForPage, statistic.getLista()));

					request.setAttribute("presenca", presenceNext);
					request.setAttribute("noDePaginas", numberOfPage);
					request.setAttribute("paginaAtual", page);
					request.setAttribute("lista", list);
					request.setAttribute("estatistica", statistic);
					rd = request
							.getRequestDispatcher("/MostrarEstatisticaDeputado.jsp");

				} else {
					rd = request
							.getRequestDispatcher("/DeputadoNaoEncontrado.jsp");
				}
			} catch (ClassNotFoundException e1) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (SQLException e) {
				rd = request.getRequestDispatcher("/Erro.jsp");
			} catch (IndexOutOfBoundsException e) {
				rd = request.getRequestDispatcher("/DeputadoNaoEncontrado.jsp");
			} catch (NumberFormatException e) {
				rd = request.getRequestDispatcher("/DadosNaoDisponiveis.jsp");
			} catch (EmptyListException e) {
				rd = request.getRequestDispatcher("/DadosNaoDisponiveis.jsp");
			}
		} else {
			rd = request.getRequestDispatcher("SqlDetectado.jsp");
		}

		rd.forward(request, response);
	}
}