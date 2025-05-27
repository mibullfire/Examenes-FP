package fp.tipos;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;

public record ProduccionNetflix(String titulo, Tipo tipo, Integer anyo, Duration duracion, Set<String> generos, Integer numTemporadas, Double scoreIMDB, Long popularidadIMDB) implements Comparable<ProduccionNetflix> {
	public ProduccionNetflix {
		R1(anyo);
		R2(scoreIMDB);
		R3(popularidadIMDB);
		R4(tipo, numTemporadas);
		
	}
	
	private void R1(Integer anyo) {
		if (anyo <= 1900) {
			throw new IllegalArgumentException("El año debe de ser posterior a 1900");
		}
	}
	
	private void R2(Double score) {
		if (score < 0 && score > 10) {
			throw new IllegalArgumentException("El score debe de estar entre 0 y 10");
		}
	}
	
	private void R3(Long popularidad) {
		if (popularidad < 0) {
			throw new IllegalArgumentException("La popularidad debe ser mayor o igual que cero");
		}
	}
	
	private void R4(Tipo tipo, Integer numTemporadas) {
		if (tipo.equals(Tipo.MOVIE) && numTemporadas != 0) {
			throw new IllegalArgumentException("Una pelicula no puede tener numero de temporadas");
		}
		if (tipo.equals(Tipo.SHOW) && numTemporadas < 1) {
			throw new IllegalArgumentException("Una serie debe tener al menos una temporada");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(anyo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProduccionNetflix other = (ProduccionNetflix) obj;
		return Objects.equals(anyo, other.anyo) && tipo == other.tipo;
	}

	@Override
	public int compareTo(ProduccionNetflix o) {
		int res;
		if (o == null) {
			throw new NullPointerException();
		}
		res = titulo.compareTo(o.titulo);
		if (res == 0) {
			res = anyo.compareTo(o.anyo);
		}
		return res;
	}
	
	
}
