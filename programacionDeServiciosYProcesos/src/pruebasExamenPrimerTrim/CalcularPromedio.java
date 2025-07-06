package pruebasExamenPrimerTrim;

// Clase PromedioHilo que extiende Thread

import java.util.Scanner;

class PromedioHilo extends Thread {
    private double[] notas;
    private String nombreAlumno;

   
    public PromedioHilo(String nombreAlumno, double[] notas) {
        this.nombreAlumno = nombreAlumno;
        this.notas = notas;
    }

    @Override
    public void run() {
    	
        double suma = 0;
        
        for (double nota : notas) {
            suma += nota;
        }
        double promedio = suma / notas.length;

        // Mostrar el resultado
        System.out.println("El promedio del alumno " + nombreAlumno + " es: " + promedio);
    }
}

// Clase principal
public class CalcularPromedio {
	
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos del alumno
        System.out.println("Introduce el nombre del alumno:");
        String nombreAlumno = scanner.nextLine();

        // Pedir 5 notas
        double[] notas = new double[5];
        System.out.println("Introduce 5 notas del alumno:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Nota " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();
        }

        // Crear e iniciar el hilo para calcular el promedio
        PromedioHilo promedioHilo = new PromedioHilo(nombreAlumno, notas);
        promedioHilo.start();

        // Esperar a que el hilo termine
        try {
            promedioHilo.join();
        } catch (InterruptedException e) {
            System.out.println("Error al esperar el hilo: " + e.getMessage());
        }

        System.out.println("El cálculo del promedio ha finalizado.");
        scanner.close();
    }
}
