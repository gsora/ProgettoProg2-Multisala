package it.unisa.prog2.multisala.exceptions;

public class SpettacoloNonTrovatoException extends Exception {

	/**
	 * Eccezione generata quando i dati per prendere un oggetto spettacolo non coincidono con nessuno degli oggetti presenti nella cartella spettacoli
	 */
	
	public SpettacoloNonTrovatoException() {
		
	}
	
	public SpettacoloNonTrovatoException(String message) {
		super(message);
	}

}
