package ejerciciosHilosJava;

public class Ejercicio3NumerosPares extends Thread {

	
	private int suma=0;
	
	public int getSuma() {
		return this.suma;
	}
	
	@Override
	public void run() {
		
		System.out.println("***Lista de numeros pares*** ");
		for (int i = 2; i <= 10 ; i+=2) {
			System.out.print("Numeros pares: " + i + "\n");
			suma+= i;
			
			try {
				sleep(500);
			} catch (Exception e) {
				System.err.println("Ocurrio algun error en el hilo SUMAR PARES: " + e.getMessage());
			}
		}
		
		System.out.println("La suma total de los numeros pares es: " + suma + "\n");
	}
	
	
	
	
	
	
}
