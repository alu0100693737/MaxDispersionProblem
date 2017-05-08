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

	public double centroGravedad(int columna) {
		double aux = 0;
		for(int i = 0; i < getProblema().getNumElementos(); i++) {
			if(!getSolucionFinal().contains(i)){
				aux += getProblema().getDatoMatrizDatos(i, columna);
				//System.out.println(getProblema().getDatoMatrizDatos(i,  columna));
			}
		}
		aux = aux / (getProblema().getNumElementos() - getSolucionFinal().size());
		System.out.println("El centro de gravedad es: " + aux + " en la columna " + columna);
		return aux;
	}

	//Devuelve valor, diferencia y posicion
	public Point3D elementoAlejado(int columna, double centroGravedad) {
		int posMasAlejado = 0;  //posicion del elemento mas alejado
		double aux = 0; //distancia al centro
		for(int i = 0; i < getProblema().getNumElementos(); i++) {
			if(!getSolucionFinal().contains(i)){
				//elegimos el mas alejado el primer elemento y comprobamos si algun otro se aleja aun más del centro
				if(aux < distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad)) {
					//System.out.print("Cambiando " + aux + " por ");
					aux = distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad);
					posMasAlejado = i;
					//System.out.println(aux + " en la pos " + i);
				}	
			}
		}
		System.out.println("El elemento mas alejado de la columna " + columna + " es el elemento de la pos " + posMasAlejado + " con valor " + getProblema().getDatoMatrizDatos(posMasAlejado, columna) +  " y distancia " + aux);
		return new Point3D(getProblema().getDatoMatrizDatos(posMasAlejado, columna), aux, posMasAlejado);
	}

	public double distancia(double a, double b) {
		//System.out.println("La distancia entre " + a + " y " + b + " es: " +(Math.sqrt(Math.pow(a - b, 2))));
		return Math.sqrt(Math.pow(a - b, 2));
	}


	public void ejecutar(int tamanoSolucion) {
	}
}
