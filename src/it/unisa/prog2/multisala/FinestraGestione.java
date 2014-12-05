package it.unisa.prog2.multisala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

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
	Border loweredetched;
	
	public FinestraGestione() {
		gestione = new JFrame();
		gestione.setSize(900, 700);
		gestione.setTitle("Gestione Multisala Pancakes");
		gestione.setResizable(false);
		gestione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gbl = new GridBagLayout();
		gestione.setLayout(gbl);
		
		// pannelloSinistra
		pannelloSinistra = new JPanel();
		pannelloSinistra.setLayout(new GridLayout(6,1));
		Dimension dim = new Dimension(150 ,105);
		bottoneInserisciProgramma = new JButton("Inserisci programma settimanale");
		bottoneInserisciProgramma.setPreferredSize(dim);
	 /*	GridBagConstraints bip = new GridBagConstraints();
		bip.gridx = 0;
		bip.gridy = 0;
		gbl.setConstraints(bottoneInserisciProgramma, bip);
		gestione.add(bottoneInserisciProgramma); */
		
		bottoneCambiaPrezzi = new JButton("Setta prezzi spettacoli");
		bottoneCambiaPrezzi.setPreferredSize(dim);
		/*GridBagConstraints bcp = new GridBagConstraints();
		bcp.gridx = 0;
		bcp.gridy = 1;
		gbl.setConstraints(bottoneCambiaPrezzi, bcp);
		gestione.add(bottoneCambiaPrezzi);*/
		
		visualizzaListaSpettacoli = new JButton("Visualizza lista spettacoli");
		visualizzaListaSpettacoli.setPreferredSize(dim);
		/*GridBagConstraints vls = new GridBagConstraints();
		vls.gridx = 0;
		vls.gridy = 2;
		gbl.setConstraints(visualizzaListaSpettacoli, vls);
		gestione.add(visualizzaListaSpettacoli);*/
		
		applicaSconti = new JButton("Applica sconti");
		applicaSconti.setPreferredSize(dim);
		/*GridBagConstraints as = new GridBagConstraints();
		as.gridx = 0;
		as.gridy = 3;
		gbl.setConstraints(applicaSconti, as);
		gestione.add(applicaSconti);*/
		
		modificaStatusPosto = new JButton("Modifica status posti");
		modificaStatusPosto.setPreferredSize(dim);
		/*GridBagConstraints msp = new GridBagConstraints();
		msp.gridx = 0;
		msp.gridy = 4;
		gbl.setConstraints(modificaStatusPosto, msp);
		gestione.add(modificaStatusPosto);*/
		
		visualizzaBilancioSettimanale = new JButton("Visualizza bilancio settimanale");
		visualizzaBilancioSettimanale.setPreferredSize(dim);
 /*		GridBagConstraints vbs = new GridBagConstraints();
		vbs.gridx = 0;
		vbs.gridy = 5;
		gbl.setConstraints(visualizzaBilancioSettimanale, vbs); 
		gestione.add(visualizzaBilancioSettimanale); */
		pannelloSinistra.add(bottoneInserisciProgramma);
		pannelloSinistra.add(bottoneCambiaPrezzi);
		pannelloSinistra.add(visualizzaListaSpettacoli);
		pannelloSinistra.add(applicaSconti);
		pannelloSinistra.add(modificaStatusPosto);
		pannelloSinistra.add(visualizzaBilancioSettimanale);
		gestione.add(pannelloSinistra);
		
		//pannelloDestra
		pannelloDestra = new JPanel();
		/*GridBagConstraints pd = new GridBagConstraints();
		pd.gridx = 1;
		pd.gridy = 0;
		pd.gridheight = 6;
		pd.gridwidth = 4; */
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JLabel prova = new JLabel("PROVA");
		prova.setBorder(loweredetched);
		prova.setPreferredSize(new Dimension(700, 650));
		pannelloDestra.add(prova);
		
		//gbl.setConstraints(pannelloDestra, pd);
		gestione.add(pannelloDestra);
	}

	@Override
	public void costruisciUI(JFrame frameChiamante) {
		gestione.setVisible(true);
		frameChiamante.setVisible(false);
	}

	@Override
	public void costruisciUI() { }

}
