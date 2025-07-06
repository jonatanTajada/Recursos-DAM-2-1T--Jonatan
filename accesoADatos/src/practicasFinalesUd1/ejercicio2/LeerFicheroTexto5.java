package practicasFinalesUd1.ejercicio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Ejercicio 5: Visualizar el fichero de texto creado anteriormente

public class LeerFicheroTexto5 {
	
	public static void main(String[] args) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Alumni\\Documents\\notas.txt"))) {
			String linea;
			
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
