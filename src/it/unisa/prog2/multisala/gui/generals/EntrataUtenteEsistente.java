package it.unisa.prog2.multisala.gui.generals;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.GestioneGrafica;
import it.unisa.prog2.multisala.gui.gestione.FinestraGestione;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class EntrataUtenteEsistente implements GestioneGrafica {

	private JFrame utenteEsistente;
	private JLabel messaggio1;
	private JLabel messaggio2;
	private JTextField codiceID;
	private JButton bottoneEntra;
	private JPanel pan;
	
	public EntrataUtenteEsistente(){
		utenteEsistente = new JFrame();
		utenteEsistente.setSize(400 , 200);
		utenteEsistente.setTitle("Bentornato");
		utenteEsistente.setResizable(false);
		utenteEsistente.setLocationRelativeTo(null);
		utenteEsistente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		utenteEsistente.setLayout(new BorderLayout());
		
		messaggio1 = new JLabel("Bentornato al Multisala Pancakes", SwingConstants.CENTER);
		messaggio2 = new JLabel("Inserisci il tuo codice utente per continuare:", SwingConstants.CENTER);
		
		
		codiceID = new JTextField();
		codiceID.setEditable(true);
		codiceID.setColumns(12);
		codiceID.setHorizontalAlignment(JTextField.CENTER);
		codiceID.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 28));
		JPanel flowPanel1 = new JPanel(new FlowLayout());
		flowPanel1.add(codiceID);
		
		bottoneEntra = new JButton("Entra");
		JPanel flowPanel2 = new JPanel(new FlowLayout());
		flowPanel2.add(bottoneEntra);
		
		pan = new JPanel();
		pan.setLayout(new GridLayout(4,1));
		pan.add(messaggio1);
		pan.add(messaggio2);
		pan.add(flowPanel1);
		pan.add(flowPanel2);
	
		utenteEsistente.add(pan);
	}
	
	@Override
	public void costruisciUI(JFrame frameChiamante) {
		utenteEsistente.setVisible(true);
		
		bottoneEntra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(codiceID.getText().contentEquals("MLGPR0")) {
					// TODO: apri finestra gestione
					FinestraGestione fg = new FinestraGestione();
					fg.costruisciUI(utenteEsistente);
					frameChiamante.setVisible(false);
				} else if(DBManager.controllaEsistenzaUtente(codiceID.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Impossibile trovare l'utente inserito", "Utente non trovato", JOptionPane.ERROR_MESSAGE);
				}
				// TODO: inserire controllo ID valido
				// se controllo ok:
				// frameChiamante.setVisible(false);
			}
		});
		
		codiceID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(codiceID.getText().contentEquals("MLGPR0")) {
					// TODO: apri finestra gestione
					FinestraGestione fg = new FinestraGestione();
					fg.costruisciUI(utenteEsistente);
					frameChiamante.setVisible(false);
				} else if(DBManager.controllaEsistenzaUtente(codiceID.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Impossibile trovare l'utente inserito", "Utente non trovato", JOptionPane.ERROR_MESSAGE);
				}
				// TODO: inserire controllo ID valido
				// se controllo ok:
				// frameChiamante.setVisible(false);	
				
			}
		});
		
	}

	@Override
	public void costruisciUI() { }
}
