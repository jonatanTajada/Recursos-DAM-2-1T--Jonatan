package ejerciciosHilosJava;

public class Ejercicio3NumerosImpares extends Thread {

	private int suma = 0;

	public int getSuma() {
	return this.suma;
	}
	
	
	@Override
	public void run() {

		System.out.println("***Lista de numeros impares***");
		for (int i = 1; i <= 9; i += 2) {
			System.out.print("Numeros pares: " + i + "\n");
			suma += i;

			try {
				sleep(500);
			} catch (Exception e) {
				System.err.println("Ocurrio algun error en el hilo SUMAR IMPARES: " + e.getMessage());
			}
		}

		System.out.println("La suma total de los numeros impares es: " + suma + "\n");
	}

}
