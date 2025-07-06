package ejerciciosHilosJava;

public class Ejercicio2Contador extends Thread {

    private int contador;
    private String nombreHilo;
    private int limite;

   
    public Ejercicio2Contador(String nombreHilo, int limite) {
        this.contador = 0;
        this.nombreHilo = nombreHilo;
        this.limite = limite;
    }

    @Override
    public void run() {
     
        while (contador <= limite) {
            System.out.println(nombreHilo + " - Contador: " + contador);
            contador++;

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
        System.out.println(nombreHilo + " ha llegado al limite del tiempo: " + limite + " segundos.");
    }

    
    public static void main(String[] args) {
   
    	
        Ejercicio2Contador hiloContador1 = new Ejercicio2Contador("Hilo1", 10);
        Ejercicio2Contador hiloContador2 = new Ejercicio2Contador("Hilo2", 5);
        Ejercicio2Contador hiloContador3 = new Ejercicio2Contador("Hilo3", 15);

   
        hiloContador1.start();
        hiloContador2.start();
        hiloContador3.start();
    }
}
