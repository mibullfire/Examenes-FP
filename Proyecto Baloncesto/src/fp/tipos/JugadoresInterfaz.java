package fp.tipos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JugadoresInterfaz {

	String toString();

	// Ejercicio 1
	Boolean existeJugador(LocalDate fecha, Double r);

	// Ejercicio 2
	Double mediaPuntosLetra(Character caracter);

	// Ejercicio 3
	Map<String, Set<Jugador>> jugadoresTiempoPorPais();

	List<String> menosPuntos(Integer anyo, Integer n);

	// Ejercicio 5
	Map<String, List<String>> nombresPorPais();

	// Ejercicio 6
	/*
	public Integer masPuntos(Integer n) {
		return jugadores.stream().filter(x->(x.puntos1()+x.puntos2()+x.puntos3())>n).toList().size();
	}*/
	Integer masPuntos(Integer n);

}