package ejerciciosHilosJava;

public class Ejercicio3Main {

	public static void main(String[] args) {

		Ejercicio3NumerosPares hiloPares1 = new Ejercicio3NumerosPares();
		Ejercicio3NumerosPares hiloPares2 = new Ejercicio3NumerosPares();

		
		Ejercicio3NumerosImpares hiloImpares1 = new Ejercicio3NumerosImpares();
		Ejercicio3NumerosImpares hiloImpares2 = new Ejercicio3NumerosImpares();

		try {
			
			hiloPares1.start();
			hiloPares1.join();

			hiloPares2.start();
			hiloPares2.join(); 

		
			hiloImpares1.start();
			hiloImpares1.join(); 

			hiloImpares2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
