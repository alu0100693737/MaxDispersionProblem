package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Principal {
	
	private static algoritmo prueba;
	
	public Principal(String text) {
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner in = new Scanner(System.in);
		clock tiempo;
		System.out.println("Introduzca el fichero de entrada");
		String fichero = in.nextLine();
		int num = -1;
		while(num != 0) {
			mostrarOpciones();
			System.out.println("Introduzca una opcion: ");

			num = in.nextInt();

			switch (num) {
			case 1:  System.out.println("\nAlgoritmo Voraz Constructivo");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoVorazConstructivo(fichero, 4);
			tiempo.stop(); tiempo.elapseTime();
			break;
			case 2:  System.out.println("\nAlgoritmo Voraz Destructivo");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoVorazDestructivo(fichero, 4);
			tiempo.stop(); tiempo.elapseTime();
			break;
			case 3:  System.out.println("\nAlgoritmo Aleatorio con Busqueda local.");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoAleatorioYBusquedaLocal(fichero, 4);
			tiempo.stop(); tiempo.elapseTime();
			break;
			case 4: System.out.println("\nAlgoritmo Grasp con Busqueda local");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoGraspConstructivo(fichero, 4);
			tiempo.stop(); tiempo.elapseTime();
			break;
			case 5:System.out.println("\nAlgoritmo Ramificacion y poda.");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoRamificacionYPoda(fichero, 4);
			tiempo.stop(); tiempo.elapseTime();
			case 10:  System.out.println("Salir");
			break;
			}
		}

	}
	
	public static void mostrarOpciones() {
		System.out.println("\n\nPráctica 4. Algoritmos Voraces y Busquedas por Entorno.");
		System.out.println("----------------------------------------------------------\n");
		System.out.println("1. Algoritmo Voraz Constructivo. Apartado A.");
		System.out.println("2. Algoritmo Voraz Destructivo.  Apartado B");
		System.out.println("3. Algoritmo Aleatorio con Busqueda Local.");
		System.out.println("4. Algoritmo Grasp con Busqueda Local.");
		System.out.println("5. Algoritmo Ramificacion y Poda.");
		System.out.println("0. Salir.");
	}
}
