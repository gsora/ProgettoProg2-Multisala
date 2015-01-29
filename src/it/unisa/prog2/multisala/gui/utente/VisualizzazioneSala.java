package it.unisa.prog2.multisala.gui.utente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unisa.prog2.multisala.abstracts.DrawPosto;
import it.unisa.prog2.multisala.abstracts.Sala;

public class VisualizzazioneSala implements MouseListener{

	private JFrame frm;
	private DrawPosto[] a;
	private Sala sSelez;
	
	public VisualizzazioneSala(Sala spass) {
		
		sSelez = spass;
		frm = new JFrame();
		frm.setSize(1150, 720);
		frm.setResizable(false);
		frm.setTitle("Sala " + sSelez.getNumeroSala());
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setLayout(new GridLayout(5, 10, 50, 50));
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
		a = new DrawPosto[50];
		
		ricaricaUI(frm, sSelez, a);
		
	}
	
	private void ricaricaUI(JFrame frame, Sala s, DrawPosto[] a) {
		
		frame.getContentPane().removeAll();
		
		for(int i = 0; i < s.getNumeroPostiTotali(); i++) {
			a[i] = new DrawPosto(s.getStatoPostoSingolo(i), i+1);
			a[i].addMouseListener(this);
			frame.add(a[i]);			
		}
		
		frame.revalidate();
		frame.repaint();
	}
	
	public VisualizzazioneSala() {}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int numeroPosto = ((DrawPosto) e.getSource()).getNumeroPostoDraw();
			ricaricaUI(frm, sSelez, a); 
			frm = new JFrame();
			frm.setSize(600, 350);
			frm.setVisible(true);
			frm.setResizable(false);
			frm.setTitle("Compra o Prenota Biglietto");
			frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frm.setLayout(new BorderLayout());
			JPanel subpanel = new JPanel();
			JButton Compra = new JButton("Compra");
			JButton Prenota = new JButton("Prenota");	
			subpanel.add(Prenota);
			subpanel.add(Compra);
			frm.add(subpanel, BorderLayout.SOUTH);
			JLabel lab1 = new JLabel("Scegliere l'opzione da eseguire con il posto numero " + numeroPosto);
			frm.add(lab1, BorderLayout.NORTH);
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