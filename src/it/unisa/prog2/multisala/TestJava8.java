package it.unisa.prog2.multisala;

public class TestJava8 {

	public static void main(String[] args) {
		String t = "aa:44";
		Integer a;
		Integer b;
		String[] g = t.split(":");
		try {
			Integer aa = Integer.parseInt(g[0]);
			System.out.println(aa);
		} catch (NumberFormatException nfe) {
			System.out.println("Le ore non sono state passate in modo corretto.");
		}
		
		System.out.println("asd");
	}

}
