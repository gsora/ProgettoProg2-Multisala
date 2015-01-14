package it.unisa.prog2.multisala;

import java.io.Serializable;

public class Sala implements Serializable {
	// numero massimo di posti in una sala
	private static int MAX_POSTI = 50;
	
	private Posto[] listaPosti = new Posto[MAX_POSTI];
	
	private int numeroSala;
	private int postiLiberi;
	private int postiAssegnati;
	private int postiPrenotati;
	private int postiTotali;
		
	/**
	 * Costruttore della classe sala che non accetta Spettacolo come argomento
	 * @param ns numero della sala
	 */
	public Sala(int ns) {
		postiTotali = MAX_POSTI;
		numeroSala = ns;
		inizializzaListaPosti(listaPosti);
		ricaricaStatusPosti(listaPosti);
	}	
		
	/**
	 * Numero di posti liberi nella sala
	 * @return numero di posti liberi
	 */
	
	public int getNumeroPostiLiberi() {
		ricaricaStatusPosti(listaPosti);
		return postiLiberi;
	}
	
	/**
	 * Numero di posti assegnati nella sala;
	 * @return numero di posti assegnati
	 */
	public int getNumeroPostiAssegnati() {
		ricaricaStatusPosti(listaPosti);
		return postiAssegnati;
	}
	
	/**
	 * Numero di posti prenotati nella sala
	 * @return numero di posti prenotati
	 */
	
	public int getNumeroPostiPrenotati() {
		ricaricaStatusPosti(listaPosti);
		return postiPrenotati;
	}
	
	/**
	 * Numero di posti totali nella sala
	 * @return numero posti totali
	 */
	
	public int getNumeroPostiTotali() {
		return postiTotali;
	}
	
	/**
	 * Numero assegnato alla sala
	 * @return numero della sala
	 */
	
	public int getNumeroSala() {
		return numeroSala;
	}
	
	
	// TODO: scrivere javadoc 
	// TODO: controllare le funzioni di prenotazione, incremento e acquisto biglietto perché non scrivono nell'oggetto Posto
	public void prenotaPosto() throws PostiPrenotabiliEsauritiException {
		if(postiLiberi <= 0) {
			throw new PostiPrenotabiliEsauritiException("posti prenotabili nella sala esauriti.");
		}
	}
	
	public void incrementaPostiLiberi() {
		postiLiberi++;
		postiAssegnati--;
	}
	
	public void compraBiglietto() throws PostiLiberiEsauritiException {
		if(postiLiberi <= 0) {
			throw new PostiLiberiEsauritiException("posti liberi nella sala esauriti.");
		}
		listaPosti[postiAssegnati].setStatus(1);
		ricaricaStatusPosti(listaPosti);
	}
	
	public void compraBiglietto(int postoNumero) throws PostiLiberiEsauritiException {
		if(postiLiberi <= 0) {
			throw new PostiLiberiEsauritiException("posti liberi nella sala esauriti.");
		}
		listaPosti[postoNumero-1].setStatus(1);
		ricaricaStatusPosti(listaPosti);
	}
	
	public String statoPostiSala() {
		return "Posti liberi: " + getNumeroPostiLiberi() + "|" + "Posti assegnati: " + getNumeroPostiAssegnati() + "|" + "Posti prenotati: " + getNumeroPostiPrenotati();
	}
	
	
	// TODO: aggiugnere controllo overflow posti
	public int getStatoPostoSingolo(int a) {
		return listaPosti[a].getStatus();
	}
	
	// METODI PRIVATI //
	
	private void inizializzaListaPosti(Posto[] p) {
		for(int i = 0; i  < MAX_POSTI; i++) {
			p[i] = new Posto(i+1, 0);
		}
	}
	
	private void ricaricaStatusPosti(Posto[] p) {
		
		postiLiberi = 0;
		postiAssegnati = 0;
		postiPrenotati = 0;
		
		for(Posto ps : p) {
			int valore = ps.getStatus();
			/*if(!(postiLiberi >= MAX_POSTI))
				postiLiberi++;*/
			if(valore == 0) {
				postiLiberi++;
			}
			
			if(valore == 1) {
				postiAssegnati++;
			}
			
			if(valore == 2) {
				postiPrenotati++;
			}
		}
	}
}
