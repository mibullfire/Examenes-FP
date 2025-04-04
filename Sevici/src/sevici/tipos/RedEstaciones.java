package sevici.tipos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RedEstaciones {
	private SortedSet<Estacion> estaciones;
	private String nombreRed;
	
	public RedEstaciones(String nombreRed, SortedSet<Estacion> estaciones) {
		this.nombreRed = nombreRed;
		this.estaciones = estaciones;
	}
	
	public Integer getNumeroEstaciones() {
		Long l = estaciones.stream().count();
		return l.intValue();
	}
	
	public List<Estacion> getEstacionesBicisDisponibles(Integer k){
		return estaciones.stream()
				.filter(e->e.getBicisDisponibles()>=k)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public SortedSet<Estacion> getEstacionesCercanas(Coordenadas cs, Double distancia) {
		return estaciones.stream()
				.filter(e -> e.getUbicacion().getDistanciaHaversine(cs) <= distancia)
				.sorted(Comparator.comparing(e -> e.getUbicacion().getDistanciaHaversine(cs)))
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	public Estacion getEstacionMasBicisDisponibles() {
		return estaciones.stream()
				.max(Comparator.comparing(e->e.getBicisDisponibles())).orElse(null);
	}
	
	public Integer getTotalPuestos() {
		return estaciones.stream()
				.mapToInt(e->e.getNumPuestos())
				.sum();
	}
	
	public Double getOcupacionMediaEstacionesConBicis() {
		return estaciones.stream()
				.mapToDouble(e->e.getOcupacion())
				.average().orElse(0.);
	}
	
	public Boolean tienenBicisDisponiblesTodasEstacionesCercanas(Coordenadas cs, Double distancia) {
		return estaciones.stream()
				.filter(e -> e.getUbicacion().getDistanciaHaversine(cs) <= distancia)
				.allMatch(e->e.getTieneBicis());
	}
}
