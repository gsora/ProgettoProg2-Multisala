package it.unisa.prog2.multisala.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaPrenotazioni implements Serializable {
	
	private Map<Object[], ArrayList<Prenotazione>> lPrenotazioni;
	
	public ListaPrenotazioni() {
		lPrenotazioni = new HashMap<Object[], ArrayList<Prenotazione>>();
	}
	
	public void aggiungiPrenotazione(int posto, String nomeF, String dataF, String orarioF, int salaF) {
		Object[] filmRif = new Object[4];
		filmRif[0] = nomeF;
		filmRif[1] = dataF;
		filmRif[2] = orarioF;
		filmRif[3] = salaF;
		ArrayList<Prenotazione> app = lPrenotazioni.get(filmRif);
		if(app == null) {
			app = new ArrayList<Prenotazione>();
			app.add(new Prenotazione(posto));
			lPrenotazioni.put(filmRif, app);
		} else {
			app.add(new Prenotazione(posto));
			lPrenotazioni.put(filmRif, app);
		}
		
	
	}
	
	public ArrayList<Prenotazione> numeroPostiDaPrenotazione(int posto, String nomeF, String dataF, String orarioF, int salaF) {
		return lPrenotazioni.get(new Object[] {
				nomeF,
				dataF,
				orarioF,
				salaF
		});
	}
	
	public void rimuoviPrenotazione(int posto, String nomeF, String dataF, String orarioF, int salaF) {
		ArrayList<Prenotazione> app = lPrenotazioni.get(new Object[] {
				nomeF,
				dataF,
				orarioF,
				salaF
		});
		
		for(Prenotazione i : app) {
			if(i.getPostoPrenotato() == posto) {
				app.remove(i);
			}
		}
		lPrenotazioni.put(new Object[] {
				nomeF,
				dataF,
				orarioF,
				salaF
		}, app);
	}
	
	public HashMap<Object[], ArrayList<Prenotazione>> prenotazioniUtente() {
		return (HashMap<Object[], ArrayList<Prenotazione>>) lPrenotazioni;
	}
	
}