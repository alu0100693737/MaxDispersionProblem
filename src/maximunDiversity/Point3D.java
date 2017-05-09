package maximunDiversity;

import java.awt.geom.Point2D;

//Estructura que almacena un valor de la matriz, su distancia al centro 
//de gravedad y la posicion en la que se encuentra
public class Point3D {
	 private Point2D.Double xy;
	    private int z;
	    
	    public Point3D(double x, double y, int z) {
	        xy = new Point2D.Double(x, y);
	        this.z = z;
	    }
	    
	    public Point3D(Point3D aux) {
	    	xy.x = aux.getValor();
	    	xy.y = aux.getDistancia();
	    	z = aux.getPosicion();	    	
	    }

	    public double getValor() {
	        return xy.getX();
	    }
	    
	    public double getDistancia() {
	        return xy.getY();
	    }
	   
	    public int getPosicion() {
	        return z;
	    }
	    
	    public void mostrarPoint3D() {
	    	System.out.println("El elemento elegido es " + getValor() + " en la posicion " + getPosicion() + " con una distancia al centro de gravedad de " + getDistancia()); 
	    }
}
