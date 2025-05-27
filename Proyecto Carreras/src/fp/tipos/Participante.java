package fp.tipos;

import java.time.Duration;

public record Participante(String idCarrera, String nombre, String apellidos, Integer edad, Sexo sexo, Duration duracion) {
	
	public Participante {
		checkEdad(edad);
	}
	
	private void checkEdad(Integer edad) {
		if (edad < 17) {
			throw new IllegalArgumentException("Edad inválida");
		}
	}
	
	public Categoria getCategoria() {
		if (sexo.equals(Sexo.HOMBRE)) {
			if (edad >= 17 && edad <= 20) {
				return Categoria.JUNIOR_M;
			}
			else if(edad >= 21 && edad <= 23) {
				return Categoria.PROMESA_M;
			}
			else if(edad >= 24 && edad <= 39) {
				return Categoria.SENIOR_M;
			}
			else if(edad >= 40) {
				return Categoria.VETERANO_M;
			}
		} else {
			if (edad >= 17 && edad <= 20) {
				return Categoria.JUNIOR_F;
			}
			else if(edad >= 21 && edad <= 23) {
				return Categoria.PROMESA_F;
			}
			else if(edad >= 24 && edad <= 39) {
				return Categoria.SENIOR_F;
			}
			else if(edad >= 40) {
				return Categoria.VETERANO_F;
			}
		}
		return null;
	}
	
	

}
