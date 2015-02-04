package it.unisa.prog2.multisala.exceptions;

@SuppressWarnings("serial")
public class DataNonValidaException extends Exception {
	
	/**
	 * Eccezione generata nel caso in cui la data fornita non è valida
	 */
	
	public DataNonValidaException() {
		
	}
	
	public DataNonValidaException(String messaggio) {
		super(messaggio);
	}
}
