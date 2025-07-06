package pruebasExamenPrimerTrim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class HiloLecturaProceso extends Thread {
	private BufferedReader reader;

	public HiloLecturaProceso(BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public void run() {
		
		try {
			
			String linea;
			while ((linea = reader.readLine()) != null) {
				
				System.out.println("Salida del proceso: " + linea);
			}
		} catch (Exception e) {
			System.out.println("Error al leer la salida del proceso: " + e.getMessage());
		}
	}
}


//-------------------------------------------------------------------------------------------------------------

public class ProcesosConHilos {

	public static void main(String[] args) {
	
		try {
			// Crear el proceso para listar archivos (dir en Windows, ls en Linux)
			String comando = System.getProperty("os.name").toLowerCase().contains("win") ? "cmd /c dir" : "ls";
			ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
			Process proceso = pb.start();

			// Crear un hilo para leer la salida del proceso
			BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			HiloLecturaProceso hiloLectura = new HiloLecturaProceso(reader);
			hiloLectura.start();

			// Esperar a que el proceso termine
			int exitCode = proceso.waitFor();
			hiloLectura.join(); // Esperar a que el hilo termine de leer la salida

			System.out.println("El proceso terminó con el código: " + exitCode);
		} catch (Exception e) {
			System.out.println("Error al ejecutar el proceso: " + e.getMessage());
		}
	}
}
