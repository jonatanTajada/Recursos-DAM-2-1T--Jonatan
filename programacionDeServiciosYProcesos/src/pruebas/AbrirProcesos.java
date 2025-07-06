package pruebas;

import java.io.IOException;

public class AbrirProcesos {
	
	public static void main(String[] args) {
		
		// Crear el proceso para abrir el Bloc de notas
		ProcessBuilder blocDeNotas = new ProcessBuilder("notepad.exe");
		
		// Crear el proceso para abrir Paint
		ProcessBuilder paint = new ProcessBuilder("mspaint.exe");

		try {
			// Iniciar el primer proceso (Bloc de notas)
			Process procesoBlocDeNotas = blocDeNotas.start();
			System.out.println("Bloc de notas abierto.");

			// Esperar a que el primer proceso termine
			procesoBlocDeNotas.waitFor();
			System.out.println("Bloc de notas cerrado.");

			// Iniciar el segundo proceso (Paint) después de cerrar el Bloc de notas
			Process procesoPaint = paint.start();
			System.out.println("Paint abierto.");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
