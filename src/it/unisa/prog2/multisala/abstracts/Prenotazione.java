package it.unisa.prog2.multisala.abstracts;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Prenotazione implements Serializable {
	
	private GregorianCalendar dataPrenotazione;
	private int posto;
	
	/**
	 * Costruttore con parametro della classe
	 * @param posto numero del posto da prenotare
	 */
	public Prenotazione(int posto) {
		dataPrenotazione = new GregorianCalendar();
		this.posto = posto;
	}
	
	/**
	 * Controlla se la prenotazione attuale e` valida
	 * @return boolean con status della prenotazione
	 */
	public boolean prenotazioneNonValida() {
		return dataPrenotazione.after(new GregorianCalendar());
	}

	/**
	 * Restituisci il numero del posto prenotato
	 * @return int con numero posto prenotato
	 */
	public int getPostoPrenotato() {
		return posto;
	}
	
	@Override
	public String toString() {
		return String.valueOf(posto);
	}
}
