package maximunDiversity;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class algoritmoGraspConstructivo extends algoritmo {
	private final static int LRC = 2;

	public algoritmoGraspConstructivo(String fichero, int m) throws FileNotFoundException, IOException{
		super(fichero);
		getProblema().mostrarMatrizDatos();
		ejecutar(m);
	}

	public void ejecutar(int tamanoSolucion) {
		ArrayList<Point3D> elementosMasAlejados = new ArrayList<Point3D>();
		for(int k = 0; k < tamanoSolucion; k++) {
			elementosMasAlejados.clear();


			//INICIAL
			if(k == 0) 
				for(int i = 0; i < getProblema().getDimension(); i++) 
					elementosMasAlejados.add(elementoAlejado(i, centroGravedadConstructivoInicial(i))); 
			else 
				for(int i = 0; i < getProblema().getDimension(); i++) 
					elementosMasAlejados.add(elementoAlejado(i, centroGravedadConstructivo(i)));

			System.out.println();

			for(int i = 0; i < elementosMasAlejados.size(); i++ ) 
				System.out.println("Las distancias son " + elementosMasAlejados.get(i).getValor() + " en la pos " + i + " " + elementosMasAlejados.get(i).getPosicion() + " con distancia " + elementosMasAlejados.get(i).getDistancia());

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
		System.out.print("\nCentro de gravedad FINAL: \n | ");

		for(int i = 0; i < getProblema().getDimension(); i++) 
			elementosMasAlejados.add(elementoAlejado(i, centroGravedadConstructivo(i)));
		System.out.println();
	}

	//GRASP
	public double centroGravedadConstructivoInicial(int columna) {
		double aux = 0;
		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			if(!getSolucionFinal().contains(i))
				aux += getProblema().getDatoMatrizDatos(i, columna);

		aux /= getProblema().getNumElementos() - getSolucionFinal().size();

		System.out.println("\nCentro de gravedad: en " + columna);
		System.out.println(" | " + aux + " | \n");
		return aux;
	}

	public double centroGravedadConstructivo(int columna) {
		double aux = 0;
		for(int i = 0; i < getSolucionFinal().size(); i++) 
			aux += getProblema().getDatoMatrizDatos(getSolucionFinal().get(i), columna);

		aux /= getSolucionFinal().size();
		
		System.out.println("Centro de gravedad: en " + columna);
		System.out.println(" | " + aux + " | \n");
		return aux;
	}

	//Devuelve valor, diferencia y posicion
	public Point3D elementoAlejado(int columna, double centroGravedad) {
		ArrayList<Point2D.Double> posiblesCandidatos = new ArrayList<Point2D.Double>();
		//System.out.println("Analizando columna " + columna + " con gravedad " + centroGravedad);
		for(int i = 0; i < getProblema().getNumElementos(); i++) 
			if(!getSolucionFinal().contains(i)) {
				//elegimos los LRC mas alejado el primer elemento y comprobamos si algun otro se aleja aun más del centro
				if(posiblesCandidatos.size() < LRC) {
					posiblesCandidatos.add(new Point2D.Double(distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad), (double)i));
					//System.out.println("Entre: " + nodoCandidato + " con valor " + maxCandidato);
				} else {
					System.out.println("Se quiere añadir un nuevo nodo con distancia " + distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad) + " de la pos " + i);
					//Realizamos una busqueda de los que ya hemos elegido y quitamos el que mejora menos la solucion
					double peor = 99999;
					int pos = 0;
					for(int l = 0; l < posiblesCandidatos.size(); l++) {
						if(peor > posiblesCandidatos.get(l).getX()) {
							peor = posiblesCandidatos.get(l).getX();
							pos = l;
						}
					}
					System.out.println("el peor elemento de la lista es " + peor + " en la pos " + pos);
					
					boolean cambio = false;				
					//System.out.println("candidato pos " + i + " con distancia " + distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad) + " actual " + peor + " en " + pos);
					if(distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad) > peor) {
						//peor = distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad);
						//pos = i;
						cambio = true;
					}
						
					
					if(cambio == true) {
						System.out.println("Eliminado el nodo de peor calidad entre los candidatos: " + pos);
						System.out.println("Añadiendo " + distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad));
						posiblesCandidatos.remove(pos);
						posiblesCandidatos.add(new Point2D.Double(distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad), (double)i));
					}
					//System.out.println("Posibles candidatos" + posiblesCandidatos);

				}
				//System.out.println("Posibles candidatos" + posiblesCandidatos);
				System.out.println("Posibles candidatos" + posiblesCandidatos);
			}
		
		int randomNum = 0;
		if(posiblesCandidatos.size() > 0) { //si se ha encontrado una posible mejora
			//System.out.println("Posibles candidatos " + posiblesCandidatos);
			randomNum = (int)(Math.random() * (posiblesCandidatos.size())); //nodo elegido 
			System.out.println("Random vale: " + randomNum);
			System.out.println("Elemento en pos " + (int)posiblesCandidatos.get(randomNum).getY() + " con distancia al centro " + posiblesCandidatos.get(randomNum).getX());
			//System.out.println("Se suma: " + maxActual + " a " + maxCandidato);
			//System.out.println("Dispersion media " + (maxActual / getVectorSolucion().size()));
			//System.out.println("El vector solucion queda: " + getVectorSolucion());
		}
		return new Point3D(getProblema().getDatoMatrizDatos((int)posiblesCandidatos.get(randomNum).getY(), columna), posiblesCandidatos.get(randomNum).getX(), (int)posiblesCandidatos.get(randomNum).getY());
	}
}
