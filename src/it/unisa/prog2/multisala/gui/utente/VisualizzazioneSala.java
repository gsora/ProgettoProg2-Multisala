package it.unisa.prog2.multisala.gui.utente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unisa.prog2.multisala.abstracts.DBManager;
import it.unisa.prog2.multisala.abstracts.DrawPosto;
import it.unisa.prog2.multisala.abstracts.Sala;
import it.unisa.prog2.multisala.abstracts.Spettacolo;
import it.unisa.prog2.multisala.exceptions.PostiLiberiEsauritiException;
import it.unisa.prog2.multisala.exceptions.PostiPrenotabiliEsauritiException;
import it.unisa.prog2.multisala.exceptions.SpettacoloNonTrovatoException;

public class VisualizzazioneSala implements MouseListener{

	private JFrame frm;
	private JFrame frm0;
	private DrawPosto[] a;
	private Spettacolo sSelez;
	private JCheckBox studente;
	private JButton compra;
	private JButton prenota;
	private DBManager dbm;
	private float prezzostudenti;
	private float prezzo;
	private String userID;
	
	public VisualizzazioneSala(Spettacolo spass, String userID) {
		
		this.userID = userID;
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
	
	private void ricaricaUI(JFrame frame, Spettacolo s, DrawPosto[] a) {
		
		frame.getContentPane().removeAll();
		
		for(int i = 0; i < s.sala().getNumeroPostiTotali(); i++) {
			a[i] = new DrawPosto(s.sala().getStatoPostoSingolo(i), i+1);
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
			frm0 = new JFrame();
			frm0.setSize(400, 150);
			frm0.setResizable(false);
			frm0.setLocationRelativeTo(null);
			frm0.setTitle("Compra o Prenota Biglietto");
			frm0.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frm0.setLayout(new GridLayout(3, 1, 10, 10));
			
			dbm = new DBManager();
			
			JPanel subpanel = new JPanel(new FlowLayout());
			compra = new JButton("Compra");
			compra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (studente.isSelected())
						prezzostudenti = dbm.getPrezzoFilm() - Float.parseFloat(dbm.getScontoStudenti());
					else 
						prezzostudenti = 10000.0f;
					
					prezzo = dbm.getPrezzoFilm();
					try {
						sSelez.sala().compraBiglietto(numeroPosto);
						ricaricaUI(frm, sSelez, a);
						try {
							dbm.rimuoviSpettacolo(sSelez.getTitoloSpettacolo(), sSelez.getOrarioDiInizio(), sSelez.getData(), sSelez.getNumeroSala());
							dbm.salvaSpettacolo(sSelez);
						} catch (SpettacoloNonTrovatoException e) {
							e.printStackTrace();
						}	
						frm0.dispose();
					} catch (PostiLiberiEsauritiException e) {
						e.printStackTrace();
					}
					
				}
			});
			
			prenota = new JButton("Prenota");	
			prenota.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						sSelez.sala().prenotaPosto(numeroPosto, userID, sSelez);
						ricaricaUI(frm, sSelez, a);
						try {
							dbm.rimuoviSpettacolo(sSelez.getTitoloSpettacolo(), sSelez.getOrarioDiInizio(), sSelez.getData(), sSelez.getNumeroSala());
							dbm.salvaSpettacolo(sSelez);
						} catch (SpettacoloNonTrovatoException e1) {
							e1.printStackTrace();
						}
						frm0.dispose();
					} catch (PostiPrenotabiliEsauritiException e1) {
						e1.printStackTrace();
					}
					
					
				}
			});
			
			subpanel.add(prenota);
			subpanel.add(compra);
			
			studente = new JCheckBox();
			studente.setText("Sono uno studente");
			
			JLabel lab1 = new JLabel("Scegliere l'opzione da eseguire con il posto numero " + numeroPosto);
			JPanel app0 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
			app0.add(lab1);
			frm0.add(app0);
			frm0.add(studente);
			frm0.add(subpanel);
			frm0.setVisible(true);
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