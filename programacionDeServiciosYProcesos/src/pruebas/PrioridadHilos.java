package pruebas;

/**
 * Prioridad de hilos
	Enunciado: Crea tres hilos y asigna diferentes prioridades a cada uno de ellos (mínima, normal y máxima) y observa el resultado.
	
	Explicación:
	Asignamos diferentes prioridades a cada hilo usando setPriority().
	El hilo de prioridad alta tiene más probabilidades de ejecutarse antes que los de prioridad más baja, pero el resultado puede variar según el sistema operativo.
 */


public class PrioridadHilos extends Thread{

	
	public PrioridadHilos(String nombre) {
		super(nombre);
	}
	
	@Override
		public void run() {
			for (int i = 1; i <= 10; i++) {
				System.out.println(getName() + " en la iteracion : " + i);
				
				try {
					sleep(500);
				} catch (Exception e) {
					System.err.println("Error en la ejecuccion del hilo: " + super.getName());
				}
			}
		}
	
	
	public static void main(String[] args) {
		
		PrioridadHilos hilo1 = new PrioridadHilos("Hilo-1 - Baja Prioridad");
		PrioridadHilos hilo2 = new PrioridadHilos("Hilo-2 - Media Prioridad");
		PrioridadHilos hilo3 = new PrioridadHilos("Hilo-3 - Alta Prioridad");
		
		hilo1.setPriority(MIN_PRIORITY);
		hilo2.setPriority(NORM_PRIORITY);
		hilo3.setPriority(MAX_PRIORITY);
	
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
	}
	
	
	
	
	
	
	
	
}
