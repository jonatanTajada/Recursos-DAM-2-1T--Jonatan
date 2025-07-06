package pruebasExamenPrimerTrimestre;

class DescargaArchivos implements Runnable {

	private String nombreArchivo;
	private int tiempoDescarga;

	public DescargaArchivos(String nombreArchivo, int tiempoDescarga) {
		this.nombreArchivo = nombreArchivo;
		this.tiempoDescarga = tiempoDescarga;
	}

	@Override
	public void run() {

		System.out.println("Iniciando descarga de : " + nombreArchivo);

		try {

			Thread.sleep(tiempoDescarga * 1000);

		} catch (Exception e) {
			System.err.println("Descarga interrumpida del archivo: " + nombreArchivo + "\n" + e.getMessage());
		}

		System.out.println("Descarga compelde de " + nombreArchivo);
	}

}



public class SimulacionDescargas {

	public static void main(String[] args) {
		
		Runnable descarga1 = new DescargaArchivos("Archivo Personas", 3);
		Runnable descarga2 = new DescargaArchivos("Archivo Contabilidad", 5);
		Runnable descarga3 = new DescargaArchivos("Archivo Logistica", 2);
		
		Thread hilo1 = new Thread(descarga1);
		Thread hilo2 = new Thread(descarga2);
		Thread hilo3 = new Thread(descarga3);
		
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		
	}
	
	
	
}
