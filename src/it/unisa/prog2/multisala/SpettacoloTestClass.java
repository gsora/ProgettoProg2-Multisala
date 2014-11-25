package it.unisa.prog2.multisala;

public class SpettacoloTestClass {
	
	// classe di test, soppressione approvata 
	@SuppressWarnings("unused")
	public static void main(String[] args) throws OrarioNonValidoException, DataNonValidaException {
		// Nuovo oggetto Spettacolo
		
		// TODO: gestire i test della data per ogni caso possibile
		
		System.out.println("*** TESTER PER CLASSE SPETTACOLO ***");
		System.out.println("Inizializzato un oggetto ggpas con titolo \"Guida Galattica Per Autostoppisti\", numero sala 42, inizio alle 18:43, data 1/2/2014 durata di 2.6h, nessuna eccezione da gestire");
		Spettacolo ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 42, "18:43", "1/2/2014", 2.6);
		
		try {
			System.out.println("Inizializzato un oggetto matrix con titolo \"Matrix\", numero sala 2, inizio alle df:44, data 1/2/2014, durata 3h, eccezione prevista a causa dell'orario contenente lettere");
			Spettacolo matrix = new Spettacolo("Matrix", 2, "df:44", "1/2/2014",  3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
		
		try {
			System.out.println("Inizializzato un oggetto iaw con titolo \"Images and words\", numero sala 26, inizio alle 44:90, data 1/2/2014 durata 3h, eccezione prevista a causa dell'orario non plausibile");
			Spettacolo iaw = new Spettacolo("Images and words", 26, "44:90", "1/2/2014",  3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
		
		try {
			System.out.println("Inizializzato un oggetto awake con titolo \"Awake\", numero sala 67, inizio alle 44:55, data 1/2/2014 durata 3h, eccezione prevista a causa dell'ora non plausibile");
			Spettacolo awake = new Spettacolo("Awake", 67, "44:55", "1/2/2014", 3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
		
		try {
			System.out.println("Inizializzato un oggetto moto con titolo \"Mocking birds out\", numero sala 33, inizio alle 21:60, data 1/2/2014, durata 3h, eccezione prevista a causa dell'orario non plausibile");
			Spettacolo moto = new Spettacolo("Mocking birds out", 33, "21:60", "1/2/2014", 3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
	}

}
