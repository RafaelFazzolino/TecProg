package br.com.MDSGPP.ChamadaParlamentar.exception;


/*esta exception é para verificar se o deputado possui*
 * dados válidos ou não.*
 */
public class ListaVaziaException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * This exception is to verify that the deputy has valid data or not.
	 */

	public ListaVaziaException() {
		super();
	}
}
