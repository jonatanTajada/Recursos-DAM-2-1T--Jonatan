package practicasExamenPrimerTrimestre.STREAMS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopiarContenidoDeUnArchivoAOtro {

	public static void main(String[] args) {

		File archivo = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\prueba.txt");
		File copiaArchivo = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\copiaPrueba.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(archivo));
				BufferedWriter bw = new BufferedWriter(new FileWriter(copiaArchivo))) {

			String linea;
			while ((linea = br.readLine()) != null) {
				bw.write(linea);
				bw.newLine();
			}

			System.out.println("Copia con exito!");

		} catch (IOException e) {
			System.out.println("Ocurrio un error durante la copia del archivo.");
			e.printStackTrace();
		}

	}

}
