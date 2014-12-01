package it.unisa.prog2.multisala;

public class Sala {
	
	// spettacolo assegnato alla sala corrente
	private Spettacolo spettacoloAssegnato;
	
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
		spettacoloAssegnato = new Spettacolo();	
		inizializzaListaPosti(listaPosti);
		ricaricaStatusPosti(listaPosti);
	}
	
	/**
	 * Costruttore della classe Sala che accetta un oggetto Spettacolo come argomento
	 * @param s oggetto Spettacolo 
	 */
	
	public Sala(int ns, Spettacolo s) {
		postiTotali = MAX_POSTI;
		numeroSala = ns;
		spettacoloAssegnato = s.clone();
		inizializzaListaPosti(listaPosti);
		ricaricaStatusPosti(listaPosti);
	}
	
	/**
	 * Getter per lo Spettacolo della Sala
	 * @return oggetto Spettacolo
	 */
	
	public Spettacolo spettacolo() {
		return spettacoloAssegnato;
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
		postiLiberi--;
		postiPrenotati++;
	}
	
	public void incrementaPostiLiberi() {
		postiLiberi++;
		postiAssegnati--;
	}
	
	public void compraBiglietto() throws PostiLiberiEsauritiException {
		if(postiLiberi <= 0) {
			throw new PostiLiberiEsauritiException("posti liberi nella sala esauriti.");
		}
		postiLiberi--;
		postiAssegnati++;
	}
	
	public String statoPostiSala() {
		return "Posti liberi: " + getNumeroPostiLiberi() + "|" + "Posti assegnati: " + getNumeroPostiAssegnati() + "|" + "Posti prenotati: " + getNumeroPostiPrenotati();
	}
	
	// METODI PRIVATI //
	
	private void inizializzaListaPosti(Posto[] p) {
		for(int i = 0; i  < MAX_POSTI; i++) {
			p[i] = new Posto(i+1, 0);
		}
	}
	
	private void ricaricaStatusPosti(Posto[] p) {
		for(Posto ps : p) {
			int valore = ps.getStatus();
			if(valore == 0) {
				if(postiLiberi >= 50) {
					;
				} else {
					postiLiberi++;
				}
			} else if(valore == 1) {
				if(postiAssegnati >= 50) {
					;
				} else {
					postiAssegnati++;
				}
			} else if(valore == 2) {
				if(postiPrenotati >= 50) {
					;
				} else {
					postiPrenotati++;
				}
			}
		}
		
		postiLiberi = postiLiberi - postiAssegnati;
	}
}
