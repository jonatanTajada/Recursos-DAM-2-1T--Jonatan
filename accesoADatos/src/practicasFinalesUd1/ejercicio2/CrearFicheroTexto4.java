package practicasFinalesUd1.ejercicio2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Ejercicio 4: Crear un fichero de texto y escribir en él

public class CrearFicheroTexto4 {
	public static void main(String[] args) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("notas.txt"))) {
			writer.write("Mañana estudias DAO.");
			System.out.println("Fichero guardado con exito.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
