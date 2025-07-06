package Ejercicios22Octubre;

public class IncrementoSincronizadoConRunnable implements Runnable {
	
	private static int contador = 0;

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			incrementarContador();
		}
	}

	
	private static synchronized void incrementarContador() {
		contador++;
	}

	
	
	public static void main(String[] args) {
		
		// Crear 5 hilos usando la interfaz Runnable
		Thread[] hilos = new Thread[5];
		
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Thread(new IncrementoSincronizadoConRunnable());
			hilos[i].start();
		}

		// Esperar a que todos los hilos terminen
		for (Thread hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Imprimir el valor final de "contador"
		System.out.println("Valor final de contador con Runnable: " + contador);
	}
}
