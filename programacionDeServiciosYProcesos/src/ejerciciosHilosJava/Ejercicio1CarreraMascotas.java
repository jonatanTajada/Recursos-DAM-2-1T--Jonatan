package ejerciciosHilosJava;

import java.util.Random;

public class Ejercicio1CarreraMascotas extends Thread {

	Random random;

	private String nombre;
	private int distanciaRecorrida = 0;
	private final int META = 30;
	private static boolean hayGanador = false;

	public Ejercicio1CarreraMascotas(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		random = new Random();

		// La mascota avanza mientra no ha llegado a la meta y no haya ganador
		while (distanciaRecorrida < META && !hayGanador) {
			int avanzando = random.nextInt(3) + 1; 
			distanciaRecorrida += avanzando;

			System.out.println(nombre + ": ha recorrido  " + distanciaRecorrida + " metros.");

			try {
				Thread.sleep(random.nextInt(500) + 1000);
				
			} catch (InterruptedException e) {
				System.err.println("La mascota " + nombre + " ha sido interrumpida.");
			}

			// si la mascota llego primero a la meta
			if (distanciaRecorrida > META && !hayGanador) {
				hayGanador = true;
				System.out.println("\nCampeon/a  de la carrera es: " + nombre + "!");
			}

		}
	}
	
	
	
	
//--------------------------------------------------------------------------------------	
	public static void main(String[] args) {
		
		//cada mascota realmente es un hilo
		Ejercicio1CarreraMascotas mascota1 = new Ejercicio1CarreraMascotas("Neska");
		Ejercicio1CarreraMascotas mascota2 = new Ejercicio1CarreraMascotas("Sauron");
		Ejercicio1CarreraMascotas mascota3 = new Ejercicio1CarreraMascotas("Ojitos");
		
		//iniciar carrera
		mascota1.start();
		mascota2.start();
		mascota3.start();
		
		//esperar a que TODOS los hilos TERMINEN
		try {
			mascota1.join();
			mascota2.join();
			mascota3.join();
		
		} catch (InterruptedException e) {
			System.err.println("Ocurrio un error con alguno de los hilos: " + e.getMessage());
		}
		
		System.out.println("Carrera finalizada!");
	}

}
