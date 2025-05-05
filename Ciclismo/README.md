## Fundamentos de Programación
# Ejercicio de teoría: Ciclismo
**Autora:** Toñi Reina.

**Objetivos:**
- Proyecto para trabajar con los primeros tratamientos secuenciales con Stream

## **Enunciado** 

Se quiere implementar una aplicación para realizar estadísticas sobre los ganadores de carreras ciclistas como el Tour de Francia, el Giro de Italia o la Vuelta Ciclista a España. Para ello se diseñan: 

- Un tipo base llamado **Ganador** que representa el ganador de una edición de una carrera ciclista;
- Un tipo contenedor, llamado **EstadisticasCarrera**, que agrupa a los ganadores de las distintas ediciones de la carrera y permite obtener estadísticas sobre la misma. 
- Una factoría **FactoriaCiclismo** para crear los objetos de este dominio.

Suponga definido el tipo **Ganador** con la siguiente descripción:

**Propiedades**:

- **año**, de tipo Integer. Representa el año en el que el ganador gana la carrera. 
- **nacionalidad**, de tipo Integer. Nacionalidad del ganador. Solo consultable.
- **nombre**, de tipo String. Nombre del ganador. Solo consultable.
- **equipo**, de tipo String. Equipo con el que gana la carrera. Solo consultable.
- **kms recorridos**, de tipo Integer. Kms. Totales recorridos en la carrera. Solo consultable.
- **tiempo empleado**, de tipo Duration. Tiempo empleado para hacer el recorrido. Solo consultable.
- **num etapas ganadas**, de tipo Integer. Número de etapas ganadas en la carrera. Solo consultable.
- **num dias maillot**, de tipo Integer. Número de días con el maillot de ganador. Solo consultable.
- **velocidad media**, de tipo Double. Velocidad media en la carrera. Se calcula a partir de la distancia recorrida y el tiempo empleado. Solo consultable.

**Constructores**:

- **C1:** Constructor con un parámetro por cada propiedad básica del tipo.

**Restricciones**:

- R1: El año debe ser posterior a 1903.
- R2: El número de kms recorridos debe ser superior a 0.
- R3: La duración debe ser superior a 0.
- R4: El número de etapas ganadas debe ser superior o igual a cero.
- R5: El número de días con el maillot debe ser superior a cero.

**Representación como cadena:**

- La representación por defecto teniendo en cuenta todas las propiedades básicas del tipo.

**Criterio de igualdad**

- Dos ganadores son iguales si tienen el mismo equipo, nombre y año.

Implemente en el tipo **EstadisticasCarrera**, que tiene la siguiente descripción, los métodos definidos como otras operaciones[^1]: 

**Propiedades**:

- **nombre carrera:** String. Básica, consultable. Representa el nombre de la carrera.
- **ganadores:** List<Ganador>. Básica, consultable. Representa la lista de ganadores de la carrera.

**Constructores**:

- **C1:** Constructor con un parámetro de tipo String para el nombre de la carrera, con el que se crea una carrera sin ganadores.
- **C2**: Constructor con dos parámetros: el nombre de la carrera y una colección de ganadores de la misma.

**Representación como cadena**:

- El nombre de la carrera, seguido de un guion, y la representación como cadena de los ganadores entre corchetes y separados por comas.

**Criterio de igualdad**:

- Dos objetos de tipo EstadisticaCarrera son iguales si tienen el mismo nombre y los mismos ganadores.

**Otras operaciones**:

