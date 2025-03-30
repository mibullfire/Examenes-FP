package sevici.tipos;

import fp.utiles.*;

public record Coordenadas(Double latitud, Double longitud, UnidadMedida unidad) {
	
	public Coordenadas {
		Checkers.checkCondicion("La latitud tiene que estar entre -90, 90", (-90 < latitud && 90 > latitud));
		Checkers.checkCondicion("La longitud tiene que estar entre -180 y 180", (-180 < longitud && 180 > longitud));
	}
	
	// Constructores
	public Coordenadas() {
		this(0., 0., UnidadMedida.GRADOS);
	}
	public Coordenadas(Double latitud, Double longitud) {
		this(latitud, longitud, UnidadMedida.GRADOS);
	}
	public Coordenadas(String s) {
		this(
				Double.valueOf(s.split(",")[0].trim()),
				Double.valueOf(s.split(",")[1].trim()),
				UnidadMedida.GRADOS);

	}
	
	public Double getDistanciaHaversine(Coordenadas coord) {
        final double R = 6371.0; // Radio de la Tierra en km

        // Convertimos a radianes
        double dLat = Math.toRadians(coord.latitud - latitud);
        double dLon = Math.toRadians(coord.longitud - longitud);
        double lat1 = Math.toRadians(latitud);
        double lat2 = Math.toRadians(coord.latitud);

        // Fórmula de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Resultado en kilómetros
	}
	
	public Coordenadas aRadianes() {
		if (this.unidad.equals(UnidadMedida.RADIANES)) {
			return this;
		}
	     return new Coordenadas(
	             Math.toRadians(this.latitud),
	             Math.toRadians(this.longitud),
	             UnidadMedida.RADIANES);
	}
	
	public Coordenadas aGrados() {
		if (this.unidad.equals(UnidadMedida.GRADOS)) {
			return this;
		}
		return new Coordenadas(
				Math.toDegrees(this.latitud),
				Math.toDegrees(this.longitud));
	}
}
