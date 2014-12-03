package it.unisa.prog2.multisala;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.UUID;

public class EntrataNuovoUtente {

	private JFrame nuovoUtente;
	private JLabel messaggio1;
	private JLabel messaggio2;
	private JTextPane codiceID;
	private JButton bottoneOK;
	private JPanel pan;
	
	public EntrataNuovoUtente(){
		nuovoUtente = new JFrame();
		nuovoUtente.setSize(400, 200);
		nuovoUtente.setVisible(true);
		nuovoUtente.setTitle("Benvenuto");
		nuovoUtente.setResizable(false);
		nuovoUtente.setLocationRelativeTo(null);
		nuovoUtente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nuovoUtente.setLayout(new BorderLayout());
		
		messaggio1 = new JLabel("Il tuo codice ID è:", SwingConstants.CENTER);
		
		codiceID = new JTextPane();
		codiceID.setText(generaID());
		codiceID.setEditable(false);
		codiceID.setBackground(null);
		codiceID.setBorder(null);
		codiceID.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 28));
		StyledDocument doc = codiceID.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		messaggio2 = new JLabel("Conserva questo codice per poter gestire le tue prenotazioni!", SwingConstants.CENTER);
		
		bottoneOK = new JButton("OK");
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.add(bottoneOK);
		pan = new JPanel();
		pan.setLayout(new GridLayout(4,1));
		
		
		pan.add(messaggio1);
		pan.add(codiceID);
		pan.add(messaggio2);
		pan.add(flowPanel);
		
		nuovoUtente.add(pan, BorderLayout.CENTER);
		
	}
	
	private String generaID(){
		UUID codice = UUID.randomUUID();
		return codice.toString().substring(0, 5);
	}
}
