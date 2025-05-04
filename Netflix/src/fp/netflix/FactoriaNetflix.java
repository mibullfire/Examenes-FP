package fp.netflix;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaNetflix {
	
	private static final String DELIMITADOR = ";";
	
	public static ProduccionNetflix parse(String s) {
		String [] trozos = s.split(DELIMITADOR);
		Checkers.check("Formato no válido", trozos.length==8);
		
		String titulo = trozos[0].trim();
		ProduccionNetflixTipo tipo = ProduccionNetflixTipo.valueOf(trozos[1].trim());
		Integer anyo = Integer.valueOf(trozos[2].trim());
		Duration duracion = Duration.ofMinutes(Integer.valueOf(trozos[3].trim()));
		Set<String> generos = parseaGenero(trozos[4].trim());
		Integer numTemp = Integer.valueOf(trozos[5].trim());
		Double score = Double.valueOf(trozos[6].trim());
		Long popularidad = Long.valueOf(trozos[7].trim());
		
		
		return new ProduccionNetflix(titulo, tipo, anyo, duracion, generos, numTemp, score, popularidad);
	}
	
	private static Set<String> parseaGenero(String cadena){
		Set<String> res = new HashSet<String>();
		cadena.replace("[", "").replace("]", "");
		String [] trozos = cadena.split(";");
		for (String x: trozos) {
			res.add(x.replace("'", ""));
		}
		return res;
		
	}
	
	public static CatalogoNetflix leeNetflix(String rutaFichero) {
		List<String> lineas = Ficheros.leeFichero("Error", rutaFichero);
		lineas.remove(0);
		
		Stream<ProduccionNetflix> res = lineas.stream().map(x->parse(x));
		return new CatalogoNetflix(res);
	}

}
