package sevici.tipos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Operaciones {
	
	public static List<Estacion> getEstacionesBicisDisponiblesNoK(List<Estacion> estaciones){
		return estaciones.stream().filter(e->e.getTieneBicis()).toList();
	}
	
	public static List<Estacion> getEstacionesBicisDisponibles(List<Estacion> estaciones, Integer k){
		return estaciones.stream()
				.filter(e->e.getBicisDisponibles()>=k).toList();
	}
	
	public static SortedSet<Estacion> getEstacionesCercanas(List<Estacion> estaciones, Coordenadas cs, Double distancia) {
		return estaciones.stream()
				.filter(e -> e.getUbicacion().getDistanciaHaversine(cs) <= distancia)
				.sorted(Comparator.comparing(e -> e.getUbicacion().getDistanciaHaversine(cs)))
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	public static Estacion getEstacionMasBicisDisponibles(List<Estacion> estaciones) {
		return estaciones.stream()
				.max(Comparator.comparing(Estacion::getBicisDisponibles).thenComparing(Comparator.naturalOrder()))
				.orElse(null);
	}
	
	public static Integer getTotalPuestos(List<Estacion> estaciones) {
		return estaciones.stream()
				.mapToInt(e->e.getNumPuestos())
				.sum();
	}
	
	public static Double getOcupacionMediaEstacionesConBicis(List<Estacion> estaciones) {
		return estaciones.stream()
				.mapToDouble(e->e.getOcupacion())
				.average().orElse(0.);
	}
	
	public static Boolean tienenBicisDisponiblesTodasEstacionesCercanas(List<Estacion> estaciones, Coordenadas cs, Double distancia) {
		return estaciones.stream()
				.filter(e -> e.getUbicacion().getDistanciaHaversine(cs) <= distancia)
				.allMatch(e->e.getTieneBicis());
	}
	
	public static Map<Integer, List<Estacion>> estacioesPorBicisDisponibles(List<Estacion> estaciones){
		Map<Integer, List<Estacion>> res = new HashMap<Integer, List<Estacion>>();
		for(Estacion e: estaciones) {
			Integer clave = e.getBicisDisponibles();
			if (res.containsKey(clave)) {
				res.get(clave).add(e);
			} else {
				List<Estacion> valor = new ArrayList<Estacion>();
				valor.add(e);
				res.put(clave, valor);
			}
		}
		return res;
	}
	
	public static Map<Integer, List<Estacion>> estacionesporBicisDisponibles(List<Estacion> estaciones){
		return estaciones.stream().collect(Collectors.groupingBy(v->v.getBicisDisponibles()));
	}
	
	public static Map<Integer, Integer> numestacionesporBicisDisponibles(List<Estacion> estaciones){
		Function<Long, Integer> funcion = l->l.intValue();
		return estaciones.stream().collect(Collectors.groupingBy(v->v.getBicisDisponibles(), Collectors.collectingAndThen(Collectors.counting(), funcion)));
	}

}
