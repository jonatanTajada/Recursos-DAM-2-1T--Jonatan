package pruebasExamenPrimerTrimestre;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EjecucionProcesoWindows {

	public static void main(String[] args) {

		String[] comandos = { "cmd", "/c", "echo Hola, este es un proceso externo" };

		ProcessBuilder pb = new ProcessBuilder(comandos);

		try {
			System.out.println("Iniciando proceso");
			Process proceso = pb.start();
			
			//capturar la salida del proceso
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String linea;
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
			int codigoSalida = proceso.waitFor();
			System.out.println("El proceso termino con el codigo de salida: " + codigoSalida);
			
			
		} catch (Exception e) {
			System.err.println("Ocurrio algun error: " + e.getMessage());
		}

	}

}
