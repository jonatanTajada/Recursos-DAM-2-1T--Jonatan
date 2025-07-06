package pruebas;
	/**
	 * Crear múltiples hilos
	 *	Enunciado: Crea tres hilos que impriman un mensaje con su nombre, cada uno con una pausa de medio segundo entre iteraciones.
	 */


	public class HiloMultiple extends Thread {
		
		
		
		private String nombre;
	
		public HiloMultiple(String nombre) {
			this.nombre = nombre;
		}
	
		@Override
		public void run() {
			for (int i = 1; i <= 5; i++) {
				System.out.println("Hilo " + nombre + " está corriendo... Iteración: " + i);
				try {
					Thread.sleep(500); // Pausa de 0.5 segundos
				} catch (InterruptedException e) {
					System.out.println("Hilo " + nombre + " interrumpido.");
				}
			}
		}

		//-----------------------
		public static void main(String[] args) {
			HiloMultiple hilo1 = new HiloMultiple("Hilo1");
			HiloMultiple hilo2 = new HiloMultiple("Hilo2");
			HiloMultiple hilo3 = new HiloMultiple("Hilo3");
	
			hilo1.start();
			hilo2.start();
			hilo3.start();
		}
	}
