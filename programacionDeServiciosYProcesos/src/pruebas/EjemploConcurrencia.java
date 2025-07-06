package pruebas;

public class EjemploConcurrencia {
	public static void main(String[] args) {
		
		// Crear el primer hilo
		Thread hilo1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					System.out.println("Hilo 1 - Tarea " + i);
					try {
						Thread.sleep(500); // Pausar por 500 ms
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Crear el segundo hilo
		Thread hilo2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (char c = 'A'; c <= 'Z'; c++) {
					System.out.println("Hilo 2 - Tarea " + c);
					try {
						Thread.sleep(500); // Pausar por 500 ms
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Iniciar los hilos
		hilo1.start();
		hilo2.start();
	}
}
