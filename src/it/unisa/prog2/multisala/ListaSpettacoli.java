package it.unisa.prog2.multisala;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


public class ListaSpettacoli extends JPanel {
	
	private JList<String> lstSpettacoli;
	private JPanel pnl; 
	private DBManager caricaSpettacoli;
	
	
	public ListaSpettacoli() {
		
		setLayout(new GridLayout());
		
		caricaSpettacoli = new DBManager();
		ArrayList<Spettacolo> appoggioSpettacoli = new ArrayList<Spettacolo>();
		Map<Integer, Spettacolo> mapSpettacoli = new TreeMap<Integer, Spettacolo>();
		for(Spettacolo s: caricaSpettacoli.caricaSpettacoli()) {
			mapSpettacoli.put(s.sala().getNumeroPostiLiberi(), s);
		}
		
		for(Map.Entry<Integer, Spettacolo> spettacoliOrdinati: mapSpettacoli.entrySet()){
			appoggioSpettacoli.add(spettacoliOrdinati.getValue());
		}
		
		for (Spettacolo s: appoggioSpettacoli) {
			System.out.println(s.getTitoloSpettacolo());
		}
		
		/*for(Spettacolo s: appoggioSpettacoli) {
			pnl = new JPanel(new FlowLayout());
			JLabel l1 = new JLabel("Titolo: " +s.getTitoloSpettacolo());
			JLabel l2 = new JLabel("Sala: " + s.getNumeroSala());
			JLabel l3 = new JLabel("Data: "+ s.getData());
			JLabel l4 = new JLabel("Durata: " + s.getDurata());
			JLabel l5 = new JLabel("Orario di inizio: " + s.getOrarioDiInizio());
			pnl.add(l1);
			pnl.add(l2);
			pnl.add(l3);
			pnl.add(l4);
			pnl.add(l5);
			add(pnl);
		}*/
		
		String[] nomiColonne ={"Titolo", "Numero Sala", "Data", "Orario di inizio", "Durata", "Posti liberi"};
		
		JTable informazioni = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(nomiColonne);
		informazioni.setModel(dtm);
		
		for(Spettacolo s: appoggioSpettacoli) {
			dtm.addRow(new Object[] {
					s.getTitoloSpettacolo(),
					s.getNumeroSala(),
					s.getData(),
					s.getOrarioDiInizio(),
					s.getDurata(),
					s.sala().getNumeroPostiLiberi(),
			});
		}
		
		JScrollPane scroll = new JScrollPane(informazioni);
		add(scroll);
		
		
		
	}
	
}
