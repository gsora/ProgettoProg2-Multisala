package it.unisa.prog2.multisala;

import javax.swing.*;

import sun.awt.windows.ThemeReader;

import java.awt.*;

public class TestMultisala {

	public static void main(String[] args) throws OrarioNonValidoException, DataNonValidaException, PostiLiberiEsauritiException {
		
		Spettacolo ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 42, "18:43", "1/2/2014", 2.6);
		Sala s = new Sala(1, ggpas);
		
		JFrame frame = new JFrame();
		frame.setSize(1150, 720);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(5, 10, 50, 50));
		frame.setLocationRelativeTo(null);
		
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto(47);
		
		DrawPosto[] a = new DrawPosto[50];
		
		ricaricaUI(frame, s, a);
		
	}
	
	private static void ricaricaUI(JFrame frame, Sala s, DrawPosto[] a) {
		
		frame.getContentPane().removeAll();
		
		for(int i = 0; i < s.getNumeroPostiTotali(); i++) {
			a[i] = new DrawPosto(s.getStatoPostoSingolo(i), i+1);
			frame.add(a[i]);
		}
		
		frame.revalidate();
		frame.repaint();
	}

}
