	package it.unisa.prog2.multisala.gui.gestione;

import it.unisa.prog2.multisala.abstracts.DBManager;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ApplicaSconti extends JPanel {
	
	private DBManager valoriSconti;
	
	private JPanel scontoUno;
	
	private JLabel messaggioUno;
	
	private JLabel messaggioAttualeUno;
	
	private double valoreAttualeUno;
	
	private JTextField valoreNuovoUno;
	
	private JButton aggiornaUno;
	
	private JPanel scontoDue;
	
	private JLabel messaggioDue;
	
	private JComboBox<String> giorniDue;
	
	private JTextField valoreNuovoDue;
	
	private double valoreAttualeDue;
	
	private String giornoAttualeDue; 
	
	private JButton aggiornaDue;
	
	/**
	 * Tramite questo JPanel è possibile gestire e modificare le politiche di sconto attualmente in uso
	 */
	
		public ApplicaSconti() {
		
			setLayout(new GridLayout(2, 1));
			valoriSconti = new DBManager();
			
			//pannello del primo sconto
			
			scontoUno = new JPanel();
			scontoUno.setLayout(new GridLayout(2, 1));
			messaggioAttualeUno = new JLabel("Lo sconto attuale per gli studenti è di " + valoriSconti.getScontoStudenti() + "€");
			JPanel app = new JPanel(new FlowLayout());
			app.add(messaggioAttualeUno);
			
			messaggioUno = new JLabel("Nuovo sconto:");
			valoreNuovoUno = new JTextField();
			valoreNuovoUno.setEditable(true);
			valoreNuovoUno.setPreferredSize(new Dimension(250, 30));
			aggiornaUno = new JButton("Aggiorna");
			aggiornaUno.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String prv = valoreNuovoUno.getText().toString();
						if(prv.contains(",")) 
						prv = prv.replace(",", ".");
						valoriSconti.setScontoStudenti(prv);
						messaggioAttualeUno.setText("Lo sconto attuale per gli studenti è di " + valoriSconti.getScontoStudenti() + "€");
						
						
					}
					catch (Exception ee) {

					}
				}
			});
			
			JPanel app0 = new JPanel(new FlowLayout());
			app0.add(messaggioUno);
			app0.add(valoreNuovoUno);
			app0.add(aggiornaUno);
			
			scontoUno.setBorder(new TitledBorder("Sconto studenti"));
			scontoUno.add(app);
			scontoUno.add(app0);
			
			add(scontoUno);
			
			//pannello del secondo sconto
			
			scontoDue = new JPanel();
			scontoDue.setLayout(new GridLayout(2, 2));
			
			messaggioDue = new JLabel("Lo sconto attuale è impostato nel giorno di " + valoriSconti.getGiornoScontoSettimanale() + " per il valore di " + valoriSconti.getValoreScontoSettimanale() + "€." );
			JPanel app1 = new JPanel(new FlowLayout());
			app1.add(messaggioDue);
			
			String[] giorni = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica" };
			giorniDue = new JComboBox<String>(giorni);
			valoreNuovoDue = new JTextField();
			valoreNuovoDue.setEditable(true);
			valoreNuovoDue.setPreferredSize(new Dimension(250, 30));
			aggiornaDue = new JButton("Aggiorna");
			aggiornaDue.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String vlr = valoreNuovoDue.getText().toString();
						if(vlr.contains(",")) 
						vlr = vlr.replace(",", ".");
						valoriSconti.setValoreScontoSettimanale(vlr);
						
						String grn = giorniDue.getSelectedItem().toString().toLowerCase();
						valoriSconti.setGiornScontoSettimanale(grn);
						
						messaggioDue.setText("Lo sconto attuale è impostato nel giorno di " + valoriSconti.getGiornoScontoSettimanale() + " per il valore di " + valoriSconti.getValoreScontoSettimanale() + "€." );
						
						
						}
					catch (Exception ee) {

					}
					
				}
			});
			JPanel app2 = new JPanel(new FlowLayout());
			app2.add(giorniDue);
			app2.add(valoreNuovoDue);
			app2.add(aggiornaDue);
			
			scontoDue.setBorder(new TitledBorder("Sconto su un giorno della settima"));
			scontoDue.add(app1);
			scontoDue.add(app2);			
			add(scontoDue);
			
		}

}
