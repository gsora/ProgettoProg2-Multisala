package it.unisa.prog2.multisala;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class DBManager {
	
	// numero delle sale da gestire
	private static final int NUM_SALE = 4;
	
	// path dove sono contenuti i file dati
	private static String cartellaDati;
	
	// prezzo dei film
	private float prezzoFilm;
	
	// arraylist con la lista dei posti prenotati
	// TODO: controllare il funzionamento del meccanismo, probabilmente è più comodo crearne uno al volo all'interno del metodo
	private ArrayList<Prenotazione> listaPostiPrenotati;
	
	/**
	 * Costruttore di DBManager
	 * 
	 * Automaticamente crea le cartelle nella path appropriata in base al sistema operativo su cui è in esecuzione.
	 * Basta istanziarlo per accedere al database e ai suoi metodi di scrittura.
	 * 
	 */
	public DBManager() {
		cartellaDati = new String();
		
		// il prezzo e` definito?
		// se si carica altrimenti inizializzalo a zero
		if(!prezzoDefinito()) {
			prezzoFilm = 0f;
		}
		
		// questa stringa contiene il nome del sistema operativo in utilizzo sulla macchina attuale
		String OS = System.getProperty("os.name").toUpperCase();
		
		if(OS.contains("WIN")) {
			// macchina Windows, si usa AppData
			cartellaDati = (System.getenv("AppData") + "/MultisalaPancakes");
		} else if(OS.contains("MAC")) {
			// macchina OS X, si usa ~/Library/Application Support
			cartellaDati = (System.getProperty("user.home").toString() + "/Library/Application Support/MultisalaPancakes");
		} else if(OS.contains("LINUX")) {
			// macchina Linux, si usa ~/.config/
			cartellaDati = (System.getProperty("user.home").toString() + "/.config/MultisalaPancakes");
		}
		
		// controllo: se cartellaDati esiste carica i nomi delle sale, altrimenti crea la struttura della directory
		if(controllaEsistenzaPath()) {
			;
		} else {
			creaStrutturaDirectory();
		}
	}
	
	// controlla se esiste già un vecchio database
	private Boolean controllaEsistenzaPath() {
		File test = new File(cartellaDati);
		return test.exists();
	}
	
	// crea la struttura delle directory che serviranno a memorizzare prenotazioni, utenti e film nelle sale
	private void creaStrutturaDirectory() {
		
		/*
		 * STRUTTURA DIRECTORY
		 * 
		 * 	DataDir->
		 * 		MultisalaPancakes->
		 * 			Spettacoli ->
		 * 				nomeSpettacolo-data-ora-numerosala.pks
		 * 			Prezzo.pks
		 * 			StatisticheVendita.pks
		 * 			Utenti->
		 * 				utenteX.pks
		 */
		
		// crea la cartella dei dati nella path definita prima
		File d = new File(cartellaDati);
		d.mkdir();
		
		// crea la cartella dove verranno contenuti gli utenti e le loro prenotazioni
		File du = new File(cartellaDati + "/Utenti");
		du.mkdir();
		
		// crea la cartella per gli spettacoli
		File sp = new File(cartellaDati + "/Spettacoli");
		sp.mkdir();
		
		// serializza il prezzo
		try {
			FileOutputStream prezzoFile = new FileOutputStream(new File(cartellaDati + "/Prezzo.pks"));
			ObjectOutputStream outFile = new ObjectOutputStream(prezzoFile);
			outFile.writeObject(prezzoFilm);
			outFile.close();
			prezzoFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// il prezzo e` mai stato definito?
	private Boolean prezzoDefinito() {
		File prezzo = new File(cartellaDati + "/Prezzo.pks");
		return prezzo.exists();
	}
	
	/**
	 * Salva uno spettacolo nel database
	 * @param s oggetto spettacolo da aggiungere al database
	 */
	
	public float getPrezzoFilm() {
		return prezzoFilm;
	}
	
	public void salvaSpettacolo(Spettacolo s) {
		String filename = s.getTitoloSpettacolo().replace(" ", "") + "-" + s.getData().replace("/", "") + "-" + s.getOrarioDiInizio().replace(":", "") + "-" + Integer.toString(s.getNumeroSala()) + ".pks";
		String pathSpettacolo = cartellaDati + "/Spettacoli/" + filename;
		File fA = new File(pathSpettacolo);
		
		try {
			// serializza l'argomento
			FileOutputStream fos = new FileOutputStream(fA);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(s);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// LA RIMOZIONE DI UNO SPETTACOLO è DA RIVEDERE, PASSARE UN OGGETTO INTERO DA RIMUOVERE NON è ELEGANTE
//	public void rimuoviSpettacolo(Spettacolo s) {
//		String pathSpettacolo = cartellaDati + "/" + nomiFileSale[s.getNumeroSala() - 1];
//		File fA = new File(pathSpettacolo);
//		
//		try {
//			FileInputStream fis = new FileInputStream(fA);
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			ArrayList<Spettacolo> als = (ArrayList<Spettacolo>) ois.readObject();
//			als.remove(s);
//			ois.close();
//			fis.close();
//			
//			fA.delete();
//			
//			FileOutputStream fos = new FileOutputStream(fA);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			oos.writeObject(als);
//			oos.close();
//			fos.close();
//		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Carica gli spettacoli
	 * @return array di Spettacolo contenente gli spettacoli della sala data in argomento
	 */
	public Spettacolo[] caricaSpettacoli() {
		ArrayList<Spettacolo> returnthis = new ArrayList<Spettacolo>();
		
		for(File f : new File(cartellaDati + "/Spettacoli/").listFiles()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream oos = new ObjectInputStream(fis);
				returnthis.add((Spettacolo) oos.readObject());
				oos.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return returnthis.toArray(new Spettacolo[returnthis.size()]);
	}
	
	/**
	 * Aggiungi un utente al database
	 * @param userID ID dell'utente
	 */
	public void aggiungiUtente(String userID) {
		// crea una cartella sotto cartellaDati/Utenti con l'userID come nome
		File user = new File(cartellaDati + "/Utenti/" + userID);
		user.mkdir();
	}
	
	/**
	 * Aggiungi una prenotazione per un utente
	 * @param userID identificativo dell'utente
	 * @param numeroPosto numero posto da prenotare
	 * @param numeroSala numero sala in cui prenotare il posto
	 */
	public void aggiungiPrenotazione(String userID, int numeroPosto, int numeroSala) {
		
		ArrayList<Prenotazione> prenotazioniUtente;
		
		// controlla che non esista un database per le prenotazioni di quell'utente
		// se non esiste, crealo
		File user = new File(cartellaDati + "/Utenti/" + userID + "Prenotazioni" + ".pks");
		
		if(!user.exists()) {
			try {
			prenotazioniUtente = new ArrayList<Prenotazione>();
			FileOutputStream f = new FileOutputStream(user);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(prenotazioniUtente);
			o.close();
			f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// crea una prenotazione p
			Prenotazione p = new Prenotazione(numeroSala, numeroPosto);
			
			try {
				FileInputStream f = new FileInputStream(user);
				ObjectInputStream o = new ObjectInputStream(f);
				prenotazioniUtente = (ArrayList<Prenotazione>) o.readObject();
				prenotazioniUtente.add(p);
				o.close();
				f.close();
				
				user.delete();
				FileOutputStream f2 = new FileOutputStream(user);
				ObjectOutputStream o2 = new ObjectOutputStream(f2);
				o2.writeObject(prenotazioniUtente);
				o2.close();
				f2.close();
				
			} catch (ClassNotFoundException | IOException e) {
				
			}
			
		}
	}
	
	/**
	 * Restituisce la lista delle prenotazioni di un utente
	 * @param userID ID dell'utente in questione
	 * @return array di Prenotazione contenente tutte le prenotazioni che l'utente ha effettuato
	 */
	
	public Prenotazione[] prenotazioniUtente(String userID) {
		
		// arraylist d'appoggio su cui deserializzare le prenotazioni dell'utente in argomento
		ArrayList<Prenotazione> prenotazioniUtente = new ArrayList<Prenotazione>();
		File user = new File(cartellaDati + "/Utenti/" + userID + "Prenotazioni" + ".pks");
		
		try {
			FileInputStream f = new FileInputStream(user);
			ObjectInputStream o = new ObjectInputStream(f);
			prenotazioniUtente = (ArrayList<Prenotazione>) o.readObject();
			o.close();
			f.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		// ritorna direttamente l'array derivato dall'arraylist d'appoggio
		return prenotazioniUtente.toArray(new Prenotazione[prenotazioniUtente.size()]);
	}
	
}
