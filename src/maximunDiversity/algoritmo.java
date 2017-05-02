package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class algoritmo {
	private estructuraProblema problema;
	
	public algoritmo(String fichero) throws FileNotFoundException, IOException {
		problema = new estructuraProblema(fichero);
		
	}
	
	public estructuraProblema getProblema() {
		return problema;
	}
	
	
	public double centroGravedad(int columna) {
		double aux = 0;
		for(int i = 0; i < getProblema().getNumElementos(); i++) {
			aux += getProblema().getDatoMatrizDatos(i, columna);
			System.out.println(getProblema().getDatoMatrizDatos(i,  columna));
		}
		aux = aux/getProblema().getNumElementos();
		return aux;
	}
	public double elementoAlejado(int columna, double centroGravedad) {
	}
	}
}
