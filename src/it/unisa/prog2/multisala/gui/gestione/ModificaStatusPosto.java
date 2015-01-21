package it.unisa.prog2.multisala.gui.gestione;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.Posto;
import it.unisa.prog2.multisala.abstracts.Spettacolo;
import it.unisa.prog2.multisala.exceptions.SpettacoloNonTrovatoException;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.NumericShaper;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ModificaStatusPosto extends JPanel { 
	
	private JComboBox<String> listaSpettacoli;
	
	private JComboBox<Integer> listaPosti;
	
	private JTabbedPane pane;
	
	private JLabel statusAttuale;
	
	private JRadioButton postoLibero;
	
	private JRadioButton postoOccupato;
	
	private DBManager DBm;
	
	private DefaultComboBoxModel<String> inserimentoSpettacoli;
	
	private DefaultComboBoxModel<Integer> inserimentoPosti;
	
	private Spettacolo[] spettacoli;
	
	private ButtonGroup group;
	
	public ModificaStatusPosto(JTabbedPane p) {
		pane = p;
		
		p.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(pane.getSelectedIndex() == 4) {
					ricaricaLista();
				}
				
			}
		});
		
		setLayout(new GridLayout(2, 2));
		DBm = new DBManager();
		
		Vector<String> stringheFilm = new Vector<String>();
		inserimentoSpettacoli = new DefaultComboBoxModel<String>(stringheFilm);
		
		spettacoli = DBm.caricaSpettacoli();
		listaSpettacoli = new JComboBox<String>(inserimentoSpettacoli);
		listaSpettacoli.addItemListener(new SelListaSpettacoli());
		listaSpettacoli.setPreferredSize(new Dimension(300, 30));
		JPanel app = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 100));
		app.add(listaSpettacoli);
		
		Vector<Integer> intPosti = new Vector<Integer>();
		inserimentoPosti = new DefaultComboBoxModel<Integer>(intPosti);
		listaPosti = new JComboBox<Integer>(inserimentoPosti);
		listaPosti.setPreferredSize(new Dimension(300, 30));
		
		statusAttuale = new JLabel("<html> Status attuale <br> status" );
		statusAttuale.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 30));
		
		postoLibero = new JRadioButton("Posto Libero");
		postoOccupato = new JRadioButton("Posto occupato");
		group = new ButtonGroup();
		group.add(postoLibero);
		group.add(postoOccupato);
		
		JPanel app0 = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 70));
		app0.add(listaPosti);
		
		JPanel app1 = new JPanel(new GridLayout(3, 1));
		app1.add(postoLibero);
		app1.add(postoOccupato);
		
		add(app);
		add(statusAttuale);
		add(app0);
		add(app1);
	}
	
	private void ricaricaLista() {
		spettacoli = DBm.caricaSpettacoliOrdinati();
		inserimentoSpettacoli.removeAllElements();
		
		for(Spettacolo s: spettacoli) {
			inserimentoSpettacoli.addElement(s.getTitoloSpettacolo() + " - " + s.getOrarioDiInizio() + " - " + s.getData()+ " - " + String.valueOf(s.getNumeroSala()));
		}
		try {
			listaSpettacoli.setSelectedIndex(0);
			listaPosti.setEnabled(false);
			listaSpettacoli.setEnabled(false);
			postoOccupato.setEnabled(false);
			postoLibero.setEnabled(false);
		} catch (IllegalArgumentException e) {
			// probabilmente il database è vuoto
			listaPosti.setEnabled(true);
			listaSpettacoli.setEnabled(true);
			postoOccupato.setEnabled(true);
			postoLibero.setEnabled(true);
		}
	}

	class SelListaSpettacoli implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(arg0.getStateChange() == ItemEvent.SELECTED) {
				for(ItemListener i : listaPosti.getItemListeners()) {
					listaPosti.removeItemListener(i);
				}
				String[] param = arg0.getItem().toString().split(" - ");
				Spettacolo rif = null;
				try {
					rif = DBm.getSpettacolo(param[0], param[1], param[2], Integer.parseInt(param[3]));
				} catch (NumberFormatException | SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
								
				inserimentoPosti.removeAllElements();
				for(int i = 1; i <= rif.sala().getNumeroPostiTotali(); i++) {
					inserimentoPosti.addElement(i);
				}
				
				listaPosti.addItemListener(new SelListaPosti(rif));
			}
		}
		
	}
	
	class SelListaPosti implements ItemListener {

		private Spettacolo spett;
		
		public SelListaPosti(Spettacolo s) {
			spett = s;
			
			for(ActionListener a : postoLibero.getActionListeners()) {
				postoLibero.removeActionListener(a);
			}
			
			for(ActionListener a : postoOccupato.getActionListeners()) {
				postoOccupato.removeActionListener(a);
			}
			
		}
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(arg0.getStateChange() == ItemEvent.SELECTED) {
				System.out.println("Film selezionato: " + spett.getTitoloSpettacolo());
				int posto = (Integer.parseInt(arg0.getItem().toString())-1);
				switch (spett.sala().getStatoPostoSingolo(posto)) {
				case 0:
					statusAttuale.setText("<html> Status attuale: <br> libero");
					postoLibero.setSelected(true);
					break;
				
				case 1:
					statusAttuale.setText("<html> Status attuale: <br> occupato");
					postoOccupato.setSelected(true);
					break;
					
				case 3:
					statusAttuale.setText("<html> Status attuale: <br> prenotato");
					postoLibero.setSelected(false);
					postoOccupato.setSelected(false);
					break;
				default:
					break;
				}
				
				postoLibero.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						spett.sala().setStatusPostoSingolo(posto+1, Posto.LIBERO);
						DBm.salvaSpettacolo(spett);
					}
				});
				
				postoOccupato.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						spett.sala().setStatusPostoSingolo(posto+1, Posto.ASSEGNATO);
						DBm.salvaSpettacolo(spett);
					}
				});
			}
		}
		
	}
}
