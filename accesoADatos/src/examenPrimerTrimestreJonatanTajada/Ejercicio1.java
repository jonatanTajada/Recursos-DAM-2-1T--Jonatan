package examenPrimerTrimestreJonatanTajada;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio de escritura en archivo de texto
 */

public class Ejercicio1 {

	public static void escribirNombres(List<String> nombres, String nombreArchivo) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

			for (String nombre : nombres) {
				writer.write(nombre);
				writer.newLine();
			}

			System.out.println("Nombres escritos correctamente en el archivo: " + nombreArchivo);

		} catch (IOException e) {

			System.err.println("Ocurrio un error al escribir en el archivo: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Lista de nombres de ejemplo
		List<String> nombresSeries = new ArrayList<>();

		nombresSeries.add("Stranger Things");
		nombresSeries.add("Outer Banks");
		nombresSeries.add("One Tree Hill");

		escribirNombres(nombresSeries, "series.txt");
	}
}
