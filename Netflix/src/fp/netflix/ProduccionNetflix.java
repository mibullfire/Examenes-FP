package fp.netflix;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import fp.utiles.Checkers;
public class ProduccionNetflix implements Comparable<ProduccionNetflix>{
	
	private String titulo;
	private ProduccionNetflixTipo tipo;
	private Integer anyo;
	private Duration duracion;
	private Set<String> generos;
	private Integer numTemps;
	private Double scoreIMBD;
	private Long popularidadIMBD;
	
	
	public ProduccionNetflix(String titulo, ProduccionNetflixTipo tipo, Integer anyo, Duration duracion,
			Set<String> generos, Integer numTemps, Double scoreIMBD, Long popularidadIMBD) {
		
		this.titulo = titulo;
		this.tipo = tipo;
		this.anyo = anyo;
		this.duracion = duracion;
		this.generos = generos;
		this.numTemps = numTemps;
		this.scoreIMBD = scoreIMBD;
		this.popularidadIMBD = popularidadIMBD;
		Checkers.check("El año de producción debe ser posterior a 1900", anyo > 1900);
		Checkers.check("El ScoreIMBD debe estar comprendido entre 0 y 10", scoreIMBD >= 0 && scoreIMBD <= 10);
		Checkers.check("La PopularidadIMBD debe ser mayor o igual a cero", popularidadIMBD >= 0);
		restriccion4(tipo, numTemps);
		
	}
	
	public void restriccion4(ProduccionNetflixTipo tipo, Integer numTemps) {
		if (tipo.equals(ProduccionNetflixTipo.MOVIE)) {
			if (!numTemps.equals(0)) {
				throw new IllegalArgumentException("Una película no puede tener números de temporada.");
			}
		} else {
			if (!(numTemps >= 1)) {
				throw new IllegalArgumentException("Una serie ha de tener al menos un temporada.");
			}
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public ProduccionNetflixTipo getTipo() {
		return tipo;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public Set<String> getGeneros() {
		return generos;
	}

	public Integer getNumTemps() {
		return numTemps;
	}

	public Double getScoreIMBD() {
		return scoreIMBD;
	}

	public Long getPopularidadIMBD() {
		return popularidadIMBD;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anyo, titulo);
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
		return Objects.equals(anyo, other.anyo) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "ProduccionNetflix [titulo=" + titulo + ", tipo=" + tipo + ", anyo=" + anyo + ", duracion=" + duracion
				+ ", generos=" + generos + ", numTemps=" + numTemps + ", scoreIMBD=" + scoreIMBD + ", popularidadIMBD="
				+ popularidadIMBD + "]";
	}

	@Override
	public int compareTo(ProduccionNetflix o) {
		int res;
		if (o==null) {
			throw new NullPointerException();
		}
		res = titulo.compareTo(o.getTitulo());
		if (res == 0) {
			res = anyo.compareTo(o.getAnyo());
		}
		return res;
	}
}
