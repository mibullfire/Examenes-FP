package fp.tipos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FactoriaJugadores {
	
	public static Jugadores leeJugadores(String fichero) {
		List<Jugador> res = new ArrayList<Jugador>();
		
		List<String> lineas = null;
		try { 
			lineas = Files.readAllLines(Paths.get(fichero), StandardCharsets.ISO_8859_1); // El fallo se debe a no incluir el StandarCharsets
		} catch(IOException e) {
			e.printStackTrace();
			lineas = new ArrayList<String>();
		}
		
		
		lineas.remove(0);
		
		for (String l: lineas) {
			res.add(parseaJugador(l));
		}
			
		return new Jugadores(res);
		
	}
	
	public static Jugador parseaJugador(String x) {
		String [] trozos = x.split(";");
		checkTrozo(trozos);
		
		String nombre = trozos[0].trim();
		LocalDate fecha = LocalDate.parse(trozos[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Puesto puesto = Puesto.valueOf(trozos[2].trim().toUpperCase());
		String pais = trozos[3].trim();
		Duration duracion = Duration.ofMinutes(Long.valueOf(trozos[4].trim()));
		Integer tiros1 = Integer.valueOf(trozos[5].trim());
		Integer tiros2 = Integer.valueOf(trozos[6].trim());
		Integer tiros3 = Integer.valueOf(trozos[7].trim());
		Integer puntos1 = Integer.valueOf(trozos[8].trim());
		Integer puntos2 = Integer.valueOf(trozos[9].trim());
		Integer puntos3 = Integer.valueOf(trozos[10].trim());
		
		return new Jugador(fecha, nombre, puesto, pais, duracion, tiros1, tiros2, tiros3, puntos1, puntos2, puntos3);
		
	}
	
	public static void checkTrozo(String [] trozo) {
		if (trozo.length != 11) {
			throw new IllegalArgumentException("Cadena no válida");
		}
	}
}
