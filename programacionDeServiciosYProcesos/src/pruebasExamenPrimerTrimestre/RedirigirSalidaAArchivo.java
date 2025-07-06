package pruebasExamenPrimerTrimestre;

import java.io.File;
import java.io.IOException;

public class RedirigirSalidaAArchivo {
	
	
	public static void main(String[] args) {
		
		
		String[] comando = { "cmd", "/c", "dir C:\\Windows" };

		ProcessBuilder processBuilder = new ProcessBuilder(comando);

		
		File archivoSalida = new File("salida.txt");
		processBuilder.redirectOutput(archivoSalida); 

		try {
			
			Process proceso = processBuilder.start();

			
			int codigoSalida = proceso.waitFor();
			System.out.println("El proceso terminó con el código de salida: " + codigoSalida);

			if (codigoSalida == 0) {
				System.out.println("La salida del comando se ha guardado en " + archivoSalida.getAbsolutePath());
			} else {
				System.out.println("Hubo un error al ejecutar el comando.");
			}

		} catch (IOException | InterruptedException e) {
			System.out.println("Ocurrió un error al ejecutar el proceso: " + e.getMessage());
	}
	}
}
