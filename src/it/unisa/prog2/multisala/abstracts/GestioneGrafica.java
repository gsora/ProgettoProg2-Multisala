package it.unisa.prog2.multisala.abstracts;

import javax.swing.JFrame;

public interface GestioneGrafica {
	
	/**
	 * Metodo pubblico per gestire la costruzione e la soprapposizione dei frame visibili
	 * @param frameChiamante frame che sta utilizzando il medoto 
	 */
	
	public void costruisciUI(JFrame frameChiamante);
	
	/**
	 * Metodo pubblico vuoto
	 */
	public void costruisciUI();

}
