package fp.tipos.test;

import fp.tipos.*;

public class TestPaises {
	
	public static void main (String [] args) {
		PaisesdelMundo prueba = FactoriaPaises.leeFichero("./data/CSV de la sesión 2.csv");
		
		System.out.println(prueba.getNumPaises());
	}

}
