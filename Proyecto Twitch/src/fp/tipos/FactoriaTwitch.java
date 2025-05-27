package fp.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FactoriaTwitch {
	public static EstadisticasTwitch leeCanales(String ruta) {
		List<CanalTwitch> canales = new ArrayList<>();
		
		try {
			List<String> lineas = Files.readAllLines(Path.of(ruta));
			lineas.remove(0);
			for (String s: lineas) {
				canales.add(parseCanal(s));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return new EstadisticasTwitch(canales);
	}
	
	private static CanalTwitch parseCanal(String s) {
		String [] trozos = s.split(",");
		checkTrozos(trozos, 11);
		
		String nombre = trozos[0].trim();
		Duration tiempoVisionado = Duration.ofMinutes(Long.valueOf(trozos[1].trim()));
		Duration duracionContenido = Duration.ofMinutes(Long.valueOf(trozos[2].trim()));;
		Integer picoEspectadores = Integer.valueOf(trozos[3].trim());
		Integer mediaEspectadores = Integer.valueOf(trozos[4].trim());
		Integer numSeguidores = Integer.valueOf(trozos[5].trim());
		Integer numSeguidoresGanados = Integer.valueOf(trozos[6].trim());
		Integer numEspectadoresGanados = Integer.valueOf(trozos[7].trim());
		Boolean socio = parseBool(trozos[8].trim());
		Boolean nsfw = parseBool(trozos[9].trim());;
		String idioma = trozos[10].trim();
		
		return new CanalTwitch(nombre, tiempoVisionado, duracionContenido, picoEspectadores, mediaEspectadores, numSeguidores, numSeguidoresGanados, numEspectadoresGanados, socio, nsfw, idioma);
	}
	
	private static Boolean parseBool(String s) {
		if (s.equals("True")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	private static void checkTrozos(String [] x, Integer y) {
		if (x.length != y) {
			throw new IllegalArgumentException("Numero de trozos incorrectos detectado");
		}
	}
}
