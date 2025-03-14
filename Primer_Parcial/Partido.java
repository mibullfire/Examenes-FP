package fp.tipos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Partido implements Comparable<Partido>{
	private LocalDate fecha;
	private String local;
	private String visitante;
	private Long numEspectadores;
	private Competicion competicion;
	private List<Futbol> resultado;
	
	public Partido(LocalDate fecha, String local, String visitante, Long numEspectadores, Competicion competicion,
			List<Futbol> resultado) {
		this.fecha = fecha;
		this.local = local;
		this.visitante = visitante;
		this.numEspectadores = numEspectadores;
		this.competicion = competicion;
		this.resultado = new ArrayList<>(resultado);
	}

	public Integer getGolesLocal() {
		Integer result = 0;
		for(Futbol r:resultado) {
			result += r.golesLocal();
		}
		return result;
	}
	
	public Integer getGolesVisitante() {
		Integer result = 0;
		for(Futbol r:resultado) {
			result += r.golesVisitante();
		}
		return result;
	}
	
	public List<Integer> getMinutos() {
		List<Integer> result = new ArrayList<Integer>();
		for(Futbol r:resultado) {
			result.add(r.minuto());
		}
		return result;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getLocal() {
		return local;
	}

	public String getVisitante() {
		return visitante;
	}

	public Long getNumEspectadores() {
		return numEspectadores;
	}

	public Competicion getCompeticion() {
		return competicion;
	}

	public List<Futbol> getResultado() {
		return new ArrayList<>(resultado);
	}
	
	public String toString() {
		return fecha + ", " + local + ", " + visitante + ", " + numEspectadores + ", " + competicion + ", " + resultado;
	}
	
	public int hashCode() {
		return Objects.hash(fecha, local, visitante);
	}
	
	public boolean equals(Object o) {
		boolean result = false;
		if(o instanceof Partido) {
			Partido p = (Partido) o;
			result = Objects.equals(fecha, p.fecha) && Objects.equals(local, p.local) && Objects.equals(visitante, p.visitante);
		}
		return result;
	}
	
	public int compareTo(Partido o) {
		int r;
		if(o == null) {
			throw new NullPointerException();
		} r = fecha.compareTo(getFecha());
		if(r == 0) {
			r = local.compareTo(getLocal());
			if(r == 0) {
				r = visitante.compareTo(getVisitante());
			}
		}
		return r;
	}
	
	public Integer getNumGolesEquipos(Set<String> equipos) {
		Integer result = 0;
		if(equipos.contains(local)) {
			result += getGolesLocal();
		}
		if(equipos.contains(visitante)) {
			result += getGolesVisitante();
		}
		return result;
	}
}
