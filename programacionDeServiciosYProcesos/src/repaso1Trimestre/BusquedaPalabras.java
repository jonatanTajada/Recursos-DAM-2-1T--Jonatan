package repaso1Trimestre;

public class BusquedaPalabras {
	

	public static void main(String[] args) {
		

		String rutaDirectorio = "C:\\Users\\Usuario\\Desktop\\Orduna20242025";
		String palabraABuscar = "jonatan";

		Thread hilo1 = new Thread(new BuscadorDePalabras(rutaDirectorio, palabraABuscar));
		Thread hilo2 = new Thread(new BuscadorDePalabras(rutaDirectorio, palabraABuscar));

		hilo1.start();
		hilo2.start();

		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Busqueda completa!.");
	}
}
