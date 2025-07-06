package Ejercicios22Octubr;

public class MyOwnCommandLineArguments {

	public static void main(String[] args) {
		
		
		// Validar que se reciban al menos 6 argumentos (3 nombres y 3 edades) y como
		// máximo 12 argumentos (6 personas)
		if (args.length < 6 || args.length > 12 || args.length % 2 != 0) {
			System.out.println(
					"Por favor, ingresa los nombres y las edades en pares. Ejemplo: Monica 12 Daniel 34 Shelley 23");
			return;
		}

		int totalEdades = 0;
		int cantidadPersonas = 0;

		// Iterar sobre los argumentos en pares (nombre, edad)
		for (int i = 0; i < args.length; i += 2) {
			
			String nombre = args[i]; // El nombre está en los índices pares (0, 2, 4...)
			int edad;

			// Convertir la edad (en índices impares) a entero
			try {
				edad = Integer.parseInt(args[i + 1]);
			} catch (NumberFormatException e) {
				System.out.println("Error: La edad para " + nombre + " no es un numero valido.");
				return;
			}

			totalEdades += edad;
			cantidadPersonas++;
		}

		
		double promedio = (double) totalEdades / cantidadPersonas;

		// Mostrar el resultado
		System.out.println("El promedio de las edades es: " + promedio);
	}
}
