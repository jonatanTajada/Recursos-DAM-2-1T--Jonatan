package pruebas;

/**
 * Sincronización de hilos
 *  Enunciado: Crea dos hilos que accedan a una misma variable compartida. Usa la palabra clave synchronized para evitar problemas de concurrencia.
 */

class Contador{
	
	private int cuenta = 0;
	
	public synchronized void incrementar() {
		cuenta++;
		System.out.println("Cuenta: " + cuenta);
	}
}



public class HiloSincronizado extends Thread{

	private Contador contador;
	
	public HiloSincronizado(Contador contador) {
		this.contador=contador;
	}
	
	@Override
		public void run() {
			
			for (int i = 1; i <= 5; i++) {
				contador.incrementar();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.println("erro en el hilo");
				}
				
			}
		
	
		}
	
	
	public static void main(String[] args) {
		
		Contador contadorCompartido = new Contador();
		
		HiloSincronizado hilo1 = new HiloSincronizado(contadorCompartido);
		HiloSincronizado hilo2 = new HiloSincronizado(contadorCompartido);
		HiloSincronizado hilo3 = new HiloSincronizado(contadorCompartido);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
	}
	
	
	
}
