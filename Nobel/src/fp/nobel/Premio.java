package fp.nobel;

import java.util.Objects;

public record Premio(Integer anyo, String categoria, String nombre, String apellidos, Genero genero, Integer anyoNacimiento) {
	
	public Integer edadPremiado() {
		return anyo-anyoNacimiento();
	}

	@Override
	public int hashCode() {
		return Objects.hash(anyo, apellidos, categoria, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Premio other = (Premio) obj;
		return Objects.equals(anyo, other.anyo) && Objects.equals(apellidos, other.apellidos)
				&& Objects.equals(categoria, other.categoria) && Objects.equals(nombre, other.nombre);
	}
	
	
}
