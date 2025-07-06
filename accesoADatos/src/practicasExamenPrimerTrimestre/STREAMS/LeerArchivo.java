package practicasExamenPrimerTrimestre.STREAMS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo {

	
	public static void main(String[] args) {
		
		File archivo = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\prueba.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
			
		} catch (IOException e) {
			System.err.println("Ocurrio algun error al intentar leer el archivo: " + e.getMessage());
		}
		
		
	}
	
	
}
