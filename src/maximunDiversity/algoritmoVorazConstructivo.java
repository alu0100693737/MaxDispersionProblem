package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class algoritmoVorazConstructivo extends algoritmo {
	public algoritmoVorazConstructivo(String fichero, int m) throws FileNotFoundException, IOException{
		super(fichero);
		getProblema().mostrarMatrizDatos();
		ejecutar(m);
	}

	public void ejecutar(int tamanoSolucion) {

		ArrayList<Double> elementosMasAlejados = new ArrayList<Double>();
		for(int k = 0; k < tamanoSolucion; k++) {
			elementosMasAlejados.clear();
			System.out.println("\nIteracion " + (k + 1));
			System.out.println("Centro de gravedad ");
			System.out.println(centroGravedad());
			//Se añaden iterativamente las distancias de los vectores al centro de gravedad
			for(int i = 0; i < getProblema().getNumVectores(); i++) {
				elementosMasAlejados.add(distanciaEuclideaVector(getProblema().getVectorProblema(i), centroGravedad()));
			//System.out.println("HEYS" + elementosMasAlejados);
				//System.out.println("Z vale: " + sumaDistanciasSolucion());
			}double valor = 0;
			for(int i = 0; i < elementosMasAlejados.size(); i++ )  
				if(valor < elementosMasAlejados.get(i) && !getSolucionFinal().contains(i)) 
					valor = elementosMasAlejados.get(i);

			getSolucionFinal().add(elementosMasAlejados.indexOf(valor));
			System.out.println(getSolucionFinal());
		}
		System.out.println("Z vale: " + sumaDistanciasSolucion());
	}
}
