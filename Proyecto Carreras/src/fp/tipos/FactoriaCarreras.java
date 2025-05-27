package fp.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FactoriaCarreras {
	
	public static Carreras leeCarreras(String nomfichCarreras, String nomfichParticipantes) {
		
		List<Carrera> carreras = new ArrayList<>();
		List<Participante> participantes = new ArrayList<>();
		
		try {
			List<String> lineasC = Files.readAllLines(Path.of(nomfichCarreras));
			List<String> lineasP = Files.readAllLines(Path.of(nomfichParticipantes));
			lineasC.remove(0);
			lineasP.remove(0);
			
			for (String s: lineasC) {
				carreras.add(parseCarrera(s));
			}
			for (String s: lineasP) {
				participantes.add(parseParticipante(s));
			}
			
			Map<String, List<Participante>> mapeo = participantes.stream().collect(Collectors.groupingBy(x->x.idCarrera(),Collectors.toList()));
			
			for (Carrera c: carreras) {
				c.añdadeParticipantes(mapeo.get(c.getId()));
			}
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return new Carreras(carreras.stream());
	}
	
	
	private static Carrera parseCarrera(String s) {
		String [] trozos = s.split(",");
		
		String carreraID = trozos[0].trim();
		String localidad = trozos[1].trim();
		LocalDateTime fechaHora = LocalDateTime.parse(trozos[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm"));
		Modalidad tipo = Modalidad.valueOf(trozos[3].trim().toUpperCase());
		Integer distancia = Integer.valueOf(trozos[4].trim());
		Integer desnivel = Integer.valueOf(trozos[5].trim());
		
		return new Carrera(carreraID, localidad, fechaHora, tipo, distancia, desnivel);
	}
	private static Participante parseParticipante(String s) {
		String [] trozos = s.split(",");
		
		String id = trozos[0].trim();
		String nombre = trozos[1].trim();
		String apellidos = trozos[2].trim();
		Integer edad = Integer.valueOf(trozos[3].trim());
		Sexo sexo = Sexo.valueOf(trozos[4].trim().toUpperCase());
		Duration duracion = Duration.parse(trozos[5].trim());
		
		return new Participante(id, nombre, apellidos, edad, sexo, duracion);
	}
	

}
