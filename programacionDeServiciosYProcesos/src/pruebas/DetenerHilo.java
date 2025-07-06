package pruebas;

/**
 * Crear y detener un hilo manualmente Enunciado: Crea un hilo que imprima un
 * mensaje indefinidamente, pero permite detenerlo desde el hilo principal
 * después de 5 segundos.
 */

public class DetenerHilo extends Thread {

	private boolean hiloCorriendo = true;

	@Override
	public void run() {

		while (hiloCorriendo) {
			System.out.println("El hilo esta corriendo...");

			try {

				sleep(1000);
			} catch (InterruptedException e) {

				System.err.println("Erroen en el hilo: " + e.getMessage());
			}
		}
		System.out.println("El hilo se ha detenido!!");

	}

	public void detenerHilo() {
		hiloCorriendo = false;
	}
//-------------------------------------------------------
	
	public static void main(String[] args) {

		DetenerHilo hilo = new DetenerHilo();

		hilo.start();

		try {
			sleep(500);
		} catch (Exception e) {
			System.err.println("Error al iniciar el hilo");
		}

		hilo.detenerHilo();

	}

}
