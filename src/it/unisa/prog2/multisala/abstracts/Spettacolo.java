package it.unisa.prog2.multisala.abstracts;

import it.unisa.prog2.multisala.exceptions.DataNonValidaException;
import it.unisa.prog2.multisala.exceptions.OrarioNonValidoException;

import java.io.Serializable;
import java.util.Calendar;

// TODO: ordinare i getter e i setter 

@SuppressWarnings("serial")
public class Spettacolo implements Cloneable, Serializable {
	
	private String titoloSpettacolo;
	private int numeroSala;
	
	private String orarioDiInizio;	
	private int orarioOreInteger;
	private int orarioMinutiInteger;
	
	private String dataSpettacolo;
	private int dataGiornoInteger;
	private int dataMeseInteger;
	private int dataAnnoInteger;
	
	private int durata;
	private double sconto;
	private double incasso;
	
	private Sala salaAssegnata;

	/**
	 * Costruttore con parametri preimpostati
	 * 
	 * @param titolo titolo dello spettacolo
	 * @param numSala numero della sala assegnato
	 * @param orarioDI orario di inizio passato in formato HH:MM
	 * @param data data dello spettacolo passata in format GG/MM/AA
	 * @param dur durata dello spettacolo
	 * @throws OrarioNonValidoException 
	 * @throws DataNonValidaException 
	 */
	
	public Spettacolo(String titolo, int numSala, String orarioDI, String data, int dur) throws OrarioNonValidoException, DataNonValidaException {
		titoloSpettacolo = titolo;
		numeroSala = numSala;
		
		orarioDiInizio = orarioDI;
		CheckValOrario(orarioDiInizio);
		
		dataSpettacolo = data;
		CheckData(dataSpettacolo);
		
		durata = dur;
		sconto = 0;
		incasso = 0d;
		salaAssegnata = new Sala(numSala);
	}
	
	/**
	 * Costruttore con sconto
	 * 
	 * @param titolo titolo dello spettacolo
	 * @param numSala numero della sala assegnato
	 * @param orarioDI orario di inizio passato in formato HH:MM
	 * @param data data dello spettacolo passata in format GG/MM/AA
	 * @param dur durata dello spettacolo
	 * @param sc sconto per lo spettacolo
	 * @throws OrarioNonValidoException 
	 * @throws DataNonValidaException 
	 */
	
	public Spettacolo(String titolo, int numSala, String orarioDI, String data, int dur, double sc) throws OrarioNonValidoException, DataNonValidaException {
		titoloSpettacolo = titolo;
		numeroSala = numSala;
		
		orarioDiInizio = orarioDI;
		CheckValOrario(orarioDiInizio);
		
		dataSpettacolo = data;
		CheckData(dataSpettacolo);
		
		durata = dur;
		sconto = sc;
		incasso = 0d;
		salaAssegnata = new Sala(numSala);
	}
	
	/*
	 * Metodi pubblici
	 */
	
