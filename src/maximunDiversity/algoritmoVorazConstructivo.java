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
		for(int k = 0; k < tamanoSolucion; k++) {
			ArrayList<Point3D> auxSolucion = new ArrayList<Point3D>();
			for(int i = 0; i < getProblema().getDimension(); i++) {
				auxSolucion.add(elementoAlejado(i, centroGravedad(i)));
			}    
			
			Point3D aux = new Point3D(auxSolucion.get(0).getValor(), auxSolucion.get(0).getDistancia(), auxSolucion.get(0).getPosicion());
			for(int i = 1; i < auxSolucion.size(); i++) {
				if(aux.getDistancia() < auxSolucion.get(i).getDistancia()) 
					aux = new Point3D(auxSolucion.get(i).getValor(), auxSolucion.get(i).getDistancia(), auxSolucion.get(i).getPosicion());
			}
			aux.mostrarPoint3D();
			getSolucionFinal().add(aux.getPosicion());
			System.out.println("Solucion actual " + getSolucionFinal());
		}
	}

}
