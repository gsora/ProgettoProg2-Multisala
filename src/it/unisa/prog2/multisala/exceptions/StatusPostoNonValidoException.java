package it.unisa.prog2.multisala.exceptions;

@SuppressWarnings("serial")
public class StatusPostoNonValidoException extends Exception {
	
	/**
	 * Eccezione generata quando viene dato ad un posto uno status non previsto
	 */
	
	public StatusPostoNonValidoException() {
		
	}
	
	public StatusPostoNonValidoException(String message) {
		super(message);
	}

}
