package cargarDirectorios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppDir {
	
    public static void main(String[] args) {
        try {
           
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir", "C:\\");///c: para que se cierre al finalizar y desìes esta /k: que se quede abierto
            builder.redirectErrorStream(true);
            Process proceso = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            int exitCode = proceso.waitFor();
            if (exitCode == 0) {
                System.out.println("\nProceso finalizado exitosamente.");
                
            } else {
                System.out.println("\nProceso finalizado con código: " + exitCode);
                System.out.println("Hubo un error al ejecutar el comando.");
            }
            
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());
        }
    }
}
