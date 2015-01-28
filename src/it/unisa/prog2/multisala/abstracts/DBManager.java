package it.unisa.prog2.multisala.abstracts;

import it.unisa.prog2.multisala.exceptions.SpettacoloNonTrovatoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class DBManager {
	
	// numero delle sale da gestire
	private static final int NUM_SALE = 4;
	
	// path dove sono contenuti i file dati
	private static String cartellaDati;
	
	// prezzo dei film
	private float prezzoFilm;
	
	// Array contenente gli sconti per studenti e quello per giorno della settimana
	private String[] sconti;
	
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
		sconti = new String[3];
		sconti[0] = "0";
		sconti[1] = "lunedi";
		sconti[2] = "0";
		
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
			// il prezzo e` definito?
			// se si carica altrimenti inizializzalo a zero
			if(!prezzoDefinito()) {
				prezzoFilm = 0f;
			} else {
				try {
					FileInputStream fis = new FileInputStream(new File(cartellaDati + "/Prezzo.pks"));
					ObjectInputStream ois = new ObjectInputStream(fis);
					prezzoFilm = (float) ois.readObject();
					ois.close();
					fis.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			// gli sconti sono stati definiti?
			// se si carica altrimenti inizializza {0, lunedi, 0}
			if(scontiDefiniti()) {
				try {
					FileInputStream fis = new FileInputStream(new File(cartellaDati + "/Sconti.pks"));
					ObjectInputStream ois = new ObjectInputStream(fis);
					sconti = (String[]) ois.readObject();
					ois.close();
					fis.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
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
		 * 			Sconti.pks
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
		
		// serializza gli sconti
		try {
			FileOutputStream prezzoSconti = new FileOutputStream(new File(cartellaDati + "/Sconti.pks"));
			ObjectOutputStream outFileSconti = new ObjectOutputStream(prezzoSconti);
			outFileSconti.writeObject((String[]) sconti);
			outFileSconti.close();
			prezzoSconti.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// il prezzo e` mai stato definito?
	private Boolean prezzoDefinito() {
		File prezzo = new File(cartellaDati + "/Prezzo.pks");
		return prezzo.exists();
	}
	
	// gli sconti sono mai stati definiti?
	private Boolean scontiDefiniti() {
		File sconti = new File(cartellaDati + "/Sconti.pks");
		return sconti.exists();
	}
	
	/**
	 * Leggi il prezzo degli spettacoli dal database
	 * @return float contenente il prezzo dei film
	 */
	
	public float getPrezzoFilm() {
		return prezzoFilm;
	}
	
	/**
	 * Restituisci lo sconto studenti dal database
	 * @return double contenente lo sconto studenti
	 */
	public String getScontoStudenti() {
		String a = sconti[0];
		return a;
	}
	
	/**
	 * Restituisci il giorno della settimana dove viene scontato il prezzo del biglietto
	 * @return stringa contenente il giorno della settimana in italiano senza accento
	 */
	public String getGiornoScontoSettimanale() {
		String a = sconti[1];
		return a;
	}
	
	/**
	 * Restituisci il valore dello sconto settimanale
	 * @return double contenente lo sconto settimanale
	 */
	public String getValoreScontoSettimanale() {
		String a = sconti[2];
		return a;
	}
	
	/**
	 * Setta lo sconto settimanale
	 * @param d double contenente sconto settimanale
	 */
	public void setScontoStudenti(String d) {
		sconti[0] = d;
		// serializza il prezzo
		File f = new File(cartellaDati + "/Sconti.pks");
		f.delete();
		try {
			FileOutputStream prezzoFile = new FileOutputStream(f);
			ObjectOutputStream outFile = new ObjectOutputStream(prezzoFile);
			outFile.writeObject(sconti);
			outFile.close();
			prezzoFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setta il giorno della settimana in cui scontare i biglietti
	 * @param d String contenente il giorno della settimana in italiano senza accento
	 */
	public void setGiornScontoSettimanale(String s) {
		sconti[1] = s;
		// serializza il prezzo
		File f = new File(cartellaDati + "/Sconti.pks");
		f.delete();
		try {
			FileOutputStream prezzoFile = new FileOutputStream(f);
			ObjectOutputStream outFile = new ObjectOutputStream(prezzoFile);
			outFile.writeObject(sconti);
			outFile.close();
			prezzoFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setta il valore dello sconto settimanale
	 * @param d double il valore sconto settimanale
	 */
	public void setValoreScontoSettimanale(String d) {
		sconti[2] = d;
		// serializza il prezzo
		File f = new File(cartellaDati + "/Sconti.pks");
		f.delete();
		try {
			FileOutputStream prezzoFile = new FileOutputStream(f);
			ObjectOutputStream outFile = new ObjectOutputStream(prezzoFile);
			outFile.writeObject(sconti);
			outFile.close();
			prezzoFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setta il prezzo dei film
	 * @param p prezzo dei film da salvare nel database
	 */
	public void setPrezzoFilm(float p) {
		// serializza il prezzo
		prezzoFilm = p;
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
	
	/**
	 * Salva uno spettacolo nel database
	 * @param s oggetto spettacolo da aggiungere al database
	 */
	
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
	
	/**
	 * Elimina uno Spettacolo dal database
	 * @param titolo titolo dello spettacolo
	 * @param orario orario dello spettacolo denotato dai campi HH:MM
	 * @param data data dello spettacolo denotato dai campi GG/MM/HHHH
	 * @param sala sala in cui viene proiettato lo spettacolo
	 * @throws SpettacoloNonTrovatoException file dello spettacolo non trovato
	 */
	
	public void rimuoviSpettacolo(String titolo, String orario, String data, int sala) throws SpettacoloNonTrovatoException {
		String filename = titolo.replace(" ", "") + "-" + data.replace("/", "") + "-" + orario.replace(":", "") + "-" + Integer.toString(sala) + ".pks";
		String pathSpettacolo = cartellaDati + "/Spettacoli/" + filename;
		File removeMe = new File(pathSpettacolo);
		
		if(!removeMe.exists()) {
			throw new SpettacoloNonTrovatoException();
		} else {
			removeMe.delete();
		}
	}
	
	/**
	 * Prendi un singolo spettacolo dal database 
	 * @param titolo titolo dello spettacolo
	 * @param orario orario dello spettacolo denotato dai campi HH:MM
	 * @param data data dello spettacolo denotato dai campi GG/MM/HHHH
	 * @param sala sala in cui viene proiettato lo spettacolo
	 * @throws SpettacoloNonTrovatoException file dello spettacolo non trovato
	 */
	public Spettacolo getSpettacolo(String titolo, String orario, String data, int sala) throws SpettacoloNonTrovatoException {
		String filename = titolo.replace(" ", "") + "-" + data.replace("/", "") + "-" + orario.replace(":", "") + "-" + Integer.toString(sala) + ".pks";
		String pathSpettacolo = cartellaDati + "/Spettacoli/" + filename;
		Spettacolo returnthis = null;
		try {
			FileInputStream fis = new FileInputStream(new File(pathSpettacolo));
			ObjectInputStream oos = new ObjectInputStream(fis);
			returnthis = (Spettacolo) oos.readObject();
			oos.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return returnthis;
	}
	
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
	
	public Spettacolo[] caricaSpettacoliOrdinati() {
		ArrayList<Spettacolo> spettDis = new ArrayList<Spettacolo>(Arrays.asList(caricaSpettacoli()));
		ArrayList<Spettacolo> spettOrd = new ArrayList<Spettacolo>();
		
		while(spettDis.size() > 0) {
			Spettacolo minimo = null;
			Spettacolo[] iterThis = spettDis.toArray(new Spettacolo[spettDis.size()]);
			for(int i = 0; i < iterThis.length; i++) {
				if(i == 0) {
					minimo = iterThis[i];
				} else if(i > 0) {
					if(iterThis[i].sala().getNumeroPostiLiberi() < minimo.sala().getNumeroPostiLiberi()) {
						minimo = iterThis[i];
					}
				}
			}
			spettOrd.add(minimo);
			spettDis.remove(minimo);
		}
		 return spettOrd.toArray(new Spettacolo[spettOrd.size()]);
	}
	
	/**
	 * Restituisce gli spettacoli nella sala data in  argomento
	 * @param n numero della sala
	 * @return Spettacolo[] contenente gli spettacoli della sala numero N
	 */
	public Spettacolo[] caricaSpettacoliInSala(int n) {
		ArrayList<Spettacolo> spettacoli = new ArrayList<Spettacolo>();
		for(File f : new File(cartellaDati + "/Spettacoli/").listFiles()) {
			String[] fn = f.getName().split("-");
			if(fn[3].replace(".pks", "").contentEquals(String.valueOf(n))) {
				try {
					Spettacolo lol = null;
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream oos = new ObjectInputStream(fis);
					lol = (Spettacolo) oos.readObject();
					oos.close();
					fis.close();
					spettacoli.add(lol);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return spettacoli.toArray(new Spettacolo[spettacoli.size()]);
	}
	
	/**
	 * Restituisce il numero di sale presenti nel database
	 * @return int contenente il numero di sale presenti nel database
	 */
	public int numeroMassimoSale() {
		int nSala = 0;
		for(File f : new File(cartellaDati + "/Spettacoli/").listFiles()) {
			String[] fn = f.getName().split("-");
			if(Integer.valueOf(fn[3].replace(".pks", "")) > nSala) {
				nSala = Integer.valueOf(fn[3].replace(".pks", ""));
			}
		}
		return nSala;
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
	
	public static Boolean controllaEsistenzaUtente(String userID) {
		File user = new File(cartellaDati + "/Utenti/" + userID);
		return user.exists();
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
