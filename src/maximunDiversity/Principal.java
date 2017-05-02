package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Principal {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Introduzca el nombre del fichero");
		Scanner in = new Scanner(System.in);
		String fichero = in.nextLine();
		algoritmo prueba = new algoritmo(fichero);
		System.out.println(prueba.centroGravedad(0));
		
		prueba.getProblema().mostrarMatrizDatos();
	}
}
