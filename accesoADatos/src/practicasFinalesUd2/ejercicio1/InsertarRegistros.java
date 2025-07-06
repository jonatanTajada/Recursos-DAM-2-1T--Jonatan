package practicasFinalesUd2.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarRegistros {

	private static final String URL = "jdbc:mysql://localhost:3306/vuelos_pasajeros";
	private static final String USER = "root";
	private static final String PASS = "1234";
	

	public static void main(String[] args) {
		
	//mas eficiente centralizar la conexion, y para si en un futuro necesitas transacciones y estas han de ser las conexiones atomicas
		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {

			insertarDatosEnVuelos(conexion);

			insertarDatosEnPasajeros(conexion);

		} catch (SQLException e) {
			System.err.println("Error al conectar o insertar los datos: " + e.getMessage());
			e.printStackTrace();
		}
	}

	 //metodo para insertar datos en la tbla vuelos.
	public static void insertarDatosEnVuelos(Connection conexion) {
		
		String sqlInsertarVuelos = "INSERT INTO VUELOS (COD_VUELO, HORA_SALIDA, DESTINO, PROCEDENCIA, PLAZAS_FUMADOR, "
								 + "PLAZAS_NO_FUMADOR, PLAZAS_TURISTA, PLAZAS_PRIMERA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement psVuelos = conexion.prepareStatement(sqlInsertarVuelos)) {

			psVuelos.setString(1, "IB-SP-4567");
			psVuelos.setString(2, "27/03/99-10:30");
			psVuelos.setString(3, "PARIS");
			psVuelos.setString(4, "MADRID");
			psVuelos.setInt(5, 25);
			psVuelos.setInt(6, 100);
			psVuelos.setInt(7, 160);
			psVuelos.setInt(8, 40);
			psVuelos.executeUpdate();

			
			psVuelos.setString(1, "IB-BA-46DC");
			psVuelos.setString(2, "28/03/99-12:30");
			psVuelos.setString(3, "ROMA");
			psVuelos.setString(4, "MADRID");
			psVuelos.setInt(5, 90);
			psVuelos.setInt(6, 100);
			psVuelos.setInt(7, 160);
			psVuelos.setInt(8, 30);
			psVuelos.executeUpdate();

			System.out.println("Datos insertados correctamente en la tabla VUELOS.");

		} catch (SQLException e) {
			System.err.println("Error al insertar datos en la tabla VUELOS: " + e.getMessage());
			e.printStackTrace();
		}
	}

	 // metodo para insertar datos en la tabla pasajeros
	public static void insertarDatosEnPasajeros(Connection conexion) {
		
		String sqlInsertarPasajeros = "INSERT INTO PASAJEROS (NUM, COD_VUELO, TIPO_PLAZA, FUMADOR) VALUES (?, ?, ?, ?)";
		try (PreparedStatement psPasajeros = conexion.prepareStatement(sqlInsertarPasajeros)) {

			// Insertar pasajeros para el vuelo IB-SP-4567
			psPasajeros.setInt(1, 123);
			psPasajeros.setString(2, "IB-SP-4567");
			psPasajeros.setString(3, "TU"); 
			psPasajeros.setString(4, "SI");
			psPasajeros.executeUpdate();

			psPasajeros.setInt(1, 124);
			psPasajeros.setString(2, "IB-SP-4567");
			psPasajeros.setString(3, "PR"); 
			psPasajeros.setString(4, "SI");
			psPasajeros.executeUpdate();

			psPasajeros.setInt(1, 125);
			psPasajeros.setString(2, "IB-SP-4567");
			psPasajeros.setString(3, "PR");
			psPasajeros.setString(4, "NO"); 
			psPasajeros.executeUpdate();

			// Insertar pasajeros para el vuelo IB-BA-46DC
			psPasajeros.setInt(1, 126);
			psPasajeros.setString(2, "IB-BA-46DC");
			psPasajeros.setString(3, "TU");
			psPasajeros.setString(4, "SI");
			psPasajeros.executeUpdate();

			psPasajeros.setInt(1, 127);
			psPasajeros.setString(2, "IB-BA-46DC");
			psPasajeros.setString(3, "PR");
			psPasajeros.setString(4, "SI");
			psPasajeros.executeUpdate();

			psPasajeros.setInt(1, 128);
			psPasajeros.setString(2, "IB-BA-46DC");
			psPasajeros.setString(3, "PR");
			psPasajeros.setString(4, "SI");
			psPasajeros.executeUpdate();

			System.out.println("Datos insertados correctamente en la tabla PASAJEROS.");

		} catch (SQLException e) {
			System.err.println("Error al insertar datos en la tabla PASAJEROS: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
