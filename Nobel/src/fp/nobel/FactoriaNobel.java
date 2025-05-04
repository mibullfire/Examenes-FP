package fp.nobel;

import java.util.List;
import java.util.stream.Stream;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaNobel {
	private static Implementacion implementacion = Implementacion.STREAM;
	
	public static Premio parsearPremio(String linea) {
		String [] trozos = linea.split(",");
		
		Checkers.check("La línea ha de ser de 6 variables", trozos.length == 6);
		
		Integer anyo = Integer.valueOf(trozos[0].trim());
		String categoria = trozos[1].trim();
		String nombre = trozos[2].trim();
		String apellidos = trozos[3].trim();
		Genero genero = Genero.valueOf(trozos[4].trim().toUpperCase());
		Integer anyoNacimiento = Integer.valueOf(trozos[5].trim());
		
		return new Premio(anyo, categoria, nombre, apellidos, genero, anyoNacimiento);
	}
	
	public static Premios leerPremios(String rutaFichero){
		List<String> lineas = Ficheros.leeFichero("Error leyendo el fichero", rutaFichero);
		lineas.remove(0);
		
		Stream<Premio> res = lineas.stream().map(linea->parsearPremio(linea));
		
		if (implementacion.equals(Implementacion.STREAM)) {
			return new PremiosStream(res);
		} else {
			return new PremiosBucles(res);
		}
	}
	
	public static void setImplementacion(Implementacion i) {
		implementacion = i;
	}
}
