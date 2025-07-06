package pruebas;

/**
 * Hilos que monitorean un proceso en segundo plano Enunciado: Crea un hilo que
 * abra el bloc de notas y un segundo hilo que monitoree el tiempo que el bloc
 * de notas permanece abierto.
 */

public class MonitorearProceso {

	public static void main(String[] args) {

		Thread hiloNotepad = new Thread(() -> {

			ProcessBuilder pbNotepad = new ProcessBuilder("notepad.exe");

			try {

				Process proceso = pbNotepad.start();
				System.out.println("Notepar abierto");
				proceso.waitFor();
				System.out.println("Notepad cerrado");

			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		// hilo monitoreo
		Thread hiloMonitoreo = new Thread(() -> {

			long tiempoInicio = System.currentTimeMillis();

			while (hiloNotepad.isAlive()) {

				try {

					Thread.sleep(1000);
					long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;
					System.out.println("El bloc de notas lleva abierto " + tiempoTranscurrido + " segundos.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		});
		
		hiloNotepad.start();
		hiloMonitoreo.start();

	}

}
