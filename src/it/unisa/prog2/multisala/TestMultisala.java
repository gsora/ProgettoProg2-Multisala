package it.unisa.prog2.multisala;

import javax.swing.*;

import com.sun.glass.events.WindowEvent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestMultisala implements MouseListener {
	
	private static Spettacolo ggpas;
	private static JFrame frame;
	private static Sala s;
	private static DrawPosto[] a;

	public static void main(String[] args) throws OrarioNonValidoException, DataNonValidaException, PostiLiberiEsauritiException {
		
		ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 42, "18:43", "1/2/2014", 2.6);
		s = new Sala(1, ggpas);
		
		frame = new JFrame();
		frame.setSize(1150, 720);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Sala n�");
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
		
		a = new DrawPosto[50];
		
		ricaricaUI(frame, s, a);
		
	}
	
	private static void ricaricaUI(JFrame frame, Sala s, DrawPosto[] a) {
		
		frame.getContentPane().removeAll();
		
		for(int i = 0; i < s.getNumeroPostiTotali(); i++) {
			a[i] = new DrawPosto(s.getStatoPostoSingolo(i), i+1);
			a[i].addMouseListener(new TestMultisala());
			frame.add(a[i]);
		}
		
		frame.revalidate();
		frame.repaint();
	}

	public TestMultisala() {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int numeroPosto = ((DrawPosto) e.getSource()).getNumeroPostoDraw();
		try {
			
			s.compraBiglietto(numeroPosto);
			ricaricaUI(frame, s, a);
		} catch (PostiLiberiEsauritiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		frame = new JFrame();
		frame.setSize(600, 350);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Compra o Prenota Biglietto");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JPanel subpanel = new JPanel();
		JButton Compra = new JButton("Compra");
		JButton Prenota = new JButton("Prenota");
		subpanel.add(Prenota);
		subpanel.add(Compra);
		frame.add(subpanel, BorderLayout.SOUTH);
		JLabel lab1 = new JLabel("Scegliere l'opzione da eseguire con il posto numero " + numeroPosto);
		frame.add(lab1, BorderLayout.NORTH);
		}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
