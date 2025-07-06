package pruebasExamenPrimerTrimestre;

import java.util.LinkedList;
import java.util.Queue;

class Almacen {
	
    private Queue<Integer> elementos = new LinkedList<>();
    
    private final int CAPACIDAD = 5;
    private boolean terminado = false; 

    
    // Método para agregar un elemento al almacén
    public synchronized void producir(int valor) throws InterruptedException {
    	
        while (elementos.size() == CAPACIDAD) {
            wait();
        }
        elementos.add(valor);
        System.out.println("Producido: " + valor);
        notifyAll();
    }

    // Método para consumir un elemento del almacén
    public synchronized int consumir() throws InterruptedException {
    	
        while (elementos.isEmpty() && !terminado) {
            wait();
        }
        
        if (elementos.isEmpty()) {
            return -1; // Indicador de fin de consumo
        }
        
        int valor = elementos.poll();
        System.out.println("Consumido: " + valor);
        notifyAll();
        return valor;
    }

    public synchronized void finalizarProduccion() {
        terminado = true;
        notifyAll(); // Notificar a los consumidores que la producción ha terminado
    }
}

//------------------------------------------------------

class Productor implements Runnable {
	
    private Almacen almacen;
    
    private int cantidadProduccion;

    public Productor(Almacen almacen, int cantidadProduccion) {
        this.almacen = almacen;
        this.cantidadProduccion = cantidadProduccion;
    }

    @Override
    public void run() {
    	
        try {
        	
            for (int i = 0; i < cantidadProduccion; i++) {
                almacen.producir(i);
                Thread.sleep(500); // Simula tiempo de producción
            }
            almacen.finalizarProduccion(); // Indicar que ya no se producirán más elementos
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumidor implements Runnable {
    private Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int valor = almacen.consumir();
                if (valor == -1) {
                    break; // Salir cuando ya no hay más elementos que consumir
                }
                Thread.sleep(1000); // Simula tiempo de consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

//----------------------------***MAIN***--------------------------------------------------------------------------
public class ProductorConsumidorTest {
	
    public static void main(String[] args) {
    	
        Almacen almacen = new Almacen();
        int cantidadProduccion = 10;

        Thread productor = new Thread(new Productor(almacen, cantidadProduccion));
        Thread consumidor = new Thread(new Consumidor(almacen));

        productor.start();
        consumidor.start();

        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Proceso completado.");
    }
}
