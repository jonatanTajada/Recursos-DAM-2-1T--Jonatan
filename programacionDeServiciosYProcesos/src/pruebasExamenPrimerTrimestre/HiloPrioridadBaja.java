package pruebasExamenPrimerTrimestre;

public class HiloPrioridadBaja implements Runnable{

	
	@Override
	public void run() {
		
		for (int i = 0; i < 5; i++) {
			System.out.println("Hilo de PRIORIDAD__BAJA");
		}
		
		try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            System.out.println("El hilo de baja prioridad fue interrumpido.");
        }
		
	}
	
	
	
}
