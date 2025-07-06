package pruebasExamenPrimerTrim;

class ContadorConSincronizacion implements Runnable {
	
	
    private static int contador = 0; // Variable compartida por todos los hilos
    private static final Object lock = new Object(); // Bloqueo para sincronización

    @Override
    public void run() {
    	
        for (int i = 0; i < 1000; i++) {
        	
            synchronized (lock) {
            	
                contador++; // Bloque sincronizado para proteger la variable
            }
        }
    }

    public static int getContador() {
        return contador;
    }
}

public class ConSincronizacion {
    public static void main(String[] args) {
        Thread[] hilos = new Thread[5];

        // Crear e iniciar los hilos
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new ContadorConSincronizacion());
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Error al esperar a los hilos: " + e.getMessage());
            }
        }

        // Imprimir el valor final del contador
        System.out.println("Valor final del contador (con sincronización): " + ContadorConSincronizacion.getContador());
    }
}
