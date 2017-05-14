package maximunDiversity;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class algoritmoGraspConstructivo extends algoritmo {
	static final int NUM_MAX_BUSQUEDAS_LOCALES = 5;
	static final int LRC = 2;
	private int numBusquedas;

	public algoritmoGraspConstructivo(String fichero, int m) throws FileNotFoundException, IOException{
		super(fichero);
		getProblema().mostrarMatrizDatos();
		ejecutar(m);
	}

	int getNumBusquedas() {
		return numBusquedas;
	}

	void setNumBusquedas(int valor) {
		numBusquedas = valor;
	}

	public void ejecutar(int tamanoSolucion) {
		//Point2D //distancia y posicion
		ArrayList<Point2D.Double> elementosMasAlejados = new ArrayList<Point2D.Double>();
		for(int k = 0; k < tamanoSolucion; k++) {
			elementosMasAlejados.clear();
			System.out.println("\nIteracion " + (k + 1));
			System.out.println("Centro de gravedad ");
			System.out.println(centroGravedad());
			//Se añaden iterativamente las distancias de los vectores al centro de gravedad
			for(int i = 0; i < getProblema().getNumVectores(); i++) {
				if(!getSolucionFinal().contains(i)) {
					System.out.println("Candidatos");
					System.out.println(elementosMasAlejados + "\n");
					if(elementosMasAlejados.size() < LRC) {
						elementosMasAlejados.add(new Point2D.Double(distanciaEuclideaVector(getProblema().getVectorProblema(i), centroGravedad()), i));
					} else {
						System.out.println("Se quiere añadir un nuevo nodo con distancia " + distanciaEuclideaVector(getProblema().getVectorProblema(i), centroGravedad()) + " pos " + i);
						//Realizamos una busqueda de los que ya hemos elegido y quitamos el que mejora menos la solucion
						double peor = 99999;
						int pos = 0;
						for(int l = 0; l < elementosMasAlejados.size(); l++) {
							if(peor > elementosMasAlejados.get(l).getX()) {
								peor = elementosMasAlejados.get(l).getX();
								pos = l;
							}
						}
						System.out.println("el peor elemento de la lista es " + peor + " en la pos " + pos);

						boolean cambio = false;				
						//System.out.println("candidato pos " + i + " con distancia " + distancia(getProblema().getDatoMatrizDatos(i, columna), centroGravedad) + " actual " + peor + " en " + pos);
						if(distanciaEuclideaVector(getProblema().getVectorProblema(i), centroGravedad()) > peor) {
							cambio = true;
						}
						if(cambio == true) {
							System.out.println("Eliminado el nodo de peor calidad entre los candidatos: " + pos);
							System.out.println("Añadiendo " + distanciaEuclideaVector(getProblema().getVectorProblema(i), centroGravedad()));
							elementosMasAlejados.remove(elementosMasAlejados.get(pos));
							elementosMasAlejados.add(new Point2D.Double(distanciaEuclideaVector(getProblema().getVectorProblema(i), centroGravedad()), i));
						}
					}
				}
			}

			int randomNum = 0;
			if(elementosMasAlejados.size() > 0) { //si se ha encontrado una posible mejora
				//System.out.println("Posibles candidatos " + posiblesCandidatos);
				randomNum = (int)(Math.random() * (elementosMasAlejados.size())); //nodo elegido 
				System.out.println("Random vale: " + randomNum);
				System.out.println("Elegido elemento " + elementosMasAlejados.get(randomNum) + " en pos " + randomNum);
				getSolucionFinal().add((int)elementosMasAlejados.get(randomNum).getY());
			}	
			System.out.println(getSolucionFinal());
		}

		System.out.println("\nBUSQUEDA LOCAL");
		busquedaLocalMáximaPendiente();
		sumaDistanciasSolucion();

	}


	//Estrategia, eliminar un nodo para poner otro
	void busquedaLocalMáximaPendiente() {
		setNumBusquedas(getNumBusquedas() + 1); 
		if(getNumBusquedas() > NUM_MAX_BUSQUEDAS_LOCALES) {
			System.out.println("Maximo de busquedas locales");
			System.out.println(getSolucionFinal());

		} else {
			//Guardamos el vector solucion final en una copia
			ArrayList<Integer> copiaVersionSolucion = new ArrayList<Integer>(getSolucionFinal());
			ArrayList<Integer> auxNodosVecinos = getNodosVecinos(getSolucionFinal());

			//Analizamos para cada nodo de la solucion si al quitarlo y ponerle otro, se mejora la solucion
			boolean mejora = false;
			double Zactual = 0;
			int quitar = -1, poner = -1;

			for(int k = 0; k < getSolucionFinal().size(); k++) {
				//mejora = false;
				double sumaAntes = sumaDistanciasSolucion();
				getSolucionFinal().remove(k);
				//System.out.println("Suma antes " + sumaAntes + " suma ahora " + sumaDistanciasSolucion());

				System.out.println("Centro de gravedad ");
				System.out.println(centroGravedad());
				System.out.println("Solucion actual " + getSolucionFinal());
				//System.out.println("Actual " + sumaDistanciasSolucion() + " antes " + sumaAntes);
				//mostrarCentroGravedadSolucion();
				for(int i = 0; i < auxNodosVecinos.size(); i++) {
					//System.out.println("Intentando meter " + auxNodosVecinos.get(i));
					getSolucionFinal().add(auxNodosVecinos.get(i));
					//System.out.println(getSolucionFinal());
					if(sumaDistanciasSolucion() > sumaAntes) {
						if(Zactual < sumaDistanciasSolucion()) {
							quitar = copiaVersionSolucion.get(k);
							poner = auxNodosVecinos.get(i);
							Zactual = sumaDistanciasSolucion();
							mejora = true;
							System.out.println("ESTE CAMBIO MEJORA");
							System.out.println("Actual " + sumaDistanciasSolucion() + " antes " + sumaAntes);
							System.out.println(getSolucionFinal());
						}
					}
					getSolucionFinal().remove(getSolucionFinal().indexOf(auxNodosVecinos.get(i)));
				}
				setSolucionFinal(copiaVersionSolucion); 
				//System.out.println("Volvemos a la solucion " + getSolucionFinal());
				//System.out.println("Centro de gravedad ");
				//System.out.println(centroGravedad());
			}
			System.out.println("el elemento a quitar es "  + quitar + " por " + poner);
			System.out.println("mejora la distancia de: " + sumaDistanciasSolucion() + " a " + Zactual );
			if(mejora == true) {
				getSolucionFinal().remove(getSolucionFinal().indexOf(quitar));
				getSolucionFinal().add(poner);
				System.out.println("Solucion " + getSolucionFinal());
				System.out.println("Centro de gravedad ");
				System.out.println(centroGravedad());
				System.out.println("MEJORANDO Y VOLVEMOS A EJECUTAR BUSQUEDA LOCAL" );
				busquedaLocalMáximaPendiente();	
			}
		}
	}
}
