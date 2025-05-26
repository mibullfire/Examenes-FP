package fp.tipos;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Viaje {
	
	private Double precio;
	private Integer distancia;
	private Duration duracion;
	private TipoViaje tipoViaje;
	private List<Parada> trayecto;
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getDistancia() {
		return distancia;
	}
	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
	public Duration getDuracion() {
		return duracion;
	}
	public void setDuracion(Duration duracion) {
		this.duracion = duracion;
	}
	public TipoViaje getTipoViaje() {
		return tipoViaje;
	}
	public void setTipoViaje(TipoViaje tipoViaje) {
		this.tipoViaje = tipoViaje;
	}
	public List<Parada> getTrayecto() {
		return trayecto;
	}
	public void setTrayecto(List<Parada> trayecto) {
		this.trayecto = trayecto;
	}
	
	public Double getVelocidadMedia() {
		return Double.valueOf(distancia/duracion.toHours());
	}
	
	public Integer getNumParadas() {
		return (int) trayecto.stream().map(x->x.nombre()).distinct().count() - 2;
	}
	
	public List<String> getNombresParadas() {
		List<String> res = new ArrayList<String>();
		if (trayecto.isEmpty()) {
			return res;
		}
		res = trayecto.stream().map(x->x.nombre()).distinct().toList();
		//res.removeLast();
		//res.removeFirst();
		return res;
	}
	
	public String getOrigen() {
		return trayecto.stream().map(x->x.nombre()).toList().get(0);
	}
	
	public String getDestino() {
		return trayecto.stream().map(x->x.nombre()).toList().getLast();
	}
	@Override
	public int hashCode() {
		return Objects.hash(trayecto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return Objects.equals(trayecto, other.trayecto);
	}
	@Override
	public String toString() {
		return "Viaje [precio=" + precio + ", distancia=" + distancia + ", duracion=" + duracion + ", tipoViaje="
				+ tipoViaje + ", trayecto=" + trayecto + "]";
	}
	public Viaje(Double precio, Integer distancia, Duration duracion, TipoViaje tipoViaje, List<Parada> trayecto) {
		super();
		this.precio = precio;
		this.distancia = distancia;
		this.duracion = duracion;
		this.tipoViaje = tipoViaje;
		this.trayecto = trayecto;
	}
	
	
	

}
