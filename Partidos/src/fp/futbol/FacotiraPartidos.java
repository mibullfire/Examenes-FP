package fp.futbol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FacotiraPartidos {
	
	public static EstadisticasPartidos leePartidos(String ruta) {
		List<Partido> res = new ArrayList<Partido>();
		List<String> lineas = Ficheros.leeFichero("Error", ruta);
		for (String l: lineas) {
			res.add(parsearPartido(l));
		}
		return new EstadisticasPartidos(res.stream());
	}
	
	
	//19/4/2023;Celta de Vigo;Villarreal;14467;Liga;[22-0-1,42-1-1,67-2-1] 
	public static Partido parsearPartido(String lineaCSV) {
		String [] trozos = lineaCSV.split(";");
		Checkers.check("Número incorrecto de trozos", trozos.length==6);
		LocalDate fecha = LocalDate.parse(trozos[0].trim());
		String equipoL = trozos[1].trim();
		String equipoV = trozos[2].trim();
		Long numEspectadores = Long.valueOf(trozos[4].trim());
		Competicion competicion = Competicion.valueOf(trozos[5].trim().toUpperCase());
		List<Resultado> resultados = parsearResultados(trozos[6].trim());
		return new Partido(fecha, equipoL, equipoV, numEspectadores, competicion, resultados);
		
	}
	
	public static List<Resultado> parsearResultados(String cadena){
		List<Resultado> res = new ArrayList<Resultado>();
		cadena = cadena.replace("[", "").replace("]", "");
		String [] trozos = cadena.split(",");
		for (String i: trozos) {
			res.add(parseaRes(i));
		}
		return res;
		
	}
	
	public static Resultado parseaRes(String cadena) {
		String [] trozos = cadena.split("-");
		Checkers.check("Cadena Incorrecta", trozos.length==3);
		Integer minuto = Integer.valueOf(trozos[0].trim());
		Integer golL = Integer.valueOf(trozos[1].trim());
		Integer golV = Integer.valueOf(trozos[2].trim());
		return new Resultado(minuto, golL, golV);
	}
	


}
