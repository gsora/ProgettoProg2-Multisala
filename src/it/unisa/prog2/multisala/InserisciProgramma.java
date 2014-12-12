package it.unisa.prog2.multisala;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InserisciProgramma extends JPanel {
	
	public JPanel pnlpro;
	public JLabel lblpro;
		
		public InserisciProgramma() {
			pnlpro = new JPanel();
			pnlpro.setPreferredSize(new Dimension(400, 300));
			pnlpro.setBackground(Color.CYAN);
			lblpro = new JLabel("prova");
			pnlpro.add(lblpro);
			add(pnlpro);
		}

}
