package fp.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FactoriaNetflix {
	
	public static CatalogoNetflix leeNetflix(String rutaFichero) {
		List<ProduccionNetflix> res = new ArrayList<>();
		
		try {
			List<String> lineas = Files.readAllLines(Path.of(rutaFichero));
			lineas.remove(0);
			for (String s: lineas) {
				res.add(parse(s));
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		return new CatalogoNetflix(res.stream());
	}
	
	private static ProduccionNetflix parse(String s) {
		String [] trozos = s.split(",");
		checkTrozo(trozos, 8);
		
		String titulo = trozos[0].trim();
		Tipo tipo = Tipo.valueOf(trozos[1].trim().toUpperCase());
		Integer anyo = Integer.valueOf(trozos[2].trim());
		Duration duracion = Duration.ofMinutes(Long.valueOf(trozos[3].trim()));
		Set<String> generos = parseGeneros(trozos[4].trim());
		Integer numTemporadas = Integer.valueOf(trozos[5].trim());
		Double score = Double.valueOf(trozos[6].trim());
		Long popularadidad = Long.valueOf(trozos[7].trim());
		
		return new ProduccionNetflix(titulo, tipo, anyo, duracion, generos, numTemporadas, score, popularadidad);
	}
	
	private static void checkTrozo(String [] trozos, Integer tamanyo) {
		if (trozos.length != tamanyo) {
			throw new IllegalArgumentException("El tamaño de trozos es erroneo");
		}
	}
	
	private static Set<String> parseGeneros(String s) {
		Set<String> res = new HashSet<>();
		s = s.replace("[", "").replace("]", "");
		String [] trozos = s.split(";");
		for (String i: trozos) {
			res.add(i);
		}
		return res;
		
	}

}
