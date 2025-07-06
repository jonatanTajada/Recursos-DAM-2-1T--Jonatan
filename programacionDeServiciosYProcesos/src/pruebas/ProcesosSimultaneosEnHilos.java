package pruebas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Ejecutar dos procesos simultáneamente en hilos Enunciado: Crea dos hilos que
 * ejecuten simultáneamente el bloc de notas y el comando dir para listar los
 * archivos de un directorio.
 */

public class ProcesosSimultaneosEnHilos {

	public static void main(String[] args) {

		// Primer hilo para ejecutar el bloc de notas
		Thread hiloNotepad = new Thread(() -> {

			ProcessBuilder pbNotepad = new ProcessBuilder("notepad.exe");

			try {
				Process proceso = pbNotepad.start();
				System.out.println("Bloc de notas abierto.");
				proceso.waitFor();
				System.out.println("Bloc de notas cerrado.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// segundo hilo para ejecutar el comando dir
		Thread hiloDir = new Thread(() -> {

			ProcessBuilder pbDir = new ProcessBuilder("cmd.exe", "/c", "dir");

			try {

				Process procesoDir = pbDir.start();

				BufferedReader br = new BufferedReader(new InputStreamReader(procesoDir.getInputStream()));
				String linea;
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
				procesoDir.waitFor();
				System.out.println("Comando 'dir' ejecutado.");
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		});

		hiloNotepad.start();
		hiloDir.start();
	}

}
