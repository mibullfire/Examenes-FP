package fp.nobel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PremiosStream implements Premios {
	public List<Premio> premios;
	
	public PremiosStream() {
		this.premios = new ArrayList<Premio>();
	}
	public PremiosStream(Stream x) {
		this.premios = x.toList();
	}
	@Override
	public String toString() {
		return ""+premios.size();
	}
	@Override
	public int hashCode() {
		return Objects.hash(premios);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PremiosStream other = (PremiosStream) obj;
		return Objects.equals(premios, other.premios);
	}
	
	@Override
	public void anyadirPremio(Premio p) {
		premios.add(p);
	}
	@Override
	public List<Premio> obtenerPremiosDeGenero(Genero genero){
		return premios.stream().filter(x->x.genero().equals(genero)).toList();
	}
	@Override
	public Integer calcularNumeroPremiadosMasJovenesDe(Integer edadUmbral) {
		long l = premios.stream().filter(x->x.edadPremiado()<edadUmbral).count();
		return Integer.valueOf(String.valueOf(l));
	}
	@Override
	public Map<Genero, Integer> calcularNumeroPremiosPorGenero(){
		return premios.stream().collect(Collectors.groupingBy(Premio::genero,Collectors.collectingAndThen(Collectors.toList(), lista->(int) lista.stream().count())));
	}
	@Override
	public Map<Integer, Set<Premio>> calcularPremiosPorEdad(){
		return premios.stream().collect(Collectors.groupingBy(Premio::edadPremiado, Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().collect(Collectors.toSet()))));
	}
	@Override
	public Map<String, Double> calcularMediaEdadPorCategoria(){
		return premios.stream().collect(Collectors.groupingBy(Premio::categoria, Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().mapToDouble(x->x.edadPremiado()).average().orElse(0.))));
	}
	
}
