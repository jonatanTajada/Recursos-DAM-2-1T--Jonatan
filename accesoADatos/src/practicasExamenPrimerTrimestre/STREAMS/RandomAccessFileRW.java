package practicasExamenPrimerTrimestre.STREAMS;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileRW {

	
	
	public static void main(String[] args) {
		
		String archivo = "datos.bin";
		
		try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")){
			
			raf.writeInt(100);
			raf.writeUTF("Hola Jonatan!");
			
			raf.seek(0);
			
			int numero = raf.readInt();
			String texto = raf.readUTF();
			
			System.out.println("Numero leido: " + numero);
			System.out.println("Texto leido: " + texto);
			
		} catch (IOException e) {
			System.err.println("Ocurrio algun error: " + e.getMessage());
		}
		
		
		
		
	}
	
	
	
	
	
	
}
