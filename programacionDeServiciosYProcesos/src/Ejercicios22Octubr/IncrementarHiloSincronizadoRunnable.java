package Ejercicios22Octubr;

class IncrementarHiloSincronizadoRunnable implements Runnable {
    public static int contador = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementar();
        }
    }

    private synchronized static void incrementar() {
        contador++;
    }

    public static void main(String[] args) {
        Thread[] hilos = new Thread[5];

        // Lanzar 5 hilos usando Runnable
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new IncrementarHiloSincronizadoRunnable());
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
        System.out.println("Valor final del contador sincronizado (Runnable): " + contador);
    }
}
