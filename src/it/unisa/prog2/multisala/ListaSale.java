package it.unisa.prog2.multisala;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class ListaSale {
	
	private JFrame listaSale;
	
	public ListaSale(JFrame frameChiamante) {
		// inizializzazione listaSale con le proprietà corrette
		listaSale = new JFrame();
		listaSale.setSize(500,800);
		listaSale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listaSale.setVisible(true);
	}
	
	public void costruisciUI(JFrame frameChiamante) {
		
	}

}
