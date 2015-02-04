package it.unisa.prog2.multisala.exceptions;

@SuppressWarnings("serial")
public class PostiLiberiEsauritiException extends Exception {
	
	/**
	 * Eccezione che viene generata quando non ci sono più posti liberi in una sala
	 */
	public PostiLiberiEsauritiException() {
		
	}
	
	public PostiLiberiEsauritiException(String message) {
		super(message);
	}

}
