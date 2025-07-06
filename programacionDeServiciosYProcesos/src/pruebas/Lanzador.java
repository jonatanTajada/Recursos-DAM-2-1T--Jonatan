package pruebas;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;

// Es una clase que lanza procesos independientes que ejecutan la clase Sumador. 
// Estos procesos se ejecutan varias veces con diferentes valores y guardan los resultados en archivos.


public class Lanzador {

    public void lanzarSumador(int n1, int n2, String archivoResultado) {
        try {
            // Comando para ejecutar el proceso Sumador con n1 y n2 como argumentos
            String comando = "java Sumador " + n1 + " " + n2;

            // Ejecutamos el comando
            Process proceso = Runtime.getRuntime().exec(comando);

            // Capturamos la salida del proceso (resultado de la suma)
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            StringBuilder resultado = new StringBuilder();

            // Leemos la salida del proceso
            while ((linea = lector.readLine()) != null) {
                resultado.append(linea).append("\n");
            }

            // Escribimos el resultado en un archivo
            FileWriter escritorArchivo = new FileWriter(archivoResultado);
            escritorArchivo.write(resultado.toString());
            escritorArchivo.close();

            System.out.println("Resultado guardado en: " + archivoResultado);

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso o guardar el resultado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Lanzador l = new Lanzador();
        l.lanzarSumador(1, 5, "../result1.txt");
        l.lanzarSumador(6, 10, "result2.txt");
        System.out.println("Ok");
    }
}
