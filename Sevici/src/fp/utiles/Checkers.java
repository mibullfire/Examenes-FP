package fp.utiles;

public class Checkers {
	
	public static void checkCondicion(String texto,
			Boolean condicion) {
		if(condicion.equals(false)) {
			throw new IllegalArgumentException(texto);
		}
	}

	public static void checkNotNull(Object... o) {
		for (Object objeto : o) {
			if (objeto == null) {
				throw new IllegalArgumentException(
						"Referencia nula");
			}
		}
	}

}
