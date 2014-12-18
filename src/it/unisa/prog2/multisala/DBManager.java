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
	
	// path del file Database.pks
	private String pathFileDatabase;
	
	// array popolato con i nomi dei file .pks di ogni singola sala
	private static String[] nomiFileSale;
	
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
		nomiFileSale = new String[4];
		
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
		
		// ora possiamo sapere dove si trova il file di Database
		pathFileDatabase = cartellaDati + "/" + "Database.pks";
		
		// controllo: se cartellaDati esiste carica i nomi delle sale, altrimenti crea la struttura della directory
		if(controllaEsistenzaPath()) {
			caricaNomiCartelle();
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
		 * 			sala1.pks
		 * 			sala2.pks
		 * 			sala3.pks
		 * 			sala4.pks
		 * 			Database.pks
		 * 			StatisticheVendita.pks
		 * 			Utenti->
		 * 				utenteX.pks
		 */
		
		
		// crea un arraylist di arraylist che contengono solo spettacoli, d'appoggio
		ArrayList<ArrayList<Spettacolo>> arrayListeSpettPerSala = new ArrayList<ArrayList<Spettacolo>>();
		
		// riempi l'arraylist in base alle sale
		for(int i = 0; i < NUM_SALE; i++) {
			ArrayList<Spettacolo> a = new ArrayList<Spettacolo>();
			arrayListeSpettPerSala.add(a);
		}
		
		// crea la cartella dei dati nella path definita prima
		File d = new File(cartellaDati);
		d.mkdir();
		
		// crea la cartella dove verranno contenuti gli utenti e le loro prenotazioni
		File du = new File(cartellaDati + "/Utenti");
		du.mkdir();
		
		// array di UUID random, creato solo per comodità di iterazione
		UUID [] sale = new UUID[NUM_SALE];
		
		// per ogni elemento nell'array sopracitato... 
		for (int j = 0; j < sale.length; j++) {
			try {
				
				// ...inizializza l'elemento attuale...
				sale[j] = UUID.randomUUID();
				
				// ...serializza un arraylist<Spettacolo> preso da arrayListeSpettPerSala...
				FileOutputStream salaX = new FileOutputStream(cartellaDati + "/" + sale[j].toString() + ".pks");
				ObjectOutputStream salaOut = new ObjectOutputStream(salaX);
				salaOut.writeObject(arrayListeSpettPerSala.get(j));
				salaOut.close();
				salaX.close();
				
				// ...per poi aggiungere il suo nome alla lista delle cartelle
				nomiFileSale[j] = sale[j].toString() + ".pks";
			} catch (IOException e) {
				// TODO: magari c'è da controllare l'eccezione?
				e.printStackTrace();
			}
		}
		
		// serializza Database.pks con l'array nomiFileSale 
		try {
			FileOutputStream nomiFileSaleSerializzato = new FileOutputStream(pathFileDatabase);
			ObjectOutputStream outStream = new ObjectOutputStream(nomiFileSaleSerializzato);
			outStream.writeObject(nomiFileSale);
			outStream.close();
			nomiFileSaleSerializzato.close();
		} catch (IOException e) {
			// TODO: magari c'è da controllare l'eccezione?
			e.printStackTrace();
		}
		
	}
	
	// carica la struttura delle directory del database in memoria e rendila utilizzabile 
	private void caricaNomiCartelle() {
		try {
			
			// deserializza Database.pks in nomiFileSale
			FileInputStream nomiCartelleSaleSerializzato = new FileInputStream(pathFileDatabase);
			ObjectInputStream inStream = new ObjectInputStream(nomiCartelleSaleSerializzato);
			nomiFileSale = (String []) inStream.readObject();
			inStream.close();
			nomiCartelleSaleSerializzato.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO: magari c'è da controllare l'eccezione?
			e.printStackTrace();
		}
	}
	
	// ritorna la path della sala data in argomento
	private static String pathSalaNumero(int numeroSala) {
		// TODO: lanciare un'eccezione se la sala non esiste?
		return cartellaDati + "/" + nomiFileSale[numeroSala - 1];
	}
	
	/**
	 * Salva uno spettacolo nel database
	 * @param s oggetto spettacolo da aggiungere al database
	 */
	
	public void salvaSpettacolo(Spettacolo s) {
		// apro la sala di riferimento leggendola da s
		String pathSpettacolo = cartellaDati + "/" + nomiFileSale[s.getNumeroSala() - 1];
		File fA = new File(pathSpettacolo);
		
		try {
			// deserializzo l'oggetto...
			FileInputStream fis = new FileInputStream(fA);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Spettacolo> als = (ArrayList<Spettacolo>) ois.readObject();
			// ...lo aggiungo all'arraylist deserializzato als...
			als.add(s);
			ois.close();
			fis.close();
			
			// ...elimino il vecchio file per evitare conflitti...
			fA.delete();
			
			// ...serializzo als in un file con lo stesso nome di prima.
			FileOutputStream fos = new FileOutputStream(fA);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(als);
			oos.close();
			fos.close();
		} catch (IOException | ClassNotFoundException e) {
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
	 * Carica gli spettacoli da una sala
	 * @param numeroSala numero della sala da cui caricare gli spettacoli
	 * @return array di Spettacolo contenente gli spettacoli della sala data in argomento
	 */
	public static Spettacolo[] caricaSpettacoliInSala(int numeroSala) {
		File a = new File(pathSalaNumero(numeroSala));
		ArrayList<Spettacolo> als = new ArrayList<Spettacolo>();
		try {
			FileInputStream fis = new FileInputStream(a);
			ObjectInputStream oos = new ObjectInputStream(fis);
			als = (ArrayList<Spettacolo>) oos.readObject();
			oos.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Spettacolo[] returnThis = als.toArray(new Spettacolo[als.size()]);
		return  returnThis;
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
		File user = new File(cartellaDati + "/Utenti/" + userID + ".pks");
		
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
		
		// serializzazione della prenotazione

	}
	
	// TODO: ELIMINAMI
	public static void main(String[] args) {
		DBManager asd = new DBManager();
		asd.aggiungiPrenotazione("okfunge", 22, 34);
	}
	
}
