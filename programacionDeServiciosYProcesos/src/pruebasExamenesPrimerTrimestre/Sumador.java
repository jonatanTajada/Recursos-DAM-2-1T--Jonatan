package pruebasExamenesPrimerTrimestre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sumador {

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Por favor, introduce dos numero como argumentos.");
			return;
		}

		try {

			double n1 = Double.parseDouble(args[0]);
			double n2 = Double.parseDouble(args[1]);
			double resultado = n1 + n2;

			System.out.println("La suma de los numeros entre " + n1 + " y " + n2 + " es: " + resultado);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static double sumaNumeros(double num1, double num2) {

		double resultado = 0;

		for (double i = num1; i <= num2; i++) {
			resultado += i;

		}

		return resultado;
	}

}

// ---------------------------------*****CLASE LANZADORA **********--------------------------------------------------------------------------

class Lanzador {
	

	public void lanzarSumador(double n1, double n2, String archivo) {

		try {

			// Construir el comando para ejecutar el Sumador con los argumentos dados
			ProcessBuilder pb = new ProcessBuilder("java", "Sumador", String.valueOf(n1), String.valueOf(n2));
			pb.redirectErrorStream(true);
			Process proceso = pb.start();

			// Leer la salida del proceso Sumador
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

			String linea;

			while ((linea = br.readLine()) != null) {
				bw.write(linea);
				bw.newLine();
			}

			bw.close();
			br.close();

			proceso.wait();

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	public static void main(String[] args) {
		
		Lanzador l = new Lanzador();
		
		l.lanzarSumador(1, 5, "resultado1.txt");
		l.lanzarSumador(6, 10, "resultado2.txt");
		System.out.println("Ok");
	}

}
