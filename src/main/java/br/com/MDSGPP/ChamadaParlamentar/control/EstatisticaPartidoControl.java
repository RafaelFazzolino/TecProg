package br.com.MDSGPP.ChamadaParlamentar.control;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import br.com.MDSGPP.ChamadaParlamentar.exception.ListaVaziaException;
import br.com.MDSGPP.ChamadaParlamentar.model.EstatisticaPartido;
import br.com.MDSGPP.ChamadaParlamentar.model.Partidos;

public final class EstatisticaPartidoControl {
	
	/**
	 * This method is to generate the statistics of one political party.
	 * 
	 * @param nome
	 *            String, name of the political party.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ListaVaziaException
	 */
	
	public static EstatisticaPartido gerarEstatisticaPartido(String nome) 
			throws ClassNotFoundException, SQLException, ListaVaziaException {
		EstatisticaPartido estatistica = new EstatisticaPartido();
		
		Partidos partido = PartidoControl.passarPartidoComDadosCompletos(nome);
		estatistica.setPartido(partido);
		
		int totalDeSessoes = 0;
		int totalDeSessoesAssistidas = 0;
		for(int i = 0; i<partido.getEstatisticaDosDeputados().size(); i++) {
			totalDeSessoes += Integer.parseInt(partido.getEstatisticaDosDeputados().get(i).getTotalSessao());
			totalDeSessoesAssistidas += Integer.parseInt(partido.getEstatisticaDosDeputados().get(i).getNumeroSessao());
		}
		
		double porcentagem = (((double)(totalDeSessoesAssistidas))/((double)totalDeSessoes))*100.0;
		
		String porcentagemAPassar = formatarNumeroDouble(porcentagem);
		
		estatistica.setPorcentagem(porcentagemAPassar);
		estatistica.setQuantidadeDeSessoes(totalDeSessoes);
		estatistica.setSessoesAssistidas(totalDeSessoesAssistidas);
		return estatistica;	
	}
	
	/**
	 * this method is only to format a number on the pattern 0.00.
	 * 
	 * @param numero
	 *            Double, number to be formated.
	 * @return returns a string with the formated number.
	 */
	
	public static String formatarNumeroDouble(double numero) {
		NumberFormat formatardouble= new DecimalFormat("0.00");    
		
		return formatardouble.format(numero);
	}
}
