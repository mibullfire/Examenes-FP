package fp.tipos.test;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import fp.tipos.*;

public class Testeos {
	
	public static void main(String [] args) {
		CatalogoNetflix x = FactoriaNetflix.leeNetflix("./data/titulos_netflix.csv");
		System.out.println(x);
		
		Map<String, Set<ProduccionNetflix>> e1 = x.getProduccionesPorGenero();
		System.out.println(e1);
		SortedSet<String> e2 =x.getGeneros();
		System.out.println(e2);
		
	}

}
