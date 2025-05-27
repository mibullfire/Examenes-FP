package fp.tipos.test;

import java.util.SortedMap;

import fp.tipos.*;

public class Testeo {
	
	public static void main (String [] args) {
		Carreras x = FactoriaCarreras.leeCarreras("./data/csv1.csv", "./data/csv2.csv");
		/*System.out.println(x);
		for(Carrera y: x.getCarreras()) {
			System.out.println(y);
		}
		*/
		Carrera u = x.carreraMayorDesnivelParticipante("Elena", "Blanco Vázquez");
		System.out.println(u);
		
		SortedMap<Categoria, String> v = x.ganadoresPorCategoria("tr_lima_pedr_24");
		System.out.println(v);
	}

}
