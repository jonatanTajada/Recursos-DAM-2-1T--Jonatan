package examenProcesosHilosPrimerTrimestreJonatanTajada;

public class HiloLetrasNumeros implements Runnable {
    private int tipo;

    public HiloLetrasNumeros(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
    	
        if (tipo == 1) {
        	
            for (char letra = 'a'; letra <= 'z'; letra++) {
                System.out.print(letra + " ");
            }
            
        } else if (tipo == 2) {
            for (int numero = 1; numero <= 30; numero++) {
                System.out.print(numero + " ");
            }
        }
        
        System.out.println(); 
    }

    public static void main(String[] args) {
    	
        Thread hilo1 = new Thread(new HiloLetrasNumeros(1));
        Thread hilo2 = new Thread(new HiloLetrasNumeros(2));

        hilo1.start();
        hilo2.start();
    }
}

