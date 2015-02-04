package it.unisa.prog2.multisala.gui.utente;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.Prenotazione;
import it.unisa.prog2.multisala.abstracts.Spettacolo;
import it.unisa.prog2.multisala.exceptions.PostiLiberiEsauritiException;
import it.unisa.prog2.multisala.exceptions.SpettacoloNonTrovatoException;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrenotazioniUtente {

	private JFrame frm;
	private JButton annullaPrenotazione;
	private JButton completaAcquisto;
	private JLabel mex;
	private JComboBox<String> listaPrenotazioni;
	private DBManager dbm;
	private DefaultComboBoxModel<String> inserimentoPrenotazioni;
	private HashMap<ArrayList<Object>, ArrayList<Prenotazione>> prenotazione;
	
	/**
	 * Tramite questo Jframe l'utente può visualizzare tutte le prenotazioni attualmente fatte a suo nome e può decidere se renderle nulle oppure se confermarle e quindi renderle posti comprati
	 * @param userID codice d'identificazione dell'utente necessario per modificare la situazione delle prenotazioni
	 */
	
	public PrenotazioniUtente(String userID) {
		
		dbm = new DBManager();
		
		frm = new JFrame();
		frm.setTitle("Area Prenotazioni");
		frm.setSize(400, 150);
		frm.setResizable(false);
		frm.setLocationRelativeTo(null);
		frm.setLayout(new GridLayout(3, 1));
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mex = new JLabel("Scegliere la prenotazione da confermare o annullare");
		
		Vector<String> stringhePrenotazioni = new Vector<String>();
		inserimentoPrenotazioni = new DefaultComboBoxModel<String>(stringhePrenotazioni);
		listaPrenotazioni = new JComboBox<String>(inserimentoPrenotazioni);
		listaPrenotazioni.setPreferredSize(new Dimension(200, 30));
		
		prenotazione = dbm.prenotazioniUtente(userID);
		for (Entry<ArrayList<Object>, ArrayList<Prenotazione>> i : prenotazione.entrySet()) {
			ArrayList<Object> pren = i.getKey();
			for (Prenotazione a : i.getValue()) {
				inserimentoPrenotazioni.addElement(pren.get(0) + "-" + pren.get(1) + "-" + pren.get(2) + "-" + pren.get(3) + "- posto " + a);
			}
		}
		JPanel app1 = new JPanel(new FlowLayout());
		app1.add(listaPrenotazioni);
		
		annullaPrenotazione = new JButton("Annulla Prenotazione");
		annullaPrenotazione.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String iPSelect = inserimentoPrenotazioni.getSelectedItem().toString().replace(" posto ", "");
			String[] prenSelezionato = iPSelect.split("-");
			
			try {
				dbm.rimuoviPrenotazione(userID, Integer.valueOf(prenSelezionato[4]), dbm.getSpettacolo(prenSelezionato[0], prenSelezionato[2], prenSelezionato[1], Integer.parseInt(prenSelezionato[3])));
			} catch (NumberFormatException | SpettacoloNonTrovatoException e) {
				e.printStackTrace();
			}
			
		}
	});
		
		completaAcquisto = new JButton("Completa Acquisto");
		completaAcquisto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String iPSelect = inserimentoPrenotazioni.getSelectedItem().toString().replace(" posto ", "");
				String[] prenSelezionato = iPSelect.split("-");
				
				try {
					dbm.rimuoviPrenotazione(userID, Integer.valueOf(prenSelezionato[4]), dbm.getSpettacolo(prenSelezionato[0], prenSelezionato[2], prenSelezionato[1], Integer.parseInt(prenSelezionato[3])));
				} catch (NumberFormatException | SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
				
				try {
					Spettacolo fin = dbm.getSpettacolo(prenSelezionato[0], prenSelezionato[2], prenSelezionato[1], Integer.parseInt(prenSelezionato[3]));
					fin.sala().compraBiglietto(Integer.parseInt(prenSelezionato[4]));
					dbm.rimuoviSpettacolo(prenSelezionato[0], prenSelezionato[2], prenSelezionato[1], Integer.parseInt(prenSelezionato[3]));
					dbm.salvaSpettacolo(fin);
				} catch (NumberFormatException | PostiLiberiEsauritiException | SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
			}
		});
		
		JPanel app0 = new JPanel(new FlowLayout());
		app0.add(annullaPrenotazione);
		app0.add(completaAcquisto);
		
		frm.add(mex);
		frm.add(app1);
		frm.add(app0);
		frm.setVisible(true);
	}
	
	
	
}
