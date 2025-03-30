package sevici.tipos;

import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaBicis {
	
	
	public static List<Estacion> leeBicis(String ruta){
		List <Estacion> res = new ArrayList<Estacion>();
		List <String> lineas = Ficheros.leerLineas(ruta);
		lineas.remove(0);
		for(String linea:lineas) {
			res.add(creaEstacion(linea));
		}
		return res;
	}
	
	public static Estacion creaEstacion(String linea) {
		String [] splits = linea.split(",");
		
		Checkers.checkCondicion("Número de elementos incorrectos", splits.length==6);
		
		String id = splits[0].split("_")[0].trim();
		String nombre = splits[0].split("_")[1].trim();
		Integer numPuestos = Integer.valueOf(splits[1].trim());
		Integer bicisDisponibles = Integer.valueOf(splits[3].trim());
		Double latitud = Double.valueOf(splits[4].trim());
		Double longitud = Double.valueOf(splits[5].trim());
		
		Coordenadas c = new Coordenadas(latitud, longitud);
		
		return new Estacion(id, nombre, numPuestos, bicisDisponibles, c);
	}

}
