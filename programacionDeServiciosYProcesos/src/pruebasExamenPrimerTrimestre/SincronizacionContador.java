package pruebasExamenPrimerTrimestre;

class Contador {

	private int contador = 0;

	public synchronized void incrementar() {
		contador++;
	}

	public int getValor() {
		return contador;
	}

}

//-----------------------------------------------------------------------

class HiloIncrementador implements Runnable {

	private Contador contador;
	
	public HiloIncrementador(Contador contador) {
        this.contador = contador;
    }

	@Override
	public void run() {

		for (int i = 0; i < 1000; i++) {
			contador.incrementar();
		}

	}

}

//----------------------------***MAIN***----------------------------------------------------------

public class SincronizacionContador {
	
	public static void main(String[] args) {
		
		Contador contador = new Contador();
		
		// Crear e iniciar 5 hilos que incrementen el contador
		Thread[] hilos = new Thread[5];
		
		for (int i = 0; i < 5; i++) {
			
            hilos[i] = new Thread(new HiloIncrementador(contador));
            hilos[i].start();
        }
		
		// Esperar a que todos los hilos terminen
		for(int i = 0; i <5;i++) {
			
			try {
				hilos[i].join(); //espera al que hilo termine
			} catch (Exception e) {
				System.err.println("Hilo interrrumpido");
			}
		}
		
		System.out.println("Valor final del contador: " + contador.getValor());
	}
	
	
}

