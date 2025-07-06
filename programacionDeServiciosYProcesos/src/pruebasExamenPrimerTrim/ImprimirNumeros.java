package pruebasExamenPrimerTrim;


class HiloNumeros extends Thread {
	
 @Override
 public void run() {
	 
	 
     System.out.println("Iniciando impresión de números del 20 al 40...");
     
     for (int i = 20; i <= 40; i++) {
    	 
         System.out.println(i);
         
         try {
         
             Thread.sleep(1500);
         } catch (InterruptedException e) {
        	 
             System.out.println("El hilo fue interrumpido.");
         }
     }
     System.out.println("Hilo terminado: Impresión completa.");
 }
}

//Clase principal
public class ImprimirNumeros {
	
	
 public static void main(String[] args) {
	 
	 

     HiloNumeros hiloNumeros = new HiloNumeros();

 
     hiloNumeros.start();

  
     try {
         hiloNumeros.join();
     } catch (InterruptedException e) {
    	 
         System.out.println("Error al esperar el hilo: " + e.getMessage());
     }

     System.out.println("Todos los números han sido impresos.");
 }
}
