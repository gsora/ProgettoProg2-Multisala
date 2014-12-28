package it.unisa.prog2.multisala;

import java.io.Serializable;
import java.time.LocalTime;

public class Prenotazione implements Serializable{

	private static final long serialVersionUID = 4393668541232102771L;
	private int numeroSalaPrenotazione;
	private int numeroPostoPrenotazione;
	@SuppressWarnings("unused")
	private LocalTime dataCreazione;
	
	public Prenotazione() {
		dataCreazione = LocalTime.now();
	}
	
	public Prenotazione(int nsp, int npp) {
		numeroSalaPrenotazione = nsp;
		numeroPostoPrenotazione = npp;
		dataCreazione = LocalTime.now();
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
