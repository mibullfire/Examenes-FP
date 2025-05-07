package fp.futbol;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class Partido implements Comparable<Partido>{
	private LocalDate fecha;
	private String equipoL;
	private String equipoV;
	private Long numEspectadores;
	private Competicion competicion;
	private List<Resultado> parciales;
	
	public LocalDate getFecha() {
		return fecha;
	}
	public String getEquipoL() {
		return equipoL;
	}
	public String getEquipoV() {
		return equipoV;
	}
	public Long getNumEspectadores() {
		return numEspectadores;
	}
	public Competicion getCompeticion() {
		return competicion;
	}
	public List<Resultado> getParciales() {
		return parciales;
	}
	public Integer getGolesL() {
		return parciales.stream().mapToInt(Resultado::golesL).max().orElse(0);
	}
	public Integer getGolesV() {
		return parciales.stream().mapToInt(Resultado::golesV).max().orElse(0);
	}
	public List<Integer> getMinutos() {
		return parciales.stream().map(Resultado::minuto).toList();
	}

	public Partido(LocalDate fecha, String equipoL, String equipoV, Long numEspectadores, Competicion competicion,
			List<Resultado> parciales) {
		super();
		this.fecha = fecha;
		this.equipoL = equipoL;
		this.equipoV = equipoV;
		this.numEspectadores = numEspectadores;
		this.competicion = competicion;
		this.parciales = parciales;
		checkMinutos(getMinutos());
	}
	
	private void checkMinutos(List<Integer> minutos) {
		int x = 0;
		for (Integer i: minutos) {
			if (i<x) {
				throw new IllegalArgumentException("Los minutos han de estar ordenados");
			}
			x = i;
		}
	}
	@Override
	public String toString() {
		return "Partido [fecha=" + fecha + ", equipoL=" + equipoL + ", equipoV=" + equipoV + ", numEspectadores="
				+ numEspectadores + ", competicion=" + competicion + ", parciales=" + parciales + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(equipoL, equipoV, fecha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(equipoL, other.equipoL) && Objects.equals(equipoV, other.equipoV)
				&& Objects.equals(fecha, other.fecha);
	}
	@Override
	public int compareTo(Partido o) {
		int res;
		if (o==null) {
			throw new NullPointerException();
		}
		res = o.fecha.compareTo(fecha);
		if (res == 0) {
			res = o.equipoL.compareTo(equipoL);
		}
		if (res == 0) {
			res = o.equipoV.compareTo(equipoV);
		}
		return 0;
	}
	
	
	
	
}
