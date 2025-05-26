package fp.tipos;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaisesdelMundo {
	
	private SortedSet<Pais> paises;
	private LocalDateTime fechaHora;
	
	public SortedSet<Pais> getPaises() {
		return paises;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public Integer getNumPaises() {
		return paises.size();
	}
	public PaisesdelMundo(Stream<Pais> paises, LocalDateTime fechaHora) {
		this.paises = paises.sorted(Comparator.comparing(x->x.getNombre())).collect(Collectors.toCollection(()->new TreeSet<Pais>()));
		this.fechaHora = fechaHora;
	}
	public PaisesdelMundo(List<Pais> paises) {
		this.paises = paises.stream().sorted(Comparator.comparing(x->x.getNombre())).collect(Collectors.toCollection(()->new TreeSet<Pais>()));
		this.fechaHora = LocalDateTime.now();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, paises);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaisesdelMundo other = (PaisesdelMundo) obj;
		return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(paises, other.paises);
	}
	@Override
	public String toString() {
		return "PaisesdelMundo [paises=" + paises + ", fechaHora=" + fechaHora + "]";
	}

}
