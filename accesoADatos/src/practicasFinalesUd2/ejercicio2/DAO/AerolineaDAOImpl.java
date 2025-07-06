package practicasFinalesUd2.ejercicio2.DAO;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import practicasFinalesUd2.ejercicio2.MODEL.Fumador;
import practicasFinalesUd2.ejercicio2.MODEL.Pasajero;
import practicasFinalesUd2.ejercicio2.MODEL.TipoPlaza;
import practicasFinalesUd2.ejercicio2.MODEL.Vuelo;


public class AerolineaDAOImpl implements AerolineaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/vuelospasajeros";
    private static final String USER = "root";
    private static final String PASS = "1234";

    @Override
    public List<Vuelo> obtenerVuelosLimitados() {
        String sql = "SELECT COD_VUELO, HORA_SALIDA, DESTINO FROM VUELOS LIMIT 2";
        List<Vuelo> vuelos = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vuelo vuelo = new Vuelo(rs.getString("COD_VUELO"), 
                                        rs.getString("HORA_SALIDA"),
                                        rs.getString("DESTINO"));
                vuelos.add(vuelo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vuelos;
    }

    @Override
    public List<Pasajero> obtenerTodosLosPasajeros() {
        String sql = "SELECT * FROM PASAJEROS";
        List<Pasajero> pasajeros = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pasajero pasajero = new Pasajero(
                    rs.getInt("NUM"),
                    rs.getString("COD_VUELO"),
                    TipoPlaza.valueOf(rs.getString("TIPO_PLAZA")),
                    Fumador.valueOf(rs.getString("FUMADOR"))
                );
                pasajeros.add(pasajero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajeros;
    }

    @Override
    public List<Pasajero> obtenerPasajerosPorVuelo(String codigoVuelo) {
        String sql = "SELECT * FROM PASAJEROS WHERE COD_VUELO = ?";
        List<Pasajero> pasajeros = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigoVuelo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasajero pasajero = new Pasajero(
                    rs.getInt("NUM"),
                    rs.getString("COD_VUELO"),
                    TipoPlaza.valueOf(rs.getString("TIPO_PLAZA")),
                    Fumador.valueOf(rs.getString("FUMADOR"))
                );
                pasajeros.add(pasajero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajeros;
    }

    @Override
    public void insertarVuelo(Vuelo vuelo) {
        String sql = "INSERT INTO VUELOS (COD_VUELO, HORA_SALIDA, DESTINO, PROCEDENCIA, PLAZAS_FUMADOR, PLAZAS_NO_FUMADOR, PLAZAS_TURISTA, PLAZAS_PRIMERA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, vuelo.getCodigo());
            ps.setString(2, vuelo.getHoraSalida());
            ps.setString(3, vuelo.getDestino());
            ps.setString(4, vuelo.getProcedencia());
            ps.setInt(5, vuelo.getPlazasFumador());
            ps.setInt(6, vuelo.getPlazasNoFumador());
            ps.setInt(7, vuelo.getPlazasTurista());
            ps.setInt(8, vuelo.getPlazasPrimera());
            ps.executeUpdate();

            System.out.println("Vuelo insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertarPasajero(Pasajero pasajero) {
        String sql = "INSERT INTO PASAJEROS (NUM, COD_VUELO, TIPO_PLAZA, FUMADOR) VALUES (?, ?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, pasajero.getNum());
            ps.setString(2, pasajero.getCodVuelo());
            ps.setString(3, pasajero.getTipoPlaza().name());
            ps.setString(4, pasajero.getFumador().name());
            ps.executeUpdate();

            System.out.println("Pasajero insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrarVuelo(String codVuelo) {
        String sql = "DELETE FROM VUELOS WHERE COD_VUELO = ?";
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codVuelo);
            ps.executeUpdate();
            System.out.println("Vuelo eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificarVuelosFumadores() {
        String sql = "UPDATE VUELOS SET PLAZAS_NO_FUMADOR = PLAZAS_NO_FUMADOR + PLAZAS_FUMADOR, PLAZAS_FUMADOR = 0";
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            int filasActualizadas = ps.executeUpdate();
            System.out.println(filasActualizadas + " vuelos actualizados a no fumadores.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
