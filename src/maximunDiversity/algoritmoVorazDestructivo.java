package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class algoritmoVorazDestructivo extends algoritmo {
	public algoritmoVorazDestructivo(String fichero, int m) throws FileNotFoundException, IOException{
		super(fichero);
		getProblema().mostrarMatrizDatos();
		ejecutar(m);
	}

	//Partimos de la solucion que contiene todos los vectores y eliminamos hasta tamanoSolucion
	public void ejecutar(int tamanoSolucion) {

		//elegimos todos los nodos
		solucionInicial();

		System.out.println("La solucion inicial es: " + getSolucionFinal());
		while(getSolucionFinal().size() > tamanoSolucion) {


		}
	}

	public void solucionInicial() {
		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			getSolucionFinal().add(i);
	}

}


