package it.unisa.prog2.multisala;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FinestraGestione implements GestioneGrafica{
	
	private JFrame gestione;
	
	
	public FinestraGestione() {
		gestione = new JFrame();
		gestione.setSize(900, 500);
		gestione.setTitle("Gestione Multisala Pancakes");
		gestione.setResizable(false);
		gestione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Inserisci Programma", new InserisciProgramma());
		
		tabbedPane.addTab("Cambia Prezzi", new CambiaPrezzi());
		
		tabbedPane.addTab("Lista Spettacoli", new ListaSpettacoli(tabbedPane));
		
		tabbedPane.addTab("Applica Sconti", new ApplicaSconti());
		
		tabbedPane.addTab("Modifica Status Posti", new ModificaStatusPosto(tabbedPane));
		
		tabbedPane.addTab("Bilancio Settimanale", new BilancioSettimanale());
		
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
