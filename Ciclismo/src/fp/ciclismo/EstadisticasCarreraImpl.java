package fp.ciclismo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class EstadisticasCarreraImpl implements EstadisticasCarrera {

	private String nombreCarrera;
	private List<Ganador> ganadores;

	/**
	 * @param nombre Nombre de la carrera
	 */
	public EstadisticasCarreraImpl(String nombre) {
		this.nombreCarrera = nombre;
		this.ganadores = new ArrayList<Ganador>();
	}
	
	public EstadisticasCarreraImpl(String nombre, Collection<Ganador> ganadores) {
		this (nombre);
		this.ganadores = new ArrayList<Ganador>(ganadores);
	}
	
	@Override
	public String getNombreCarrera() {
		return this.nombreCarrera;
	}
	/* (non-Javadoc)
	 * @see fp.ciclismo.EstadisticasTour#getGanadores()
	 */
	public List<Ganador> getGanadores() {
		return new ArrayList<Ganador>(ganadores);
	}


	public boolean equals(Object obj) {
	boolean res = false;
		if (obj!= null && obj instanceof EstadisticasCarrera){
			EstadisticasCarrera est= (EstadisticasCarrera) obj;
			res = getNombreCarrera().equals(est.getNombreCarrera())
					&& this.ganadores.equals(est.getGanadores());
		}
	return res;
	}

	public int hashCode() {
		return getNombreCarrera().hashCode() + 31* this.ganadores.hashCode();
	}
	
	public String toString() {
		return getNombreCarrera()+" - " + this.ganadores;
	}

	public List<String> getGanadoresConRecorridoInferiorA(Integer km) {
		return ganadores.stream().filter(x->x.kmRecorridos()<km).map(x->x.nombre()).toList();
	}
	
	public Long getNumeroGanadores() {
		return ganadores.stream().map(x->x.nombre()).count();
	}

	public Boolean hanGanadoTodosAlgunaEtapa(){
		//TODO
		return null;		
	}
	
	public Boolean hayAlgunGanador(String nacionalidad) {
		List<Ganador> lista = ganadores.stream().filter(x->x.nacionalidad().equals(nacionalidad)).toList();
		return lista.size()>0;
	}

	public Set<String> getEquiposGanadores() {
		return ganadores.stream().map(x->x.equipo()).collect(Collectors.toSet());
	}

	public Ganador buscaGanador(String nombre) {
		List<Ganador> lista = ganadores.stream().filter(x->x.nombre().equals(nombre)).toList();
		return lista.size()>0?lista.get(0):null;
	}

	public Ganador buscaGanador(Integer anyo) {
		List<Ganador> lista =  ganadores.stream().filter(x->x.anyo().equals(anyo)).toList();
		return lista.size()>0?lista.get(0):null;
	}
	public Integer calculaDistanciaTotal() {
		return ganadores.stream().mapToInt(x->x.kmRecorridos()).sum();
	}

	public Double getMediaEtapasGanadas(String equipo) {
		Long total = ganadores.stream().count();
		Long parcial = ganadores.stream().filter(x->x.equipo().equals(equipo)).count();
		
		return (double) parcial/total;
	}

	public Integer getKmMenorRecorrido() {
		return ganadores.stream().mapToInt(x->x.kmRecorridos()).min().orElse(0);
	 } 

	public String getGanadorMasRapido() {
		return ganadores.stream().sorted(Comparator.comparing(Ganador::getVelocidadMedia)).map(x->x.nombre()).toList().get(0);
	}

	public Map<String, List<Ganador>> getGanadoresPorNacionalidad() {
		return ganadores.stream().collect(Collectors.groupingBy(x->x.nacionalidad()));
	}

	public Map<String, Set<Ganador>> getGanadoresPorNacionalidad_ejemplo2() {
		return ganadores.stream().collect(Collectors.groupingBy(x->x.nacionalidad(), Collectors.toSet()));
	}
	
	public Map<String, SortedSet<Ganador>> getGanadoresPorNacionalidad_ejemplo3() {
		return ganadores.stream().collect(Collectors.groupingBy(Ganador::nacionalidad, Collectors.collectingAndThen(Collectors.toSet(), conjunto->conjunto.stream().collect(Collectors.toCollection(()->new TreeSet<Ganador>())))));
	}

	public Map<String, Long> cuentaGanadoresPorNacionalidad() {
		return ganadores.stream().collect(Collectors.groupingBy(Ganador::nacionalidad, Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().count())));

	}

	public Map<String, Integer> getTotalEtapasGanadasPorEquipo(){
		return ganadores.stream().collect(Collectors.groupingBy(Ganador::equipo, Collectors.collectingAndThen(Collectors.toList(), lista->(int) lista.stream().count())));
	}

	public Map<String, Double> getMediaEtapasGanadasPorEquipo(){
		Long total = ganadores.stream().count();
		return ganadores.stream().collect(Collectors.groupingBy(Ganador::equipo, Collectors.collectingAndThen(Collectors.toList(), lista->(double) lista.size()/total)));
	}
	
	@Override
	public Map<String, Integer> cuentaCarrerasGanadasPorCiclista() {
		
		//TODO
		return null;
	}

	public void guardaGanadoresNacionalidadConAnyos(String nombreFichero, String nacionalidad) {
		//TODO
	}
	

	public Map<String, Ganador> ganadorMasDiasMaillotPorNacionalidad() {
		//TODO
		return null;
	}
	
	

	
	

	/* (non-Javadoc)
	 * @see fp.ciclismo.EstadisticasCarrera#guardaGanadoresDeAnyosOrdenados(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public void guardaGanadoresDeAnyosOrdenados(String nombreFichero, Integer anyoInicial, Integer anyoFinal) {
		//TODO
	}

	@Override
	public String getNacionalidadMasGanadores() {
		return null;
	}
	@Override
	public Map<String, List<Ganador>> getMasDiasMaillotPorNacionalidad(Integer n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Ganador> ganadorMasDiasMaillotPorEquipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNacionalidadesGanadores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCiclistasTop(Integer n) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
