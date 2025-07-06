package Ejercicios22Octubre;

public class IncrementoConHilosSincronizado {

	private static int contador = 0;
	
	private static synchronized void incrementarContador() {
		contador+= 1000;
	}
	
	
	public static void main(String[] args) {
		
		Thread[] hilos = new Thread[5];
		
		//crear hilos
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Thread(() -> incrementarContador());
		}
		
		//inicializar hilos
		for (Thread hilo : hilos) {
			hilo.start();
		}
		
		//esperar a que terminen
		for (Thread hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Valor de contador: " + contador);
		
		
	}
	
	
	
	
	
	
	
	
}
