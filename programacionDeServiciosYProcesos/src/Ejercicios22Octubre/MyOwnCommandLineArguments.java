package Ejercicios22Octubre;

public class MyOwnCommandLineArguments {

	public static void main(String[] args) {

		//Reciba las edades de varias personas (3 a 6) como argumentos en la línea de comandos
		//en el formato que se muestra: (nombre y edad)
		if (args.length < 6 || args.length > 12 || args.length % 2 != 0) {
			System.out.println(
					"Por favor, siga el ejemplo: Monica 12 Daniel 34 Shelley 23. Minimo 3 personas, maximos 6");
			return;
		}

		int sumaEdades = 0;
		int cantidadPersonas = args.length / 2; // Cada par representa una persona

		// Recorrer los argumentos y sumar las edades
		for (int i = 1; i < args.length; i += 2) {
			try {
				int edad = Integer.parseInt(args[i]);
				sumaEdades += edad;
			} catch (NumberFormatException e) {
				System.out.println("Error: La edad de " + args[i - 1] + " no es un número válido.");
				return;
			}
		}

		// Calcular promedio de las edades 
		double promedio = (double) sumaEdades / cantidadPersonas;
		System.out.printf("El promedio de las edades es: %.2f%n", promedio);
	}
}
