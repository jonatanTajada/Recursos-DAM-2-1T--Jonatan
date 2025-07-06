package practicasFinalesUd2.ejercicio2.SERVICE;


import java.util.List;
import practicasFinalesUd2.ejercicio2.MODEL.Pasajero;
import practicasFinalesUd2.ejercicio2.MODEL.Vuelo;


public interface AerolineaService {
	
    List<Vuelo> obtenerVuelosLimitados();
    
    List<Pasajero> obtenerTodosLosPasajeros();
    
    List<Pasajero> obtenerPasajerosPorVuelo(String codigoVuelo);
    
    void insertarVuelo(Vuelo vuelo);
    
    void insertarPasajero(Pasajero pasajero); 
    
    void borrarVuelo(String codVuelo);
    
    void modificarVuelosFumadores();
}
