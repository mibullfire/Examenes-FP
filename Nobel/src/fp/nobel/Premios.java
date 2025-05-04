package fp.nobel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Premios {

	String toString();

	int hashCode();

	boolean equals(Object obj);

	void anyadirPremio(Premio p);

	List<Premio> obtenerPremiosDeGenero(Genero genero);

	Integer calcularNumeroPremiadosMasJovenesDe(Integer edadUmbral);

	Map<Genero, Integer> calcularNumeroPremiosPorGenero();

	Map<Integer, Set<Premio>> calcularPremiosPorEdad();

	Map<String, Double> calcularMediaEdadPorCategoria();

}