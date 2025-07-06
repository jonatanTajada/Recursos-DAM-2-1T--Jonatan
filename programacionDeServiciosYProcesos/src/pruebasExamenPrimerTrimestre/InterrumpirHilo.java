package pruebasExamenPrimerTrimestre;

class TareaDeLargaDuracion extends Thread {

	@Override
	public void run() {

		try {

			int i = 1;

			while (!isInterrupted()) {
				System.out.println("Procesando...Iteracion: " + i);
				Thread.sleep(1000);
				i++;
			}

		} catch (InterruptedException e) {
			System.err.println("Tarea interrumpida. Detenido el hilo de procesamiento");
		}




	}

}


//--------------------***MAIN**-----------------------------------------------------------------------------

public class InterrumpirHilo {

	public static void main(String[] args) {
		
		TareaDeLargaDuracion hilo = new TareaDeLargaDuracion();
		hilo.start();
		
		try {
			
			Thread.sleep(5000);// Esperar 5 segundos antes de interrumpir
		} catch (InterruptedException e) {
			
			System.err.println("Ocurrio algun error con el hilo: " + e.getMessage());
		}
		
		System.out.println("Interrumpiendo el hilo de procesamiento...");
		hilo.interrupt();
		
	}
	
	
	
	
	
	
}
