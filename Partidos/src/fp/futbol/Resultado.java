package fp.futbol;

import fp.utiles.*;

public record Resultado(Integer minuto, Integer golesL, Integer golesV) {
	public Resultado{
		Checkers.check("Los minutos deben sert mayores o igual a cero", null);
		Checkers.check("Los goles deben ser mayores o iguales a cero", golesL>=0 || golesV>=0);
	}
}
