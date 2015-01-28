// TODO: rinominare la classe ListaSpettacoli?

package it.unisa.prog2.multisala.gui.utente;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.GestioneGrafica;
import it.unisa.prog2.multisala.abstracts.Spettacolo;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListaSale implements GestioneGrafica {
	
	private JFrame listaSale;
	
	private JTable informazioni;
	
	private DefaultTableModel dtm;
	
	private JComboBox<String> visualizzaSala;
	
	private JButton prenotaAcquista;

	private DBManager dbm;
	
	public ListaSale(JFrame frameChiamante) {
		// inizializzazione listaSale con le proprietà corrette
		listaSale = new JFrame();
		listaSale.setSize(750,800);
		listaSale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listaSale.setLocationRelativeTo(null);
		listaSale.setTitle("Lista spettacoli");
		listaSale.setLayout(new BorderLayout());
		dbm = new DBManager();
		
		String[] nomiColonne = {"Titolo", "Numero Sala", "Data", "Orario di inizio", "Durata", "Posti liberi", "Sconto"};
		
		informazioni = new JTable();
		informazioni.setAutoCreateRowSorter(true);
		informazioni.getTableHeader().setReorderingAllowed(false);
		informazioni.getTableHeader().setResizingAllowed(false);
		dtm = new DefaultTableModel(0, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex == 0) {
					return String.class;
				} else if(columnIndex == 1) {
					return Integer.class;
				}
				
				return Object.class;
			}
		};
		
		dtm.setColumnIdentifiers(nomiColonne);
		informazioni.setRowSelectionAllowed(true);
		informazioni.setModel(dtm);
		
		
		JScrollPane scroll = new JScrollPane(informazioni);
		listaSale.add(scroll, BorderLayout.CENTER);
		
		visualizzaSala = new JComboBox<String>();
		visualizzaSala.addItem("Programmazione Complessiva");
		for(int i = 0; i < dbm.numeroMassimoSale(); i++) {
			int num = i+1;
			visualizzaSala.addItem("Sala " + num);
		}
		visualizzaSala.setSelectedIndex(0);
		informazioni.removeAll();
		for(Spettacolo spett : dbm.caricaSpettacoli()) {
			String sconto = String.valueOf(spett.getSconto());
			if(sconto.contentEquals("0.0")) {
				sconto = "N/A";
			}
			dtm.addRow(new Object[] {
					spett.getTitoloSpettacolo(),
					spett.getNumeroSala(),
					spett.getData(),
					spett.getOrarioDiInizio(),
					spett.getDurata(),
					spett.sala().getNumeroPostiLiberi(),
					sconto
			});
		}
		visualizzaSala.setPreferredSize(new Dimension(300, 30));
		
		
		visualizzaSala.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(e.getItem().toString().contains("Programmazione")) {
						dtm.fireTableDataChanged();
						dtm = (DefaultTableModel) informazioni.getModel();
						dtm.setRowCount(0);
						for(Spettacolo spett : dbm.caricaSpettacoli()) {
							String sconto = String.valueOf(spett.getSconto());
							if(sconto.contentEquals("0.0")) {
								sconto = "N/A";
							}
							dtm.addRow(new Object[] {
									spett.getTitoloSpettacolo(),
									spett.getNumeroSala(),
									spett.getData(),
									spett.getOrarioDiInizio(),
									spett.getDurata(),
									spett.sala().getNumeroPostiLiberi(),
									sconto
							});
						}

					} else {
						String[] nS = e.getItem().toString().split(" ");
						dtm.fireTableDataChanged();
						dtm = (DefaultTableModel) informazioni.getModel();
						dtm.setRowCount(0);
						for(Spettacolo spett : dbm.caricaSpettacoliInSala(Integer.valueOf(nS[1]))) {
							String sconto = String.valueOf(spett.getSconto());
							if(sconto.contentEquals("0.0")) {
								sconto = "N/A";
							}
							dtm.addRow(new Object[] {
									spett.getTitoloSpettacolo(),
									spett.getNumeroSala(),
									spett.getData(),
									spett.getOrarioDiInizio(),
									spett.getDurata(),
									spett.sala().getNumeroPostiLiberi(),
									sconto
							});
						}
					}
				}
			}
		});
		
		JPanel app0 = new JPanel(new FlowLayout());
		app0.add(visualizzaSala);
		listaSale.add(app0, BorderLayout.NORTH);		
		
		prenotaAcquista = new JButton("Prenota/Acquista");
		prenotaAcquista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i = 0; i < informazioni.getModel().getColumnCount(); i++) {
					System.out.println(
							informazioni.getModel().getValueAt(
									informazioni.convertRowIndexToModel(informazioni.getSelectedRow()), i
							)
					);
				}

			}
		});
		JPanel app1 = new JPanel(new FlowLayout());
		app1.add(prenotaAcquista);
		listaSale.add(app1, BorderLayout.SOUTH);
	}
	
	
	public void costruisciUI(JFrame frameChiamante) {
		listaSale.setVisible(true);
	}

	@Override
	public void costruisciUI() { }

}
