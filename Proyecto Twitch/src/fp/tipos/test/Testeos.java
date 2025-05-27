package fp.tipos.test;

import fp.tipos.*;

public class Testeos {
	
	public static void main(String [] args) {
		EstadisticasTwitch x = FactoriaTwitch.leeCanales("./data/csv.csv");
		System.out.println(x.idiomasPorPresenciaLista());
	}
}
