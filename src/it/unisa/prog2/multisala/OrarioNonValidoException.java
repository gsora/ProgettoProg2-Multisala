package it.unisa.prog2.multisala;

/**
 * Eccezione generata nel caso in cui l'orario dato in input non è valido
 */

@SuppressWarnings("serial")
public class OrarioNonValidoException extends Exception {
	
	public OrarioNonValidoException() {
		super();
	}
	
	public OrarioNonValidoException(String messaggio) {
		super(messaggio);
	}
}
