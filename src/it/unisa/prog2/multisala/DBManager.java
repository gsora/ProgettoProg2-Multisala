package it.unisa.prog2.multisala;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class DBManager {
	
	private String cartellaDati;
	private String pathFileDatabase;
	private String[] nomiCartelleSale;

	public DBManager() {
		cartellaDati = new String();
		nomiCartelleSale = new String[4];
		
		String OS = System.getProperty("os.name").toUpperCase();
		if(OS.contains("WIN")) {
			cartellaDati = (System.getenv("AppData") + "/MultisalaPancakes");
		} else if(OS.contains("MAC")) {
			cartellaDati = (System.getProperty("user.home").toString() + "/Library/Application Support/MultisalaPancakes");
		} else if(OS.contains("LINUX")) {
			cartellaDati = (System.getProperty("user.home").toString() + "/.config/MultisalaPancakes");
		}
		
		pathFileDatabase = cartellaDati + "/" + "Database.pks";
		
		if(controllaEsistenzaPath()) {
			System.out.println("La path esiste");
			caricaNomiCartelle();
			for(String i : nomiCartelleSale) {
				System.out.println(i);
			}
		} else {
			System.out.println("La path non esiste");
			creaStrutturaDirectory();
		}
	}
	
	private Boolean controllaEsistenzaPath() {
		File test = new File(cartellaDati);
		return test.exists();
	}
	
	private void creaStrutturaDirectory() {
		File d = new File(cartellaDati);
		d.mkdir();
		
		UUID [] sale = new UUID[4];
		
		for (int j = 0; j < sale.length; j++) {
			sale[j] = UUID.randomUUID();
			File a = new File(cartellaDati + "/" + sale[j].toString());
			a.mkdir();
			nomiCartelleSale[j] = sale[j].toString();
		}
		
		try {
			FileOutputStream nomiCartelleSaleSerializzato = new FileOutputStream(pathFileDatabase);
			ObjectOutputStream outStream = new ObjectOutputStream(nomiCartelleSaleSerializzato);
			outStream.writeObject(nomiCartelleSale);
			outStream.close();
			nomiCartelleSaleSerializzato.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void caricaNomiCartelle() {
		try {
			FileInputStream nomiCartelleSaleSerializzato = new FileInputStream(pathFileDatabase);
			ObjectInputStream inStream = new ObjectInputStream(nomiCartelleSaleSerializzato);
			nomiCartelleSale = (String []) inStream.readObject();
			inStream.close();
			nomiCartelleSaleSerializzato.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void salvaSpettacolo(Spettacolo s) {
		String pathSpettacolo = cartellaDati + "/" + nomiCartelleSale[s.getNumeroSala() - 1] + "/" + UUID.randomUUID().toString() + ".pks";
		
		try {
			FileOutputStream fileSpettacolo = new FileOutputStream(pathSpettacolo);
			ObjectOutputStream outStream = new ObjectOutputStream(fileSpettacolo);
			outStream.writeObject(s);
			outStream.close();
			fileSpettacolo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// TODO: ELIMINAMI
	public static void main(String[] args) {
		DBManager asd = new DBManager();
		Spettacolo ggpas;
		try {
			ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 1, "18:43", "1/2/2014", 2.6);
			asd.salvaSpettacolo(ggpas);
		} catch (OrarioNonValidoException | DataNonValidaException e) {
			e.printStackTrace();
		}
	}
	
}
