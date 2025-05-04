package fp.netflix;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CatalogoNetflix {
	
	private SortedSet<ProduccionNetflix> producciones;
	
	public CatalogoNetflix(Stream<ProduccionNetflix> producciones) {
		this.producciones = producciones.collect(Collectors.toCollection(()-> new TreeSet<ProduccionNetflix>(Comparator.comparing(x->x.getAnyo()))));
	}

	public SortedSet<ProduccionNetflix> getProducciones() {
		return new TreeSet<ProduccionNetflix>(producciones);
	}

	@Override
	public int hashCode() {
		return Objects.hash(producciones);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogoNetflix other = (CatalogoNetflix) obj;
		return Objects.equals(producciones, other.producciones);
	}

	@Override
	public String toString() {
		return "CatalogoNetflix [producciones=" + producciones + "]";
	}
	
	/// Parte 4: Tratamientos Secuenciales
	
	// 1.
	public Map<String, Set<ProduccionNetflix>> getProduccionesPorGenero(){
		return getGeneros().stream()
				.collect(Collectors.toMap(x->x, x->producciones.stream()
						.filter(y->y.getGeneros().contains(x))
						.collect(Collectors.toSet())));
	}
	
	// 2.
	public SortedSet<String> getGeneros(){
		return producciones.stream().flatMap(x->x.getGeneros().stream()).collect(Collectors.toCollection(TreeSet::new));
	}
	
	// 3.
	public List<String> getTitulosDeGenerosOrdenadosPorTamanyo(List<String> genres) {
		return producciones.stream().filter(x->x.getGeneros().containsAll(genres)).sorted(Comparator.comparing(y->y.getGeneros().size())).map(z->z.getTitulo()).toList();
	}
	
	// 4.
	public String getGeneroConMayorPopularidadAcumulada() {
		return getProduccionesPorGenero().keySet().stream().max(Comparator.comparing(x->popularidadAcumulada(getProduccionesPorGenero().get(x)))).get();
	}
	private Long popularidadAcumulada(Set<ProduccionNetflix> seta) {
		return seta.stream().mapToLong(x->x.getPopularidadIMBD()).sum();
	}
	
	// 5.
	public SortedMap<Integer, Double> getMediaTopNScoresDeGeneroPorAnyo(String g, Integer n){
		return null;
	}
	
	/// Parte 5:
	
	// 1.
	public SortedMap<Integer, String> getTituloDeTipoMasPopularPorAnyo(ProduccionNetflixTipo tipo){
		return producciones.stream().filter(x->x.getTipo().equals(tipo))
				.collect(Collectors.groupingBy(ProduccionNetflix::getAnyo, ()->new TreeMap<Integer,String>(Comparator.reverseOrder()),
						Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().max(Comparator.comparing(x->x.getPopularidadIMBD())).get().getTitulo())));
	}
	
	// 2.
	public Double getDuracionMediaDeAnyoYTipo(ProduccionNetflixTipo tipo, Integer anyo) {
		return producciones.stream().filter(x->x.getTipo().equals(tipo) && x.getAnyo().equals(anyo)).mapToDouble(x->x.getDuracion().getSeconds()).average().orElse(0.);
	}
	
	// 3.
	public List<Integer> getAnyosConScoreMedioSuperiorA(Double umbralScore){
		Map<Integer, Double> scoreMedioPorAnyo = producciones.stream().
				collect(Collectors.groupingBy(x->x.getAnyo(), Collectors.averagingDouble(y->y.getScoreIMBD())));
		
		return scoreMedioPorAnyo.entrySet().stream().filter(entry->entry.getValue()>umbralScore).map(Map.Entry::getKey).toList();
	}
	
	// 4.
	public Map<Integer, Double> getPorcentajeTemporadasSeriesPorAnyo(){
		return producciones.stream().filter(x->x.getTipo().equals(ProduccionNetflixTipo.SHOW)).collect(Collectors.groupingBy(x->x.getAnyo(), Collectors.averagingDouble(x->x.getNumTemps())));
	}
	
	// 5. 
	public Map<ProduccionNetflixTipo, ProduccionNetflix> getProduccionMenosScorePorTipo(){
		Comparator<ProduccionNetflix> cmp = Comparator.comparing(ProduccionNetflix::getScoreIMBD);
		return producciones.stream().collect(Collectors.groupingBy(x->x.getTipo(), TreeMap::new, Collectors.collectingAndThen(Collectors.minBy(cmp),Optional::get)));
	}
	
	// 6.
	public Map<Integer, Set<String>> getGenerosPorAnyo (ProduccionNetflixTipo tipo){
		return producciones.stream().collect(Collectors.groupingBy(ProduccionNetflix::getAnyo, ()->new HashMap<Integer, Set<String>>(),
				Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().flatMap(x->x.getGeneros().stream()).collect(Collectors.toSet()))));
	}
}