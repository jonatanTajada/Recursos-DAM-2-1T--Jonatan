package practicasFinalesUd1.ejercicio2;


import java.io.File;

// Ejercicio 1: Mostrar información de un fichero o directorio

public class MostrarInformacion1 {
	
	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\Jonathan\\Desktop\\Libros de Programacion");

		if (file.exists()) {
			System.out.println("Nombre: " + file.getName());
			System.out.println("Ruta relativa: " + file.getPath());
            System.out.println("Ruta absoluta: " + file.getAbsolutePath());
			System.out.println("Permite lectura: " + file.canRead());
			System.out.println("Permite escritura: " + file.canWrite());
			System.out.println("Tamaño: " + file.length() + " bytes");
		} else {
			System.out.println("El fichero o directorio no existe.");
		}
		
	}
}
