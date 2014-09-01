package br.com.MDSGPP.ChamadaParlamentar.model;

public class EstatisticaPartido {

	private Partidos partido;
	private int quantidadeDeSessoes;
	private int sessoesAssistidas;
	private String porcentagem;
	
	/**
	 * Getter of the political party
	 * 
	 * @return an name of {@link partido} containing the name of the political
	 *         party.
	 * 
	 */
	
	public Partidos getPartido() {
		return partido;
	}
	
	/**
	 * Setter of the political party
	 * 
	 * @param partido
	 *            an variable of {@link partido} containing the name of the
	 *            political party.
	 * 
	 */
	
	public void setPartido(Partidos partido) {
		this.partido = partido;
	}
	
	/**
	 * Getter of number of sessions
	 * 
	 * @return an variable of {@link partido} containing the number of the
	 *         sessions.
	 * 
	 */
	
	public int getQuantidadeDeSessoes() {
		return quantidadeDeSessoes;
	}
	
	/**
	 * Setter of number of sessions
	 * 
	 * @param quantidadeDeSessoes
	 *            an variable of {@link sessoes} containing the number of the
	 *            sessions.
	 * 
	 */
	
	public void setQuantidadeDeSessoes(int quantidadeDeSessoes) {
		this.quantidadeDeSessoes = quantidadeDeSessoes;
	}
	
	/**
	 * Getter of assisted sessions
	 * 
	 * @return an variable of {@link partido} containing the number of the
	 *         sessions assisted.
	 * 
	 */
	
	public int getSessoesAssistidas() {
		return sessoesAssistidas;
	}
	
	/**
	 * Setter of assisted sessions
	 * 
	 * @param sessoesAssistidas
	 *            an variable of {@link partido} containing the number of the
	 *            sessions assisted.
	 * 
	 */
	
	public void setSessoesAssistidas(int sessoesAssistidas) {
		this.sessoesAssistidas = sessoesAssistidas;
	}
	
	/**
	 * Getter of percentage
	 * 
	 * @return an variable of {@link partido} containing the percentage of the
	 *         sessions assisted.
	 * 
	 */
	
	public String getPorcentagem() {
		return porcentagem;
	}
	
	/**
	 * Setter of percentage
	 * 
	 * @param porcentagem
	 *            an variable of {@link partido} containing the percentage of
	 *            the sessions assisted.
	 * 
	 */
	
	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}	
}
