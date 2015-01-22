// TODO: rinominare la classe ListaSpettacoli?

package it.unisa.prog2.multisala.gui.gestione;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.GestioneGrafica;

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
		
		String[] nomiColonne = {"Titolo", "Numero Sala", "Data", "Orario di inizio", "Durata", "Posti liberi", "Sconto"};
		
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
		
		
		JScrollPane scroll = new JScrollPane(informazioni);
		listaSale.add(scroll, BorderLayout.CENTER);
		
		visualizzaSala = new JComboBox<String>();
		visualizzaSala.addItem("Programmazione Complessiva");
		visualizzaSala.addItem("Sala 1");
		visualizzaSala.addItem("Sala 2");
		visualizzaSala.addItem("Sala 3");
		visualizzaSala.addItem("Sala 4");
		visualizzaSala.setPreferredSize(new Dimension(300, 30));
		JPanel app0 = new JPanel(new FlowLayout());
		app0.add(visualizzaSala);
		listaSale.add(app0, BorderLayout.NORTH);		
		
		prenotaAcquista = new JButton("Prenota//Acquista");
		prenotaAcquista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.out.println(informazioni.getSelectedRow());
				
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
	public void costruisciUI() {
		// TODO Auto-generated method stub
		
	}

}
