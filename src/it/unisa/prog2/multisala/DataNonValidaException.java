package it.unisa.prog2.multisala;

@SuppressWarnings("serial")
public class DataNonValidaException extends Exception {
	
	public DataNonValidaException() {
		
	}
	
	public DataNonValidaException(String messaggio) {
		super(messaggio);
	}
}
