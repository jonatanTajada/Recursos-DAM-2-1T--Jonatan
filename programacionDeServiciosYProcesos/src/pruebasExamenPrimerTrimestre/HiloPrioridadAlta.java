package pruebasExamenPrimerTrimestre;

public class HiloPrioridadAlta implements Runnable {

	
	
	@Override
	public void run() {
		
		for(int i = 0; i <= 4; i++) {
			
			System.out.println("Hilo de PRIORIDAD_ALTA: " + (i+1));
			
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.err.println("El hilo de alta prioridad fue interrumpido");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}

