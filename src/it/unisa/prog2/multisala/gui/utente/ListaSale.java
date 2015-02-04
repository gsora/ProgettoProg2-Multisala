// TODO: rinominare la classe ListaSpettacoli?

package it.unisa.prog2.multisala.gui.utente;

import it.unisa.prog2.multisala.abstracts.CheckSconto;
import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.GestioneGrafica;
import it.unisa.prog2.multisala.abstracts.Spettacolo;
import it.unisa.prog2.multisala.exceptions.SpettacoloNonTrovatoException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ListaSale implements GestioneGrafica {
	
	private JFrame listaSale;
	
	private JTable informazioni;
	
	private DefaultTableModel dtm;
	
	private JComboBox<String> visualizzaSala;
	
	private JButton prenotaAcquista;
	
	private JButton visualizzaPrenotazioni;

	private DBManager dbm;
	
	private CheckSconto cS;
	
	private JLabel scontoOggi;
	
	private String userID;
	
	private PrenotazioniUtente pU;
	
	/**
	 * Tramite questo JFrame l'utente ha accesso a tutti gli spettacoli attualmente disponibili, ordinabili per sala, in ordine alfabetico o visualizzabili per sala singola
	 * @param frameChiamante frame che ha invocato la Classe ListaSale utile per modificare la visibilità dei frame sullo schermo
	 * @param userID codice dell'utente necessario per poter abbinare le prenotazioni ad ogni utente
	 */
	
	public ListaSale(JFrame frameChiamante,String userID ) {
		// inizializzazione listaSale con le proprietà corrette
		this.userID = userID;
		listaSale = new JFrame();
		listaSale.setSize(750,800);
		listaSale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listaSale.setLocationRelativeTo(null);
		listaSale.setTitle("Lista spettacoli");
		listaSale.setLayout(new BorderLayout());
		dbm = new DBManager();
		
		String[] nomiColonne = {"Titolo", "Numero Sala", "Data", "Orario di inizio", "Durata", "Posti liberi", "Prezzo"};
		
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
			dtm.addRow(new Object[] {
					spett.getTitoloSpettacolo(),
					spett.getNumeroSala(),
					spett.getData(),
					spett.getOrarioDiInizio(),
					spett.getDurata(),
					spett.sala().getNumeroPostiLiberi(),
					prezzoFilm(spett)
			});
		}
		visualizzaSala.setPreferredSize(new Dimension(300, 30));
		
		
		visualizzaSala.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					dtm.fireTableDataChanged();
					dtm = (DefaultTableModel) informazioni.getModel();
					dtm.setRowCount(0);
					if(e.getItem().toString().contains("Programmazione")) {
						for(Spettacolo spett : dbm.caricaSpettacoli()) {
							dtm.addRow(new Object[] {
									spett.getTitoloSpettacolo(),
									spett.getNumeroSala(),
									spett.getData(),
									spett.getOrarioDiInizio(),
									spett.getDurata(),
									spett.sala().getNumeroPostiLiberi(),
									prezzoFilm(spett)
							});
						}

					} else {
						String[] nS = e.getItem().toString().split(" ");
						for(Spettacolo spett : dbm.caricaSpettacoliInSala(Integer.valueOf(nS[1]))) {
							dtm.addRow(new Object[] {
									spett.getTitoloSpettacolo(),
									spett.getNumeroSala(),
									spett.getData(),
									spett.getOrarioDiInizio(),
									spett.getDurata(),
									spett.sala().getNumeroPostiLiberi(),
									prezzoFilm(spett)
							});
						}
					}
				}
			}
		});
		
		JPanel app0 = new JPanel(new FlowLayout());
		scontoOggi = new JLabel("Oggi c'è uno sconto di " + dbm.getValoreScontoSettimanale() + "€");
		app0.add(visualizzaSala);
		if(CheckSconto.oggiSconto())
			app0.add(scontoOggi);
			
				

		listaSale.add(app0, BorderLayout.NORTH);		
		
		prenotaAcquista = new JButton("Prenota/Acquista");
		prenotaAcquista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Object> argomenti = new ArrayList<Object>();
				try {
					try {
						for(int i = 0; i < informazioni.getModel().getColumnCount(); i++) {
							argomenti.add(informazioni.getModel().getValueAt(informazioni.convertRowIndexToModel(informazioni.getSelectedRow()), i));
						}
					} catch (IndexOutOfBoundsException e) {
						for(int i = 0; i < informazioni.getModel().getColumnCount(); i++) {
							argomenti.add(informazioni.getModel().getValueAt(informazioni.getSelectedRow(), i));
						}
					}
				} catch (IndexOutOfBoundsException e) {
					;
				}
				
				try {
					Spettacolo spSel = dbm.getSpettacolo((String) argomenti.get(0), (String) argomenti.get(3), (String) argomenti.get(2), (int) argomenti.get(1));
					VisualizzazioneSala vs = new VisualizzazioneSala(spSel, userID);
				} catch (SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
			}
		});
		
		visualizzaPrenotazioni = new JButton("Visualizza prenotazioni");
		visualizzaPrenotazioni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pU = new PrenotazioniUtente(userID);
			}
		});
		JPanel app1 = new JPanel(new FlowLayout());
		app1.add(prenotaAcquista);
		app1.add(visualizzaPrenotazioni);
		listaSale.add(app1, BorderLayout.SOUTH);
	}
	
	
	public void costruisciUI(JFrame frameChiamante) {
		listaSale.setVisible(true);
	}

	@Override
	public void costruisciUI() { }

	private String prezzoFilm(Spettacolo spett) {
		float pr = dbm.getPrezzoFilm();
		String prezzoSpettacoloFinale = null;
		
		if(spett.getSconto() == 0.0d) {
			if(CheckSconto.oggiSconto()) {
				prezzoSpettacoloFinale = String.valueOf(pr - Double.valueOf(dbm.getValoreScontoSettimanale()));
			} else {
				prezzoSpettacoloFinale = String.valueOf(pr);
			}
		} else {
			if(CheckSconto.oggiSconto()) {
				double pScontoSettimanale = (pr - Double.valueOf(dbm.getValoreScontoSettimanale()));
				double pScontoFilm = (pr - spett.getSconto());
				if(pScontoSettimanale > pScontoFilm)
					prezzoSpettacoloFinale = String.valueOf(pScontoFilm);
				else
					prezzoSpettacoloFinale = String.valueOf(pScontoSettimanale);
			} else {
				prezzoSpettacoloFinale = String.valueOf(pr - spett.getSconto());
			}
			
		}
		return prezzoSpettacoloFinale;
	}

}