- ***List\<String\> getGanadoresConRecorridoInferiorA(Integer km)*:** Devuelve una lista con los nombres de los ganadores que han recorrido menos kilómetros que los dados como parámetro.
- ***Boolean hanGanadoTodosAlgunaEtapa()*:** Devuelve *true* si todos los ganadores han ganado alguna etapa en la edición en la que ganaron la carrera.
- ***Long getNumeroGanadores()*:** Devuelve el número de ganadores distintos de la carrera. Si un ciclista ha           ganado el tour en varias ediciones, solo se debe contar una vez.
- ***Set\<String\> getEquiposGanadores()*:** Devuelve un conjunto con los nombres de los equipos que han ganado la carrera.
- ***Ganador buscaGanador(String nombre)*:** Devuelve el primer ganador que concuerde con el nombre dado como parámetro. Si no se encuentra ninguno, se devuelve null.
- ***Ganador buscaGanador(Integer anyo)*:** Devuelve el ciclista ganador de ese año, si es que existe. Si no se\*         encuentra ninguno, se devuelve null.
- ***Integer calculaDistanciaTotal()*:** Devuelve la suma de los kilómetros recorridos en todas las ediciones de la carrera.
- ***Integer getKmMenorRecorrido():*** Devuelve el número de kilómetros de la edición en la que se ha hecho el recorrido más corto. Si no se puede calcular, devuelve cero.
- ***Double getMediaEtapasGanadas(String equipo):*** Devuelve el número medio de etapas que han ganado los ciclistas que corren en ese equipo. Si no se puede calcular, devuelve cero.
- ***String getGanadorMasRapido():*** Devuelve el nombre del ganador que ha alcanzado una mayor velocidad media en la edición en la que ha sido ganador. Si no se puede calcular devuelve null.
- ***Map\<String, List\<Ganador\>\> getGanadoresPorNacionalidad():*** Devuelve un Map en el que las claves son las nacionalidades y los valores la lista de ganadores de esa nacionalidad.
- ***Map\<String, Long\> cuentaGanadoresPorNacionalidad():*** Devuelve un Map en el que las claves son las nacionalidades y los valores el número de ganadores de esa nacionalidad.
- ***Map\<String, Integer\> getTotalEtapasGanadasPorEquipo():*** Devuelve un Map en el que las claves son los equipos, y los valores el número total de etapas ganadas por los equipos.
- ***Map<String, Integer> cuentaCarrerasGanadasPorCiclista():*** Devuelve un *Map* en el que las claves son el nombre de los corredores, y los valores, el número de veces que el corredor ha ganado la carrera.
- ***Map<String, Ganador> ganadorMasDiasMaillotPorNacionalidad():*** Devuelve un Map en el que las claves son las nacionalidades y los valores el Ganador de esa nacionalidad que más días ha tenido puesto el maillot. 
- ***String getNacionalidadMasGanadores():*** Devuelve la nacionalidad que ha tenido más ganadores.

- ***Boolean hayAlgunGanador(String nacionalidad):*** Devuelve *true* si entre los ganadores de la carrera hay alguno de la nacionalidad dada como parámetro.
- ***void guardaGanadoresDeAnyosOrdenados(String nombreFichero, Integer anyoInicial, Integer anyoFinal)***: Toma como parámetros el nombre de un fichero, el año inicial y el año final del intervalo temporal [anyoInicial, anyoFinal). Genera un fichero con el nombre dado como parámetro con los ciclistas ganadores de la carrera. En el fichero solamente deben aparecer los ganadores que ganaron en los años que van de anyoInicial (inclusive) hasta anyoFinal (sin incluirlo). Además, el orden en el que aparecen los ciclistas es de ganador más reciente a ganador más lejano en el tiempo. Un ejemplo del archivo generado es el que se muestra en la Figura 1.

![image](https://user-images.githubusercontent.com/72299672/231244829-bd876747-b425-4f0d-af4f-531d569aa42f.png)

**Figura 1. Extracto del fichero generado por guardaGanadoresDeAnyosOrdenados**

- ***void guardaGanadoresNacionalidadConAnyos(String nombreFichero, String nacionalidad)***: Toma como parámetros el nombre de un fichero, y una nacionalidad. Genera un fichero con los ganadores de esa nacionalidad y los años en los que ganaron la carrera. El formato del fichero resultado se muestra en la  Figura 2.

![image](https://user-images.githubusercontent.com/72299672/231244963-0e2051ac-9e9a-4171-be91-2e82b9eebf94.png)**Figura 2. Extracto del fichero generado por *guardaGanadoresNacionalidadConAnyos***

- ***Map<String, List<Ganador>> getMasDiasMaillotPorNacionalidad(Integer n):*** Dado un número n, devuelve un Map en el que las claves son las nacionalidades y los valores son listas con los n ganadores que hayan llevado más días el maillot en la carrera que ganaron.
- ***Map<String, Ganador> ganadorMasDiasMaillotPorEquipo():*** Devuelve un Map en el que las claves son los nombres de los equipos y los valores representan al ganador de ese equipo que más días llevó el maillot amarillo en la carrera que ganó. 
- ***List<String> getNacionalidadesGanadores():*** 
Devuelve una lista ordenada alfabéticamente con las nacionalidades de los ganadores.
- ***List\<String\> getCiclistasTop(Integer n):*** Devuelve una lista con el nombre de los n ciclistas top. La lista estará ordenada de mayor a menor número de carreras ganadas por los ciclistas, de forma que el primer ciclista de la lista será el que ha ganado más carreras. La lista solo debe tener n elementos. Así, si n vale 3 la lista contendrá los tres mejores ciclistas de la carrera.


