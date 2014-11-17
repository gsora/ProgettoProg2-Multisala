package it.unisa.prog2.multisala;


public class Spettacolo {
	
	private String titoloSpettacolo;
	private int numeroSala;
	private String orarioDiInizio;
	private int orarioOreInteger;
	private int orarioMinutiInteger;
	private double durata;
	
	/**
	 * Costruttore senza parametri
	 */
	
	public Spettacolo() {
		titoloSpettacolo = null;
		numeroSala = 0;
		
		// TODO: metodo per splittare orario di inizio da stringa verso due int ora e minuti?
		orarioDiInizio = null;
		durata = 0;
		orarioOreInteger = 0;
		orarioMinutiInteger = 0;
	}
	
	/**
	 * Costruttore con parametri preimpostati
	 * 
	 * @param titolo titolo dello spettacolo
	 * @param numSala numero della sala assegnato
	 * @param orarioDI orario di inizio passato in formato HH:MM
	 * @param dur durata dello spettacolo
	 * @throws OrarioNonValidoException 
	 */
	
	public Spettacolo(String titolo, int numSala, String orarioDI, Double dur) throws OrarioNonValidoException {
		titoloSpettacolo = titolo;
		numeroSala = numSala;
		// TODO: metodo per splittare orario di inizio da stringa verso due int ora e minuti?
		orarioDiInizio = orarioDI;
		CheckValOrario(orarioDiInizio);
		durata = dur;
		orarioOreInteger = 0;
		orarioMinutiInteger = 0;
	}
	
	/*
	 * Metodi pubblici
	 */
	
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
	 * Restituisce la durata dello spettacolo
	 * @return durata dello spettacolo
	 */
	
	public double getDurata() {
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
	
	/*
	 * Metodi privati
	 */
	
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
