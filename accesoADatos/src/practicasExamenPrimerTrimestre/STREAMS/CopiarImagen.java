package practicasExamenPrimerTrimestre.STREAMS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiarImagen {
	
	
	public static void main(String[] args) {
		
		
		String archivoOrigen = "java.jpg";
		String archivoDestino = "imagen_copia.jpg";

		
		try (FileInputStream fis = new FileInputStream(archivoOrigen);
				FileOutputStream fos = new FileOutputStream(archivoDestino)) {

			byte[] buffer = new byte[1024]; 
			int bytesLeidos;

			
			while ((bytesLeidos = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesLeidos); 
			}
			System.out.println("Imagen copiada exitosamente a " + archivoDestino);
		} catch (IOException e) {
			System.out.println("Ocurrió un error durante la copia del archivo.");
			e.printStackTrace();
		}
	}
}
