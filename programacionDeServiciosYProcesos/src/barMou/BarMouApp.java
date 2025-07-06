package barMou;


public class BarMouApp {
	
	public static void main(String[] args) {
		
		// Crear el camarero
		Camarero mou = new Camarero("Mou");

		// Crear clientes
		HiloCliente homer = new HiloCliente("Homer", mou);
		HiloCliente barney = new HiloCliente("Barney", mou);
		HiloCliente carl = new HiloCliente("Carl", mou);
		HiloCliente lenny = new HiloCliente("Lenny", mou);
		HiloCliente lurleen = new HiloCliente("Lurleen", mou);

		homer.start();
		barney.start();
		carl.start();
		lenny.start();
		lurleen.start();

		// Este es un bucle de espera para que el programa se mantenga ejecutando y permita ver la simulacion
		try {
			
			Thread.sleep(10000); // El bar funciona durante 10 segundos
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

		//detener cliente despues de cerrar el bar al de 10 segundos
		homer.detener();
		barney.detener();
		carl.detener();
		lenny.detener();
		lurleen.detener();

		System.out.println("***El bar esta cerrando. Gracias por su visita.***");
	}
}
