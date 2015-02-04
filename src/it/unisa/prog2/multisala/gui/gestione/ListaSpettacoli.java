package it.unisa.prog2.multisala.gui.gestione;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.Spettacolo;
import it.unisa.prog2.multisala.exceptions.SpettacoloNonTrovatoException;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ListaSpettacoli extends JPanel {
	
	private DBManager caricaSpettacoli;
	
	private Spettacolo[] spettacoliCaricati;
	
	private JTabbedPane pane;
	
	private JTable informazioni;
	
	private DefaultTableModel dtm;
	
	private JButton elimina;
	
	private JPanel sezioneElimina;
	
	private JComboBox<String> listaEliminaFilm;
	
	private DefaultComboBoxModel<String> inserimentoInComboBox; 
	
	/**
	 * Tramite questo JPanel è possibile visualizzare tutti gli spettacoli attualmente inseriti e eventualmente eliminarli 
	 * @param p valore del pane necessario per ricaricare il JPanel prima di visualizzare la lista
	 */
	
	public ListaSpettacoli(JTabbedPane p) {
		pane = p;
		
		caricaSpettacoli = new DBManager();
		
		spettacoliCaricati = caricaSpettacoli.caricaSpettacoliOrdinati();
		
		p.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(pane.getSelectedIndex() == 2) {
					ricaricaLista();
				}
			}
		});
		
		
		setLayout(new BorderLayout());		
		String[] nomiColonne ={"Titolo", "Numero Sala", "Data", "Orario di inizio", "Durata", "Posti liberi", "Sconto"};
		
		informazioni = new JTable();
		dtm = new DefaultTableModel(0, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		dtm.setColumnIdentifiers(nomiColonne);
		informazioni.setCellSelectionEnabled(true);
		informazioni.setModel(dtm);
		
		informazioni.getColumnModel().getColumn(0).setPreferredWidth(350);
		informazioni.getTableHeader().setReorderingAllowed(false);
		informazioni.getTableHeader().setResizingAllowed(false);
		
		JScrollPane scroll = new JScrollPane(informazioni);
		add(scroll,  BorderLayout.CENTER);
		
		Vector<String> stringheFilm = new Vector<String>();
		inserimentoInComboBox = new DefaultComboBoxModel<String>(stringheFilm);
		
		sezioneElimina = new JPanel();
		sezioneElimina.setLayout(new FlowLayout());
		listaEliminaFilm = new JComboBox<String>(inserimentoInComboBox);
		listaEliminaFilm.setPreferredSize(new Dimension(700, 30));
		elimina = new JButton("Elimina");
		
		elimina.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] sce = {"Si", "No"}; 
				int risposta = JOptionPane.showOptionDialog(null, "Sei sicuro di voler eliminare questo film?", "Eliminazione dal database", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, sce, sce[0]);
				if(risposta == 0) {
					try {
						risposta = 13;
						String[] selIt = inserimentoInComboBox.getSelectedItem().toString().split(" - ");
						caricaSpettacoli.rimuoviSpettacolo(selIt[0], selIt[1], selIt[2], Integer.parseInt(selIt[3]));
						ricaricaLista();
					} catch (SpettacoloNonTrovatoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		sezioneElimina.add(listaEliminaFilm);
		sezioneElimina.add(elimina);
		add(sezioneElimina, BorderLayout.SOUTH);
		
	}
	
	private void ricaricaLista() {
			spettacoliCaricati = caricaSpettacoli.caricaSpettacoliOrdinati();
			dtm.fireTableDataChanged();
			dtm = (DefaultTableModel) informazioni.getModel();
			dtm.setRowCount(0);
			inserimentoInComboBox.removeAllElements();
			
			for(Spettacolo s: spettacoliCaricati) {
				String sconto = String.valueOf(s.getSconto());
				if(sconto.contentEquals("0.0")) {
					sconto = "N/A";
				}

				
				dtm.addRow(new Object[] {
						s.getTitoloSpettacolo(),
						s.getNumeroSala(),
						s.getData(),
						s.getOrarioDiInizio(),
						s.getDurata(),
						s.sala().getNumeroPostiLiberi(),
						sconto
				});
				
				inserimentoInComboBox.addElement(s.getTitoloSpettacolo() + " - " + s.getOrarioDiInizio() + " - " + s.getData()+ " - " +  String.valueOf(s.getNumeroSala()));
			}
			
		}
}
