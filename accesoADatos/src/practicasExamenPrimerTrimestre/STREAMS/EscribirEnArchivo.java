package practicasExamenPrimerTrimestre.STREAMS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class EscribirEnArchivo {

	
	
	public static void main(String[] args) {
		
		File archivo = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\prueba.txt");
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
			
			bw.write("Primera linea de texto");
			bw.newLine();
			bw.write("Segunda linea de texto");
			
		} catch (Exception e) {
			System.err.println("Ocurrio algun error al intentear escribir: " + e.getMessage());
		}
		
		
		
	}
	
	
	
}
