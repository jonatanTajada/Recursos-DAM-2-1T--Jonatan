package pruebas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Procesos secuenciales en hilos
 * Enunciado: Crea dos hilos que ejecuten procesos, pero el segundo hilo no debe empezar hasta que el primer proceso haya terminado.
 */

public class ProcesosSecuencialesEnHilos {

    public static void main(String[] args) throws InterruptedException {
    	
        Thread hiloNotepad = new Thread(() -> {
            ProcessBuilder pbNotepad = new ProcessBuilder("notepad.exe");
            try {
                Process proceso = pbNotepad.start();
                System.out.println("Bloc de notas abierto.");
                proceso.waitFor();
                System.out.println("Bloc de notas cerrado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread hiloDir = new Thread(() -> {
            ProcessBuilder pbDir = new ProcessBuilder("cmd.exe", "/c", "dir");
            try {
                Process proceso = pbDir.start();
                BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
                proceso.waitFor();
                System.out.println("Comando 'dir' ejecutado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        
        hiloNotepad.start();
        hiloNotepad.join(); 
       
        hiloDir.start();
    }
}

	
	
	
	
	
	

