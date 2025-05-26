package fp.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Jugadores {
	
	List<Jugador> jugadores;

	public Jugadores(List<Jugador> jugadores) {
		super();
		this.jugadores = jugadores;
	}
	
	

	public List<Jugador> getJugadores() {
		return jugadores;
	}



	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}



	@Override
	public String toString() {
		return "Jugadores [jugadores=" + jugadores + "]";
	}
	
	public Integer getElementosLista() {
		return jugadores.size();
	}
	
	// Ejercicio 1
	public Boolean existeJugador(LocalDate fecha, Double r) {
		return jugadores.stream().allMatch(x->(x.fecha().equals(fecha))&&(procentajeTiros(x).equals(r)));
	}
	
	private Double procentajeTiros (Jugador x) {
		return Double.valueOf(x.puntos1()/x.tiros1());
	}
	
	// Ejercicio 2
	public Double mediaPuntosLetra(Character caracter) {
		List<Jugador> filtrados = jugadores.stream().filter(x->Character.valueOf(x.nombre().charAt(0)).equals(caracter)).toList();
		return Double.valueOf(filtrados.stream().mapToInt(x->x.puntos1()+x.puntos2()+x.puntos3()).sum() / filtrados.size());
	}
	
	// Ejercicio 3
	public Map<String, Set<Jugador>> jugadoresTiempoPorPais() {
		return jugadores.stream().collect(Collectors.groupingBy(x->x.pais(), 
				
				Collectors.collectingAndThen(Collectors.toList(),
						lista->lista.stream()
							.sorted(Comparator.comparing(x->x.tiempo()))
							.collect(Collectors.toSet()))));
	}
	
	// Ejercicio 4
	public List<String> menosPuntos(Integer anyo, Integer n) {
		return jugadores.stream().filter(x->Integer.valueOf(x.fecha().getYear()).equals(anyo)).sorted(Comparator.comparing(x->x.puntos1()+x.puntos2()+x.puntos3())).map(x->x.nombre()).limit(n).toList();
	}
	
	// Ejercicio 5 
	public Map<String, List<String>> nombresPorPais(){
		return jugadores.stream().collect(Collectors.groupingBy(x->x.pais(),
				
				Collectors.collectingAndThen(Collectors.toList(),
						lista->lista.stream().sorted(Comparator.comparing(x->x.tiempo())).map(x->x.nombre()).toList()
						)
				));
	}
	
	// Ejercicio 6
	public Integer masPuntos(Integer n) {
		Map<String, Integer> jugadorPorPuntos = jugadores.stream().collect(Collectors.groupingBy(x->x.nombre(),
				Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().mapToInt(x->x.puntos1()+x.puntos2()+x.puntos3()).sum())));
		
		Map<String, Integer> filtrado = jugadorPorPuntos.entrySet().stream()
				.filter(entry->entry.getValue()>n).collect(Collectors.toMap(x->x.getKey(), x->x.getValue()));
		
		Long cuenta = filtrado.entrySet().stream().count();
		return cuenta.intValue();
	}
	
	
	
	

}
