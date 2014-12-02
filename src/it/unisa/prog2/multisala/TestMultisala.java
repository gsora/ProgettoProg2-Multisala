package it.unisa.prog2.multisala;

import javax.swing.*;

import java.awt.*;


public class TestMultisala {

	public static void main(String[] args) throws OrarioNonValidoException, DataNonValidaException, PostiLiberiEsauritiException {
		
		Spettacolo ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 42, "18:43", "1/2/2014", 2.6);
		Sala s = new Sala(1, ggpas);
		
		System.out.println(s.statoPostiSala());
		
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		
		System.out.println(s.statoPostiSala());
		
		
		JFrame frame = new JFrame();
		
		frame.setSize(1150, 720);
		frame.setResizable(false);
		frame.setTitle("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(5, 10));
		
		DrawPosto[] a = new DrawPosto[50];
		
		
		for(int i = 0; i < s.getNumeroPostiTotali(); i++) {
			a[i] = new DrawPosto(s.getStatoPostoSingolo(i));
			frame.repaint();
			frame.add(a[i]);
		}
		frame.setVisible(true);
		
	}

}
