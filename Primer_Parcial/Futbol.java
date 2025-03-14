package fp.tipos;

import fp.utiles.Checkers;

public record Futbol(Integer minuto, Integer golesLocal, Integer golesVisitante) {
	
	public Futbol(Integer minuto, Integer golesLocal, Integer golesVisitante) {
		Checkers.check("Los minutos deben ser mayores o iguales que cero.", minuto >= 0);
		Checkers.check("Los goles del equipo locale deben ser mayores o iguales que cero. ", golesLocal >= 0);
		Checkers.check("Los goles del equipo visitante deben ser mayores o iguales que cero. ", golesVisitante >= 0);
		this.minuto = minuto;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}
	
	public String toString() {
		return minuto + ", " + golesLocal + ", " + golesVisitante;
	}
	
	
}
