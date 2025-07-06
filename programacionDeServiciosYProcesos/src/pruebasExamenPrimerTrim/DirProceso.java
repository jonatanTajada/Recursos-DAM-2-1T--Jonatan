package pruebasExamenPrimerTrim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DirProceso {
    public static void main(String[] args) {
        try {
           
            String ruta = "C:\\Users";
            
            // Crear el proceso con el comando dir
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir", ruta);
            pb.redirectErrorStream(true); 
            Process proceso = pb.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;

            System.out.println("Salida del comando:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que termine el proceso y obtener el código de salida
            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con el código: " + exitCode);

        } catch (Exception e) {
            System.out.println("Error al ejecutar el proceso: " + e.getMessage());
        }
    }
}

