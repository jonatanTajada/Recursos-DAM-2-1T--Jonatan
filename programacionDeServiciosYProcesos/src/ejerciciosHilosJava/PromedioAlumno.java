package ejerciciosHilosJava;

import java.util.Scanner;

class PromedioAlumno extends Thread {

	Scanner scanner = new Scanner(System.in);

	public void run() {

		double total = 0;
		int cantidadNotas = 5;

		System.out.print("Introduce las 5 notas del alumno: \n");

		for (int i = 1; i <= cantidadNotas; i++) {
			System.out.print("Nota #" + i + ": ");
			double nota = Double.parseDouble(scanner.nextLine());
			total += nota;
		}

		double promedio = total / cantidadNotas;
		System.out.println("El promedio del alumno es: " + promedio);
	}

	public static void main(String[] args) {
		
		PromedioAlumno hiloPromedio = new PromedioAlumno();
		hiloPromedio.start();
	}
}
