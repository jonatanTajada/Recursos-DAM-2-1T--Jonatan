package pruebas;

import java.io.File;
import java.io.IOException;

public class RedirigirSalida {
	
    public static void main(String[] args) {
    	
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dir"); 
        // ProcessBuilder pb = new ProcessBuilder("ls"); 
        
        pb.redirectOutput(new File("salidaProceso.txt")); 

        try {
        	
            Process proceso = pb.start();
            System.out.println("El resultado del comando ha sido redirigido a salida.txt");
        } catch (IOException e) {
            System.err.println("Error al ejecutar el comando.");
            e.printStackTrace();
        }
    }
}

	
	
	
	
	
	
	

