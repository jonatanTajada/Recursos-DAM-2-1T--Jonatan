package pruebas;

import java.util.Random;

public class CarrerasMascotas extends Thread{
	
	Random random;
	
	private String nombre;
	private final int META = 30;	
	private int distanciaRecorrida;
	private boolean hayGanador = false;
	
	
	public CarrerasMascotas(String nombre) {
		this.nombre=nombre;
	}
	
	
	@Override
	public void run() {
		
		random = new Random();
		
		while (distanciaRecorrida<META && !hayGanador) {
			
			int avanzar = random.nextInt(3) + 1;
			distanciaRecorrida += avanzar;
			System.out.println("Distancia recorrida: " + distanciaRecorrida + " metros, por la mascota: " + nombre);
			
			try {
				Thread.sleep(random.nextInt(500) + 1000);
			} catch (Exception e) {
				System.err.println("Ocurrio un error en el hilo: " + nombre + "\n");
				e.printStackTrace();
			}
		}
		
		System.out.println("Carrera finaliza! - Enorabuena al campeon: " + nombre);
	}
	
	
	public static void main(String[] args) {
		
		CarrerasMascotas hiloPerro = new CarrerasMascotas("Perro");
		CarrerasMascotas hiloGato = new CarrerasMascotas("Gato");
		CarrerasMascotas hiloRaton = new CarrerasMascotas("Raton");
		
		hiloPerro.start();
		hiloGato.start();
		hiloRaton.start();
		
		try {
			hiloPerro.join();
			hiloGato.join();
			hiloRaton.join();
		
		} catch (InterruptedException e) {
			System.err.println("Ocurrio un error con alguno de los hilos: " + e.getMessage());
		}
		
		System.out.println("Carrera finalizada!");
		
	}

}
