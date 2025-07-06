package examenProcesosHilosPrimerTrimestreJonatanTajada;

public class CarreraGeneralizada {

    public static void main(String[] args) {
    	
        for (int carrera = 1; carrera <= 50; carrera++) {
            System.out.println("=== Carrera #" + carrera + " ===");

            Thread[] corredores = new Thread[8];
            
            for (int i = 0; i < corredores.length; i++) {
            	
                final int numeroCorredor = i + 1; // Para usar en el hilo
                
                corredores[i] = new Thread(() -> {
                	
                    for (int j = 0; j <= 1500; j++) {
                    	
                        try {
                        	
                            Thread.sleep(5); 
                        } catch (InterruptedException e) {
                        	
                            e.printStackTrace();
                        }
                    }
                    System.out.println("El corredor " + numeroCorredor + " ha finalizado la prueba.");
                });
                corredores[i].start();
            }

            // Esperar a que todos los hilos terminen antes de iniciar la siguiente carrera
            for (Thread corredor : corredores) {
                try {
                    corredor.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

