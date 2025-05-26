package fp.tipos;

import java.util.Objects;

public record Idioma(String nombre, Double porcentaje) {
	
	public Idioma{
		checkPorcentaje(porcentaje);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Idioma other = (Idioma) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	private void checkPorcentaje(Double porcentaje) {
		if (porcentaje > 0. && porcentaje < 1.) {
			throw new IllegalArgumentException("EL porcentaje es erróneo");
		}
	}
	
	

}
