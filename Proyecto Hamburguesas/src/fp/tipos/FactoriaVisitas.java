package fp.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FactoriaVisitas {
	
	public static Competicion leerVisitas(String rutaFichero) {
		List<Visita> res = new ArrayList<>();
		
		try {
			List<String> aux = Files.readAllLines(Path.of(rutaFichero));
			aux.remove(0);
			for (String s: aux) {
				res.add(parseVisita(s));
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new Competicion(res.stream());
	}
	
	private static Visita parseVisita(String s) {
		String [] trozos = s.split(";");
		checkTrozos(trozos);
		
		String email = trozos[0].trim();
		String ciudad = trozos[1].trim();
		String cp = trozos[2].trim();
		LocalDateTime entrada = LocalDateTime.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		LocalDateTime salida = LocalDateTime.parse(trozos[5].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		Double temperatura = Double.valueOf(trozos[4].trim());
		List<Evaluacion> evaluaciones = parseEvaluaciones(trozos[6].trim());
		
		return new Visita(email, ciudad, cp, entrada, salida, temperatura, evaluaciones);
	}
	
	private static void checkTrozos(String [] s) {
		if (s.length != 7) {
			throw new IllegalArgumentException("Trozo erroneo");
		}
	}
	
	private static List<Evaluacion> parseEvaluaciones(String s){
		
		List<Evaluacion> res = new ArrayList<>();
		
		s = s.replace("[", "").replace("]", "");
		String [] trozos = s.split("-");
		
		for (String x: trozos) {
			String [] trocitos = x.split(":");
			String hamburgueseria = trocitos[0].trim();
			String trocito2 = trocitos[1].trim();
			trocito2 = trocito2.replace("(", "").replace(")", "");
			
			String [] trocitosPequenyos = trocito2.split(",");
			
			Integer presentacion = Integer.valueOf(trocitosPequenyos[0].trim());
			Integer carne = Integer.valueOf(trocitosPequenyos[1].trim());
			Integer ingrediente = Integer.valueOf(trocitosPequenyos[2].trim());
			Integer pan = Integer.valueOf(trocitosPequenyos[3].trim());
			
			res.add(new Evaluacion(hamburgueseria, presentacion, carne, ingrediente, pan));
			
		}
		
		return res;
	}

}
