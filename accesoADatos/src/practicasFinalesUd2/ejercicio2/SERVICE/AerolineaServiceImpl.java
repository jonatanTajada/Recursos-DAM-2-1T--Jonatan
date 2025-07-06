package practicasFinalesUd2.ejercicio2.SERVICE;


import java.util.List;
import practicasFinalesUd2.ejercicio2.DAO.AerolineaDAO;
import practicasFinalesUd2.ejercicio2.DAO.AerolineaDAOImpl;
import practicasFinalesUd2.ejercicio2.MODEL.Pasajero;
import practicasFinalesUd2.ejercicio2.MODEL.Vuelo;


public class AerolineaServiceImpl implements AerolineaService {

    private AerolineaDAO aerolineaDAO = new AerolineaDAOImpl();

    @Override
    public List<Vuelo> obtenerVuelosLimitados() {
        return aerolineaDAO.obtenerVuelosLimitados();
    }

    @Override
    public List<Pasajero> obtenerTodosLosPasajeros() {
        return aerolineaDAO.obtenerTodosLosPasajeros();
    }

    @Override
    public List<Pasajero> obtenerPasajerosPorVuelo(String codigoVuelo) {
        return aerolineaDAO.obtenerPasajerosPorVuelo(codigoVuelo);
    }

    @Override
    public void insertarVuelo(Vuelo vuelo) {
        aerolineaDAO.insertarVuelo(vuelo);
    }

    @Override
    public void insertarPasajero(Pasajero pasajero) {
        aerolineaDAO.insertarPasajero(pasajero);
    }

    @Override
    public void borrarVuelo(String codVuelo) {
        aerolineaDAO.borrarVuelo(codVuelo);
    }

    @Override
    public void modificarVuelosFumadores() {
        aerolineaDAO.modificarVuelosFumadores();
    }
}
