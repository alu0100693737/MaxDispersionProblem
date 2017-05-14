package maximunDiversity;

import java.util.ArrayList;

public class nodo {
		  
		  private nodo padre;
		  private ArrayList<nodo> hijos;
		 /* private double cota;
		  private boolean ramificable;*/
		  private Integer numero;
		  
		  public nodo (nodo p, Integer num) {
		    padre = p;
		    hijos = new ArrayList<nodo> ();
		    /*cota = 0;
		    ramificable = true;*/
		    numero = num;
		  }
		  
		  public void añadirHijo (nodo h) {
		    hijos.add(h);
		  }
		  
		  public ArrayList<nodo> getHijos () {
		    return hijos;
		  }
		  
		  public void setHijos (ArrayList<nodo> h) {
		    hijos = h;
		  }
		  
		  public nodo getPadre () {
		    return padre;
		  }
		 /* 
		  public void setCota (double c) {
		    cota = c;
		  }
		  
		  public double getCota () {
		    return cota;
		  }
		  
		  public void setRamificable (boolean estado) {
		    ramificable = estado;
		  }
		  
		  public boolean getRamificable () {
		    return ramificable;
		  }
		  */
		  public int getNumero () {
		    return numero;
		  }	  
}
