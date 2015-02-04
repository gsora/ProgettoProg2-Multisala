package it.unisa.prog2.multisala.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListaPrenotazioni implements Serializable {
	
	private Map<ArrayList<Object>, ArrayList<Prenotazione>> lPrenotazioni;
	
	public ListaPrenotazioni() {
		lPrenotazioni = new HashMap<ArrayList<Object>, ArrayList<Prenotazione>>();
	}
	
	public void aggiungiPrenotazione(int posto, String nomeF, String dataF, String orarioF, int salaF) {
		ArrayList<Object> keyRif = new ArrayList<Object>();
		keyRif.add(nomeF);
		keyRif.add(dataF);
		keyRif.add(orarioF);
		keyRif.add(salaF);
		
		ArrayList<Prenotazione> app = lPrenotazioni.get(keyRif);
		if(app == null) {
			app = new ArrayList<Prenotazione>();
			app.add(new Prenotazione(posto));
			lPrenotazioni.put(keyRif, app);
		} else {
			app.add(new Prenotazione(posto));
			lPrenotazioni.put(keyRif, app);
		}
		
	
	}
	
	public ArrayList<Prenotazione> numeroPostiDaPrenotazione(int posto, String nomeF, String dataF, String orarioF, int salaF) {
		ArrayList<Object> keyRif = new ArrayList<Object>();
		keyRif.add(nomeF);
		keyRif.add(dataF);
		keyRif.add(orarioF);
		keyRif.add(salaF);
		
		return lPrenotazioni.get(keyRif);
	}
	
	public void rimuoviPrenotazione(int posto, String nomeF, String dataF, String orarioF, int salaF) {
		
		ArrayList<Object> keyRif = new ArrayList<Object>();
		keyRif.add(nomeF);
		keyRif.add(dataF);
		keyRif.add(orarioF);
		keyRif.add(salaF);
		
		ArrayList<Prenotazione> app = lPrenotazioni.get(keyRif);
		
		for(Iterator<Prenotazione> it = app.iterator(); it.hasNext();) {
			Prenotazione nextPren = it.next();
			if(nextPren.getPostoPrenotato() == posto)
				it.remove();
		}
		
		
		lPrenotazioni.put(keyRif, app);
	}
	
	public HashMap<ArrayList<Object>, ArrayList<Prenotazione>> prenotazioniUtente() {
		return (HashMap<ArrayList<Object>, ArrayList<Prenotazione>>) lPrenotazioni;
	}
	
}