package maximunDiversity;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class algoritmo {
	private estructuraProblema problema;
	private ArrayList<Integer> solucionFinal;

	public algoritmo(String fichero) throws FileNotFoundException, IOException {
		problema = new estructuraProblema(fichero);
		solucionFinal = new ArrayList<Integer>();
	}

	public estructuraProblema getProblema() {
		return problema;
	}

	public ArrayList<Integer> getSolucionFinal() {
		return solucionFinal;
	}
	
	public void setSolucionFinal(ArrayList<Integer> aux) {
		solucionFinal = new ArrayList<Integer>(aux);
	}
	
	public ArrayList<Integer> getNodosVecinos(ArrayList<Integer> vector){
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for(int i = 0; i < getProblema().getNumVectores(); i++)
			if(!vector.contains(i))
				aux.add(i);
		return aux;
	}
	
	public double distanciaEuclideaVector(ArrayList<Double> a, ArrayList<Double> b) {
		double suma = 0;
		if(a.size() == b.size()) {
			for(int i = 0; i < a.size(); i++) {
				suma += Math.pow(a.get(i) - b.get(i),  2);
			}
		} else {
			System.out.println("ERRORRRRRRRRRR!");
			return -1;
		}
		return Math.sqrt(suma);
	}
	
	public ArrayList<Double> centroGravedad() {
		ArrayList<Double> centroGravedad = new ArrayList<Double>();
		double aux = 0;
		//centro de gravedad inicial
		if(getSolucionFinal().size() == 0) {
			for(int i = 0; i < getProblema().getDimension(); i++) {
				aux = 0;
				for(int j = 0; j < getProblema().getNumVectores(); j++) 
					aux += getProblema().getDatoMatrizDatos(j, i);
				aux /= getProblema().getNumVectores();
				centroGravedad.add(aux);
			}
		}
		//centro de gravedad 
		else {
			for(int i = 0; i < getProblema().getDimension(); i++) {
				aux = 0;
				for(int j = 0; j < getSolucionFinal().size(); j++) {
					aux += getProblema().getDatoMatrizDatos(getSolucionFinal().get(j), i);
				}
				aux /= getSolucionFinal().size();
				centroGravedad.add(aux);
			}
		}
		return centroGravedad;
	}
	
	public double sumaDistanciasSolucion() {
		double suma = 0;
		for(int i = 0; i < getSolucionFinal().size() - 1; i++) {
			for(int j = i + 1; j < getSolucionFinal().size(); j++) {
				suma += distanciaEuclideaVector(getProblema().getVectorProblema(getSolucionFinal().get(i)), getProblema().getVectorProblema(getSolucionFinal().get(j)));
			}
		}
		//System.out.println("Z vale : " + suma);
		return suma;
	}


	public void ejecutar(int tamanoSolucion) {
	}
	
	public void calcularDiversity() {
		for(int i = 0; i < getSolucionFinal().size(); i++) {
			
		}
	}
}
