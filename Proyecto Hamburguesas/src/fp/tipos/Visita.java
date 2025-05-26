package fp.tipos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Visita implements Comparable<Visita> {
	
	private String email;
	private String ciudad;
	private String codigoPostal;
	private LocalDateTime entrada;
	private LocalDateTime salida;
	private Double temperatura;
	private List<Evaluacion> evaluaciones;
	
	public String getEmail() {
		return email;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public LocalDateTime getEntrada() {
		return entrada;
	}
	public LocalDateTime getSalida() {
		return salida;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public Duration getTiempoTranscurrido() {
		return Duration.between(entrada, salida);
	}
	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
	public Integer getNumEvaluaciones() {
		return evaluaciones.size();
	}
	public Paladar getPaladar() {
		Double puntuaciones = evaluaciones.stream().mapToDouble(x->x.getPuntuacionFinal()).average().orElse(0.);
		if (puntuaciones >= 9) {
			return Paladar.BAJO;
		} else if (puntuaciones <= 6) {
			return Paladar.ALTO;
		} else {
			return Paladar.MEDIO;
		}
	}
	public Visita(String email, String ciudad, String codigoPostal, LocalDateTime entrada, LocalDateTime salida,
			Double temperatura, List<Evaluacion> evaluaciones) {
		super();
		this.email = email;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.entrada = entrada;
		this.salida = salida;
		this.temperatura = temperatura;
		this.evaluaciones = evaluaciones;
		
		R1(entrada, salida);
		R2(email);
		R3(evaluaciones);
		R4(entrada, salida);
	}
	
	private void R1(LocalDateTime entrada, LocalDateTime salida) {
		if (!entrada.isBefore(salida)) {
			throw new IllegalArgumentException("La entrada no puede ser posterior a la salida");
		}
	}
	
	private void R2(String cadena) {
		if (!cadena.contains("@")) {
			throw new IllegalArgumentException("El email ha de contener el caracter @");
		}
	}
	
	private void R3(List<Evaluacion> evaluaciones) {
		if (evaluaciones.size()==0) {
			throw new IllegalArgumentException("La lista de evaluaciones ha de tener al menos un elemento");
		}
	}
	
	private void R4(LocalDateTime entrada, LocalDateTime salida) {
		if (entrada.getDayOfYear()!=salida.getDayOfYear()) {
			throw new IllegalArgumentException("El dia de entrada ha de ser el mismo que el de salida");
		}
	}
	@Override
	public String toString() {
		return "Visita [email=" + email + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + ", entrada="
				+ entrada + ", salida=" + salida + ", temperatura=" + temperatura + ", evaluaciones=" + evaluaciones
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, entrada, salida);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visita other = (Visita) obj;
		return Objects.equals(email, other.email) && Objects.equals(entrada, other.entrada)
				&& Objects.equals(salida, other.salida);
	}
	@Override
	public int compareTo(Visita o) {
		int res = 0;
		if (o == null) {
			throw new NullPointerException();
		}
		res = entrada.compareTo(o.getEntrada());
		if (res == 0) {
			res = salida.compareTo(o.getSalida());
		}
		if (res == 0) {
			res = email.compareTo(o.getEmail());
		}
		return res;
	}
	
	

}
