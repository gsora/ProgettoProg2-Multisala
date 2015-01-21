package it.unisa.prog2.multisala.exceptions;

@SuppressWarnings("serial")
public class DataNonValidaException extends Exception {
	
	public DataNonValidaException() {
		
	}
	
	public DataNonValidaException(String messaggio) {
		super(messaggio);
	}
}
