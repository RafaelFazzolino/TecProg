package br.com.MDSGPP.ChamadaParlamentar.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.MDSGPP.ChamadaParlamentar.control.SessoesEReunioesControl;
import br.com.MDSGPP.ChamadaParlamentar.dao.SessoesEReunioesDao;
import br.com.MDSGPP.ChamadaParlamentar.model.SessoesEReunioes;

public class SessaoPorNomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**
	 * This method is the only method of the servlet, it's responsible to get
	 * the session asked for the user and send the request to the right jsp
	 * page.
	 */

	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		SessoesEReunioes session = new SessoesEReunioes();

		String description = request.getParameter("descricao");
		try {
			int page = 1;
			int deputiesForPage = 20;
			
			if( request.getParameter("pagina") != null ) {
				page = Integer.parseInt(request.getParameter("pagina"));
			}
			session = SessoesEReunioesControl.passarSessao(description);
			
			if( session.getDeputadosPresentes().size() != 0 ) {
								
				int numeroDeputados = session.getDeputadosPresentes().size();
				int noDePaginas = ((int) Math.ceil(numeroDeputados * 1.0 / deputiesForPage))-1;
				
				session.setDeputadosPresentes(SessoesEReunioesControl.
						arrumarListaDeputados(page-1, deputiesForPage, session.getDeputadosPresentes()));
				
				
				request.setAttribute("quantosDeputados", numeroDeputados);
				request.setAttribute("noDePaginas", noDePaginas);
				request.setAttribute("paginaAtual", page);				
				request.setAttribute("sessao", session);
				rd = request.getRequestDispatcher("SessaoPorNome.jsp");
			}
			else {
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
