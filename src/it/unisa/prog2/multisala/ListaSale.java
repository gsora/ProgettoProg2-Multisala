// TODO: rinominare la classe ListaSpettacoli?

package it.unisa.prog2.multisala;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class ListaSale implements GestioneGrafica {
	
	private JFrame listaSale;
	private JPanel pannelloSuperiore;
	private JPanel pannelloInferiore;
	
	private JLabel titoloFilm;
	private JLabel durataFilm;
	private JLabel genereFilm;
	private JLabel orariFilm;
	private JLabel giorniFilm;
	private JLabel numeroSala;
	
	private JButton acquistaPrenota;
	
	private JRadioButton sceltaOrdinamentoLista;
	
	private JComboBox sala1;
	private JComboBox sala2;
	private JComboBox sala3;
	private JComboBox sala4;
	private JComboBox comboSale;
	
	public ListaSale(JFrame frameChiamante) {
		// inizializzazione listaSale con le proprietà corrette
		listaSale = new JFrame();
		listaSale.setSize(500,800);
		listaSale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listaSale.setLocationRelativeTo(null);
		listaSale.setTitle("Lista spettacoli");
		listaSale.setLayout(new GridLayout(2,1));
		
		
		
	}
	
	public void costruisciUI(JFrame frameChiamante) {
		listaSale.setVisible(true);
	}

	@Override
	public void costruisciUI() {
		// TODO Auto-generated method stub
		
	}

}
