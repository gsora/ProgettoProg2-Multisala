package it.unisa.prog2.multisala.abstracts;

import it.unisa.prog2.multisala.exceptions.StatusPostoNonValidoException;

import java.io.Serializable;

public class Posto implements Serializable{

	// numero identificativo del posto
	private int numeroPosto;
	
	// 0 = libero; 1 = assegnato; 2 = prenotato
	private int status;
	
	// variabili globali su status posti
	public static int LIBERO = 0;
	public static int ASSEGNATO = 1;
	public static int PRENOTATO = 2;
	
	/**
	 * Costruttore della classe Posto
	 * @param np numero del posto assegnato
	 */
	
	public Posto(int np) {
		numeroPosto = np;
		status = 0;
	}
	
	public Posto(int np, int ns) {
		numeroPosto = np;
		
		try {
			checkStatus(ns);
			status = ns;
		} catch (StatusPostoNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ritorna numero del posto
	 * @return numero posto
	 */
	
	public int getNumeroPosto() {
		return numeroPosto;
	}
	
	/**
	 * Ritorna lo status del posto.
	 * 0 = libero
	 * 1 = assegnato
	 * 2 = prenotato
	 * @return status del posto
	 */
	
	public int getStatus() {
		return status;
	}
	
	/**
	 * Setta lo status del posto
	 * @param ns status del posto
	 */
	
	public void setStatus(int ns) {
		try {
			checkStatus(ns);
			status = ns;
		} catch (StatusPostoNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setta il numero del posto
	 * @param np numero del posto
	 */
	
	public void setNumeroPosto(int np) {
		numeroPosto = np;
	}
	
	
	// controlla che lo status passato sia valido
	private void checkStatus(int ns) throws StatusPostoNonValidoException {
		if(ns < 0 || ns > 2) {
			throw new StatusPostoNonValidoException("lo status deve essere compreso tra 0 e 2.");
		}
	}
	
	
}
