package Ejercicios22Octubr;

class IncrementarHiloSincronizado extends Thread {
	
    public static int contador = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementar();
        }
    }

    // metodo sincronizado para incrementar la variable
    private synchronized static void incrementar() {
        contador++;
    }

    public static void main(String[] args) {
    	
        IncrementarHiloSincronizado[] hilos = new IncrementarHiloSincronizado[5];

        // Lanzar 5 hilos
        for (int i = 0; i < 5; i++) {
            hilos[i] = new IncrementarHiloSincronizado();
            hilos[i].start();
        }

        // Esperar que todos los hilos terminen
        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el resultado final
        System.out.println("Valor final del contador sincronizado: " + contador);
    }
}

