package examenProcesosHilosPrimerTrimestreJonatanTajada;

public class Corredor implements Runnable {
	
    private String nombre;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
    	
        for (int i = 0; i <= 1500; i++) {
        	
            
            try {
            	
                Thread.sleep(5); //Tiempo entre "metros recorridos"
            } catch (InterruptedException e) {
            	
                e.printStackTrace();
            }
        }
        System.out.println("El corredor " + nombre + " ha finalizado la prueba.");
    }

    public static void main(String[] args) {
    	
        Thread[] corredores = new Thread[8];

        for (int i = 0; i < corredores.length; i++) {
        	
            corredores[i] = new Thread(new Corredor("Corredor " + (i + 1)));
            corredores[i].start();
        }
    }
}

