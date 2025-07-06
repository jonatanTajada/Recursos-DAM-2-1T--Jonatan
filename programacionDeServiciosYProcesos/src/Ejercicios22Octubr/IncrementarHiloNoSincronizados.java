package Ejercicios22Octubr;

class IncrementarHiloNoSincronizados extends Thread {
	
	public static int contador = 0;

	@Override 
	public void run() {
		
		for (int i = 0; i < 1000; i++) {
			contador++; 
		}
	}

	public static void main(String[] args) {
		
		int cantidadHilos= 5;

		IncrementarHiloNoSincronizados[] hilos = new IncrementarHiloNoSincronizados[cantidadHilos];

		for (int i = 0; i < cantidadHilos; i++) {
			
			hilos[i] = new IncrementarHiloNoSincronizados();
			hilos[i].start();
		}

		// Esperar que todos los hilos terminen
		for (int i = 0; i < cantidadHilos; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
		System.out.println("Valor final del contador: " + contador);
	}
}
