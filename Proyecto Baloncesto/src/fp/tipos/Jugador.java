package fp.tipos;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

public record Jugador(LocalDate fecha, String nombre, Puesto puesto, String pais, Duration tiempo, Integer tiros1, Integer tiros2, Integer tiros3, Integer puntos1, Integer puntos2, Integer puntos3) implements Comparable<Jugador> {
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int compareTo(Jugador o) {
		int res;
		if (o == null) {
			throw new NullPointerException();
		}
		res = nombre.compareTo(o.nombre);
		if (res == 0) {
			res = fecha.compareTo(o.fecha);
		}
		return res;
	}
	
}
