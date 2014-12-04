package it.unisa.prog2.multisala;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		gestione.setSize(800, 600);
	}

	@Override
	public void costruisciUI(JFrame frameChiamante) {
		gestione.setVisible(true);
		frameChiamante.setVisible(false);
	}

	@Override
	public void costruisciUI() { }

}
