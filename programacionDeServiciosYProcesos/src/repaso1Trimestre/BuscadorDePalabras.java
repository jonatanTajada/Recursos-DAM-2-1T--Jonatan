package repaso1Trimestre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuscadorDePalabras implements Runnable {

	private String rutaDirectorio;
	private String palabra;

	public BuscadorDePalabras(String rutaDirectorio, String palabra) {
		this.rutaDirectorio = rutaDirectorio;
		this.palabra = palabra;
	}

	
	@Override
	public void run() {
		
		File directorio = new File(rutaDirectorio);

		if (directorio.exists() && directorio.isDirectory()) {
			
			int contador = 0;

			File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(".txt"));
			
			if (archivos != null) {
				
				for (File archivo : archivos) {
					
					if (buscarPalabraEnArchivo(archivo)) {
						contador++;
					}
				}
				
				System.out.println("La palabra " + palabra + " se encontro en " + contador + " archivo/s en el hilo " + Thread.currentThread().getName());
				
			} else {
				System.out.println("No se encontraron archivos de texto en el directorio.");
			}
			
		} else {
			System.out.println("La ruta especificada no es un directorio valido.");
		}
	}

	
	//metodo buscar palabra en archivo
	private boolean buscarPalabraEnArchivo(File archivo) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				
				if (linea.contains(palabra)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo " + archivo.getName() + ": " + e.getMessage());
		}
		
		return false;
	}
}
