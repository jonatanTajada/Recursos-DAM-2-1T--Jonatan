package pruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Pasar argumentos a un proceso
 * Enunciado: Crea un proceso que ejecute una calculadora básica usando ProcessBuilder. Pasa como argumento los números y la operación a realizar.
 */

public class CalculadoraProceso {

	public static void main(String[] args) {
		
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c:", "echo", "3 + 4");
		
		
		try {
			
			Process proceso= pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println("Resultado: " + linea);
			}
			
		} catch (IOException e) {
			System.err.println("Error al ejecutar la calculadora");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
}
