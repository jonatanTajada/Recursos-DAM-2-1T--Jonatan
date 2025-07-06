package practicasExamenPrimerTrimestre.STREAMS;

import java.io.File;
import java.io.IOException;

public class CrearArchivo {
	
	public static void main(String[] args) {
		
		// Crear un objeto File para el archivo prueba.txt
		File archivo = new File("prueba.txt");

		try {
			// Intentar crear el archivo
			if (archivo.createNewFile()) {
				System.out.println("Archivo creado exitosamente: " + archivo.getName());
				System.out.println("Ruta: " + archivo.getAbsolutePath());
			} else {
				System.out.println("El archivo ya existía: " + archivo.getName());
				System.out.println("Ruta: " + archivo.getAbsolutePath());
			}
		} catch (IOException e) {
			System.out.println("Ocurrió un error al crear el archivo.");
			e.printStackTrace();
		}
	}
}
