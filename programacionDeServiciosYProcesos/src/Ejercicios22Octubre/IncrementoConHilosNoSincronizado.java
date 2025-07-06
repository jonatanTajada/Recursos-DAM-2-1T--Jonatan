package Ejercicios22Octubre;

public class IncrementoConHilosNoSincronizado{

	private static int contador = 0;
	
	public static void main(String[] args) {
		
		//incrementar 5 hilos que incremetaran la variable compartida: contador
		
		Thread[] hilos = new Thread[5];
		
		for (int i = 0; i < hilos.length; i++) {
			
			hilos[i] = new Thread(() -> {
				
				for (int j = 0; j < 1000; j++) {
					contador++;
				}
			});

		}
		
		//iniciar los hilos
		for (Thread hilo : hilos) {
			hilo.start();
		}
		
		//esperar a que todos los hilos terminen
		for (Thread hilo : hilos) {
			
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Valor final de contador: " + contador);
		
	}
	
	
	
	
	
	
	
	
}
