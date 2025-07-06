package pruebasExamenPrimerTrimestre;



public class Mihilo implements Runnable{

	@Override
	public void run() {
		
		for (int i = 0; i <= 5; i++) {
			System.out.println("Saludo: (" + (i+1) + ")");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		}
		
	}

	
	public static void main(String[] args) {
		
		Mihilo tarea = new Mihilo();
		Thread miHilo = new Thread(tarea);
		miHilo.start();
		
	
		
		Mihilo tarea2 = new Mihilo();
		Thread miHilo2 = new Thread(tarea);
		miHilo2.start();
		
		
	}
	
	
}


