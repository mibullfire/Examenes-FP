package fp.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FactoriaViajes {
	
	public static AgenciaBus leerViajes(String ruta) {
		List<Viaje> res = new ArrayList<Viaje>();
		List<String> lineas = new ArrayList<String>();
		
		try {
			lineas = Files.readAllLines(Path.of(ruta));
			lineas.remove(0);
			for (String l: lineas) {
				res.add(parseViaje(l));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return new AgenciaBus("Manolo", res.stream());
		
	}
	
	private static Viaje parseViaje(String cadena) {
		String [] trozos = cadena.split(";");
		
		Double precio = Double.valueOf(trozos[0].trim());
		Integer distancia = Integer.valueOf(trozos[1].trim());
		Duration duracion = parseTiempo(trozos[2].trim());
		TipoViaje tipo = TipoViaje.valueOf(trozos[3].trim().toUpperCase());
		List<Parada> trayecto = parseParadas(trozos[4].trim());
		
		return new Viaje(precio, distancia, duracion, tipo, trayecto);
	}
	
	private static List<Parada> parseParadas(String cadena){
		List<Parada> res = new ArrayList<Parada>();
		cadena = cadena.replace("[", "").replace("]", "");
		String [] trozos = cadena.split(",");
		for (String v: trozos) {
			String [] parada = v.split("-");
			if (parada[1].equals("FIN")) {
				res.add(new Parada(parada[0].trim(), null));
			}
			else {
				res.add(new Parada(parada[0].trim(), LocalTime.parse(parada[1].trim())));
			}
		}

		return res;
		
	}
	
	private static Duration parseTiempo(String cadena) {
		String [] trozos = cadena.split(":");
		
		Integer hora = Integer.valueOf(trozos[0].trim());
		Integer minutos = Integer.valueOf(trozos[1].trim());
		return Duration.ofHours(hora).plus(Duration.ofMinutes(minutos));
	}

}
