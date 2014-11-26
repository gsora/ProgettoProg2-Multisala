package it.unisa.prog2.multisala;

public class Sala {
	
	// spettacolo assegnato alla sala corrente
	private Spettacolo spettacoloAssegnato;
	
	// numero massimo di posti in una sala
	private static int MAX_POSTI = 50;
	
	private Posto[] listaPosti;
	
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
		return postiLiberi;
	}
	
	/**
	 * Numero di posti assegnati nella sala;
	 * @return numero di posti assegnati
	 */
	public int getNumeroPostiAssegnati() {
		return postiAssegnati;
	}
	
	/**
	 * Numero di posti prenotati nella sala
	 * @return numero di posti prenotati
	 */
	
	public int getNumeroPostiPrenotati() {
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
	
	// METODI PRIVATI //
	
	private void inizializzaListaPosti(Posto[] p) {
		p = new Posto[MAX_POSTI];
		for(int i = 0; i  < MAX_POSTI-1; i++) {
			p[i] = new Posto(i+1, 0);
		}
	}
}
