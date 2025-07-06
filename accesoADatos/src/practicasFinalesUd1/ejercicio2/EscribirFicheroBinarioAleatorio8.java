package practicasFinalesUd1.ejercicio2;


import java.io.IOException;
import java.io.RandomAccessFile;

// Ejercicio 8: Escribir un fichero binario alumnos (.dat) de forma aleatoria

public class EscribirFicheroBinarioAleatorio8 {
	
    public static void main(String[] args) {
        String[] apellidos = {"FERNANDEZ", "LOPEZ", "GOMEZ", "CHEN", "SERRANO", "CASILLAS", "ALONSO"};
        int[] edades = {17, 20, 18, 17, 19, 21, 20};
        double[] notas = {7.5, 4.2, 6.5, 8.0, 3.2, 9.2, 9.9};

        try (RandomAccessFile raf = new RandomAccessFile("alumnos.dat", "rw")) {
            for (int i = 0; i < apellidos.length; i++) {
                raf.writeUTF(apellidos[i]);
                raf.writeInt(edades[i]);
                raf.writeDouble(notas[i]);
            }
            System.out.println("Fichero binario random alumnos.dat creado con exito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
