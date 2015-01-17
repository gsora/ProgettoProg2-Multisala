package it.unisa.prog2.multisala;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class ListaSpettacoli extends JPanel {
	
	private DBManager caricaSpettacoli;
	
	private Spettacolo[] spettacoliCaricati;
	
	private JTabbedPane pane;
	
	private JTable informazioni;
	
	private DefaultTableModel dtm;
	
	public ListaSpettacoli(JTabbedPane p) {
		pane = p;
		
		caricaSpettacoli = new DBManager();
		
		spettacoliCaricati = caricaSpettacoli.caricaSpettacoliOrdinati();
		
		p.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(pane.getSelectedIndex() == 2) {
					spettacoliCaricati = caricaSpettacoli.caricaSpettacoliOrdinati();
					dtm.fireTableDataChanged();
					dtm = (DefaultTableModel) informazioni.getModel();
					dtm.setRowCount(0);
					for(Spettacolo s: spettacoliCaricati) {
						dtm.addRow(new Object[] {
								s.getTitoloSpettacolo(),
								s.getNumeroSala(),
								s.getData(),
								s.getOrarioDiInizio(),
								s.getDurata(),
								s.sala().getNumeroPostiLiberi(),
						});
					}
				}
			}
		});
		
		
		setLayout(new GridLayout());		
		String[] nomiColonne ={"Titolo", "Numero Sala", "Data", "Orario di inizio", "Durata", "Posti liberi"};
		
		informazioni = new JTable();
		dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(nomiColonne);
		informazioni.setModel(dtm);
		
		JScrollPane scroll = new JScrollPane(informazioni);
		add(scroll);
		
		
		
	}
	
}
