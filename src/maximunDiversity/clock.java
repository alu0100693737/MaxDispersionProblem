/** Ivan Garcia Campos   alu0100693737@ull.edu.es
 * 30/04/17
 * 1.0v
 * Clase Clock para comprobar el tiempo de ejecucion de un bloque de codigo Java
 */
package maximunDiversity;

public class clock {
	private long startTime; 			//tiempo de comienzo
	private long endTime; 				//tiempo de finalizacion

	public clock() {
		startTime = 0;
		endTime = 0;
	}

	public void start() {
		setStartTime();
	}

	public void stop() {
		setEndTime();
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime() {
		startTime = System.nanoTime();
	}

	public void setEndTime() {
		endTime = System.nanoTime();
	}

	public long getEndTime() {
		return endTime;
	}

	public long elapseTime() {
		System.out.println(((getEndTime() - getStartTime()) / 1000) + " milisegundos"); 
		return ((getEndTime() - getStartTime()) / 1000);
	}
}