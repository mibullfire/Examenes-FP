
# 🎯 Ejercicios Adicionales de Tratamientos Secuenciales en Java

_Estos ejercicios están pensados como práctica complementaria al segundo parcial de Fundamentos de Programación. Se centran en el uso de streams, colecciones y operaciones típicas sobre listas de partidos._

---

## 📘 Ejercicios de dificultad similar

### 1. Obtener número total de partidos sin goles
Devuelve el número total de partidos que acabaron con resultado 0-0.

```java
public Long getNumPartidosSinGoles()
```

---

### 2. Obtener los equipos que más veces han jugado como locales
Devuelve un mapa con el nombre del equipo local como clave y el número de veces que ha jugado como local como valor. Solo los top 5 equipos deben estar en el resultado.

```java
public Map<String, Long> getFrecuenciaEquiposLocales()
```

---

### 3. Contar partidos por tipo de competición
Devuelve un mapa que indica cuántos partidos se han jugado en cada competición.

```java
public Map<Competicion, Long> getNumPartidosPorCompeticion()
```

---

### 4. Media de espectadores por equipo local
Calcula la media de espectadores para cada equipo en sus partidos como local.

```java
public Map<String, Double> getMediaEspectadoresPorEquipoLocal()
```

---

## 🔥 Ejercicios más desafiantes

### 5. Top N equipos con más goles marcados como visitantes
Devuelve una lista con los `n` equipos que más goles han marcado fuera de casa. Considera todos sus partidos como visitantes.

```java
public List<String> getTopNVisitantesMasGoleadores(int n)
```

---

### 6. Ranking de eficiencia goleadora
Devuelve un mapa con cada equipo local y su ratio de goles marcados como local dividido entre el número total de espectadores en sus partidos.

```java
public Map<String, Double> getRatioGolesPorEspectador()
```

---

### 7. Partido con el mayor número de goles en el menor tiempo posible
Encuentra el partido que haya acumulado más goles en los primeros 30 minutos. Si hay empate, devuelve cualquiera.

```java
public Optional<Partido> getPartidoConGolesMasRapidos()
```

---

### 8. Duración media de los partidos con goles (desde primer gol hasta último)
Devuelve la duración media en minutos entre el primer y último gol de todos los partidos que tengan al menos dos goles.

```java
public Double getDuracionMediaGoles()
```

---

✨ ¡Practicar estos ejercicios te dará mayor soltura con colecciones, streams y estadísticas básicas sobre estructuras de datos!
