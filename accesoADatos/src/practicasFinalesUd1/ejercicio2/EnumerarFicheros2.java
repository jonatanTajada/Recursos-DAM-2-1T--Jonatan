package practicasFinalesUd1.ejercicio2;

import java.io.File;

// Ejercicio 2: Enumerar todos los ficheros y subdirectorios

public class EnumerarFicheros2 {
    public static void main(String[] args) {
    	
        File directorio = new File("C:\\Users\\Alumni\\Documents\\ficheros");

        if (directorio.exists() && directorio.isDirectory()) {
        	
            File[] listaFicheros = directorio.listFiles();
            if (listaFicheros != null) {
                for (File fichero : listaFicheros) {
                    System.out.println(fichero.getName() + (fichero.isDirectory() ? " (Directorio)" : " (Fichero)"));
                }
            }
        } else {
            System.out.println("El directorio no existe o no es un directorio.");
        }
    }
}
