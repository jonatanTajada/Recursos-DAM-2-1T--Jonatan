package ud1Procesos;

import java.io.IOException;

public class PruebasMias {

   
	
	public static void main(String[] args) {
		
        try {
            // Abrir Notepad
            ProcessBuilder notepad = new ProcessBuilder("notepad.exe");
            Process p1 = notepad.start();
            System.out.println("Notepad abierto");

            // Abrir Calculadora
            ProcessBuilder calculadora = new ProcessBuilder("calc.exe");
            Process p2 = calculadora.start();
            System.out.println("Calculadora abierta");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	 
	 
	 
	 
		
}
	
	
	
	
	
	
	
	


