package pruebasExamenPrimerTrim;

//Clase para imprimir números pares y calcular su suma
class HiloPares extends Thread {
 @Override
 public void run() {
     int suma = 0;
     System.out.println("Hilo de números pares:");
     for (int i = 1; i <= 10; i++) {
         if (i % 2 == 0) {
             System.out.println(i);
             suma += i;
         }
     }
     System.out.println("Suma de números pares: " + suma);
 }
}

//Clase para imprimir números impares
class HiloImpares extends Thread {
 @Override
 public void run() {
     int suma = 0;
     System.out.println("Hilo de números impares:");
     for (int i = 1; i <= 10; i++) {
         if (i % 2 != 0) {
             System.out.println(i);
             suma += i;
         }
     }
     System.out.println("Suma de números impares: " + suma);
 }
}

//Clase principal
public class NumerosConHilos {
 public static void main(String[] args) {
     // Crear dos instancias de hilos pares
     HiloPares hiloPares1 = new HiloPares();
     HiloPares hiloPares2 = new HiloPares();

     // Crear dos instancias de hilos impares
     HiloImpares hiloImpares1 = new HiloImpares();
     HiloImpares hiloImpares2 = new HiloImpares();

     // Iniciar los hilos
     hiloPares1.start();
     hiloPares2.start();
     hiloImpares1.start();
     hiloImpares2.start();

     // Esperar a que todos los hilos terminen
     try {
         hiloPares1.join();
         hiloPares2.join();
         hiloImpares1.join();
         hiloImpares2.join();
     } catch (InterruptedException e) {
         System.out.println("Error al esperar a los hilos: " + e.getMessage());
     }

     System.out.println("Todos los hilos han terminado.");
 }
}
