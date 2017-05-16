package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//parte de un algoritmo Voraz como Cota
public class algoritmoRamificacionYPoda extends algoritmo {
	private arbol arbolRamificacion;
	private double cotaInferior;
	public int contador ;

	public algoritmoRamificacionYPoda(String fichero, int m) throws FileNotFoundException, IOException{
		super(fichero);
		getProblema().mostrarMatrizDatos();
		arbolRamificacion = new arbol(new nodo(null, -1));
		algoritmoVoraz(m);
		System.out.println(getSolucionFinal());
		System.out.println("Solucion Z = " + sumaDistanciasSolucion());
		cotaInferior = sumaDistanciasSolucion() - 10;
		System.out.println("Cota inferior " + cotaInferior);
		getSolucionFinal().clear();
		contador = 0;
		//System.out.println("Solucion Z = " + sumaDistanciasSolucion());
		ejecutar(m);

	}

	arbol getArbolRamificacion() {
		return arbolRamificacion;
	}

	public double getCotaInferior() {
		return cotaInferior;
	}

	public void ejecutar(int tamanoSolucion) {
		//inicializamos el arbol

		//ramificamos
		nodo actual = new nodo(getArbolRamificacion().getRaiz(), -1);
		
		double cotaInicial = 0;
		contador += 1;
		for(int i = 0; i < getProblema().getNumVectores() - 1; i++) {
			nodo nodo = new nodo (actual, i);

			getSolucionFinal().add(nodo.getNumero().get(nodo.getNumero().size() - 1));
			cotaInicial += sumaDistanciasSolucion();
			actual.añadirHijo(nodo);
			getSolucionFinal().clear();
		}
		cotaInicial /= getProblema().getNumVectores();
		System.out.println("Cota inicial " + cotaInicial);
		

		actual.mostrarNodo();
		actual.mostrarNodosHijos();
		ArrayList<nodo> nodosCandidatos = new ArrayList<nodo>();
		while(tamanoSolucion > contador ) {
			contador += 1;
			for(int i = 0; i < actual.getHijos().size(); i++) {
				nodo aux = new nodo(actual.getHijos().get(i));
				for(int j = 0; j < getProblema().getNumVectores() - 1; j++) {
					if(!aux.getNumero().contains(j)) {
						aux.añadirHijo(new nodo(aux, j)); 

						//System.out.println(calcularCota(aux.getHijos().get(aux.getHijos().size() - 1).getNumero()) + " ");
						//Cota
						if(cotaInferior > calcularCota(aux.getHijos().get(aux.getHijos().size() - 1).getNumero())) {
							System.out.println("CORTAMOS RAMA " + aux.getHijos().get(aux.getHijos().size() - 1).getNumero());
							aux.getHijos().get(aux.getHijos().size() - 1).setRamificable(false);
						} else {
							//System.out.println("Nodo candidato " + j);
							nodosCandidatos.add(aux.getHijos().get(aux.getHijos().size() - 1));
							//System.out.println(calcularCota(aux.getHijos().get(aux.getHijos().size() - 1).getNumero()) + " ");
							//System.out.println(aux.getHijos().get(aux.getHijos().size() - 1).getNumero());
						}
					}
				}
			}
		} 
		System.out.println("Terminado");
		double max = 0;
		ArrayList<Integer> solucion = new ArrayList<Integer>();
		for(int i = 0; i < nodosCandidatos.size(); i++) {
			if(max < sumaDistanciasSolucion(nodosCandidatos.get(i).getNumero())) {
				max = sumaDistanciasSolucion(nodosCandidatos.get(i).getNumero());
				solucion = new ArrayList<Integer>(nodosCandidatos.get(i).getNumero());
			}
			//System.out.println(sumaDistanciasSolucion(nodosCandidatos.get(i).getNumero()));
		}
		System.out.println("Solucion " + solucion + " con Z " + max);
		
	}

	public double calcularCota(ArrayList<Integer> solucion) {
		return sumaDistanciasSolucion(solucion);
	}
	
	//Utilizado ramificacion y poca
		public double sumaDistanciasSolucion(ArrayList<Integer> solucion) {
			double suma = 0;
			for(int i = 0; i < solucion.size() - 1; i++) {
				for(int j = i + 1; j < solucion.size(); j++) {
					suma += distanciaEuclideaVector(getProblema().getVectorProblema(solucion.get(i)), getProblema().getVectorProblema(solucion.get(j)));
				}
			}
			//System.out.println("Z vale : " + suma);
			return suma;
		}
		
		public ArrayList<Double> centroGravedad(boolean aux1) {
			ArrayList<Double> centroGravedad = new ArrayList<Double>();
			double aux = 0;
			//centro de gravedad inicial
			
				for(int i = 0; i < getProblema().getDimension(); i++) {
					aux = 0;
					for(int j = 0; j < getProblema().getNumVectores(); j++) 
						aux += getProblema().getDatoMatrizDatos(j, i);
					aux /= getProblema().getNumVectores();
					centroGravedad.add(aux);
				}
			return centroGravedad;
		}

	public void algoritmoVoraz(int tamanoSolucion) {
		ArrayList<Double> elementosMasAlejados = new ArrayList<Double>();
		for(int k = 0; k < tamanoSolucion; k++) {
			elementosMasAlejados.clear();

			//Se añaden iterativamente las distancias de los vectores al centro de gravedad
			for(int i = 0; i < getProblema().getNumVectores(); i++) 
				elementosMasAlejados.add(distanciaEuclideaVector(getProblema().getVectorProblema(i), centroGravedad()));

			double valor = 0;
			for(int i = 0; i < elementosMasAlejados.size(); i++ )  
				if(valor < elementosMasAlejados.get(i) && !getSolucionFinal().contains(i)) 
					valor = elementosMasAlejados.get(i);

			getSolucionFinal().add(elementosMasAlejados.indexOf(valor));
		}
		sumaDistanciasSolucion();
	}
}
//}
