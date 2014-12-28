package it.unisa.prog2.multisala;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Prenotazione implements Serializable{

	private static final long serialVersionUID = 4393668541232102771L;
	private int numeroSalaPrenotazione;
	private int numeroPostoPrenotazione;
	private LocalDateTime dataCreazione;
	private Boolean prenotazioneValida;
	
	public Prenotazione() {
		dataCreazione = LocalDateTime.now();
		prenotazioneValida = true;
	}
	
	public Prenotazione(int nsp, int npp) {
		numeroSalaPrenotazione = nsp;
		numeroPostoPrenotazione = npp;
		dataCreazione = LocalDateTime.now();
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
	
	public String getDataCreazione() {
		return dataCreazione.getDayOfMonth() + "/" + dataCreazione.getMonthValue() + "/" + dataCreazione.getYear();
	}
	
	public Boolean statusValPrenotazione() {
		return prenotazioneValida;
	}
	
	public void settaStatusValPrenotazione(Boolean b) {
		prenotazioneValida = b;
	}
	
	public static Boolean prenotazioneScadua(Prenotazione p) {
		LocalDateTime dataAttuale = LocalDateTime.now();
		System.out.println(dataAttuale.toString());
		String[] dataSplittata = p.getDataCreazione().split("/");
		if((Integer.parseInt(dataSplittata[1]) == dataAttuale.getMonthValue()) &&
			(Integer.parseInt(dataSplittata[2]) == dataAttuale.getYear()) &&
			(Integer.parseInt(dataSplittata[0]) >= dataAttuale.getDayOfMonth())) {
			return false;
		} else {
			p.settaStatusValPrenotazione(false);
			return true;
		}
	}
	
	public static void main(String[] args) {
		Prenotazione a = new Prenotazione();
		System.out.println(Prenotazione.prenotazioneScadua(a));
	}
}
