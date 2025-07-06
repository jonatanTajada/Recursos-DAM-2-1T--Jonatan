package pruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ejecutar múltiples procesos secuencialmente Enunciado: Crea dos procesos con
 * ProcessBuilder. Uno de ellos debe listar el contenido de un directorio y el
 * otro debe abrir el bloc de notas. Los procesos deben ejecutarse uno después
 * del otro.
 */

public class EjecutarProcesosSecuencialmente {

	public static void main(String[] args) throws InterruptedException {

		
		ProcessBuilder pbDirectorio = new ProcessBuilder("cmd.exe", "/c", "dir");
		ProcessBuilder pbNotepad = new ProcessBuilder("notepad.exe");

		try {
			Process procesoDirectio = pbDirectorio.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(procesoDirectio.getInputStream()));

			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}

			procesoDirectio.waitFor();

			Process procesoNotepad = pbNotepad.start();

		} catch (IOException e) {
			System.err.println("Error al ejecutarse...");
			e.printStackTrace();
		}

	}

}
