package pruebas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccesoAFicheros_Buffered {

	
	public void escribirEnArchivo(String ruta, String contenido) {
		
		//1.Crear metodo escribir en archivo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(contenido);
            System.out.println("Contenido escrito en el archivo usando BufferedWriter.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
		
		//2.Crear metodo leer archivo
		public void leerArchivo(String ruta) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
	            String linea;
	            while ((linea = reader.readLine()) != null) {
	                System.out.println(linea);
	            }
	            System.out.println("Lectura completada con BufferedReader.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	
	public static void main(String[] args) {
		
		AccesoAFicheros_Buffered ejemplo = new AccesoAFicheros_Buffered();
		
		String ruta = "archivoTxt.txt";
		String mensaje= "Hola estoy escribiendo desde BW";
		ejemplo.escribirEnArchivo(ruta, mensaje);
		ejemplo.leerArchivo(ruta);
		
		
	}
	
	
	
	
	
	
}
