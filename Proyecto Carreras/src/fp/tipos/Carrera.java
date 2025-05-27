package fp.tipos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Carrera implements Comparable<Carrera> {
	private String id;
	private String localidad;
	private LocalDateTime fechaHora;
	private Modalidad modalidad;
	private Integer distancia;
	private Integer desnivel;
	private List<Participante> participantes;
	
	public String getId() {
		return id;
	}
	public String getLocalidad() {
		return localidad;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public Modalidad getModalidad() {
		return modalidad;
	}
	public Integer getDistancia() {
		return distancia;
	}
	public Integer getDesnivel() {
		return desnivel;
	}
	public List<Participante> getParticipantes() {
		return participantes;
	}
	public Carrera(String id, String localidad, LocalDateTime fechaHora, Modalidad modalidad, Integer distancia,
			Integer desnivel) {
		super();
		this.id = id;
		this.localidad = localidad;
		this.fechaHora = fechaHora;
		this.modalidad = modalidad;
		this.distancia = distancia;
		this.desnivel = desnivel;
		this.participantes = new ArrayList<Participante>();
		
		checkCarrera(distancia);
		checkDesnivel(desnivel);
	}
	
	private void checkCarrera(Integer distancia) {
		if (distancia < 7) {
			throw new IllegalArgumentException("La distancia debe de ser de al menos 7km");
		}
	}
	private void checkDesnivel(Integer desnivel) {
		if (desnivel < 0 || desnivel >= 1000) {
			throw new IllegalArgumentException("Desnivel no válido");
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(distancia, fechaHora, id, localidad);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		return Objects.equals(distancia, other.distancia) && Objects.equals(fechaHora, other.fechaHora)
				&& Objects.equals(id, other.id) && Objects.equals(localidad, other.localidad);
	}
	@Override
	public int compareTo(Carrera o) {
		int res;
		if (o == null) {
			throw new NullPointerException();
		}
		res = fechaHora.compareTo(o.getFechaHora());
		if (res == 0) {
			res = localidad.compareTo(o.getLocalidad());
		}
		if (res == 0) {
			res = distancia.compareTo(o.getDistancia());
		}
		if (res == 0) {
			res = id.compareTo(id);
		}
		return res;
	}
	
	public void añdadeParticipantes(List<Participante> x) {
		for (Participante p: x) {
			if (!participantes.contains(p)) {
				participantes.add(p);
			}
		}
	}
	@Override
	public String toString() {
		return "Carrera [id=" + id + ", localidad=" + localidad + ", fechaHora=" + fechaHora + ", modalidad="
				+ modalidad + ", distancia=" + distancia + ", desnivel=" + desnivel + ", participantes=" + participantes
				+ "]";
	}
	
	public Participante buscaParticipante(String nombre, String apellidos) {
		List<Participante> busqueda = participantes.stream().filter(x->x.nombre().equals(nombre)&&x.apellidos().equals(apellidos)).toList();
		
		if (busqueda.size() > 0) {
			return busqueda.get(0);
		}
		return null;
	}
	
	public Double tiempoMedioPorKm() {
		return participantes.stream().mapToDouble(x -> x.duracion().toMillis() / 60.0 / getDistancia()).average().orElseThrow(()-> new NoSuchElementException("Imposible calcular"));
	}
	
	
	
	
}
