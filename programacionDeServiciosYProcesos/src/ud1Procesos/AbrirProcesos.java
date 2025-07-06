package ud1Procesos;

public class AbrirProcesos {

    public static void main(String[] args) {
    	
        // Llamada al metodo con los procesos que deseas abrir
        String[] procesos = { "notepad.exe", "mspaint.exe" }; 
        abrirprocesos(procesos);
    }
    
    //------------------------------------------------------------------------

    // metodo que recibe una lista de procesos y los abre secuencialmente
    public static void abrirprocesos(String[] procesos) {
    	
        try {
            for (String proceso : procesos) {
                
                ProcessBuilder pb = new ProcessBuilder(proceso);
                
                Process p = pb.start();
                System.out.println(proceso + " abierto.");

                p.waitFor();
                System.out.println(proceso + " cerrado.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
