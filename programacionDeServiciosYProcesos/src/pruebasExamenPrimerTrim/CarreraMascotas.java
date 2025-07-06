package pruebasExamenPrimerTrim;

import java.util.Scanner;

public class CarreraMascotas {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Introducir nombres de las mascotas
		System.out.println("Introduce el nombre de la primera mascota:");
		String mascota1 = scanner.nextLine();

		System.out.println("Introduce el nombre de la segunda mascota:");
		String mascota2 = scanner.nextLine();

		System.out.println("Introduce el nombre de la tercera mascota:");
		String mascota3 = scanner.nextLine();

		System.out.println("\n¡La carrera comienza!\n");

		// Crear los hilos para las mascotas
		Mascota hilo1 = new Mascota(mascota1);
		Mascota hilo2 = new Mascota(mascota2);
		Mascota hilo3 = new Mascota(mascota3);

		// Iniciar los hilos
		hilo1.start();
		hilo2.start();
		hilo3.start();

		// Esperar a que todos los hilos terminen
		try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
		} catch (InterruptedException e) {
			System.out.println("Error al esperar a los hilos: " + e.getMessage());
		}

		System.out.println("\n¡La carrera ha terminado!");
		System.out.println("El ganador es: " + Mascota.getGanador());
	}
}

//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------

//Clase Mascota que extiende Thread
class Mascota extends Thread {
	private String nombre;
	private static final int META = 30;
	private static String ganador = null;

	public Mascota(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		int distanciaRecorrida = 0;

		while (distanciaRecorrida < META) {
			// Avanzar una distancia aleatoria entre 1 y 3 metros
			int avance = (int) (Math.random() * 3) + 1;
			distanciaRecorrida += avance;

			System.out.println(nombre + " ha avanzado " + distanciaRecorrida + " metros.");

			// Simular tiempo de espera para que avance lentamente
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(nombre + " fue interrumpido.");
			}
		}

		// Verificar si es el ganador
		synchronized (Mascota.class) {
			if (ganador == null) {
				ganador = nombre;
			}
		}
	}

	// Método público para obtener el ganador
	public static String getGanador() {
		return ganador;
	}
}
