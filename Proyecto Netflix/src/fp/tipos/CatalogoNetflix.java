package fp.tipos;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CatalogoNetflix {
	
	private SortedSet<ProduccionNetflix> producciones;

	public CatalogoNetflix(Stream<ProduccionNetflix> producciones) {
		super();
		this.producciones = producciones.collect(Collectors.toCollection(TreeSet::new));
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
	
	// Tratamientos Secuenciales
	public Map<String, Set<ProduccionNetflix>> getProduccionesPorGenero(){
		Map<String, Set<ProduccionNetflix>> res = new HashMap<>();
		
		for (ProduccionNetflix i: producciones) {
			for (String ii: i.generos()) {
				if (res.containsKey(ii)) {
					Set<ProduccionNetflix> valor = res.get(ii);
					valor.add(i);
				} else {
					Set<ProduccionNetflix> valor = new HashSet<>();
					valor.add(i);
					res.put(ii, valor);
				}
			} 
		}
		return res;
	}
	
	public SortedSet<String> getGeneros() {
		return producciones.stream().flatMap(x->x.generos().stream()).collect(Collectors.toCollection(TreeSet::new));
	}
	
	public List<String> getTitulosDeGenerosOrdenadosPorTamanyo(List<String> genres) {
		Comparator<ProduccionNetflix> cmp = Comparator.comparing(x->x.generos().size());
		cmp = cmp.thenComparing(Comparator.comparing(x->x.titulo().length()));
		return producciones.stream().filter(x->x.generos().containsAll(genres)).sorted(cmp).map(x->x.titulo()).toList();
	}
	
	

}
