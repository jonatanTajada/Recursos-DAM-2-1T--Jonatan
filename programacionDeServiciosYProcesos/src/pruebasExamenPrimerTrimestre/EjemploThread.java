package pruebasExamenPrimerTrimestre;


class ContadorHilos extends Thread{
	
	private boolean ascendente;
	
	public ContadorHilos(boolean ascendente) {
		this.ascendente = ascendente;
	}
	
	@Override
	public void run() {
		
		if (ascendente) {
			
			for (int i = 1; i < 11; i++) {
				System.out.println("Contador ascendente: #" + i);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.println("Hilo ascendente interrumpido");
				}
			}
			
		}else {
			
			for (int i = 10; i >= 1; i--) {
				
				System.out.println("Contador descendiente: #" + i);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	
		
	}
	
}

//-----------------------------------------------------------------------------------



public class EjemploThread {

	
	public static void main(String[] args) {
		
		ContadorHilos hiloAscendente = new ContadorHilos(true);
		ContadorHilos hiloDescendente = new ContadorHilos(false);
		
		hiloAscendente.start();
		hiloDescendente.start();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
