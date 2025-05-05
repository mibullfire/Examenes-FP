package fp.ciclismo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author To�i Reina
 *
 */
public interface EstadisticasCarrera {
	/**
	 * @return El nombre de la carrera
	 */
	String getNombreCarrera();

	/**
	 * @return Una lista con todos los ganadores del tour
	 */
	List<Ganador> getGanadores();

	/**
	 * @param km
	 *            Umbral de kil�metros recorrido por el ciclista en el tour
	 * @return Una lista con los nombres de los ganadores que han recorrido
	 *         menos kil�metros que los dados como par�metro
	 */
	List<String> getGanadoresConRecorridoInferiorA(Integer km);

	/**
	 * @return true si todos los ganadores han ganado alguna etapa en la edici�n
	 *         en la que ganaron el tour
	 */
	Boolean hanGanadoTodosAlgunaEtapa();

	/**
	 * @return El n�mero de ganadores distintos del tour. Si un ciclista ha
	 *         ganado el tour en varias ediciones, solo se debe contar una vez.
	 */
	Long getNumeroGanadores();

	/**
	 * @return Un conjunto con los nombres de los equipos que han ganado la
	 *         carrera.
	 */
	Set<String> getEquiposGanadores();

	/**
	 * @param nombre
	 *            Nombre del ciclista
	 * @return El primer ganador que concuerde con el nombre dado como
	 *         par�metro. Si no se encuentra ninguno, se devuelve null
	 */
	Ganador buscaGanador(String nombre);

	/**
	 * @param anyo
	 *            A�o de celebraci�n de la carrera
	 * @return El ciclista ganador de ese a�o, si es que existe. Si no se
	 *         encuentra ninguno, se devuelve null.
	 */
	Ganador buscaGanador(Integer anyo);

	/**
	 * @return La suma de los kil�metros recorridos en todas las ediciones de la
	 *         carrera.
	 */
	Integer calculaDistanciaTotal();

	/**
	 * @return El n�mero de kil�metros de la edici�n en la que se ha hecho el
	 *         recorrido m�s corto. Si no se puede calcular, devuelve cero.
	 * 
	 */
	Integer getKmMenorRecorrido();

	/**
	 * @param equipo
	 *            Nombre de un equipo.
	 * @return N�mero medio de etapas que han ganado los ciclistas que corren en
	 *         ese equipo. Si no se puede calcular, devuelve cero.
	 */
	Double getMediaEtapasGanadas(String equipo);

	/**
	 * @return El nombre del ganador que ha alcanzado una mayor velocidad media
	 *         en la edici�n en la que ha sido ganador. Si no se puede calcular
	 *         devuelve null.
	 */

	String getGanadorMasRapido();

	/**
	 * @return Map en el que las claves son las nacionalidades y los valores la
	 *         lista de ganadores de esa nacionalidad
	 */
	Map<String, List<Ganador>> getGanadoresPorNacionalidad();

	/**
	 * @return Map en el que las claves son las nacionalidades, y los valores el
	 *         n�mero de veces que un corredor de esa nacionalidad ha ganado la
	 *         carrera
	 */
	Map<String, Long> cuentaGanadoresPorNacionalidad();

	/**
	 * @return Map en el que las claves son los equipos, y los valores
	 * el n�mero total de etapas ganadas por los equipos
	 */
	Map<String, Integer> getTotalEtapasGanadasPorEquipo();
	
	/**
	 * @return Map en el que las claves son los nombres de los ganadores,
	 * y los valores el n�mero de carreras que ha ganado el ciclista.
	 */
	Map<String, Integer> cuentaCarrerasGanadasPorCiclista();
	
	/**
	 * @return Map en el que las claves son las nacionalidades,
	 * y los valores el Ganador de esa nacionalidad que m�s
	 * d�as ha tenido puesto el maillot.
	 */
	Map<String, Ganador> ganadorMasDiasMaillotPorNacionalidad();

	/**
	 * @return La nacionalidad que ha tenido m�s ganadores.
	 */
	String getNacionalidadMasGanadores();
	
	/**
	 * @param nacionalidad
	 *            Nacionalidad del ciclista
	 * @return Devuelve cierto si hay alg�n ganador de la nacionalidad dada como
	 *         par�metro.
	 */
	Boolean hayAlgunGanador(String nacionalidad);
	
	/**
	 * @param nombreFichero
	 *    Nombre del fichero en el que se guarda la informaci�n.
 	 * @param nacionalidad Nacionalidad buscada.
 	 * Guarda en un fichero los ganadores de la carrera de la nacionalidad dada como
 	 * par�metro, con los a�os en los que ha ganado. Un ejemplo de l�nea del fichero 
 	 * generado es
 	 * Alberto Contador-> [2007, 2009]
	 */
	void guardaGanadoresNacionalidadConAnyos(String nombreFichero, String nacionalidad);
	
	/**
	 * @param nombreFichero Nombre fichero
	 * @param anyoInicial A�o inicial del intervalo. Se incluye en el resultado
	 * @param anyoFinal A�o final del intervalo. No se incluye en el resultado.
	 */
	void guardaGanadoresDeAnyosOrdenados(String nombreFichero, Integer anyoInicial, Integer anyoFinal);

	/**
	 * @param n Entero que representa el numero de elementos de las listas que son
	 *   el valor de Map
	 * @return Un Map en el que las claves son las nacionalidades y los valores son listas
	 * con los n ganadores que hayan llevado m�s d�as el maillot en la carrera que ganaron.
	 */
	Map<String, List<Ganador>> getMasDiasMaillotPorNacionalidad(Integer n);
	
	/**
	 * @return Un Map en el que las claves son los nombres de los equipos y los valores
	 * representa al ganador de ese equipo que m�s d�as llev� el maillot amarillo en la carrera que gan�
	 */
	Map<String, Ganador> ganadorMasDiasMaillotPorEquipo();

	/**
	 * @return Una lista ordenada alfab�ticamente con 
	 * las nacionalidades de los ganadores.
	 */
	List<String> getNacionalidadesGanadores();

	/**
	 * @param n N�mero de ciclistas a devolver.
	 * @return Devuelve los n ciclistas que han ganado m�s etapas
	 * en la carrera en la que han resultado ganador.
	 */
	List<String> getCiclistasTop(Integer n);
}
















