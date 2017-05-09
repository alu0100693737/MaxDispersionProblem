package maximunDiversity;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class algoritmo {
	private estructuraProblema problema;
	private ArrayList<Integer> solucionFinal;

	public algoritmo(String fichero) throws FileNotFoundException, IOException {
		problema = new estructuraProblema(fichero);
		solucionFinal = new ArrayList<Integer>();
	}

	public estructuraProblema getProblema() {
		return problema;
	}

	public ArrayList<Integer> getSolucionFinal() {
		return solucionFinal;
	}

	public double distancia(double a, double b) {
		//System.out.println("La distancia entre " + a + " y " + b + " es: " +(Math.sqrt(Math.pow(a - b, 2))));
		return Math.sqrt(Math.pow(a - b, 2));
	}


	public void ejecutar(int tamanoSolucion) {
	}
}
