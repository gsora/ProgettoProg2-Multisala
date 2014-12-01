package it.unisa.prog2.multisala;

public class TestMultisala {

	public static void main(String[] args) throws OrarioNonValidoException, DataNonValidaException, PostiLiberiEsauritiException {
		
		Spettacolo ggpas = new Spettacolo("Guida Galattica Per Autostoppisti", 42, "18:43", "1/2/2014", 2.6);
		Sala s = new Sala(1, ggpas);
		
		System.out.println(s.statoPostiSala());
		
		s.compraBiglietto();
		
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
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
		s.compraBiglietto();
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
		
	}

}
