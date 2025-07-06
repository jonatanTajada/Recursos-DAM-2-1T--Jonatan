package ficheros;

import java.io.File;
import java.io.IOException;

public class ManejoFicherosBloque1 {

	public static void main(String[] args) {

		// 1. Crear un directorio llamado "dam-accesodatos"
		File directorio = new File("src/dam-accesoDatosPruebaEjercicio");

		if (directorio.mkdir()) {
			System.out.println("Directorio 'acesso a datos' creado correctamente");
		} else {
			System.out.println("No se pudo crear directorio o ya existe");
		}

		// 2. Crear un fichero llamado "ejercicio1" dentro del directorio
		// "dam-accesodatos"
		File archivo1 = new File(directorio, "ejercicio1.txt");
		try {
			if (archivo1.createNewFile()) {
				System.out.println("Archivo 'ejercicio1' creado correctamente.");
			} else {
				System.out.println("No se pudo crear el archivo o ya existe");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 3. Mostrar por pantalla la longitud del fichero con nombre "ejercicio1"
		if (archivo1.exists()) {
			System.out.println("Longitud del archivo ejercicio1.txt es: " + archivo1.length() + " bytes.");
		} else {
			System.out.println("El archivo 'ejercicio1.txt' no existe");
		}

		// 4. Crear un fichero llamado "ejercicio2" dentro del directorio
		// "dam-accesodatos"
		File archivo2 = new File(directorio, "ejercicio2.txt");
		try {
			if (archivo2.createNewFile()) {
				System.out.println("Archivo 'ejercicio2' creado correctamente");
			} else {
				System.out.println("No se pudo crear el archivo o ya existe");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 5. Mostrar todos los ficheros del directorio "dam-accesodatos"
		String[] archivos = directorio.list();
		if (archivos != null && archivos.length > 0) {
			System.out.println("Archivos en el directorio 'dam-accesodatos:'");
			for (String archi : archivos) {
				System.out.println("-" + archi);
			}
		} else {
			System.out.println("El directorio 'dam-accesodatos' está vacío o no existe.");
		}

		// 6. Eliminar el fichero llamado "ejercicio1"
		if (archivo1.delete()) {
			System.out.println("Archivo 'ejercicio1.txt' eliminado correctamente");
		} else {
			System.out.println("No se pudo eliminar el archivo 'ejercicio1.txt'");
		}

		// 7. Mostrar todos los ficheros del directorio "dam-accesodatos"
		archivos = directorio.list();
		if (archivos != null && archivos.length > 0) {
			System.out.println("Archivos en el directorio 'dam-accesodatos' despues de eliminar 'ejercicio1':");
			for (String arch : archivos) {
				System.out.println("- " + arch);
			}
		} else {
			System.out.println("El directorio 'dam-accesodatos' esta vacio o no existe");
		}

		// 8. Eliminar nuevamente el fichero llamado "ejercicio1"
		if (archivo1.exists()) {
			if (archivo1.delete()) {
				System.out.println("El archivo 'ejercicio1.txt' se ha eliminado correctamente");
			} else {
				System.out.println("No se pudo eliminar el archivo 'ejercicio1.txt'. ");
			}
		} else {
			System.out.println("El archivo 'ejercicio1' no existe");
		}

		// 9. Borrar directorio sin archivos en su interior
		if (directorio.exists()) {
			if (directorio.delete()) {
				System.out.println("Se ha podido eliminar el directorio correctamente");
			} else {
				System.out.println("No se pudo eliminar el directorio o no existe");
			}
		} else {
			System.out.println("El directorio no existe");
		}

		// 9.1 Borrar directorio con archivos en su interior
		eliminarDirectorio(directorio);
	}
	
	public static void eliminarDirectorio(File directorio) {
		File[] archivos = directorio.listFiles();
		
		if (archivos != null) { 
			for (File archivo : archivos) {
				if (archivo.isDirectory()) {
					eliminarDirectorio(archivo); // Llamada recursiva
				} else {
					archivo.delete(); 
				}
			}
		}
		
		if (directorio.delete()) {
			System.out.println("Directorio '" + directorio.getName() + "' eliminado correctamente.");
		} else {
			System.out.println("No se pudo eliminar el directorio '" + directorio.getName() + "'.");
		}
	}
}