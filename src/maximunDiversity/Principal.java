package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Principal {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Introduzca el nombre del fichero");
		Scanner in = new Scanner(System.in);
		//String fichero = in.nextLine();
		algoritmo prueba = new algoritmoVorazDestructivo("max_div_5.txt", 4);
		
//prueba.ejecutar(4);
		
		prueba.getProblema().mostrarMatrizDatos();
	}
}
