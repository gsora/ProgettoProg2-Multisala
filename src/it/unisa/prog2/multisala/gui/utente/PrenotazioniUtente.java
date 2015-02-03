package it.unisa.prog2.multisala.gui.utente;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.Prenotazione;
import it.unisa.prog2.multisala.abstracts.Spettacolo;
import it.unisa.prog2.multisala.exceptions.PostiLiberiEsauritiException;
import it.unisa.prog2.multisala.exceptions.SpettacoloNonTrovatoException;

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

import com.sun.javafx.collections.MappingChange.Map;

public class PrenotazioniUtente {

	private JFrame frm;
	private JButton annullaPrenotazione;
	private JButton completaAcquisto;
	private JLabel mex;
	private JComboBox<String> listaPrenotazioni;
	private DBManager dbm;
	private DefaultComboBoxModel<String> inserimentoPrenotazioni;
	private HashMap<Object[], ArrayList<Prenotazione>> prenotazione;
	
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
		
		listaPrenotazioni = new JComboBox<String>();
		Vector<String> stringhePrenotazioni = new Vector<String>();
		inserimentoPrenotazioni = new DefaultComboBoxModel<String>(stringhePrenotazioni);
		
		prenotazione = dbm.prenotazioniUtente(userID);
		for (Entry<Object[], ArrayList<Prenotazione>> i : prenotazione.entrySet()) {
			Object[] pren = i.getKey();
			for (Prenotazione a : i.getValue()) {
				inserimentoPrenotazioni.addElement(pren[0] + "-" + pren[1] + "-" + pren[2] + "-" + pren[3] + "- posto " + a);
			}
		}
		
		
		annullaPrenotazione = new JButton("Annulla Prenotazione");
		annullaPrenotazione.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			inserimentoPrenotazioni.getSelectedItem().toString().replace(" posto ", "");
			String[] prenSelezionato = inserimentoPrenotazioni.getSelectedItem().toString().split("-");
			try {
				dbm.rimuoviPrenotazione(userID, Integer.parseInt(prenSelezionato[4]), dbm.getSpettacolo(prenSelezionato[0], prenSelezionato[1], prenSelezionato[2], Integer.parseInt(prenSelezionato[3])));
			} catch (NumberFormatException | SpettacoloNonTrovatoException e) {
				e.printStackTrace();
			}
			
		}
	});
		
		completaAcquisto = new JButton("Completa Acquisto");
		completaAcquisto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				inserimentoPrenotazioni.getSelectedItem().toString().replace(" posto ", "");
				String[] prenSelezionato = inserimentoPrenotazioni.getSelectedItem().toString().split("-");
				try {
					dbm.rimuoviPrenotazione(userID, Integer.parseInt(prenSelezionato[4]), dbm.getSpettacolo(prenSelezionato[0], prenSelezionato[1], prenSelezionato[2], Integer.parseInt(prenSelezionato[3])));
				} catch (NumberFormatException | SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
				
				try {
					dbm.getSpettacolo(prenSelezionato[0], prenSelezionato[1], prenSelezionato[2], Integer.parseInt(prenSelezionato[3])).sala().compraBiglietto(Integer.parseInt(prenSelezionato[4]));
				} catch (NumberFormatException | PostiLiberiEsauritiException
						| SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
			}
		});
		
		JPanel app0 = new JPanel(new FlowLayout());
		app0.add(annullaPrenotazione);
		app0.add(completaAcquisto);
		
		frm.add(mex);
		frm.add(listaPrenotazioni);
		frm.add(app0);
		frm.setVisible(true);
	}
	
	
	
}
