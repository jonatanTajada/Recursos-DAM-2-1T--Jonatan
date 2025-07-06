package practicasExamenPrimerTrimestre.STREAMS;

import java.io.FileInputStream;
import java.io.IOException;

public class LecturaImagen {

	public static void main(String[] args) {

		String archivoOrigen = "java.jpg";
		int totalBytes = 0;

		// Usamos FileInputStream para leer el archivo en binario
		try (FileInputStream fis = new FileInputStream(archivoOrigen)) {

			byte buffer[] = new byte[1024];
			int byteLeidos;

			while ((byteLeidos = fis.read(buffer)) != -1) {
				totalBytes += byteLeidos;
			}

			System.out.println("Total de bytes leídos: " + totalBytes);
		} catch (IOException e) {
			System.out.println("Ocurrió un error al leer el archivo.");
			e.printStackTrace();
		}
	}
}
