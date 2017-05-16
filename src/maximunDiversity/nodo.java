package maximunDiversity;

import java.util.ArrayList;

public class nodo {
		  
		  private nodo padre;
		  private ArrayList<nodo> hijos;
		  private double cota;
		  private boolean ramificable;
		  private ArrayList<Integer> numero;
		  private boolean nodoHoja;
		  
		  public nodo (nodo p, Integer num) {
		    padre = p;
		    hijos = new ArrayList<nodo> ();
		    cota = 0;
		    ramificable = true;
		    //nodo raiz
		    if(num == -1) {
		    	numero = new ArrayList<Integer>();
		    } else {
		    	numero = new ArrayList<Integer>(getPadre().getNumero());
		    	numero.add(num);
		    }
		  }
		  
		  //nodo copia
		  public nodo(nodo p) {
			  padre = p.padre;
			  hijos = p.hijos;
			  cota = p.cota;
			  ramificable = p.ramificable;
			  numero = p.numero;
		  }
		  
		  public void añadirHijo (nodo h) {
		    hijos.add(h);
		  }
		  
		  public ArrayList<nodo> getHijos () {
		    return hijos;
		  }
		  
		  public void mostrarNodo() {
			  System.out.println("Nodo actual " + getNumero());
			  System.out.println("Nodos hijos: ");
		  }
		  
		  public void mostrarNodosHijos() {
			  for(int i = 0; i < getHijos().size(); i++) {
				  System.out.print(getHijos().get(i).getNumero() + " ");
			  }
			  System.out.println();
		  }
		  
		  public void setHijos (ArrayList<nodo> h) {
		    hijos = h;
		  }
		  
		  public nodo getPadre () {
		    return padre;
		  }
		 
		  public void setCota (double c) {
		    cota = c;
		  }
		  
		  public double getCota () {
		    return cota;
		  }
		  
		  public boolean getHoja() {
			  return nodoHoja;
		  }
		  
		  public void setHoja(boolean aux) {
			  nodoHoja = aux;
		  }
		  
		  public void setRamificable (boolean estado) {
		    ramificable = estado;
		  }
		  
		  public boolean getRamificable () {
		    return ramificable;
		  }
		  
		  public ArrayList<Integer> getNumero () {
		    return numero;
		  }	 
		  
}
