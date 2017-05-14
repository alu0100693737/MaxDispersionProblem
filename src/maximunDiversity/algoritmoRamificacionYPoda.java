package maximunDiversity;

import java.io.FileNotFoundException;
import java.io.IOException;

//parte de un algoritmo Voraz como Cota
public class algoritmoRamificacionYPoda extends algoritmo {
	private arbol arbolRamificacion;

	public algoritmoRamificacionYPoda(String fichero, int m) throws FileNotFoundException, IOException{
		super(fichero);
		getProblema().mostrarMatrizDatos();
		arbolRamificacion = new arbol(new nodo(null, -1));
		ejecutar(m);
	}

	arbol getArbolRamificacion() {
		return arbolRamificacion;
	}
	public void ejecutar(int tamanoSolucion) {
		//inicializamos el arbol
		for(int i = 0; i < getProblema().getNumVectores(); i++) {
			nodo nodo = new nodo (getArbolRamificacion().getRaiz(), i);
			getArbolRamificacion().getRaiz().añadirHijo(nodo);
		}

		for(int i = 0; i < getArbolRamificacion().getRaiz().getHijos().size(); i++) {
			//System.out.println("Tamano nietos " + getArbolRamificacion().getRaiz().getHijos().size());
			System.out.println("Este nodo " + getArbolRamificacion().getRaiz().getHijos().get(i).getNumero());

			System.out.println(" tiene nietos: ");
			for(int j = 0; j < getArbolRamificacion().getRaiz().getHijos().get(i).getHijos().size(); j++) {
				System.out.print(getArbolRamificacion().getRaiz().getHijos().get(i).getHijos().get(j).getNumero());
			}
		}//System.out.println("Accediendo nietos " + nodo);
		//  double cota = calcularCota(nodo);
		// nodo.setCota(cota);
		//if(cota <= cotaInferior) {
		//  nodo.setRamificable(false);
		//System.out.print("Se poda - ");
		//} else {
		//getPosibles().add(nodo);
		//}
		//System.out.print("Nodo " + nodo.getNumero() + " - Cota "); /*+ nodo.getCota() + "\n");*/

	}



}
