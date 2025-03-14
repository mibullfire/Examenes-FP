package fp.tipos.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import fp.tipos.Competicion;
import fp.tipos.Futbol;
import fp.tipos.Partido;

public class TestPartido {

	public static void main(String[] args) {
		Futbol f = new Futbol(22,0,1);
		Futbol f1 = new Futbol(42,1,1);
		Futbol f2 = new Futbol(67,2,1);
		Partido p = new Partido(LocalDate.of(2023, 4, 19),"Celta de Vigo", "Villarreal", 14467l, 
				Competicion.LIGA, List.of(f,f1,f2));
		
		System.out.println(p);
		System.out.println(p.getGolesLocal());
		System.out.println(p.getGolesVisitante());
		System.out.println(p.getMinutos());
		System.out.println(p.getNumGolesEquipos(Set.of("Villarreal", "Celta de Vigo")));
	}

}
