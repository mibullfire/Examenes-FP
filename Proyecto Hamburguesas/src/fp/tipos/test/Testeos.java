package fp.tipos.test;

import java.time.LocalDate;
import java.util.Map;

import fp.tipos.*;

public class Testeos {
	
	public static void main(String [] args) {
		Competicion x = FactoriaVisitas.leerVisitas("./data/csv.csv");
		System.out.println(x);
		
		
		String hola = x.peorHamburgueseriaPorCalidadIngredientes();
		
		System.out.println(hola);
		
		Map<String, String> adios = x.getTopComilonPorCPEnDia( LocalDate.of(2024, 06, 02));
		System.out.println(adios);
		
		String adios2 = x.getHamburguesaGanadora();
		System.out.println(adios2);
		
	}

}
