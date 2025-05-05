package fp.nobel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class PremiosBucles implements Premios {
	
	public static List<Premio> premios;
	
	public PremiosBucles() {
		this.premios = new ArrayList<Premio>();
	}
	public PremiosBucles(Stream x) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<Premio> obtenerPremiosDeGenero(Genero genero) {
		List<Premio> res = new ArrayList<Premio>();
		for (Premio p: premios) {
			if (p.genero().equals(genero)) {
				res.add(p);
			}
		}
		return res;
	}

	@Override
	public Integer calcularNumeroPremiadosMasJovenesDe(Integer edadUmbral) {
		Integer res=0;
		for (Premio p: premios) {
			if (p.edadPremiado()<edadUmbral) {
				res+=1;
			}
		}
		return res;
	}

	@Override
	public Map<Genero, Integer> calcularNumeroPremiosPorGenero() {
		Map<Genero, Integer> res = new HashMap<Genero, Integer>();
		for (Premio p: premios) {
			if (res.containsKey(p.genero())) {
				int valor = res.get(p.genero());
				res.put(p.genero(), valor+1);
			} else {
				res.put(p.genero(), 1);
			}
		}
		return res;
	}

	@Override
	public Map<Integer, Set<Premio>> calcularPremiosPorEdad() {
		Map<Integer, Set<Premio>> res = new HashMap<Integer, Set<Premio>>();
		for (Premio p: premios) {
			if (res.containsKey(p.edadPremiado())) {
				Set<Premio> valor = res.get(p.edadPremiado());
				valor.add(p);
				res.put(p.edadPremiado(), valor);
			} else {
				res.put(p.edadPremiado(), new HashSet<Premio>());
			}
		}
		return res;
	}

	@Override
	public Map<String, Double> calcularMediaEdadPorCategoria() {
		Map<String, Double> res = new HashMap<String, Double>();
		Map<String, List<Integer>> aux = new HashMap<String, List<Integer>>();
		for(Premio p: premios) {
			if (aux.containsKey(p.categoria())) {
				List<Integer> lista = aux.get(p.categoria());
				lista.add(p.edadPremiado());
				res.put(p.categoria(), null);
			} else {
				List<Integer> lista = new ArrayList<Integer>(p.edadPremiado());
				aux.put(p.categoria(), lista);
			}
		}
		
		for (String clave: aux.keySet()) {
			List<Integer> lista = aux.get(clave);
			int suma = 0;
			for(int i: lista) {
				suma += i;
			}
			Double media = (double) suma / lista.size();
			res.put(clave, media);
		}
		
		return res;
	}

}
