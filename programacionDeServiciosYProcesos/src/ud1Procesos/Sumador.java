package ud1Procesos;

public class Sumador {

	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.out.println("Por favor, introduce dos números como argumentos.");
			return;
		}

		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);

		// Asegurarse de que n1 sea el menor y n2 sea el mayor
		if (n1 > n2) {
			int aux = n1;
			n1 = n2;
			n2 = aux;
		}

		int resultado = sumar(n1, n2);

		// Imprimir el resultado para que ProcessBuilder lo capture
		System.out.println("La suma de los números entre " + n1 + " y " + n2 + " es: " + resultado);
	}

	public static int sumar(int n1, int n2) {
		int suma = 0;
		for (int i = n1; i <= n2; i++) {
			suma += i;
		}
		return suma;
	}
}
