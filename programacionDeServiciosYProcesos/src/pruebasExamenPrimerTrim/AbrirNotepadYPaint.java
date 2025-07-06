package pruebasExamenPrimerTrim;

public class AbrirNotepadYPaint {
	
	
	
	public static void main(String[] args) {
		
		
		String[] procesos = {"notepad.exe", "mspaint.exe"};
		
			
			for (String proceso : procesos) {
				
				
				try {
				System.out.println("Abriendo: " + proceso);
				
				ProcessBuilder pb = new ProcessBuilder(proceso);
				Process p = pb.start();
				
				int codigoSalida = p.waitFor();
				System.out.println(proceso + " se cerro. Codigo de salida: " + codigoSalida);
			
				} catch (Exception e) {
				
					System.err.println("ERROR al ejecutar: " + proceso + ": " + e.getMessage());
				}

			
			}
			
			
			
		
		
		
		
		
		
		
	}

	
	
	

}
