package sevici.tipos;

import java.util.Objects;

import fp.utiles.Checkers;

public class Estacion implements Comparable<Estacion> {
	private String id;
	private String nombre;
	private Integer numPuestos;
	private Integer bicisDisponibles;
	private Coordenadas ubicacion;
	
	
	
	public Estacion(String id, String nombre, Integer numPuestos, Integer bicisDisponibles, Coordenadas ubicacion) {
		this.id = id;
		this.nombre = nombre;
		this.numPuestos = numPuestos;
		this.bicisDisponibles = bicisDisponibles;
		this.ubicacion = ubicacion;
		Checkers.checkCondicion("El número de puestos disponibles debe de ser mayor a cero", numPuestos > 0);
		Checkers.checkCondicion("El número de bicis disponibles tiene que ser mayor a cero y menor al numero de puestos", bicisDisponibles >= 0 && bicisDisponibles <= numPuestos);
	}
	
	public Integer getBicisDisponibles() {
		return bicisDisponibles;
	}
	public void setBicisDisponibles(Integer bicisDisponibles) {
		this.bicisDisponibles = bicisDisponibles;
	}
	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getNumPuestos() {
		return numPuestos;
	}
	public Coordenadas getUbicacion() {
		return ubicacion;
	}
	public Integer getPuestosVacios() {
		return (numPuestos - bicisDisponibles);
	}
	public Boolean getTieneBicis() {
		Boolean res = false;
		if (bicisDisponibles > 0) {
			res = true;
		}
		return res;
	}
	public Double getOcupacion() {
		return Double.valueOf(Math.divideExact(bicisDisponibles, numPuestos));
	}
	public int hashCode() {
		return Objects.hash(id);
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estacion other = (Estacion) obj;
		return Objects.equals(id, other.id);
	}
	public int compareTo(Estacion o) {
		if (o == null) {
			throw new NullPointerException();
		}
		return id.compareTo(o.id);
	}
	public String toString() {
		return "Estacion [id=" + id + ", nombre=" + nombre + ", numPuestos=" + numPuestos + ", bicisDisponibles="
				+ bicisDisponibles + ", ubicacion=" + ubicacion + "]";
	}
}
