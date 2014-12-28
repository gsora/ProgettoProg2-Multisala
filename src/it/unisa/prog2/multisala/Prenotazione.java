package it.unisa.prog2.multisala;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Prenotazione implements Serializable{

	private static final long serialVersionUID = 4393668541232102771L;
	private int numeroSalaPrenotazione;
	private int numeroPostoPrenotazione;
	private LocalDateTime dataCreazione;
	private LocalTime oraCreazione;
	private Boolean prenotazioneValida;
	
	public Prenotazione() {
		dataCreazione = LocalDateTime.now();
		oraCreazione = LocalTime.now();
		prenotazioneValida = true;
	}
	
	public Prenotazione(int nsp, int npp) {
		numeroSalaPrenotazione = nsp;
		numeroPostoPrenotazione = npp;
		dataCreazione = LocalDateTime.now();
		oraCreazione = LocalTime.now();
		prenotazioneValida = true;
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
	
	public Boolean statusValPrenotazione() {
		return prenotazioneValida;
	}
	
	public LocalTime getOraCreazione() {
		return oraCreazione;
	}
	
	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}
	
	public void settaStatusValPrenotazione(Boolean b) {
		prenotazioneValida = b;
	}
	
	public static Boolean prenotazioneScadua(Prenotazione p) {
		LocalDateTime dataAttuale = LocalDateTime.now();
		LocalTime oraAttuale = LocalTime.now();
		
		int oreAttuali = (dataAttuale.getDayOfYear() * 24) - (24 - oraAttuale.getHour());
		int oreCreazione = (p.getDataCreazione().getDayOfYear() * 24) - (24 - p.getOraCreazione().getHour());
		
		return true;
	}
	
	public static void main(String[] args) {
		Prenotazione a = new Prenotazione();
		Prenotazione.prenotazioneScadua(a);
	}
}
