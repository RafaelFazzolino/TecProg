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
	 * @return statistical what is the statistical of the political party.
	 * @throws ClassNotFoundException case the class is not found.
	 * @throws SQLException case an error occurs with dataBase.
	 * @throws ListaVaziaException is case an error occurs with the list.
	 */
	
	public static EstatisticaPartido gerarEstatisticaPartido(String nome) 
			throws ClassNotFoundException, SQLException, ListaVaziaException {
		EstatisticaPartido statistical = new EstatisticaPartido();
		
		Partidos partido = PartidoControl.passarPartidoComDadosCompletos(nome);
		statistical.setPartido(partido);
		
		int numberOfSessions = 0;
		int numberOfSessionsAttended = 0;
		for(int i = 0; i < partido.getEstatisticaDosDeputados().size(); i++) {
			numberOfSessions += Integer.parseInt(partido.getEstatisticaDosDeputados().get(i).getTotalSessao());
			numberOfSessionsAttended += Integer.parseInt(partido.getEstatisticaDosDeputados().get(i).getNumeroSessao());
		}
		
		double percentage = (((double)(numberOfSessionsAttended))/((double)numberOfSessions))*100.0;
		
		String porcentagemAPassar = formatarNumeroDouble(percentage);
		
		statistical.setPorcentagem(porcentagemAPassar);
		statistical.setQuantidadeDeSessoes(numberOfSessions);
		statistical.setSessoesAssistidas(numberOfSessionsAttended);
		return statistical;	
	}
	
	/**
	 * this method is only to format a number on the pattern 0.00.
	 * 
	 * @param numero
	 *            Double, number to be formated.
	 * @return returns a string with the formated number.
	 */
	
	public static String formatarNumeroDouble(double number) {
		NumberFormat formatardouble= new DecimalFormat("0.00");    
		
		return formatardouble.format(number);
	}
}
