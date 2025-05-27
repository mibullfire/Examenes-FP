package fp.tipos;

import java.time.Duration;
import java.util.Objects;

public class CanalTwitch implements Comparable<CanalTwitch> {
	private String nombre;
	private Duration tiempoVisionado;
	private Duration duracionContenido;
	private Integer picoEspectadores;
	private Integer mediaEspectadores;
	private Integer numSeguidores;
	private Integer numSeguidoresGanados;
	private Integer numEspectadoresGanados;
	private Boolean socio;
	private Boolean nsfw;
	private String idioma;
	
	public String getNombre() {
		return nombre;
	}
	public Duration getTiempoVisionado() {
		return tiempoVisionado;
	}
	public Duration getDuracionContenido() {
		return duracionContenido;
	}
	public Integer getPicoEspectadores() {
		return picoEspectadores;
	}
	public Integer getMediaEspectadores() {
		return mediaEspectadores;
	}
	public Integer getNumSeguidores() {
		return numSeguidores;
	}
	public Integer getNumSeguidoresGanados() {
		return numSeguidoresGanados;
	}
	public Integer getNumEspectadoresGanados() {
		return numEspectadoresGanados;
	}
	public Boolean getSocio() {
		return socio;
	}
	public Boolean getNsfw() {
		return nsfw;
	}
	public String getIdioma() {
		return idioma;
	}
	public Double getPorcentajeRetencion() {
		if (numEspectadoresGanados > 0) {
			return numSeguidores /numEspectadoresGanados *100.; 
		} else {
			return 0.;
		}
	}
	public Double getRatioEfectividad() {
		return (double) (tiempoVisionado.toMinutes() / duracionContenido.toMinutes());
	}
	public Integer getNumEspectadoresOcasionales() {
		return picoEspectadores - mediaEspectadores;
	}
	
	public CanalTwitch(String nombre, Duration tiempoVisionado, Duration duracionContenido, Integer picoEspectadores,
			Integer mediaEspectadores, Integer numSeguidores, Integer numSeguidoresGanados,
			Integer numEspectadoresGanados, Boolean socio, Boolean nsfw, String idioma) {
		super();
		this.nombre = nombre;
		this.tiempoVisionado = tiempoVisionado;
		this.duracionContenido = duracionContenido;
		this.picoEspectadores = picoEspectadores;
		this.mediaEspectadores = mediaEspectadores;
		this.numSeguidores = numSeguidores;
		this.numSeguidoresGanados = numSeguidoresGanados;
		this.numEspectadoresGanados = numEspectadoresGanados;
		this.socio = socio;
		this.nsfw = nsfw;
		this.idioma = idioma;
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
		CanalTwitch other = (CanalTwitch) obj;
		return Objects.equals(nombre, other.nombre);
	}
	@Override
	public int compareTo(CanalTwitch o) {
		if (o == null) {
			throw new NullPointerException();
		}
		return nombre.compareTo(o.nombre);
	}
	@Override
	public String toString() {
		return "CanalTwitch [nombre=" + nombre + ", tiempoVisionado=" + tiempoVisionado + ", duracionContenido="
				+ duracionContenido + ", picoEspectadores=" + picoEspectadores + ", mediaEspectadores="
				+ mediaEspectadores + ", numSeguidores=" + numSeguidores + ", numSeguidoresGanados="
				+ numSeguidoresGanados + ", numEspectadoresGanados=" + numEspectadoresGanados + ", socio=" + socio
				+ ", nsfw=" + nsfw + ", idioma=" + idioma + "]";
	}
	
	

}
