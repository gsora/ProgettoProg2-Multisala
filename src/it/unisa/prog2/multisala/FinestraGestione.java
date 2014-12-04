package it.unisa.prog2.multisala;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.apple.eawt.AppEventListener;

public class FinestraGestione implements GestioneGrafica{
	
	private JFrame gestione;
	private JPanel pannelloDestra;
	private JPanel pannelloSinistra;
	
	private JButton bottoneInserisciProgramma;
	private JButton bottoneCambiaPrezzi;
	private JButton visualizzaListaSpettacoli;
	private JButton applicaSconti;
	private JButton modificaStatusPosto;
	private JButton visualizzaBilancioSettimanale;
	
	public FinestraGestione() {
		gestione = new JFrame();
		gestione.setSize(900, 700);
		gestione.setTitle("Gestione Multisala Pancakes");
		gestione.setResizable(false);
		gestione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gbl = new GridBagLayout();
		gestione.setLayout(gbl);
		
		// pannelloSinistra
		bottoneInserisciProgramma = new JButton("Inserisci programma settimanale");
		GridBagConstraints bip = new GridBagConstraints();
		bip.gridx = 0;
		bip.gridy = 0;
		gbl.setConstraints(bottoneInserisciProgramma, bip);
		gestione.add(bottoneInserisciProgramma);
		
		bottoneCambiaPrezzi = new JButton("Setta prezzi spettacoli");
		GridBagConstraints bcp = new GridBagConstraints();
		bcp.gridx = 0;
		bcp.gridy = 1;
		gbl.setConstraints(bottoneCambiaPrezzi, bcp);
		gestione.add(bottoneCambiaPrezzi);
		
		visualizzaListaSpettacoli = new JButton("Visualizza lista spettacoli");
		GridBagConstraints vls = new GridBagConstraints();
		vls.gridx = 0;
		vls.gridy = 2;
		gbl.setConstraints(visualizzaListaSpettacoli, vls);
		gestione.add(visualizzaListaSpettacoli);
		
		applicaSconti = new JButton("Applica sconti");
		GridBagConstraints as = new GridBagConstraints();
		as.gridx = 0;
		as.gridy = 3;
		gbl.setConstraints(applicaSconti, as);
		gestione.add(applicaSconti);
		
		modificaStatusPosto = new JButton("Modifica status posti");
		GridBagConstraints msp = new GridBagConstraints();
		msp.gridx = 0;
		msp.gridy = 4;
		gbl.setConstraints(modificaStatusPosto, msp);
		gestione.add(modificaStatusPosto);
		
		visualizzaBilancioSettimanale = new JButton("Visualizza bilancio settimanale");
		GridBagConstraints vbs = new GridBagConstraints();
		vbs.gridx = 0;
		vbs.gridy = 5;
		gbl.setConstraints(visualizzaBilancioSettimanale, vbs);
		gestione.add(visualizzaBilancioSettimanale);
		
	}

	@Override
	public void costruisciUI(JFrame frameChiamante) {
		gestione.setVisible(true);
		frameChiamante.setVisible(false);
	}

	@Override
	public void costruisciUI() { }

}
