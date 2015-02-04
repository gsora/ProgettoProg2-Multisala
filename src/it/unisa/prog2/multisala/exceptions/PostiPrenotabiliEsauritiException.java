package it.unisa.prog2.multisala.exceptions;

@SuppressWarnings("serial")
public class PostiPrenotabiliEsauritiException extends Exception {
	
	/**
	 * Eccezione Generata quando non sono più disponibili posti da prenotare in una sala
	 */
	
	public PostiPrenotabiliEsauritiException() {
		
	}
	
	public PostiPrenotabiliEsauritiException(String message) {
		super(message);
	}

}
