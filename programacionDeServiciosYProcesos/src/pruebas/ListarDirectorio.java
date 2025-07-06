package pruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListarDirectorio {
	
	public static void main(String[] args) {
		
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dir"); 
		// ProcessBuilder pb = new ProcessBuilder("ls"); 

		try {
			Process proceso = pb.start();

			// Leer la salida del proceso
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String linea;

			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			System.err.println("Error al ejecutar el comando.");
			e.printStackTrace();
		}
	}
}
