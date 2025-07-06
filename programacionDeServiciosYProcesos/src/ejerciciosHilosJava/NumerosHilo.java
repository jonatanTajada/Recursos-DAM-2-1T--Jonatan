package ejerciciosHilosJava;


class NumerosHilo extends Thread {
	
	public void run() {
		try {
			for (int i = 20; i <= 40; i++) {
				System.out.println(i);
				Thread.sleep(1500); 
			}
		} catch (InterruptedException e) {
			System.out.println("El hilo fue interrumpido.");
		}
	}

	public static void main(String[] args) {
		
		NumerosHilo hilo = new NumerosHilo();
		hilo.start(); 
	}
}
