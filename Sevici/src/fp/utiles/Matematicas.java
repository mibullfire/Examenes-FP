package fp.utiles;

import java.util.ArrayList;
import java.util.List;

public class Matematicas {

	public static List<Integer> 
			generaEnterosAleatorios(Integer n) {
		
		List<Integer> result = new ArrayList<>();
		for(int i=0; i<n;i++) {
			Double d = Math.random()*n;
			result.add(d.intValue());
		}
		return result;
	}

}
