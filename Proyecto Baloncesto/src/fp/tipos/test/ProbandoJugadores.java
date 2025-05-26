package fp.tipos.test;

import java.time.LocalDate;
import java.util.List;

import fp.tipos.*;

public class ProbandoJugadores {
	
	
	public static void main(String[] args) {
			Jugadores jugadores = FactoriaJugadores.leeJugadores("./data/DatasetBaloncesto.csv");
	
			System.out.println(jugadores);
			
			//testLeerFichero("./data/DatasetBaloncesto.csv");
			
			/*
			System.out.println(jugadores.jugadoresTiempoPorPais());
			
			System.out.println(jugadores.menosPuntos(2001, 2));
			System.out.println(jugadores.masPuntos(500));
			
			System.out.println(jugadores.nombresPorPais());
			*/
		}
	/*
	public static void testLeerFichero(String ruta) {
		Jugadores res = FactoriaJugadores.leeJugadores(ruta);
		List<Jugador> alcachofa = res.getJugadores().stream().limit(10).toList();
		System.out.println("========== Test de Leer Fichero ==========\n");
		
		for (Jugador j: alcachofa) {
			System.out.println(j);
		}
	}
	*/
}
