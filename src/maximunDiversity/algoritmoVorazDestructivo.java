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

		ArrayList<Double> elementosMasCercanos = new ArrayList<Double>();

		while(tamanoSolucion != getSolucionFinal().size()) {
			elementosMasCercanos.clear();
			System.out.println("\nIteracion " + (getProblema().getNumVectores() - getSolucionFinal().size() + 1));
			System.out.println("Centro de gravedad ");
			System.out.println(centroGravedad());
			//Se añaden iterativamente las distancias de los vectores al centro de gravedad
			for(int i = 0; i < getSolucionFinal().size(); i++) 
				elementosMasCercanos.add(distanciaEuclideaVector(getProblema().getVectorProblema(getSolucionFinal().get(i)), centroGravedad()));

			double valor = 99999;
			for(int i = 0; i < elementosMasCercanos.size(); i++ )  
				if(valor > elementosMasCercanos.get(i)) 
					valor = elementosMasCercanos.get(i);
			getSolucionFinal().remove(elementosMasCercanos.indexOf(valor));
			System.out.println(getSolucionFinal());
		}
		sumaDistanciasSolucion();
	}

	public void solucionInicial() {
		for(int i = 0; i < getProblema().getNumVectores(); i++) 
			getSolucionFinal().add(i);
	}
}


