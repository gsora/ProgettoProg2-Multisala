package it.unisa.prog2.multisala;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Entrata {

	private JFrame entrata;
	private JButton nuovoUtente;
	private JButton utenteEsistente;
	private JPanel pan;
	private JLabel messaggioEntrata;
	
	public Entrata(String nomeCinema){
		entrata = new JFrame();
		entrata.setSize(300, 150);
		entrata.setResizable(false);
		entrata.setLocationRelativeTo(null);
		entrata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		entrata.setTitle(nomeCinema);
		entrata.setLayout(new BorderLayout());
		
		nuovoUtente = new JButton("Nuovo utente");
		utenteEsistente = new JButton("Utente esistente");
		
		pan = new JPanel();
		pan.add(nuovoUtente);
		
		pan.add(utenteEsistente);
		entrata.add(pan, BorderLayout.SOUTH);
		
		messaggioEntrata = new JLabel("Benvenuto al Multisala Pancakes", SwingConstants.CENTER);
		entrata.add(messaggioEntrata, BorderLayout.NORTH);
		entrata.setVisible(true);
		
		nuovoUtente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EntrataNuovoUtente enu = new EntrataNuovoUtente();
			}
		});
		
		utenteEsistente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EntrataUtenteEsistente ue = new EntrataUtenteEsistente();
				
			}
		});
	}
}
