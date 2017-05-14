package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class estructuraProblema {
	private int numVectores;
	private int dimension;
	private double matrizDatos[][]; //matriz para almacenar los datos del problema
	
	//Constructor que llama a la funcion leerFichero, parametro: nombre del fichero
	public estructuraProblema(String fichero) throws FileNotFoundException, IOException {
		leerFichero(fichero);
	}

	//Funcion que lee el fichero pasado por parametro y lo guarda en la matriz matrizDatos
	private void leerFichero(String fichero) throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(new FileReader(fichero));
		setNumVectores(sc.nextInt());
		setDimension(sc.nextInt());

		matrizDatos = new double[getNumVectores()][getDimension()];
		
		//Arista de doble sentido
		for(int i = 0; i < getNumVectores(); i++) 
			for(int j = 0; j < getDimension(); j++) 
				setDatoMatrizDatos(i, j, sc.nextDouble());
		sc.close();
	}

	ArrayList<Double> getVectorProblema(int fila) {
		ArrayList<Double> aux = new ArrayList<Double>();
		for(int i = 0; i < getDimension(); i++) {
			aux.add(getDatoMatrizDatos(fila, i));			
		}
		//System.out.println(aux);
		return aux;
	}
	//Funcion que devuelve el numero de vertices del problema
	public int getNumVectores() {
		return numVectores;
	}

	//Funcion que guarda en numElementos el valor especificado por parametro
	private void setNumVectores(int valor) {
		numVectores = valor;
	}
	
	//Funcion que devuelve la dimension de los datos
	public int getDimension() {
		return dimension;
	}
	
	//Funcion que guarda en dimension el valor especificado por parametro
	private void setDimension(int valor) {
		dimension = valor;
	}

	//Funcion que devuelve matrizDatos
	public double[][] getMatrizDatos() {
		return matrizDatos;
	}

	//Funcion que devuelve un dato de la Matriz de datos
	public double getDatoMatrizDatos(int i, int j) {
		return matrizDatos[i][j];
	}

	//Funcion privada que almacena un dato en la matriz de datos, utilizado en leerFichero
	private void setDatoMatrizDatos(int i , int j, double dato) {
		matrizDatos[i][j] = dato;
	}

	//Funcion que muestra la matrizDistancias
	public void mostrarMatrizDatos() {
		System.out.println("Matriz de Datos. Problema Maximum Dispersion.");
		for(int i = 0; i < getNumVectores(); ++i) {
			System.out.println();
			for(int j = 0; j < getDimension(); ++j)
				System.out.print(getDatoMatrizDatos(i, j) + "   ");
		}
		System.out.println("\n");
	}
}
