package it.unisa.prog2.multisala;

import java.io.Serializable;

public class Prenotazione implements Serializable{

	private static final long serialVersionUID = 4393668541232102771L;
	private int numeroSalaPrenotazione;
	private int numeroPostoPrenotazione;
	
	public Prenotazione() {}
	
	public Prenotazione(int nsp, int npp) {
		numeroSalaPrenotazione = nsp;
		numeroPostoPrenotazione = npp;
	}
	
	public void setNumeroSalaPrenotazione(int nsp) {
		numeroSalaPrenotazione = nsp;
	}
	
	public void setNumeroPostoPrenotazione(int npp) {
		numeroPostoPrenotazione = npp;
	}
	
	public int getNumeroPostoPrenotazione() {
		return numeroPostoPrenotazione;
	}
	
	public int getNumeroSalaPrenotazione() {
		return numeroSalaPrenotazione;
	}
}
