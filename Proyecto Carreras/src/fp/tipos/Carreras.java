package fp.tipos;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Carreras {
	
	private SortedSet<Carrera> carreras;

	public SortedSet<Carrera> getCarreras() {
		return carreras;
	}
	
	public Integer getNumCarreras() {
		return carreras.size();
	}

	public Carreras(Stream<Carrera> carreras) {
		super();
		this.carreras = carreras.collect(Collectors.toCollection(()->new TreeSet<>()));
	}

	@Override
	public int hashCode() {
		return Objects.hash(carreras);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carreras other = (Carreras) obj;
		return Objects.equals(carreras, other.carreras);
	}

	@Override
	public String toString() {
		return "Carreras [carreras=" + carreras + "]";
	}
	
	// Funciones
	
	// Ejercicio 1
	public Carrera carreraMayorDesnivelParticipante(String nombre, String apellidos) {
		Comparator<Carrera> cmp = Comparator.comparing(x->x.getDesnivel());
		cmp = cmp.thenComparing(x->x.getDistancia());
		return carreras.stream().filter(x->x.getParticipantes().contains(x.buscaParticipante(nombre, apellidos))).max(cmp).orElseThrow(() -> new NoSuchElementException("No se encontró ningun participante")) ;
	}
	
	// Ejercicio 2
	public Double tiempoMedioCarrera(String idCarrera) {
		return carreras.stream().filter(x->x.getId().equals(idCarrera)).map(x->x.tiempoMedioPorKm()).findFirst().orElseThrow(() -> new NoSuchElementException("Calculo imposible"));
	}
	
	// Ejercicio 3
	public SortedMap<Categoria, String> ganadoresPorCategoria(String idCarrera){
		Comparator<Participante> cmp = Comparator.comparing(x->x.duracion());
		
		return carreras.stream().filter(x->x.getId().equals(idCarrera)).flatMap(x->x.getParticipantes().stream())
				.collect(Collectors.groupingBy(x->x.getCategoria(),
						
						()->new TreeMap<Categoria, String>(),
						
						Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().min(cmp).get().nombre())
						
						));
	}
	
	
	
	
	
	

}
