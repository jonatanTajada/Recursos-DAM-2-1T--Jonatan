package pruebasExamenPrimerTrimestre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class InteractuarConProceso {

	public static void main(String[] args) {

		String[] comando = { "cmd", "/c", "findstr Java" };

		ProcessBuilder pb = new ProcessBuilder(comando);

		try {

			Process proceso = pb.start();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));

			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

			//enviar lineas de texto al proceso para buscar la palabra clave -java-
			pw.println("Aprendiendo Java con ejercicios practicos");
			pw.println("Este texto nociente la palabra clave");
			pw.println("Java es un lenguaje de programación popular.");
			pw.println("Terminamos con el proceso de búsqueda.");
			pw.close();
			
			
			//leer y mostrar la salida del proceso, palabras que contenga Java.
			String linea;
			
			System.out.println("Resultados de la busqueda: ");
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
			int codigoSalida = proceso.waitFor();
			System.out.println("El proceso termino con el codigo salida: " + codigoSalida);

			
		} catch (IOException | InterruptedException e) {
			System.err.println("Ocurrio algun error en la ejecucion: " + e.getMessage());
		}

	}

}
