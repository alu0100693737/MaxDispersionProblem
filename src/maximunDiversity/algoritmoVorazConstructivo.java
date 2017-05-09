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
		ArrayList<Point3D> elementosMasAlejados = new ArrayList<Point3D>();
		for(int k = 0; k < tamanoSolucion; k++) {
			elementosMasAlejados.clear();
			
			System.out.println("Centro de gravedad: ");
			System.out.print("| ");
			
			//INICIAL
			if(k == 0) 
				for(int i = 0; i < getProblema().getDimension(); i++) 
					elementosMasAlejados.add(elementoAlejado(i, centroGravedadConstructivoInicial(i)));
				    
			 else 
				for(int i = 0; i < getProblema().getDimension(); i++) 
					elementosMasAlejados.add(elementoAlejado(i, centroGravedadConstructivo(i)));
				  
			
			System.out.println();

			for(int i = 0; i < elementosMasAlejados.size(); i++ ) 
				System.out.println("Las distancias son " + elementosMasAlejados.get(i).getValor() + " en la pos " + elementosMasAlejados.get(i).getPosicion() + " con distancia " + elementosMasAlejados.get(i).getDistancia());
			

			Point3D aux = new Point3D(elementosMasAlejados.get(0).getValor(), elementosMasAlejados.get(0).getDistancia(), elementosMasAlejados.get(0).getPosicion());
			for(int i = 1; i < elementosMasAlejados.size(); i++) 
				if(aux.getDistancia() < elementosMasAlejados.get(i).getDistancia()) 
					aux = new Point3D(elementosMasAlejados.get(i).getValor(), elementosMasAlejados.get(i).getDistancia(), elementosMasAlejados.get(i).getPosicion());
			
			aux.mostrarPoint3D();
			getSolucionFinal().add(aux.getPosicion());
			System.out.println("Solucion actual " + getSolucionFinal());
		}
		
		//FINAL
				System.out.println("Solucion final: ");
				System.out.println(getSolucionFinal());
				System.out.print("Centro de gravedad FINAL: \n | ");

				for(int i = 0; i < getProblema().getDimension(); i++) 
					elementosMasAlejados.add(elementoAlejado(i, centroGravedadConstructivo(i)));
				System.out.println();
	}

	public double centroGravedadConstructivoInicial(int columna) {
		double aux = 0;
		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			if(!getSolucionFinal().contains(i))
				aux += getProblema().getDatoMatrizDatos(i, columna);

		aux /= getProblema().getNumElementos() - getSolucionFinal().size();
		
		System.out.print(aux + " | ");
		return aux;
	}

	public double centroGravedadConstructivo(int columna) {
		double aux = 0;
		for(int i = 0; i < getSolucionFinal().size(); i++) 
			aux += getProblema().getDatoMatrizDatos(getSolucionFinal().get(i), columna);
			
		aux /= getSolucionFinal().size();
		System.out.print(aux + " | ");
		return aux;
	}

	//Devuelve valor, diferencia y posicion
	public Point3D elementoAlejado(int columna, double centroGravedad) {
		int posMasAlejado = 0;  //posicion del elemento mas alejado
		double aux = 0; //distancia al centro
		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			if(!getSolucionFinal().contains(i))
				//elegimos el mas alejado el primer elemento y comprobamos si algun otro se aleja aun más del centro
				if(aux < distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad)) {
					aux = distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad);
					posMasAlejado = i;
				}	
		return new Point3D(getProblema().getDatoMatrizDatos(posMasAlejado, columna), aux, posMasAlejado);
	}
}
