package fp.futbol;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstadisticasPartidos {
	private List<Partido> partidos;

	public EstadisticasPartidos(Stream<Partido> partidos) {
		super();
		this.partidos = partidos.toList();
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public Integer numPartidos() {
		return partidos.size();
	}

	@Override
	public String toString() {
		return "EstadisticasPartidos [partidos=" + partidos + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(partidos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticasPartidos other = (EstadisticasPartidos) obj;
		return Objects.equals(partidos, other.partidos);
	}
	
	// Ejercicios
	
	// Ejercicio 1
	public Integer getNumGolesEquipos(Set<String> equipos) {
		Integer res = 0;
		for (Partido p: partidos) {
			if (equipos.contains(p.getEquipoL())){
				res += p.getGolesL();
			} else if (equipos.contains(p.getEquipoV())) {
				res += p.getGolesV();
			}
		}
		return res;
	}
	
	// Ejercicio 2
	public Integer getNumGolesDespuesMinuto(Integer minutoUmbral) {
		return partidos.stream().flatMap(x->x.getParciales().stream()).filter(x->x.minuto()>minutoUmbral).mapToInt(x->x.golesL()+x.golesV()).sum();
	}
	
	// Ejercicio 3
	public SortedSet<Partido> getNPartidosMasEspectadores(Integer n){
		Comparator<Partido> cmp = Comparator.comparing(x->x.getNumEspectadores());
		return partidos.stream().sorted(cmp.reversed()).limit(n).collect(Collectors.toCollection(()->new TreeSet<Partido>()));
	}
	
	// Ejercicio 4
	public Competicion getCompeticionMasEspectadores(Integer mes) {
		Comparator<Partido> cmp = Comparator.comparing(x->x.getNumEspectadores());
		return partidos.stream().filter(x->Integer.valueOf(x.getFecha().getMonth().getValue()).equals(mes)).max(cmp.reversed()).map(x->x.getCompeticion()).get();
	}
	
	// Ejercicio 5
	public Map<Competicion, Partido> getPartidoGolMasTardioPorCompeticion(){
		Comparator<Partido> cmp = Comparator.comparing(x->getUltimoMinuto(x));
		return partidos.stream().collect(Collectors.groupingBy(x->x.getCompeticion(),()-> new HashMap<Competicion, Partido>(), Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().max(cmp).orElse(null))));
	}
	
	public Integer getUltimoMinuto(Partido p) {
		return p.getMinutos().stream().max(Integer::compareTo).orElse(0);
	}
}
