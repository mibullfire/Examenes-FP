package fp.tipos;

public record Evaluacion(String hamburgueseria, Integer presentacion, Integer puntoCarne, Integer calidadIngredientes, Integer calidadPan) {
	
	public Double getPuntuacionFinal() {
		return Double.valueOf(((presentacion + puntoCarne + calidadIngredientes + calidadPan) /4));
	}

}
