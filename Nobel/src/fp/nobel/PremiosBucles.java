package fp.nobel;

import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Set<Premio>> calcularPremiosPorEdad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Double> calcularMediaEdadPorCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

}
