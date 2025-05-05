package fp.ciclismo;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaCiclismo {
	public static Ganador creaGanador(Integer anyo, String nacionalidad, String nombre, 
			String equipo, Integer kmRecorridos,
			Duration tiempoEmpleado, Integer numEtapasGanadas, Integer numDiasMaillotAmarillo){
		return new Ganador(anyo, nacionalidad, nombre, equipo, kmRecorridos, tiempoEmpleado, numEtapasGanadas, numDiasMaillotAmarillo);
	}
	
	public static Ganador creaGanador(String s){
		Checkers.checkNoNull(s);
		String [] trozos = s.split(",");
		Checkers.check("Formato de ganador no válido", trozos.length == 8);
		
		Integer anyo= Integer.parseInt(trozos[0].trim());
		String nacionalidad = trozos[1].trim();
		String nombre = trozos[2].trim();
		String equipo = trozos[3].trim();
		Integer kms = Integer.parseInt(trozos[4].trim());
		Duration tiempo = Duration.parse(trozos[5].trim());
		Integer etapas = Integer.parseInt(trozos[6].trim());
		Integer diasMaillot = Integer.parseInt(trozos[7].trim());
		return creaGanador(anyo, nacionalidad, nombre, equipo, kms,
				tiempo, etapas, diasMaillot);
	}
	public static EstadisticasCarrera creaEstadisticas2006_16(){

		  List<Ganador> ganadores= Arrays.asList(
		           creaGanador(2006, "Spain", "Óscar Pereiro", "Caisse d'Epargne–Illes Balears", 3657, Duration.parse("PT89H40M27S"), 0, 8),
		           creaGanador(2007, "Spain", "Alberto Contador", "Discovery Channel", 3570, Duration.parse("PT91H00M26S"), 1, 4),
		           creaGanador(2008, "Spain", "Carlos Sastre", "Team CSC", 3559, Duration.parse("PT87H52M52S"), 1, 5),
		           creaGanador(2009, "Spain", "Alberto Contador", "Astana", 3459, Duration.parse("PT85H48M35S"), 2, 7),
		           creaGanador(2010, "Luxembourg", "Andy Schleck", "Team Saxo Bank", 3642, Duration.parse("PT91H59M27S"), 2,12),
		           creaGanador(2011, "Australia", "Cadel Evans", "BMC Racing Team", 3430, Duration.parse("PT86H12M22S"), 1, 2),
		           creaGanador(2012, "Great Britain", "Bradley Wiggins", "Team Sky", 3496, Duration.parse("PT87H34M47S"), 2,14),
		           creaGanador(2013, "Great Britain", "Chris Froome", "Team Sky", 3404, Duration.parse("PT83H56M20S"), 3,14),
		           creaGanador(2014, "Italy", "Vincenzo Nibali", "Astana", 3661, Duration.parse("PT89H59M06S"), 4,19),
		           creaGanador(2015, "Great Britain", "Chris Froome", "Team Sky", 3360, Duration.parse("PT84H46M14S"), 1, 16),
		           creaGanador(2016, "Great Britain", "Chris Froome", "Team Sky", 3529, Duration.parse("PT89H04M48S"), 2, 14 )
		           );
		  return new EstadisticasCarreraImpl("Tour de Francia", ganadores);
		  
	}
	
	public static EstadisticasCarrera creaEstadisticasDeFichero(String titulo, String fichero){
		String msg = "Error leyendo fichero";
		List<Ganador> ganadores= Ficheros.leeFichero(msg,fichero, s->creaGanador(s));
		return new EstadisticasCarreraImpl(titulo, ganadores);		
	}
	

}
