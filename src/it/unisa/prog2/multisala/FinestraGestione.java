package it.unisa.prog2.multisala;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class FinestraGestione implements GestioneGrafica{
	
	private JFrame gestione;
	
	private JPanel cambiaPrezzi;
	private JPanel visualizzaListaSpettacoli;
	private JPanel applicaSconti;
	private JPanel modificaStatusPosto;
	private JPanel visualizzaBilancioSettimanale; 
	
	public FinestraGestione() {
		gestione = new JFrame();
		gestione.setSize(900, 700);
		gestione.setTitle("Gestione Multisala Pancakes");
		gestione.setResizable(false);
		gestione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Inserisci Programma", new InserisciProgramma());
		
		cambiaPrezzi = new JPanel();
		tabbedPane.addTab("Cambia Prezzi", cambiaPrezzi);
		
		visualizzaListaSpettacoli = new JPanel();
		tabbedPane.addTab("Lista Spettacoli", visualizzaListaSpettacoli);
		
		applicaSconti = new JPanel();
		tabbedPane.addTab("Applica Sconti", applicaSconti);
		
		modificaStatusPosto = new JPanel();
		tabbedPane.addTab("Modifica Status Posti", modificaStatusPosto);
		
		visualizzaBilancioSettimanale = new JPanel();
		tabbedPane.addTab("Bilancio Settimanale", visualizzaBilancioSettimanale);
		
		
		gestione.add(tabbedPane);
		
	}

	@Override
	public void costruisciUI(JFrame frameChiamante) {
		gestione.setVisible(true);
		frameChiamante.setVisible(false);
	}

	@Override
	public void costruisciUI() { }

}
