package fp.tipos;

import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EstadisticasTwitch {
	
	private List<CanalTwitch> canales;

	public EstadisticasTwitch(List<CanalTwitch> canales) {
		super();
		this.canales = canales;
	}

	public List<CanalTwitch> getCanales() {
		return canales;
	}
	
	// Funciones
	
	public String getMayorRetencion(String idioma) {
		if (!idioma.equals(null)) {
			return canales.stream().filter(x->x.getIdioma().equals(idioma)).max(Comparator.comparing(x->x.getPorcentajeRetencion())).get().getNombre();
		}
		return canales.stream().max(Comparator.comparing(x->x.getPorcentajeRetencion())).get().getNombre();	
	}
	
	public Duration getDuracionTotal(Boolean condicion) {
		Long total = canales.stream().filter(x->x.getSocio().equals(condicion)).mapToLong(x->x.getDuracionContenido().toMinutes()).sum();
		return Duration.ofMinutes(total);
	}
	
	private Double getMediaEspectadoresTotal() {
		return canales.stream().mapToInt(x->x.getMediaEspectadores()).sum()/canales.size() + 0.;
	}
	
	public Integer filtroMedia() {
		Long total = canales.stream().filter(x->x.getMediaEspectadores()>getMediaEspectadoresTotal()).count();
		return total.intValue();
	}
	
	public Double getPorcentajeNSFW(Integer n) {
		List <CanalTwitch> filtro = canales.stream().sorted(Comparator.comparing(x->x.getTiempoVisionado())).limit(n).toList();
		return filtro.stream().filter(x->x.getNsfw().equals(Boolean.TRUE)).toList().size()/filtro.size() * 100.;
	}
	
	public SortedMap<String, CanalTwitch> getCanalMasEfectivoPorIdioma() {
		return canales.stream().collect(Collectors.groupingBy(
				x->x.getIdioma(),
				() -> new TreeMap<>(),
				Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().max(Comparator.comparing(x->x.getRatioEfectividad())).get())
				));
	}
	
	private Map<String, Double> getIdiomaPorPresencia(){
		
		Map<String, Double> res = canales.stream().collect(Collectors.groupingBy(
				x->x.getIdioma(),
				
				Collectors.collectingAndThen(Collectors.toList(), lista->lista.stream().mapToLong(x->x.getDuracionContenido().toMinutes()).sum() / canales.stream().mapToLong(x->x.getDuracionContenido().toMinutes()).sum() *100.)
				));
		
		return res;
				
	}
	
	public List<String> idiomasPorPresenciaLista(){
		Comparator<Entry<String, Double>> cmp = Entry.comparingByValue();
		return getIdiomaPorPresencia().entrySet().stream().sorted(cmp.reversed()).map(x->x.getKey()).toList();
	}

}
