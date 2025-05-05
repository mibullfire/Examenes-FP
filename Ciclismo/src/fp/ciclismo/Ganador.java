package fp.ciclismo;

import java.time.Duration;

import fp.utiles.Checkers;

public record Ganador(Integer anyo, String nacionalidad, String nombre, String equipo,
Integer kmRecorridos, Duration tiempoEmpleado, Integer numEtapasGanadas,
Integer numDiasMaillot) {
	
	public Ganador{
		Checkers.check("Año incorrecto", anyo>1903);
		Checkers.check("Kms incorrecto", kmRecorridos>0);
		Checkers.check("Tiempo incorrecto", tiempoEmpleado.compareTo(Duration.ZERO)>0);
		Checkers.check("Etapas incorrectas", numEtapasGanadas >=0);
		Checkers.check("Numero dias maillot amarillo", numDiasMaillot>0);
	}
	
    public Double getVelocidadMedia() { 

    	Double horas = tiempoEmpleado().getSeconds()/3600.0;
        return kmRecorridos().doubleValue() / horas;

    }
    
	public boolean equals(Object o){
	boolean res = false;
	    if (o != null && o instanceof Ganador){
	    	Ganador gt = (Ganador) o;
	    	res = nombre().equals(gt.nombre()) &&
	    			equipo().equals(gt.equipo()) &&
	    			anyo().equals(gt.anyo()); 
	    	
	    }
	return res;
	}
	public int hashCode() {
		return nombre().hashCode()+ equipo().hashCode()* 31 + anyo().hashCode()*31*31;
	}
}
