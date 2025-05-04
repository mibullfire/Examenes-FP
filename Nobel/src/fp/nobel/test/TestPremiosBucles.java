package fp.nobel.test;
/**
 * @author Daniel Mateos
 * @author José A. Troyano
 * 
 * @since 2019-03-10
 */

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;


import fp.nobel.FactoriaNobel;
import fp.nobel.Genero;
import fp.nobel.Implementacion;
import fp.nobel.Premio;
import fp.nobel.Premios;

public class TestPremiosBucles {
public static void main(String[] args) {
	
		FactoriaNobel.setImplementacion(Implementacion.BUCLES);
		Premios premios = FactoriaNobel.leerPremios("data/nobel_prizes.csv");
		
		testObtenerPremiosDeGenero(premios, Genero.MALE);
		testObtenerPremiosDeGenero(premios, Genero.FEMALE);
		testCalcularNumeroPremiadosMasJovenesDe(premios, 60);
		testCalcularNumeroPremiadosMasJovenesDe(premios, 50);
		testCalcularNumeroPremiosPorGenero(premios);
		//testCalcularPremiosPorEdad(premios);
		testCalcularMediaEdadPorCategoria(premios);
		
		
		
	}
	
	private static void testObtenerPremiosDeGenero(Premios premios, Genero genero) {
		System.out.println("\nTEST de obtenerPremiosDeGenero");
		try {
			System.out.println("  GENERO: " + genero);
			System.out.println("    PREMIOS: " + premios.obtenerPremiosDeGenero(genero));
		} catch(Exception e) {
			System.out.println("Excepción capturada:\n   " + e);	
		}
	}
	
	private static void testCalcularNumeroPremiadosMasJovenesDe(Premios premios, Integer edad) {
		System.out.println("\nTEST de calcularNumeroPremiadosMasJovenesDe");
		try {
			System.out.println("  EDAD: " + edad);
			System.out.println("    PREMIADOS: " + premios.calcularNumeroPremiadosMasJovenesDe(edad));
		} catch(Exception e) {
			System.out.println("Excepción capturada:\n   " + e);	
		}
	}
	
	private static void testCalcularNumeroPremiosPorGenero(Premios premios) {
		System.out.println("\nTEST de calcularNumeroPremiosPorGenero");
		try {
			System.out.println("  Nº PREMIOS GENERO: " + premios.calcularNumeroPremiosPorGenero());
		} catch(Exception e) {
			System.out.println("Excepción capturada:\n   " + e);	
		}
	}
	/*
	private static void testCalcularPremiosPorEdad(Premios premios) {
		System.out.println("\nTEST de calcularPremiosPorEdad");
		try {
			System.out.println("  PREMIOS EDAD: " );
			Function<Map.Entry<Integer, List<Premio>>, String> fentry2str =
					e-> e.getKey()+ "\n " +  e.getValue().stream()
					                                     .map(Premio::toString) 
					                                     .collect(Collectors.joining("\n "));
			Map<Integer, List<Premio>> m = premios.calcularPremiosPorEdad();
			m.entrySet().stream()
				.map(fentry2str)
				.forEach(System.out::println);
			
		} catch(Exception e) {
			System.out.println("Excepción capturada:\n   " + e);	
		}
	}
	*/
	private static void testCalcularMediaEdadPorCategoria(Premios premios) {
		System.out.println("\nTEST de calcularMediaEdadPorCategoria");
		try {
			System.out.println("  MEDIAS EDAD: " + premios.calcularMediaEdadPorCategoria());
		} catch(Exception e) {
			System.out.println("Excepción capturada:\n   " + e);	
		}
	}
	
}
