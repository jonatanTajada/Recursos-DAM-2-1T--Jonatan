package practicasFinalesUd2.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearBaseDatos {

	// URL (todavia sin bd)
	private static String URL = "jdbc:mysql://localhost:3306";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		crearBaseDeDatos();

		crearTablas();
	}

	
	//crear bd
	public static void crearBaseDeDatos() {
		
		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {

			String sqlCrearBD = "CREATE DATABASE IF NOT EXISTS vuelos_pasajeros";
			try (PreparedStatement psCrearBD = conexion.prepareStatement(sqlCrearBD)) {
				psCrearBD.executeUpdate();
				System.out.println("La base de datos se ha creado correctamente o ya existia.");
			}

			//actualizo URL con la bd
			URL = "jdbc:mysql://localhost:3306/vuelos_pasajeros";

		} catch (SQLException e) {
			System.err.println("Error al crear la base de datos: " + e.getMessage());
		}
	}

	//crear tablas - vuelos, pasajeros-.
	public static void crearTablas() {
		
		try (Connection conexionBD = DriverManager.getConnection(URL, USER, PASS)) {

			// Crear la tabla VUELOS (COD_VUELO es clave primaria aquí)
			 String sqlCrearTablaVuelos = "CREATE TABLE IF NOT EXISTS VUELOS ("
	                    + "COD_VUELO VARCHAR(10), "
	                    + "HORA_SALIDA VARCHAR(15), "
	                    + "DESTINO VARCHAR(15), "
	                    + "PROCEDENCIA VARCHAR(15), "
	                    + "PLAZAS_FUMADOR INT(3), "
	                    + "PLAZAS_NO_FUMADOR INT(3), "
	                    + "PLAZAS_TURISTA INT(3), "
	                    + "PLAZAS_PRIMERA INT(3), "
	                    + "CONSTRAINT PK_VUELOS PRIMARY KEY (COD_VUELO))"; 
			 
			try (PreparedStatement psCrearTablaVuelos = conexionBD.prepareStatement(sqlCrearTablaVuelos)) {
				psCrearTablaVuelos.executeUpdate();
				System.out.println("La tabla vuelos se ha creado correctamente o ya existia");
			}

			// Crear la tabla PASAJEROS 
			String sqlCrearTablaPasajeros = "CREATE TABLE IF NOT EXISTS PASAJEROS (" 
					+ "NUM INT(7), "
					+ "COD_VUELO VARCHAR(10), " 
					+ "TIPO_PLAZA ENUM('TU', 'PR') NOT NULL, "
					+ "FUMADOR ENUM('SI', 'NO') NOT NULL, " 
					+ "CONSTRAINT PK_PASAJEROS PRIMARY KEY (NUM, COD_VUELO), " 
					+ "CONSTRAINT FK_PASAJEROS_COD_VUELO FOREIGN KEY (COD_VUELO) REFERENCES VUELOS(COD_VUELO) ON DELETE CASCADE)";
			
			try (PreparedStatement psCrearTablaPasajeros = conexionBD.prepareStatement(sqlCrearTablaPasajeros)) {
				psCrearTablaPasajeros.executeUpdate();
				System.out.println("La tabla pasajeros se ha creado correctamente o ya existia");
			}

		} catch (SQLException e) {
			System.err.println("Error al crear las tablas: " + e.getMessage());
		}
	}
}
