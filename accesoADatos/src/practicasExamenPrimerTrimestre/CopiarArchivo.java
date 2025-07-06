package practicasExamenPrimerTrimestre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CopiarArchivo{
	
	
	public static void main(String[] args) {
		
	
		String archivoOriginal = "original.txt";
		String archivoCopia = "copia.txt";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoOriginal))){
			bw.write("joantan");
			bw.newLine();
			bw.write("tajada");
			bw.newLine();
			
			System.out.println("Archivo original creado!");
			
		} catch (Exception e) {
			System.err.println("Error al crear archivo nuevo");
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCopia))){
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				bw.write(linea);
				bw.newLine();
			}
			
			System.out.println("El archivo a sido copiado correctamente: " + archivoCopia);
			
		} catch (Exception e) {
			System.err.println("error al copiar el archivo");
		}
	
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivoCopia))){
			
			System.out.println("Contenido del archivbo copia:");
			System.out.println("---------------------------------");
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
				
			}
			
			
			
		} catch (Exception e) {
			System.err.println("Error en la lectura del archivo coipia");
		}
		
		
	
	}	
	
}
