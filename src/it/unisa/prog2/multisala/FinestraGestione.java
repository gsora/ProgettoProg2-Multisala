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
		Dimension dim = new Dimension(150 ,108);
		bottoneInserisciProgramma = new JButton("<html>Inserisci<br />Programma<br />Settimanale<html>");
		bottoneInserisciProgramma.setPreferredSize(dim);
		
		bottoneCambiaPrezzi = new JButton("<html>Combia<br />Prezzi<html>");
		bottoneCambiaPrezzi.setPreferredSize(dim);
		
		visualizzaListaSpettacoli = new JButton("<html>Visualizza Lista<br />Spettacoli<html>");
		visualizzaListaSpettacoli.setPreferredSize(dim);
		
		applicaSconti = new JButton("<html>Applica<br />Sconti<html>");
		applicaSconti.setPreferredSize(dim);
		
		modificaStatusPosto = new JButton("<html>Modifica<br />Status posto</html>");
		modificaStatusPosto.setPreferredSize(dim);
		
		visualizzaBilancioSettimanale = new JButton("<html>Visualizza<br />Bilancio <br /> Settimanale</html>");
		visualizzaBilancioSettimanale.setPreferredSize(dim);
		
		pannelloSinistra.add(bottoneInserisciProgramma);
		pannelloSinistra.add(bottoneCambiaPrezzi);
		pannelloSinistra.add(visualizzaListaSpettacoli);
		pannelloSinistra.add(applicaSconti);
		pannelloSinistra.add(modificaStatusPosto);
		pannelloSinistra.add(visualizzaBilancioSettimanale);
		gestione.add(pannelloSinistra);
		
		//pannelloDestra
		pannelloDestra = new JPanel();
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		pannelloDestra.setBorder(loweredetched);
		pannelloDestra.setPreferredSize(new Dimension(700, 650));
		
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
