package pruebasExamenPrimerTrimestre;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProcesoConHilos {
	
	
	public static void main(String[] args) {
		
		
		String[] comando = { "cmd", "/c", "dir C:\\Windows" };
		
		ProcessBuilder processBuilder = new ProcessBuilder(comando);

		try {
			
			Process proceso = processBuilder.start();
			
			
			// Hilo para manejar la salida estándar
			Thread hiloSalida = new Thread(() -> {
				
				try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
					
					String linea;
					while ((linea = br.readLine()) != null) {
						System.out.println("Salida: " + linea);
					}
				} catch (IOException e) {
					System.out.println("Error al leer la salida: " + e.getMessage());
				}
			});

			
			// Hilo para manejar la salida de error
			Thread hiloError = new Thread(() -> {
				
				try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {
					
					String linea;
					while ((linea = br.readLine()) != null) {
						System.err.println("Error: " + linea);
					}
				} catch (IOException e) {
					System.out.println("Error al leer la salida de error: " + e.getMessage());
				}
			});
			

			hiloSalida.start();
			hiloError.start();

			
			int codigoSalida = proceso.waitFor();
			System.out.println("El proceso terminó con el código de salida: " + codigoSalida);

			// Esperar a que los hilos terminen
			hiloSalida.join();
			hiloError.join();

		} catch (IOException | InterruptedException e) {
			System.out.println("Ocurrió un error al ejecutar el proceso: " + e.getMessage());
		}
	}
}
