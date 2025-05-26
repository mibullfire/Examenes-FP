package fp.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FactoriaPaises {
	public static Pais parseaPais(String lineaCsv) {
		String [] trozos = lineaCsv.split(";");
		
		String ISO = trozos[0].trim();
		String pais = trozos[1].trim();
		Long poblacion = Long.valueOf(trozos[2].trim());
		Double area = Double.valueOf(trozos[3].trim());
		String capital = trozos[4].trim();
		Continente continente = Continente.valueOf(trozos[5].trim().toUpperCase());
		String moneda = trozos[6].trim();
		List<Idioma> idiomas = parseaIdiomas(trozos[7].trim());
		Set<String> vecinos = parseaVecinos(trozos[8].trim());
		
		return new Pais(ISO, pais, poblacion, area, capital, continente, moneda, idiomas, vecinos);
	}
	
	public static List<Idioma> parseaIdiomas(String cadena){
		List<Idioma> res = new ArrayList<Idioma>();

		
		if (cadena == null || cadena.isEmpty()) {
			return res;
		} else {
				
			
			String [] trozos = cadena.split(",");
			
			for (String x: trozos) {
				String [] y = x.split(":");
				if (y.length >= 2) {
					res.add(new Idioma(y[0].trim(), Double.valueOf(y[1].trim())));
				} else {
					res.add(new Idioma(y[0].trim(), 0.0));
				}
			}
			return res;
		}
		
	}
	
	public static Set<String> parseaVecinos(String cadena){
		Set<String> res = new HashSet<String>();
		
		if (cadena == null || cadena.isEmpty()) {
			return res;
		} else {
			String [] trozos = cadena.split(",");
			
			for (String x: trozos) {
				res.add(x.trim());
			}
			
			return res;
		}
	}
	
	public static PaisesdelMundo leeFichero(String ruta) {
		List<Pais> res = new ArrayList<>();
		List<String> lineas = null;
		try {
			lineas = Files.readAllLines(Path.of(ruta));

			lineas.remove(0);
			for (String l: lineas) {
				res.add(parseaPais(l));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new PaisesdelMundo(res);
	}
}
