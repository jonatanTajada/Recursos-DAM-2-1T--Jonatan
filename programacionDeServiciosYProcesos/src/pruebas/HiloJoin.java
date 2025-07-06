package pruebas;

/**
 *  Controlar la finalización de un hilo con join()
 *	Enunciado: Crea dos hilos. Uno debe esperar a que el otro termine antes de continuar usando join().
 */

public class HiloJoin extends Thread{

	
	public static void main(String[] args) {
		
		HiloJoin hilo1 = new HiloJoin("Hilo1");
		HiloJoin hilo2 = new HiloJoin("Hilo2");
		HiloJoin hilo3 = new HiloJoin("Hilo3");
		
		
		try {
			hilo1.start();
			hilo1.join();
			
			hilo2.start();
			hilo2.join();
			
			hilo3.start();
			hilo3.join();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
        }
		
	}
	
	private String nombre;
	
	public HiloJoin(String nombre) {
		this.nombre = nombre;
	}
	

	@Override
		public void run() {
		
			for (int i = 0; i <= 5; i++) {
				
				System.out.println("Hilo " + nombre + " en la iteracion: " + i);
			
				try {
					sleep(500);
				} catch (InterruptedException e) {
		            System.err.println("Error en el hilo: " + nombre);

				}
			
			}
			
			
			
			
		
		}
	
	
	
}
