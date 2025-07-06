package pruebasExamenPrimerTrim;

class ContadorSincronizacion implements Runnable {
	
	private static int contador = 0; 

	@Override
	public void run() {
		
		for (int i = 0; i < 1000; i++) {
			contador++; 
		}
	}

	public static int getContador() {
		return contador;
	}
}


//---------------------------------------------
public class SinSincronizacion {
	
	
	public static void main(String[] args) {
		
		Thread[] hilos = new Thread[5];

	
		for (int i = 0; i < 5; i++) {
			hilos[i] = new Thread(new ContadorSincronizacion());
			hilos[i].start();
		}

		
		for (Thread hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				System.out.println("Error al esperar a los hilos: " + e.getMessage());
			}
		}

	
		System.out.println("Valor final del contador (sin sincronización): " + ContadorSincronizacion.getContador());
	}
}
