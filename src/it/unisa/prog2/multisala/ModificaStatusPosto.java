package it.unisa.prog2.multisala;

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
		pane=p;
		
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
		listaPosti.setEnabled(false);
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
	}

	class SelListaSpettacoli implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(arg0.getStateChange() == ItemEvent.SELECTED) {
				String[] param = arg0.getItem().toString().split(" - ");
				Spettacolo rif = null;
				try {
					rif = DBm.getSpettacolo(param[0], param[1], param[2], Integer.parseInt(param[3]));
				} catch (NumberFormatException | SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
				
				for(int i = 1; i < rif.sala().getNumeroPostiTotali(); i++) {
					inserimentoPosti.addElement(i-1);
				}
				listaPosti.setEnabled(true);
				listaPosti.addItemListener(new SelListaPosti());
			}
		}
		
	}
	
	class SelListaPosti implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(arg0.getStateChange() == ItemEvent.SELECTED) {
				String[] param = listaSpettacoli.getSelectedItem().toString().split(" - ");
				Spettacolo rif = null;
				try {
					rif = DBm.getSpettacolo(param[0], param[1], param[2], Integer.parseInt(param[3]));
				} catch (NumberFormatException | SpettacoloNonTrovatoException e) {
					e.printStackTrace();
				}
				int posto = Integer.parseInt(arg0.getItem().toString());
				switch (rif.sala().getStatoPostoSingolo(posto)) {
				case 0:
					statusAttuale.setText("<html> Status attuale: <br> libero");
					break;
				
				case 1:
					statusAttuale.setText("<html> Status attuale: <br> occupato");
					break;
					
				case 3:
					statusAttuale.setText("<html> Status attuale: <br> prenotato");
					break;
				default:
					break;
				}
				
				postoLibero.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("posto libero");
					}
				});
				
				postoOccupato.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("posto occupato");
					}
				});
			}
		}
		
	}
}
