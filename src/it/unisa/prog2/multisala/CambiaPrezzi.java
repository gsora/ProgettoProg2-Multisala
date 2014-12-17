package it.unisa.prog2.multisala;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CambiaPrezzi extends JPanel {

	private JLabel msg;
	private JPanel pnl;
	private JLabel prezzoAttuale;
	private JLabel nuoPrezzo;
	private JTextField nuovoPrezzo;
	private JButton aggiorna;
	private float prezzo;
	
	public CambiaPrezzi() {
		
		
		Font font = new Font("Arial Unicode MS", Font.BOLD, 20);
		//setLayout(new BorderLayout());
		setBorder(new EmptyBorder(40, 20, 20, 20));
		pnl = new JPanel();
		pnl.setLayout(new GridLayout(5, 1, 0, 45));
		
		//messaggio principale
		msg = new JLabel("Inserire il prezzo desiderato per sostituirlo a quello attuale.");
		msg.setFont(font);
		pnl.add(msg);
		
		//TODO: aggiungere integrazione con il DB per il prezzo
		prezzo = 7.50f;
		prezzoAttuale = new JLabel("Il prezzo attuale del biglietto è: " + prezzo + "€");
		prezzoAttuale.setFont(font);
		pnl.add(prezzoAttuale);

		nuoPrezzo = new JLabel("Immettere qui sotto il nuovo prezzo: ");
		nuoPrezzo.setFont(font);
		pnl.add(nuoPrezzo);
		nuovoPrezzo = new JTextField();
		nuovoPrezzo.setFont(font);
		nuovoPrezzo.setColumns(12);
		nuovoPrezzo.setEditable(true);
		nuovoPrezzo.setHorizontalAlignment(JTextField.HORIZONTAL);
		JPanel app0 = new JPanel(new FlowLayout());
		app0.add(nuovoPrezzo);
		pnl.add(app0);
		
		aggiorna = new JButton("Aggiorna");
		JPanel app1 = new JPanel(new FlowLayout());
		app1.add(aggiorna);
		pnl.add(app1);
		add(pnl);
		
		
		aggiorna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String prv = nuovoPrezzo.getText().toString();
				if(prv.contains(",")) 
					prv = prv.replace(",", ".");
				float p = Float.parseFloat(prv);
				prezzo = p;
				prezzoAttuale.setText("Il prezzo attuale del biglietto è: " + prezzo + "€" );
				
			}
		});
		
	}
	
	
}
