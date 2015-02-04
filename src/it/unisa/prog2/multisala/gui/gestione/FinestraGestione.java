package it.unisa.prog2.multisala.gui.gestione;

import it.unisa.prog2.multisala.abstracts.GestioneGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FinestraGestione implements GestioneGrafica{
	
	private JFrame gestione;
	
	/**
	 * Costruttore di FinestraGestione che richiama i JPanel realizzati per la gestione del multisala e li unisce tutti in un frame unico
	 */
	
	
	public FinestraGestione() {
		gestione = new JFrame();
		gestione.setSize(900, 500);
		gestione.setTitle("Gestione Multisala Pancakes");
		gestione.setResizable(false);
		gestione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gestione.setLocationRelativeTo(null);
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
