package fp.tipos;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Pais implements Comparable<Pais>{
	String ISO;
	String nombre;
	Long poblacion;
	Double area;
	String capital;
	Continente continente;
	String moneda;
	List<Idioma> idiomas;
	Set<String> vecinos;
	
	public String getISO() {
		return ISO;
	}
	public String getNombre() {
		return nombre;
	}
	public Long getPoblacion() {
		return poblacion;
	}
	public Double getArea() {
		return area;
	}
	public String getCapital() {
		if (capital.equals("null")) {
			return null;
		}
		return capital;
	}
	public Continente getContinente() {
		return continente;
	}
	public String getMoneda() {
		if (moneda.equals("null")) {
			return null;
		}
		return moneda;
	}
	public List<Idioma> getIdiomas() {
		return idiomas;
	}
	public Set<String> getVecinos() {
		return vecinos;
	}
	public Pais(String iSO, String nombre, Long poblacion, Double area, String capital, Continente continente,
			String moneda, List<Idioma> idiomas, Set<String> vecinos) {
		super();
		ISO = iSO;
		this.nombre = nombre;
		this.poblacion = poblacion;
		this.area = area;
		this.capital = capital;
		this.continente = continente;
		this.moneda = moneda;
		this.idiomas = idiomas;
		this.vecinos = vecinos;
		R1(poblacion);
		R2(area);
		R3(poblacion, capital, moneda);
	}
	
	private void R1(Long poblacion) {
		if (poblacion < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	private void R2(Double area) {
		if (area < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	private void R3(Long poblacion, String capital, String moneda) {
		if (poblacion > 0) {
			if(capital.equals(null)) {
				throw new IllegalArgumentException();
			}
			if(moneda.equals(null)) {
				throw new IllegalArgumentException();
			}
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(ISO);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(ISO, other.ISO);
	}
	@Override
	public String toString() {
		return "Pais [ISO=" + ISO + ", nombre=" + nombre + ", poblacion=" + poblacion + ", area=" + area + ", capital="
				+ capital + ", continente=" + continente + ", moneda=" + moneda + ", idiomas=" + idiomas + ", vecinos="
				+ vecinos + "]";
	}
	
	public Boolean sonTodosVecinos(Set<Pais> paises) {
		return paises.stream().allMatch(x->vecinos.contains(x.getISO()));
	}
	@Override
	public int compareTo(Pais o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	

}
