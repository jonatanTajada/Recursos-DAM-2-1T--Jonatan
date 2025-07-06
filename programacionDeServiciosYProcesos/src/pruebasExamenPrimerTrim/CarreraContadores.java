package pruebasExamenPrimerTrim;

public class CarreraContadores {
	
	
	public static void main(String[] args) {
		
		Contador hilo1 = new Contador("Hilo 1", 0, 5);
		Contador hilo2 = new Contador("Hilo 2", 3, 7);
		Contador hilo3 = new Contador("Hilo 3", 1, 10);
		
		//inicializar los hilos
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		//esperar a que todos los hilos terminen
		try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
		} catch (Exception e) {
			System.err.println("Error al esperar que todos los hilos terminen");
		}
		
		System.out.println("Todos los contadores han terminado");
	}
	
	

}







//--------------------------------------------------------------------------------------------------------------
class Contador  extends Thread{
	
	private int contador;
	private int limite;
	private String nombreHilo;
	
	public Contador(String nombreHilo, int contadorInicial, int limite) {
        this.nombreHilo = nombreHilo;
        this.contador = contadorInicial;
        this.limite = limite;
    }
	
	@Override
	public void run() {

		while (contador < limite) {
			
			System.out.println(nombreHilo + " - Contador: " + contador);
			contador++;
			
			try {
				
				Thread.sleep(500);
				
			} catch (Exception e) {
				System.err.println(nombreHilo + " fue interrumpido");
			}
			
		}
		
		System.out.println(nombreHilo + " ha terminado.");
		
	}
	
	
}