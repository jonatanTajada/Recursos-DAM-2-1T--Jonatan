package pruebas;

/**
 * Ejecutar un proceso en un hilo Enunciado: Crea un hilo que abra el bloc de
 * notas (notepad.exe) en segundo plano utilizando ProcessBuilder.
 */

public class ProcesoEnHilo extends Thread {

	public static void main(String[] args) {
		
		ProcesoEnHilo hilo = new ProcesoEnHilo();
		hilo.start();
		

	}

	@Override
	public void run() {

		ProcessBuilder pb = new ProcessBuilder("notepad.exe");

		try {

			Process proceso = pb.start();
			System.out.println("Proceso abierto");
			proceso.waitFor();
			System.out.println("Proceso cerrado");

		} catch (Exception e) {
			System.err.println("Error en el proceso: " + e.getMessage());
		}

	}

}
