package fp.tipos;

import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AgenciaBus {

	
		private String nombre;
		private List<Viaje> viajes;
		
		public AgenciaBus(String nombre, Stream<Viaje> viajes) {
			super();
			this.nombre = nombre;
			this.viajes = viajes.toList();
		}

		public String getNombre() {
			return nombre;
		}

		public List<Viaje> getViajes() {
			return viajes;
		}

		@Override
		public String toString() {
			return "AgenciaBus [nombre=" + nombre + ", viajes=" + viajes + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(viajes);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AgenciaBus other = (AgenciaBus) obj;
			return Objects.equals(viajes, other.viajes);
		}
		
		public Duration getMaximaDuracion() {
			return viajes.stream().max(Comparator.comparing(x->x.getDuracion())).get().getDuracion();
		}
		
		public void añadirTiempoDescanso(String parada, Integer minutos) {
			viajes.stream().filter(x->x.getNombresParadas().contains(parada)).forEach(x->x.setDuracion(x.getDuracion().plus(Duration.ofMinutes(minutos))));
		}
		
		public SortedMap<String, Duration> getDuracionMinimaPorDestino(TipoViaje tipo){
			return viajes.stream().filter(x->x.getTipoViaje().equals(tipo)).collect(
					Collectors.groupingBy(x->x.getDestino(),
							()->new TreeMap<String, Duration>(),
							Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().map(x->x.getDuracion()).min(Comparator.comparing(x->x)).orElse(Duration.ofMinutes(0)))
							)
					);
		}
		
		public Map<String, Set<Viaje>> getViajesPorParada(Double precio){
		    Map<String, Set<Viaje>> res = new HashMap<>();

		    for (String s: todasParadas()) {
		        for (Viaje v: viajes) {
		            boolean cumpleCondicion = (precio == null) ? 
		                v.getNombresParadas().contains(s) : 
		                v.getNombresParadas().contains(s) && v.getPrecio() < precio;

		            if (cumpleCondicion) {
		                if (res.containsKey(s)) {
		                    Set<Viaje> valor = res.get(s);
		                    valor.add(v);
		                }
		                else {
		                    String clave = s;
		                    Set<Viaje> valor = new HashSet<>();
		                    valor.add(v);
		                    res.put(s, valor);
		                }
		            }
		        }
		    }

		    return res;
		}
		
		public SortedMap<String, Double> getPrecioMedioViajesPorParada(){
			return null;
		}
		
		private List<String> todasParadas(){
			return viajes.stream().flatMap(x->x.getNombresParadas().stream()).toList();
		}
		
		
}
