package app;

import controlador.ControladorTareas;
import vista.VentanaPrincipal;

public class MainTareas {
    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ControladorTareas controlador = new ControladorTareas(ventana);
        ventana.setVisible(true);
    }
}