	/**
	 * Implementazione dell'interfaccia Cloneable
	 */
	@Override
	public Spettacolo clone() {
		try {
			return (Spettacolo) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ritorna sconto dello spettacolo
	 * @return float contenente sconto dello spettacolo
	 */
	public double getSconto() {
		return sconto;
	}
	
	/**
	 * Restituisce il titolo dello spettacolo
	 * @return titolo dello spettacolo
	 */
	
	public String getTitoloSpettacolo() {
		return titoloSpettacolo;
	}
	
	/**
	 * Restituisce il numero della sala dove viene proiettato il film in questione
	 * @return numero della sala
	 */
	
	public int getNumeroSala() {
		return numeroSala;
	}
	
	/**
	 * Restituisce una stringa contenente l'orario di inizio formattato nella sintassi HH:MM
	 * @return orario di inizio dello spettacolo
	 */
	
	public String getOrarioDiInizio() {
		return orarioDiInizio;
	}
	
	/**
	 * Restituisce una stringa contenente la data dello spettacolo nella sintassi GG/MM/AA
	 * @return data dello spettacolo
	 */
	
	public String getData() {
		return dataSpettacolo;
	}
	
	/**
	 * Restituisce la durata dello spettacolo
	 * @return durata dello spettacolo
	 */
	
	public int getDurata() {
		return durata;
	}
	
	/**
	 * Setta il titolo dello spettacolo all'argomento passato
	 * @param s nome dello spettacolo
	 */
	
	public void setTitoloSpettacolo(String s) {
		titoloSpettacolo = s;
	}
	
	/**
	 * Setta il numero della sala dello spettacolo
	 * @param n numero della sala
	 */
	
	public void setNumeroSala(int n) {
		numeroSala = n;
	}
	
	/**
	 * Setta l'orario di inizio controllando prima la sua validità
	 * @param s orario dello spettacolo
	 * @throws OrarioNonValidoException
	 */
	
	public void setOrarioDiInizio(String s) throws OrarioNonValidoException {
		// controllare sempre prima che il nuovo orario sia corretto
		CheckValOrario(s);
		orarioDiInizio = s;
	}
	
	/**
	 * Setta la data dello spettacolo controllando prima la sua validità
	 * @param s data dello spettacolo
	 * @throws DataNonValidaException
	 */
	
	public void setData(String s) throws DataNonValidaException {
		CheckData(s);
		dataSpettacolo = s;
	}
	
	/**
	 * Restituisce la sala assegnata allo spettacolo corrente
	 * @return restituisce la sala
	 */
	public Sala sala() {
		return salaAssegnata;
	}
	
	/**
	 * Restituisce l'incasso totale dello spettacolo
	 * @return double con incasso totale dello spettacolo
	 */
	public double getIncasso() {
		return incasso;
	}
	
	/**
	 * Aggiorna le statistiche riguardo l'acquisto di uno spettacolo
	 * @param val prezzo del biglietto comprato
	 */
	public void spettacoloComprato(double val) {
		incasso += val;
	}
	
	/*
	 * Metodi privati
	 */
	
	private void CheckData(String s) throws DataNonValidaException {
		if(s.contains("/")) {
			SplitData(s);
		} else {
			throw new DataNonValidaException("la data in input non soddisfa le condizioni: il formato deve essere GG/MM/AA.");
		}
	}
	
	private void SplitData(String s) throws DataNonValidaException {
		String[] dataSplittata = s.split("/");
		try {
			dataGiornoInteger = Integer.parseInt(dataSplittata[0]);
			dataMeseInteger = Integer.parseInt(dataSplittata[1]);
			dataAnnoInteger = Integer.parseInt(dataSplittata[2]);
		} catch (NumberFormatException nfe) {
			throw new DataNonValidaException("la data in input non contiene solo numeri.");
		}
		
		// giorno nonsense
		if(dataGiornoInteger > 31 || dataGiornoInteger < 1) {
			throw new DataNonValidaException("il giorno non ha senso.");
		}
		
		// mese nonsense
		if(dataMeseInteger > 12 || dataMeseInteger < 1) {
			throw new DataNonValidaException("il mese non ha senso.");
		}
		
		// anno nonsense, confronto con anno attuale
		Calendar cal = Calendar.getInstance();
		int annoCorrente = cal.get(Calendar.YEAR);
		if(dataAnnoInteger < annoCorrente) {
			throw new DataNonValidaException("l'anno non ha senso.");
		}
	}
	
	// Controlla che l'orario di inizio dato sia valido secondo le indicazioni date e sensato
	private void CheckValOrario(String s) throws OrarioNonValidoException {
		if(s.contains(":")) {
			SplitOrarioDiInizio(s);
		} else {
			throw new OrarioNonValidoException("l'orario in input non soddisfa le condizioni: il formato deve essere HH:MM");
		}
	}
	
	// Splitta la stringa data come argomento in due interi, rispettivamente ore e minuti
	private void SplitOrarioDiInizio(String s) throws OrarioNonValidoException {
		String[] orarioSplittato = s.split(":");
		try {
			orarioOreInteger = Integer.parseInt(orarioSplittato[0]);
			orarioMinutiInteger = Integer.parseInt(orarioSplittato[1]);
		} catch (NumberFormatException nfe) {
			throw new OrarioNonValidoException("l'orario in input non contiene solo numeri.");
		}
		
		if((orarioOreInteger < 0 || orarioOreInteger > 24) || (orarioMinutiInteger < 0 || orarioMinutiInteger > 59)) {
			throw new OrarioNonValidoException("l'orario non ha senso.");
		}
	}
}
	