package ejerciciosHilosJava;

import java.util.Scanner;

public class Ejercicio4Trabajador extends Thread {

	private String nombre;
	private String dia;
	private String entradaTrabajador;
	private static final String HORA_ENTRADA = "08:00";

	
	public Ejercicio4Trabajador(String nombre, String dia, String entradaTrabajador) {
		this.nombre = nombre;
		this.dia = dia;
		this.entradaTrabajador = entradaTrabajador;
	}

//----------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("¿Cuantos trabajadores deseas ingresar?: ");
		int cantidadTrabajadores = Integer.parseInt(scanner.nextLine());

		// Recogemos los datos de los trabajadores
		Ejercicio4Trabajador[] trabajadores = recogerDatosTrabajadores(cantidadTrabajadores, scanner);

		// Ejecutamos los hilos
		ejecutarTrabajadores(trabajadores);

		scanner.close();
	}
	
//--------------------------------------------------------------------------------------------------------------- 
	
	// metodo para recoger los datos de los trabajadores
	private static Ejercicio4Trabajador[] recogerDatosTrabajadores(int cantidadTrabajadores, Scanner scanner) {
		
		Ejercicio4Trabajador[] trabajadores = new Ejercicio4Trabajador[cantidadTrabajadores];

		// Recolectar los datos de los trabajadores
		for (int i = 0; i < cantidadTrabajadores; i++) {
			System.out.println("\n*** Datos del trabajador #" + (i + 1) + " ***");
			System.out.print("Nombre: ");
			String nombre = scanner.nextLine();
			System.out.print("Día: ");
			String dia = scanner.nextLine();
			System.out.print("Hora de entrada (HH:mm): ");
			String entrada = scanner.nextLine();

			// Validar que la hora tenga el formato correcto
			while (!esHoraValida(entrada)) {
				System.out.println("Hora inválida. Por favor, introduce la hora en formato HH:mm.");
				System.out.print("Hora de entrada (HH:mm): ");
				entrada = scanner.nextLine();
			}

			// Almacenar el trabajador en el array
			trabajadores[i] = new Ejercicio4Trabajador(nombre, dia, entrada);
		}
		return trabajadores;
	}

	// metodo para ejecutar los hilos de los trabajadores
	private static void ejecutarTrabajadores(Ejercicio4Trabajador[] trabajadores) {
	
		for (Ejercicio4Trabajador trabajador : trabajadores) {
			trabajador.start();
		}

		// Esperar a que todos los hilos terminen
		for (Ejercicio4Trabajador trabajador : trabajadores) {
			try {
				trabajador.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		if (convertirHora(entradaTrabajador) <= convertirHora(HORA_ENTRADA)) {
			System.out.println(nombre + " llegó temprano el " + dia + " a las " + entradaTrabajador + ".");
		} else {
			System.out.println(nombre + " llegó tarde el " + dia + " a las " + entradaTrabajador + ".");
		}
	}

	// metodo para convertir la hora de "HH:mm" a entero para comparación
	private int convertirHora(String hora) {
		
		String[] partes = hora.split(":");
		return Integer.parseInt(partes[0]) * 100 + Integer.parseInt(partes[1]);
	}

	// metodo para validar si la hora está en el formato correcto "HH:mm"
	private static boolean esHoraValida(String hora) {
		return hora.matches("^([01]\\d|2[0-3]):([0-5]\\d)$");
	}
}
