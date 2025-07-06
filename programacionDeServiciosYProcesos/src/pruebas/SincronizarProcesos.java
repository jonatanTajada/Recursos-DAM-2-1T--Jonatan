package pruebas;

import java.io.IOException;

/**
 * Ejecutar múltiples comandos y sincronizarlos Enunciado: Crea tres hilos, cada
 * uno de los cuales debe ejecutar un proceso diferente. Los hilos deben
 * sincronizarse para que uno no comience hasta que otro haya terminado.
 */

public class SincronizarProcesos {

    public static void main(String[] args) {

        // 1º hilo: ejecuta abrir Notepad
        Thread hiloNotepad = new Thread(() -> {

            ProcessBuilder pbNotepad = new ProcessBuilder("notepad.exe");

            try {
                Process proceso = pbNotepad.start();
                System.out.println("Notepad abierto");
                proceso.waitFor();
                System.out.println("Hilo Notepad finalizado");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        // 2º Hilo: para abrir el Explorador de Archivos de Windows
        Thread hiloExplorador = new Thread(() -> {
            ProcessBuilder pbExplorer = new ProcessBuilder("explorer.exe");

            try {
                Process proceso = pbExplorer.start();
                System.out.println("Explorador de archivos abierto.");
                proceso.waitFor();
                System.out.println("Explorador de archivos cerrado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 3º hilo: ejecutar un navegador web (chrome)
        Thread hiloChrome = new Thread(() -> {

            ProcessBuilder pbChrome = new ProcessBuilder("cmd.exe", "/c", "start chrome");
            try {
                Process proceso = pbChrome.start();
                System.out.println("El chrome abierto");
              //  proceso.waitFor();
                System.out.println("Chrome cerrado");
            } catch (IOException e2) {
                e2.printStackTrace();
            }

        });

        try {
            // Iniciar el Notepad
            hiloNotepad.start();
            hiloNotepad.join(); 

            // Iniciar el Explorador de Archivos
            hiloChrome.start(); 
            hiloChrome.join(); 

            // Iniciar Chrome
            hiloExplorador.start();

        } catch (Exception e3) {
            e3.printStackTrace();
        }

    }

}
