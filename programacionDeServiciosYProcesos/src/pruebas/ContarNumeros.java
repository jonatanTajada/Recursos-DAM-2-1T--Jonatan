package pruebas;

/**
 * Contar del 1 al 10 con dos hilos
 * Enunciado: Crea dos hilos, uno de ellos cuenta los números pares del 1 al 10 y el otro cuenta los números impares del 1 al 10.
 * Ambos hilos se ejecutan en paralelo, cada uno con su propio ciclo.

 */

public class ContarNumeros extends Thread {
	
    private boolean esPar;

    public ContarNumeros(boolean esPar) {
        this.esPar = esPar;
    }

    @Override
    public void run() {
        if (esPar) {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("Par: " + i);
                try {
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 1; i <= 9; i += 2) {
                System.out.println("Impar: " + i);
                try {
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
    	
        ContarNumeros hiloPares = new ContarNumeros(true);
        ContarNumeros hiloImpares = new ContarNumeros(false);

        hiloPares.start();
        hiloImpares.start();
    }
}
