package pruebasExamenPrimerTrimestre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiProcesoConHilos {

	
	public static void main(String[] args) {
		
		String[][] comandos = {
				
				{"cmd", "/c", "dir C:\\Windows"},
				{"cmd", "/c", "dir C:\\Program Files"},
				{"cmd", "/c", "dir C:\\"},
		};
		
		
		//Lanzar un hilo para cada proceso
		for (int i = 0; i < comandos.length; i++) {
			
			final int procesoId = i + 1;
			String[] comando = comandos[i];
			
			//crear un hilo para cada comando
			new Thread(() -> ejecutarProceso(comando, procesoId)).start();
		}

	}

	private static void ejecutarProceso(String[] comando, int procesoId) {
		
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
			Process proceso = pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				System.out.println("Proceso: " + procesoId + " salida: " + linea);
			}
			System.out.println("-------------------------------------------------------------------------------");
			
			int codigoSalida = proceso.waitFor();
			
			System.out.println("El proceso con el id: " + procesoId + " termino con el codigo salida: " + codigoSalida);
			 
		} catch (IOException | InterruptedException e) {
			System.err.println("Error en el proceso " + procesoId + ": " + e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
}
