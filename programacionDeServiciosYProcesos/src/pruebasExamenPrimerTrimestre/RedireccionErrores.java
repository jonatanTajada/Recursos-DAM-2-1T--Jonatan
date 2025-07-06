package pruebasExamenPrimerTrimestre;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class RedireccionErrores {
	
	
	public static void main(String[] args) {
		

		String[] comando = { "cmd", "/c", "dir C:\\directorio_que_no_existe" };

		ProcessBuilder processBuilder = new ProcessBuilder(comando);

		try {
			
			Process proceso = processBuilder.start();

			
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
			String linea;

			System.out.println("Salida de error del proceso:");
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea); 
			}

			
			int codigoSalida = proceso.waitFor();
			System.out.println("El proceso terminó con el código de salida: " + codigoSalida);

		} catch (IOException | InterruptedException e) {
			System.err.println("Ocurrió un error al ejecutar el proceso: " + e.getMessage());
		}
	}
}
