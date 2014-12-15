package it.unisa.prog2.multisala;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xml.internal.utils.NSInfo;

public class InserisciProgramma extends JPanel {
	
	private JLabel msg;
	private JPanel pnl;
	private JTextField titolo;
	private JTextField numSala;
	private JTextField orarioDiInizio;
	private JTextField dataSpettacolo;
	private JTextField durata;
	private JLabel ttl;
	private JLabel nmsl;
	private JLabel odi;
	private JLabel dsp;
	private JLabel drt;
	private JButton okbutton;
	
		public InserisciProgramma() {
			
			//setPreferredSize(new Dimension(850, 650));
			setLayout(new BorderLayout());
			setBorder(new EmptyBorder(20, 20, 20, 20));
			pnl = new JPanel();
			
			GridBagLayout gbl = new GridBagLayout();
			
			pnl.setLayout(gbl);
			Font font = new Font("Lucida Sans Typewriter", Font.BOLD, 20);
			
			
			// titolo
			ttl = new JLabel("Titolo del Film:");
			ttl.setHorizontalAlignment(JLabel.LEFT);
			GridBagConstraints ttlc = new GridBagConstraints();
			ttlc.gridx = 2;
			ttlc.gridy = 1;
			ttlc.weightx = 1;
			ttlc.weighty = 1;
			ttlc.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(ttl, ttlc);
			
			titolo = new JTextField();
			titolo.setFont( font);
			titolo.setColumns(12);
			titolo.setEditable(true);
			titolo.setHorizontalAlignment(JTextField.CENTER);
			GridBagConstraints titoloc = new GridBagConstraints();
			titoloc.gridx = 3;
			titoloc.gridy = 1;
			titoloc.weightx = 1;
			titoloc.weighty = 1;
			titoloc.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(titolo, titoloc);
			pnl.add(ttl);
			pnl.add(titolo);
			
			//numero della sala
			nmsl = new JLabel("Numero della Sala:");
			nmsl.setHorizontalAlignment(JLabel.LEFT);
			GridBagConstraints nmslc = new GridBagConstraints();
			nmslc.gridx = 2;
			nmslc.gridy = 2;
			nmslc.weightx = 1;
			nmslc.weighty = 1;
			nmslc.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(nmsl, nmslc);
			
			numSala = new JTextField();
			numSala.setFont(font);
			numSala.setColumns(12);
			numSala.setEditable(true);
			numSala.setHorizontalAlignment(JTextField.CENTER);
			GridBagConstraints numSalac = new GridBagConstraints();
			numSalac.gridx = 3;
			numSalac.gridy = 2;
			numSalac.weightx = 1;
			numSalac.weighty = 1;
			numSalac.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(numSala, numSalac);
			pnl.add(nmsl);
			pnl.add(numSala);
			
			
			//orario inizio spettacolo
			odi = new JLabel("Orario di Inizio Spettacolo:");
			odi.setHorizontalAlignment(JLabel.LEFT);
			GridBagConstraints odic = new GridBagConstraints();
			odic.gridx = 2;
			odic.gridy = 3;
			odic.weightx = 1;
			odic.weighty = 1;
			odic.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(odi, odic);
			
			
			orarioDiInizio = new JTextField();
			orarioDiInizio.setFont(font);
			orarioDiInizio.setEditable(true);
			orarioDiInizio.setColumns(12);
			orarioDiInizio.setHorizontalAlignment(JTextField.CENTER);
			GridBagConstraints orarioDiInizioc = new GridBagConstraints();
			orarioDiInizioc.gridx = 3;
			orarioDiInizioc.gridy = 3;
			orarioDiInizioc.weightx = 1;
			orarioDiInizioc.weighty = 1;
			orarioDiInizioc.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(orarioDiInizio, orarioDiInizioc);
			pnl.add(odi);
			pnl.add(orarioDiInizio);

			
			//data dello spettacolo
			dsp = new JLabel("Data dello spettacolo:");
			dsp.setHorizontalAlignment(JLabel.LEFT);
			
			GridBagConstraints dspc = new GridBagConstraints();
			dspc.gridx = 2;
			dspc.gridy = 4;
			dspc.weightx = 1;
			dspc.weighty = 1;
			dspc.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(dsp, dspc);
			
			dataSpettacolo = new JTextField();
			dataSpettacolo.setFont(font);
			dataSpettacolo.setEditable(true);
			dataSpettacolo.setColumns(12);
			dataSpettacolo.setHorizontalAlignment(JTextField.CENTER);
			
			GridBagConstraints dataSpettacoloc = new GridBagConstraints();
			dataSpettacoloc.gridx = 3;
			dataSpettacoloc.gridy = 4;
			dataSpettacoloc.weightx = 1;
			dataSpettacoloc.weighty = 1;
			dataSpettacoloc.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(dataSpettacolo, dataSpettacoloc);
			pnl.add(dsp);
			pnl.add(dataSpettacolo);
			
			//Durata
			drt = new JLabel("Durata dello spettacolo:");
			drt.setHorizontalAlignment(JLabel.LEFT);

			GridBagConstraints drtc = new GridBagConstraints();
			drtc.gridx = 2;
			drtc.gridy = 5;
			drtc.weightx = 1;
			drtc.weighty = 1;
			drtc.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(drt, drtc);
			
			durata = new JTextField();
			durata.setFont(font);
			durata.setEditable(true);
			durata.setColumns(12);
			durata.setHorizontalAlignment(JTextField.CENTER);
			
			GridBagConstraints duratac = new GridBagConstraints();
			duratac.gridx = 3;
			duratac.gridy = 5;
			duratac.weightx = 1; 
			duratac.weighty = 1;
			duratac.fill = GridBagConstraints.HORIZONTAL;
			gbl.setConstraints(durata, duratac);
			pnl.add(drt);
			pnl.add(durata);
			
			
			okbutton = new JButton("OK");
			JPanel pnl1 = new JPanel(new FlowLayout());
			okbutton.setPreferredSize(new Dimension(60, 20));
			pnl1.add(okbutton);
			add(pnl1, BorderLayout.SOUTH);
			
			add(pnl, BorderLayout.CENTER);
						
			
			msg = new JLabel("Inserire i dati richiesti per aggiungere un nuovo spettacolo al Database.");
			add(msg, BorderLayout.NORTH);
			
			okbutton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String t1 = titolo.getText().toString(); 
					int n1 = Integer.parseInt(numSala.getText().toString());
					String o1 = orarioDiInizio.getText().toString();
					String d1 = dataSpettacolo.getText().toString();
					int p1 = Integer.parseInt(durata.getText().toString());
					DBManager dbm = new DBManager();
					
					try {
						Spettacolo s = new Spettacolo(t1, n1, o1, d1, p1);
						dbm.salvaSpettacolo(s);
					} catch (OrarioNonValidoException | DataNonValidaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

}
