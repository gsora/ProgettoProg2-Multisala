package it.unisa.prog2.multisala.abstracts;

import java.util.Calendar;

public class CheckSconto {
	
	/**
	 * Metodo vuoto per CheckSconto
	 * 
	 * 
	 */
	
	public CheckSconto() {}

	/**
	 * Metodo che controlla se il giorno corrente corrisponde o meno al giorno predefinito per lo sconto
	 * 
	 * 
	 * @return restituisce un valore booleano veritiero se se il giorno corrente corrisponde al giorno per lo sconto altrimenti falso
	 */
	
	public static boolean oggiSconto() {
		DBManager dbm = new DBManager();
		
		String giorno = dbm.getGiornoScontoSettimanale();
		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_WEEK) - 1;
		
		switch (day) {
		case 0:
			return(giorno.equals("domenica")? true: false);
		case 1:
			return(giorno.equals("lunedì")? true: false);
		case 2:
			return(giorno.equals("martedì")? true: false);
		case 3:
			return(giorno.equals("mercoledì")? true: false);
		case 4:
			return(giorno.equals("giovedì")? true: false);
		case 5:
			return(giorno.equals("venerdì")? true: false);
		case 6:
			return(giorno.equals("sabato")? true: false);
		default:
			return false;
		}
	}
}
