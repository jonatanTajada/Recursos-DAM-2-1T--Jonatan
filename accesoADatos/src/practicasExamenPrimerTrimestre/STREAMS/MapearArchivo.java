package practicasExamenPrimerTrimestre.STREAMS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lea el archivo prueba.txt.
 * Cuente el número de líneas, palabras y caracteres en el archivo.
 * Muestre los resultados en consola.
 */


public class MapearArchivo {

	
	public static void main(String[] args) {
		
		int numPalabras = 0;
		int numLineas = 0;
		int numCaracteres = 0;
		
		
		try (BufferedReader br = new BufferedReader(new FileReader("prueba.txt"))){
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				numLineas++;
				numCaracteres+= linea.length();
				
				String[] palabras = linea.split("\\s+");
				numPalabras+= palabras.length;
			}
			
            System.out.println("Número de líneas: " + numLineas);
            System.out.println("Número de palabras: " + numPalabras);
            System.out.println("Número de caracteres: " + numCaracteres);
			
		} catch (IOException e) {
			System.err.println("Ocurrio algun eerror al entrar en la lectura: " + e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
