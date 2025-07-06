package practicasExamenPrimerTrimestre.STREAMS;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileRWPersonas {
	
    public static void main(String[] args) {
    	
        String archivo = "registro.dat";

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
        	
           
            escribirPersona(raf, 1, "Ana");
            escribirPersona(raf, 2, "Luis");
            escribirPersona(raf, 3, "Maria");

            System.out.println("Datos iniciales:");
            leerPersonas(raf);

           
            modificarNombrePersona(raf, 2, "Carlos");

            System.out.println("\nDatos después de la modificación:");
            leerPersonas(raf);
            
        } catch (IOException e) {
        	
            System.out.println("Ocurrió un error en la manipulación del archivo.");
            e.printStackTrace();
        }
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------    

  
    private static void escribirPersona(RandomAccessFile raf, int id, String nombre) throws IOException {
    	
        raf.writeInt(id); 
        raf.writeUTF(fixedLengthString(nombre, 10)); 
    }

   
    private static void leerPersonas(RandomAccessFile raf) throws IOException {
    	
        raf.seek(0); 
        
        while (raf.getFilePointer() < raf.length()) {
        	
            int id = raf.readInt();
            String nombre = raf.readUTF().trim();
            System.out.println("ID: " + id + ", Nombre: " + nombre);
        }
    }

   
    private static void modificarNombrePersona(RandomAccessFile raf, int idObjetivo, String nuevoNombre) throws IOException {
    	
        raf.seek(0); 
        while (raf.getFilePointer() < raf.length()) {
            int id = raf.readInt();
            if (id == idObjetivo) {
                raf.writeUTF(fixedLengthString(nuevoNombre, 10)); 
                break;
            } else {
                raf.skipBytes(12); // Saltar el nombre (10 caracteres + 2 bytes para UTF)
            }
        }
    }

   
    private static String fixedLengthString(String texto, int longitud) {
    	
        if (texto.length() > longitud) {
            return texto.substring(0, longitud); // Cortar si es demasiado largo
        } else {
            return String.format("%-" + longitud + "s", texto); // Añadir espacios si es más corto
        }
    }
}
