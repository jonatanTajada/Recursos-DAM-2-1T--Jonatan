package practicasFinalesUd1.ejercicio2;

import java.io.IOException;
import java.io.RandomAccessFile;

// Ejercicio 9: Visualizar el fichero binario alumnos (.dat) de forma aleatoria

public class LeerFicheroBinarioAleatorio9 {
	
    public static void main(String[] args) {
    	
        try (RandomAccessFile raf = new RandomAccessFile("alumnos.dat", "r")) {
        	
            while (raf.getFilePointer() < raf.length()) {
                String apellido = raf.readUTF();
                int edad = raf.readInt();
                double nota = raf.readDouble();
                System.out.println("Apellido: " + apellido + ", Edad: " + edad + ", Nota: " + nota);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

