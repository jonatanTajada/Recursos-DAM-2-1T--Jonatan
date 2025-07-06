package ficheros;

import java.io.File;
import java.io.IOException;

public class EjerciciosFile {
	
	
	 public static void main(String[] args) {
	        EjerciciosFile fm = new EjerciciosFile();

	        // Crear un directorio y un archivo
	        fm.crearArchivoODirectorio("miDirectorio", true);
	        fm.crearArchivoODirectorio("miDirectorio/miArchivo.txt", false);

	        // Renombrar un archivo o directorio
	        fm.renombrarArchivoODirectorio("miDirectorio", "miNuevoDirectorio");

	        // Listar archivos en un directorio
	        fm.listarArchivosEnDirectorio("miNuevoDirectorio");

	        // Obtener información de un archivo
	        fm.obtenerInformacion("miNuevoDirectorio/miArchivo.txt");

	        // Eliminar archivo
	        fm.eliminarArchivoODirectorio("miNuevoDirectorio/miArchivo.txt");
	    }
	
	//---------------------------------------------------------------------------------------

    // Metodo crear un archivo
    public void crearArchivoODirectorio(String ruta, boolean esDirectorio) {
        File file = new File(ruta);
        try {
            if (esDirectorio) {
                if (file.mkdir()) {
                    System.out.println("Directorio creado: " + ruta);
                } else {
                    System.out.println("El directorio ya existe o no se pudo crear.");
                }
            } else {
                if (file.createNewFile()) {
                    System.out.println("Archivo creado: " + ruta);
                } else {
                    System.out.println("El archivo ya existe o no se pudo crear.");
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrio un error al crear el archivo: " + e.getMessage());
        }
    }

    // Metodo renombrar 
    public void renombrarArchivoODirectorio(String rutaAntigua, String rutaNueva) {
        File fileAntiguo = new File(rutaAntigua);
        File fileNuevo = new File(rutaNueva);
        if (fileAntiguo.renameTo(fileNuevo)) {
            System.out.println("Renombrado con exito: " + rutaAntigua + " -> " + rutaNueva);
        } else {
            System.out.println("No se pudo renombrar el archivo o directorio.");
        }
    }

    // Metodo para listar los archivos y directorios dentro de un directorio
    public void listarArchivosEnDirectorio(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        if (directorio.isDirectory()) {
            String[] archivos = directorio.list();
            if (archivos != null) {
                for (String archivo : archivos) {
                    System.out.println(archivo);
                }
            } else {
                System.out.println("El directorio esta vacio o no se puede leer.");
            }
        } else {
            System.out.println(rutaDirectorio + " no es un directorio.");
        }
    }

    // Metodo para eliminar 
    public void eliminarArchivoODirectorio(String ruta) {
        File file = new File(ruta);
        if (file.delete()) {
            System.out.println("El archivo o directorio ha sido eliminado: " + ruta);
        } else {
            System.out.println("No se pudo eliminar el archivo o directorio.");
        }
    }

    // Metodo para obtener información sobre un archivo o directorio
    public void obtenerInformacion(String ruta) {
        File file = new File(ruta);
        if (file.exists()) {
            System.out.println("Nombre: " + file.getName());
            System.out.println("Ruta absoluta: " + file.getAbsolutePath());
            System.out.println("Es directorio: " + file.isDirectory());
            System.out.println("Es archivo: " + file.isFile());
            System.out.println("Ultima modificacion: " + file.lastModified());
        } else {
            System.out.println("El archivo o directorio no existe.");
        }
    }

   
}
