package pruebas;

public class EjemploParalelismo {
	
	public static void main(String[] args) {
		
		
		// Crear el primer hilo (tarea intensiva)
		Thread hilo1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hilo 1 - Iniciando tarea intensiva");
				long suma = 0;
				for (long i = 0; i < 1000000000; i++) {
					suma += i;
				}
				System.out.println("Hilo 1 - Tarea completada: " + suma);
			}
		});

		// Crear el segundo hilo (tarea intensiva)
		Thread hilo2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hilo 2 - Iniciando tarea intensiva");
				long suma = 0;
				for (long i = 0; i < 1000000000; i++) {
					suma += i;
				}
				System.out.println("Hilo 2 - Tarea completada: " + suma);
			}
		});

		// Iniciar los hilos
		long startTime = System.currentTimeMillis();
		hilo1.start();
		hilo2.start();

		try {
			// Esperar a que ambos hilos terminen
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Tiempo total de ejecución: " + (endTime - startTime) + " ms");
	}
}
