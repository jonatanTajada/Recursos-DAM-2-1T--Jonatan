package practicasFinalesUd2.ejercicio2.DAO;

import java.util.List;
import practicasFinalesUd2.ejercicio2.MODEL.Pasajero;
import practicasFinalesUd2.ejercicio2.MODEL.Vuelo;

public interface AerolineaDAO {
	
	// Obtener una lista limitada de vuelos (para la demostración)
	List<Vuelo> obtenerVuelosLimitados();

	// Obtener todos los pasajeros
	List<Pasajero> obtenerTodosLosPasajeros();

	// Obtener los pasajeros de un vuelo específico
	List<Pasajero> obtenerPasajerosPorVuelo(String codigoVuelo);

	// Insertar un nuevo vuelo
	void insertarVuelo(Vuelo vuelo);

	// Insertar un nuevo pasajero
	void insertarPasajero(Pasajero pasajero);

	// Borrar un vuelo por su código
	void borrarVuelo(String codVuelo);

	// Modificar los vuelos para que las plazas de fumadores pasen a no fumadores
	void modificarVuelosFumadores();
}
