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
			throw new OrarioNonValidoException("l'orario in input non contiene numeri.");
		}
		
		if((orarioOreInteger < 0 || orarioOreInteger > 24) || (orarioMinutiInteger < 0 || orarioMinutiInteger > 60)) {
			throw new OrarioNonValidoException("l'orario non ha senso.r");
		}
	}
}
