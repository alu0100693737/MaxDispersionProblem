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
			case 1:  System.out.println("\nAlgoritmo Constructivo Voraz");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoVorazConstructivo(fichero, 2);
			tiempo.stop(); tiempo.elapseTime();
			break;
			case 2:  System.out.println("\nAlgoritmo Destructivo Voraz");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoVorazDestructivo(fichero, 2);
			tiempo.stop(); tiempo.elapseTime();
			break;
			case 3:  System.out.println("\nAlgoritmo Grasp");
			tiempo = new clock(); tiempo.start();
			prueba = new algoritmoGraspConstructivo(fichero, 2);
			tiempo.stop(); tiempo.elapseTime();
			break;
			case 10:  System.out.println("Salir");
			break;
			}
		}

	}
	
	public static void mostrarOpciones() {
		System.out.println("\n\nPráctica 4. Algoritmos Voraces y Busquedas por Entorno.");
		System.out.println("----------------------------------------------------------\n");
		System.out.println("1. Algoritmo Voraz Constructivo. Apartado A.");
		System.out.println("2. Algoritmo Voraz Constructivo. Segunda estrategia. Peor.");
		System.out.println("3. Algoritmo Voraz Destructivo. Apartado B.");
		System.out.println("4. Algoritmo Grasp a partir de Algoritmo 2.");
		System.out.println("5. Algoritmo Grasp a partir de Algoritmo 2. Segunda estrategia. Peor.");
		System.out.println("6. Algoritmo Multiarranque a partir de Algoritmo 5.");
		System.out.println("7. Algoritmo Aleatorio.");
		System.out.println("8. Algoritmo VNS utilizando a partir del Algoritmo 7.");
		System.out.println("9. Algoritmo Hibrido utilizado en la modificacion de la practica");
		System.out.println("0. Salir.");
	}
}
