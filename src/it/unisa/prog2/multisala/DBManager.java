package it.unisa.prog2.multisala;

import java.awt.event.FocusAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class DBManager {
	
	private static String cartellaDati;
	private String pathFileDatabase;
	private static String[] nomiFileSale;
	private ArrayList<ArrayList<Spettacolo>> arrayListeSpettPerSala;
	
	public DBManager() {
		cartellaDati = new String();
		nomiFileSale = new String[4];
		arrayListeSpettPerSala = new ArrayList<ArrayList<Spettacolo>>();
		
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
		// riempi l'arraylist
		for(int i = 0; i < 4; i++) {
			ArrayList<Spettacolo> a = new ArrayList<Spettacolo>();
			arrayListeSpettPerSala.add(a);
		}
		
		
		File d = new File(cartellaDati);
		d.mkdir();
		
		UUID [] sale = new UUID[4];
		
		for (int j = 0; j < sale.length; j++) {
			try {
				sale[j] = UUID.randomUUID();
				FileOutputStream salaX = new FileOutputStream(cartellaDati + "/" + sale[j].toString() + ".pks");
				ObjectOutputStream salaOut = new ObjectOutputStream(salaX);
				salaOut.writeObject(arrayListeSpettPerSala.get(j));
				salaOut.close();
				salaX.close();
				nomiFileSale[j] = sale[j].toString() + ".pks";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream nomiFileSaleSerializzato = new FileOutputStream(pathFileDatabase);
			ObjectOutputStream outStream = new ObjectOutputStream(nomiFileSaleSerializzato);
			outStream.writeObject(nomiFileSale);
			outStream.close();
			nomiFileSaleSerializzato.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void caricaNomiCartelle() {
		try {
			FileInputStream nomiCartelleSaleSerializzato = new FileInputStream(pathFileDatabase);
			ObjectInputStream inStream = new ObjectInputStream(nomiCartelleSaleSerializzato);
			nomiFileSale = (String []) inStream.readObject();
			inStream.close();
			nomiCartelleSaleSerializzato.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static String pathSalaNumero(int numeroSala) {
		return cartellaDati + "/" + nomiFileSale[numeroSala - 1];
	}
	
	public void salvaSpettacolo(Spettacolo s) {
		String pathSpettacolo = cartellaDati + "/" + nomiFileSale[s.getNumeroSala() - 1];
		File fA = new File(pathSpettacolo);
		
		try {
			FileInputStream fis = new FileInputStream(fA);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Spettacolo> als = (ArrayList<Spettacolo>) ois.readObject();
			als.add(s);
			ois.close();
			fis.close();
			
			fA.delete();
			
			FileOutputStream fos = new FileOutputStream(fA);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(als);
			oos.close();
			fos.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
	
	
	// TODO: ELIMINAMI
	public static void main(String[] args) {
		DBManager asd = new DBManager();
		Spettacolo ggpas;
		try {
			ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 1, "18:43", "1/2/2014", 2.6);
			asd.salvaSpettacolo(ggpas);
			for(Spettacolo i : caricaSpettacoliInSala(1)) {
				System.out.println(i.getTitoloSpettacolo());
			}
		} catch (OrarioNonValidoException | DataNonValidaException e) {
			e.printStackTrace();
		}
	}
	
}
