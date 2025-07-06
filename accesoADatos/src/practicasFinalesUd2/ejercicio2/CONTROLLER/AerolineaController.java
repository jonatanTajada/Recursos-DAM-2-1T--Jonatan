package practicasFinalesUd2.ejercicio2.CONTROLLER;

import practicasFinalesUd2.ejercicio2.MODEL.Fumador;
import practicasFinalesUd2.ejercicio2.MODEL.Pasajero;
import practicasFinalesUd2.ejercicio2.MODEL.TipoPlaza;
import practicasFinalesUd2.ejercicio2.MODEL.Vuelo;
import practicasFinalesUd2.ejercicio2.SERVICE.AerolineaService;
import practicasFinalesUd2.ejercicio2.SERVICE.AerolineaServiceImpl;





public class AerolineaController {

    private AerolineaService aerolineaService = new AerolineaServiceImpl();

    public static void main(String[] args) {
        AerolineaController controller = new AerolineaController();

        // Insertar un nuevo vuelo
        Vuelo nuevoVuelo = new Vuelo("FR-PR-5000", "21/05/99 15:30", "Madrid", "Paris", 30, 120, 90, 60);
        controller.aerolineaService.insertarVuelo(nuevoVuelo);

        // Mostrar las 3 primeras columnas de 2 filas de la tabla vuelos
        System.out.println("Vuelos disponibles:");
        controller.aerolineaService.obtenerVuelosLimitados().forEach(v -> 
            System.out.println(v.getCodigo() + " | " + v.getHoraSalida() + " | " + v.getDestino())
        );

        // Insertar algunos pasajeros
        Pasajero pasajero1 = new Pasajero(123, "FR-PR-5000", TipoPlaza.TU, Fumador.SI);
        Pasajero pasajero2 = new Pasajero(124, "FR-PR-5000", TipoPlaza.PR, Fumador.NO);
        controller.aerolineaService.insertarPasajero(pasajero1);
        controller.aerolineaService.insertarPasajero(pasajero2);

        // Mostrar todos los pasajeros
        System.out.println("\nPasajeros registrados:");
        controller.aerolineaService.obtenerTodosLosPasajeros().forEach(p -> 
            System.out.println(p.getNum() + " | " + p.getCodVuelo() + " | " + p.getTipoPlaza() + " | " + p.getFumador())
        );

        // Borrar el vuelo insertado
        controller.aerolineaService.borrarVuelo("FR-PR-5000");

        // Modificar los vuelos de fumadores a no fumadores
        controller.aerolineaService.modificarVuelosFumadores();
    }
}
