package it.unisa.prog2.multisala.abstracts;

import javax.swing.JComponent;
import java.awt.Color;	
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

@SuppressWarnings("serial")
public class DrawPosto extends JComponent {
	
	private Graphics2D g2;
	private GeneralPath path;
	private Color c;
	private int numeroPosto;
	
	/**
	 * costruttore vuoto per il metodo DrawPosto
	 * 
	 */

	public DrawPosto() {
		
	}
	
	/**
	 *  	Costruttore di DrawPosto che inizializza un posto di un determinato colore in base al suo status
	 * @param status  indica la sistuazione del posto ovvero se è libero, prenotato o occupato
	 * @param ns indica il numero del posto che si sta inizializzando
	 */
	
	public DrawPosto(int status, int ns) {
		switch (status) {
		case 0:
			c = new Color(0, 177, 106);
			break;
		case 1:
			c = new Color(192, 57, 43);
			break;
		case 2:
			c = new Color(232, 126, 4);
			break;
		default:
			c = new Color(0, 177, 106);
			break;
		}
		
		numeroPosto = ns;
	}
	
	/**
	 * Metodo privato per costruire nelle interfacce grafiche la struttura visiva del posto con il proprio numero
	 * 
	 */
	
	private void disegnaPath() {
        g2.setColor(c);
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path.moveTo(15, 50);
        path.curveTo(20, 10, 50, 10, 55, 50);
        path.lineTo(60, 50);
        path.lineTo(60, 85);
        path.lineTo(50, 85);
        path.lineTo(50, 80);
        path.lineTo(20, 80);
        path.lineTo(20, 85);
        path.lineTo(10, 85);
        path.lineTo(10, 50);
        path.closePath();
        g2.fill(path);
        Font textFont = new Font("Arial", Font.BOLD, 16);  
        g2.setFont(textFont); 
        g2.drawString(Integer.toString(numeroPosto), this.getWidth()/2, this.getHeight()/5);
        g2.draw(path);
		
	}
	 
	/**
	 * metodo pubblico che restituisce il numero del posto corrente
	 * @return restituisce il numero del posto corrente
	 */
	
	public int getNumeroPostoDraw() {
		return numeroPosto;
	}
	
	/**
	 * Metodo pubblico per disegnare i posti nelle interfacce grafiche
	 */
	
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		disegnaPath();

	}


}
