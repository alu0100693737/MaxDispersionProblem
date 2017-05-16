package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class algoritmoAleatorioYBusquedaLocal extends algoritmo {
	static final int NUM_MAX_BUSQUEDAS_LOCALES = 5;
	private int numBusquedas;

	public algoritmoAleatorioYBusquedaLocal(String fichero, int m) throws FileNotFoundException, IOException{
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
		System.out.println("Solucion inicial " + getSolucionFinal());
		for(int i = 0; i < tamanoSolucion; i++) {
			int aux = (int)(Math.random() * (getProblema().getNumVectores())); //nodo elegido 
			if(!getSolucionFinal().contains(aux)) 
				getSolucionFinal().add(aux);
			else {
				while(getSolucionFinal().contains(aux)) {
					aux = (int)(Math.random() * (getProblema().getNumVectores())); //nodo elegido 
				} 
				getSolucionFinal().add(aux);
			}
		}
		System.out.println("Solucion final");
		System.out.println(getSolucionFinal());
		System.out.println("Z vale: " + sumaDistanciasSolucion());
		
		System.out.println("BUSQUEDA LOCAL");
		busquedaLocalMáximaPendiente();
		System.out.println(getSolucionFinal());
		System.out.println("Z vale " + sumaDistanciasSolucion());
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
