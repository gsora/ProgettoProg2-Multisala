package it.unisa.prog2.multisala.exceptions;

public class UtenteNonEsistenteException extends Exception {
	
	/**
	 * Eccezione generata quando l'utente inserito non corrisponde a nessuno degli utenti esistenti
	 */
	
	public UtenteNonEsistenteException() {
		
	}
	
	public UtenteNonEsistenteException(String s) {
		super(s);
	}

}
