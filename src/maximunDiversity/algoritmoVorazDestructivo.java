package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class algoritmoVorazDestructivo extends algoritmo {
	public algoritmoVorazDestructivo(String fichero, int m) throws FileNotFoundException, IOException{
		super(fichero);
		getProblema().mostrarMatrizDatos();
		ejecutar(m);
	}

	//Partimos de la solucion que contiene todos los vectores y eliminamos el elemento mas cercano hasta tamanoSolucion
	public void ejecutar(int tamanoSolucion) {
		//elegimos todos los nodos
		solucionInicial();

		System.out.println("La solucion inicial es: " + getSolucionFinal());

		ArrayList<Point3D> elementosMasCercanos = new ArrayList<Point3D>();;
		while(getSolucionFinal().size() > tamanoSolucion) {		
			elementosMasCercanos.clear();

			System.out.print("Centro de gravedad: \n | ");

			for(int i = 0; i < getProblema().getDimension(); i++) 
				elementosMasCercanos.add(elementoCercano(i, centroGravedadDestructivo(i)));

			System.out.println();

			for(int i = 0; i < elementosMasCercanos.size(); i++ ) 
				System.out.println("Las distancias son " + elementosMasCercanos.get(i).getValor() + " en la pos " + elementosMasCercanos.get(i).getPosicion() + " con distancia " + elementosMasCercanos.get(i).getDistancia());

			Point3D aux = new Point3D(elementosMasCercanos.get(0).getValor(), elementosMasCercanos.get(0).getDistancia(), elementosMasCercanos.get(0).getPosicion());
			for(int i = 1; i < elementosMasCercanos.size(); i++) 
				if(aux.getDistancia() > elementosMasCercanos.get(i).getDistancia()) 
					aux = new Point3D(elementosMasCercanos.get(i).getValor(), elementosMasCercanos.get(i).getDistancia(), elementosMasCercanos.get(i).getPosicion());
			
			aux.mostrarPoint3D();

			getSolucionFinal().remove(getSolucionFinal().indexOf(aux.getPosicion()));
			System.out.println(getSolucionFinal());
		}
		//FINAL
		System.out.println("Solucion final: ");
		System.out.println(getSolucionFinal());
		System.out.print("Centro de gravedad FINAL: \n | ");

		for(int i = 0; i < getProblema().getDimension(); i++) 
			elementosMasCercanos.add(elementoCercano(i, centroGravedadDestructivo(i)));
		System.out.println();
	}

	public double centroGravedadDestructivo(int columna) {
		double aux = 0;
		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			if(getSolucionFinal().contains(i))
				aux += getProblema().getDatoMatrizDatos(i, columna);
			
		aux /= getSolucionFinal().size();
		System.out.print(aux + " | ");
		return aux;
	}

	//Devuelve el elemento mas cercano al centro de gravedad de la columna
	public Point3D elementoCercano(int columna, double centroGravedad) {
		int posMasCercano = 0;  //posicion del elemento mas alejado
		double aux = 999999; //distancia al centro

		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			if(getSolucionFinal().contains(i))
				//elegimos el mas cercano el primer elemento y comprobamos si algun otro se acerca aun mas del centro
				if(aux > distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad)) {
					aux = distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad);
					posMasCercano = i;
				}	
		return new Point3D(getProblema().getDatoMatrizDatos(posMasCercano, columna), aux, posMasCercano);
	}

	public void solucionInicial() {
		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			getSolucionFinal().add(i);
	}
}


