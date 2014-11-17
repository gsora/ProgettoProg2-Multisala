package it.unisa.prog2.multisala;

public class SpettacoloTestClass {

	public static void main(String[] args) throws OrarioNonValidoException {
		// Nuovo oggetto Spettacolo
		System.out.println("*** TESTER PER CLASSE SPETTACOLO ***");
		System.out.println("Inizializzato un oggetto ggpas con titolo \"Guida Galattica Per Autostoppisti\", numero sala 42, inizio alle 18:43, durata di 2.6h, nessuna eccezione da gestire");
		Spettacolo ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 42, "18:43", 2.6);
		
		try {
			System.out.println("Inizializzato un oggetto matrix con titolo \"Matrix\", numero sala 2, inizio alle df:44, durata 3h, eccezione prevista a causa dell'orario contenente lettere");
			Spettacolo matrix = new Spettacolo("Matrix", 2, "df:44", 3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
		
		try {
			System.out.println("Inizializzato un oggetto iaw con titolo \"Images and words\", numero sala 26, inizio alle 44:90, durata 3h, eccezione prevista a causa dell'orario non plausibile");
			Spettacolo iaw = new Spettacolo("Images and words", 26, "44:90", 3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
		
		try {
			System.out.println("Inizializzato un oggetto awake con titolo \"Awake\", numero sala 67, inizio alle 44:55, durata 3h, eccezione prevista a causa dell'ora non plausibile");
			Spettacolo awake = new Spettacolo("Awake", 67, "44:55", 3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
		
		try {
			System.out.println("Inizializzato un oggetto moto con titolo \"Mocking birds out\", numero sala 33, inizio alle 21:60, durata 3h, eccezione prevista a causa dell'orario non plausibile");
			Spettacolo moto = new Spettacolo("Mocking birds out", 33, "21:60", 3.0);
		} catch (OrarioNonValidoException onve) {
			System.out.println("Eccezione gestita correttamente");
		}
	}

}
