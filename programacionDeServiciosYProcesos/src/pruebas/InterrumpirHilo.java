package pruebas;

/**
 * Interrupción de un hilo Enunciado: Crea un hilo que pueda ser interrumpido
 * desde el hilo principal y maneje la interrupción adecuadamente.
 */

public class InterrumpirHilo extends Thread {
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Hilo en la iteración: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("El hilo ha sido interrumpido.");
		}
	}

//-----------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		InterrumpirHilo hilo = new InterrumpirHilo();
		hilo.start();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		hilo.interrupt();
	}
}
