package fp.tipos.test;
import java.util.Map;
import java.util.Set;

import fp.tipos.*;

public class BusTest {
	
	public static void main(String [] args) {
		AgenciaBus agencia = FactoriaViajes.leerViajes("./data/viajes.csv");
		//agencia.añadirTiempoDescanso("Bilbao", 30);
		//System.out.println(agencia.getViajesPorParada(20.));
		testMap(agencia);
	}
	
	private static void testMap(AgenciaBus x) {
		Map<String, Set<Viaje>> res = x.getViajesPorParada(20.);
		res.entrySet().stream().forEach(y->System.out.println(y));
		System.out.println(res.entrySet().size());
	}

}
