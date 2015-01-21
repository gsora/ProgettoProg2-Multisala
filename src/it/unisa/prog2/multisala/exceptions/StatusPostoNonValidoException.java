package it.unisa.prog2.multisala.exceptions;

@SuppressWarnings("serial")
public class StatusPostoNonValidoException extends Exception {
	
	public StatusPostoNonValidoException() {
		
	}
	
	public StatusPostoNonValidoException(String message) {
		super(message);
	}

}
