package ud1Procesos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Lanzador {

	public static void main(String[] args) {
		Lanzador l = new Lanzador();
		l.lanzarSumador(1, 5, "result1.txt");
		l.lanzarSumador(6, 10, "result2.txt");
		System.out.println("Proceso completado con éxito.");
	}

	public void lanzarSumador(int n1, int n2, String archivoResultado) {
		if (n1 < 0 || n2 < 0 || archivoResultado == null || archivoResultado.isEmpty()) {
			String mensajeError = "Error: Los argumentos proporcionados no son válidos.";
			System.out.println(mensajeError);
			escribirErrorEnArchivo(mensajeError);
			return;
		}

		try {
			// Crear el comando para ejecutar Sumador con n1 y n2
			ProcessBuilder builder = new ProcessBuilder("java", "ud1Procesos.Sumador", String.valueOf(n1),
					String.valueOf(n2));

			// Iniciar el proceso
			Process proceso = builder.start();

			// Leer la salida del proceso (stdout)
			InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			StringBuilder resultado = new StringBuilder();
			String linea;
			while ((linea = reader.readLine()) != null) {
				resultado.append(linea).append("\n");
			}

			// Esperar a que el proceso termine
			proceso.waitFor();

			// Guardar el resultado en un archivo
			guardarResultadoEnArchivo(resultado.toString(), archivoResultado);

			System.out.println("Resultado guardado en: " + archivoResultado);

		} catch (IOException e) {
			String mensajeError = "Error de entrada/salida al lanzar el sumador o escribir en el archivo: "
					+ archivoResultado;
			System.out.println(mensajeError);
			escribirErrorEnArchivo(mensajeError + "\n" + e.getMessage());
		} catch (InterruptedException e) {
			String mensajeError = "El proceso fue interrumpido.";
			System.out.println(mensajeError);
			escribirErrorEnArchivo(mensajeError + "\n" + e.getMessage());
		} catch (Exception e) {
			String mensajeError = "Ha ocurrido un error inesperado.";
			System.out.println(mensajeError);
			escribirErrorEnArchivo(mensajeError + "\n" + e.getMessage());
		}
	}

	// Método para guardar el resultado en un archivo
	private void guardarResultadoEnArchivo(String resultado, String archivoResultado) {
		try (FileWriter writer = new FileWriter(new File(archivoResultado))) {
			writer.write(resultado);
		} catch (IOException e) {
			String mensajeError = "Error al escribir el archivo: " + archivoResultado;
			System.out.println(mensajeError);
			escribirErrorEnArchivo(mensajeError + "\n" + e.getMessage());
		}
	}

	// Método para escribir errores en un archivo de log
	private void escribirErrorEnArchivo(String mensajeError) {
		try (FileWriter writer = new FileWriter(new File("errores.txt"), true)) { // 'true' para agregar al final del
																					// archivo
			writer.write(mensajeError + "\n");
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo de errores: " + e.getMessage());
		}
	}
}
