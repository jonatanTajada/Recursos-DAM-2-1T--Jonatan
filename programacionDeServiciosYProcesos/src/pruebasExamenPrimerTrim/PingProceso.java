package pruebasExamenPrimerTrim;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PingProceso {

	
	
	public static void main(String[] args) {
		
		Scanner scanner =new Scanner(System.in);
		
		System.out.println("Introduce la direccion ip o dominio para hacer ping");
		String direccion = scanner.nextLine();
		
		
		
		try {
		//crear proceso
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ping", direccion);
		pb.redirectErrorStream(true);
		Process proceso = pb.start();
		
		
		//leer salida del proceso
		BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		String linea;
		
		System.out.println("\nResultado del comando `ping`:");
		while ((linea = br.readLine()) != null) {
			System.out.println(linea);	
		}
		
		
		//esperar a que el proceso termine y me de el codifo de salida
		int exitCode = proceso.waitFor();
		if (exitCode == 0) {
			System.out.println("El comando pig se ejecuto correctamente");
		}else {
			System.err.println("No se pudo ejecutar el comando pig correctamente");
		}
		
		
		}catch(Exception e) {
			System.err.println("Error al uintentar  ");
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
