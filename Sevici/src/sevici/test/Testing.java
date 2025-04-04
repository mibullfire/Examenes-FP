package sevici.test;

import java.util.List;

import sevici.tipos.Coordenadas;
import sevici.tipos.Estacion;
import sevici.tipos.FactoriaBicis;
import sevici.tipos.Operaciones;

public class Testing {
	public static void main(String[] args) {
		List<Estacion> espacios = FactoriaBicis.leeBicis("data/estaciones.csv");
		System.out.println("Listado de estaciones");
		espacios.stream().forEach(es->System.out.println(es));
		Coordenadas c = espacios.get(4).getUbicacion().aRadianes();
		System.out.println(c);
		
		Integer total = Operaciones.getTotalPuestos(espacios);
		System.out.println(total);
		
		Estacion muchasBicis = Operaciones.getEstacionMasBicisDisponibles(espacios);
		System.out.println(muchasBicis);
	}
}
