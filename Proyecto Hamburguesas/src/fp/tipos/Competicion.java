package fp.tipos;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Competicion {
	private SortedSet<Visita> visitas;

	public Competicion(Stream<Visita> visitas) {
		super();
		Comparator<Visita> cmp = Comparator.comparing(Visita::getCodigoPostal);
		this.visitas = visitas.sorted(cmp).collect(Collectors.toCollection(()->new TreeSet<>()));
	}

	@Override
	public String toString() {
		return "Competicion [visitas=" + visitas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(visitas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competicion other = (Competicion) obj;
		return Objects.equals(visitas, other.visitas);
	}
	
	// Ejercicios
	
	//Ejercicio 1
	public SortedSet<String> getEmailsOrdenados(Duration d){
		return visitas.stream().filter(x->x.getTiempoTranscurrido().toMinutes()<d.toMinutes() && x.getTemperatura() < getTemperaturaMedia()).map(x->x.getEmail()).sorted(Comparator.naturalOrder()).collect(Collectors.toCollection(()->new TreeSet<>()));
	}
	
	private Double getTemperaturaMedia() {
		return visitas.stream().mapToDouble(x->x.getTemperatura()).average().orElse(0.);
	}
	
	// Ejercicio 2. Nota: no entiendo el enunciado
	public Integer getTotalVisitasComilones() {
		return null;
	}
	
	// Ejercicio 3
	public String peorHamburgueseriaPorCalidadIngredientes() {
		Map<String, Integer> aux = visitas.stream().flatMap(x->x.getEvaluaciones().stream()).collect(Collectors.groupingBy(x->x.hamburgueseria(), Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().mapToInt(x->x.calidadIngredientes()).sum())));
		return aux.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
	}
	
	// Ejercicio 4
	public Map<String, String> getTopComilonPorCPEnDia(LocalDate d){
		return visitas.stream().filter(x->x.getEntrada().toLocalDate().equals(d)).collect(Collectors.groupingBy(x->x.getCodigoPostal(),
				Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().max(Comparator.comparing(x->x.getNumEvaluaciones() ) ).get().getEmail()))
				);
	}
	
	// Ejericicio 5
	public String getHamburguesaGanadora() {
		Map<String, Set<Double>> mapa = new HashMap<>();
		Map<String, Double> res = new HashMap<>();
		
		for (Visita v: visitas) {
			for (Evaluacion e: v.getEvaluaciones()) {
				if (mapa.containsKey(e.hamburgueseria())) {
					Set<Double> valor = mapa.get(e.hamburgueseria());
					valor.add(e.getPuntuacionFinal());
					mapa.put(e.hamburgueseria(), valor);
				} else {
					Set<Double> valor = new HashSet<>();
					valor.add(e.getPuntuacionFinal());
					mapa.put(e.hamburgueseria(), valor);
				}
			}
		}
		
		for (String s: mapa.keySet()) {
			Set<Double> aux = mapa.get(s);
			Double x = 0.;
			for (Double y: aux) {
				x += y;
			}
			
			res.put(s, x/aux.size());
			
		}
		
		return Collections.max(res.entrySet(), Map.Entry.comparingByValue()).getKey();
	}
	
	
	
	
}
