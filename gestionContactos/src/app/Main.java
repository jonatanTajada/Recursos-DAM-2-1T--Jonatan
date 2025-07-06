package app;

import controlador.ControladorPrincipal;
import vista.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// Crear la ventana principal y el controlador
	    VentanaPrincipal vista = new VentanaPrincipal();
	    ControladorPrincipal controlador = new ControladorPrincipal(vista);
	    vista.setVisible(true); 
	}
	
}
