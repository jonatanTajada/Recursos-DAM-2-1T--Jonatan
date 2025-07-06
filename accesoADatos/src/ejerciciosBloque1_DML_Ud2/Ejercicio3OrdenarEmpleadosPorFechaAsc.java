package ejerciciosBloque1_DML_Ud2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio3OrdenarEmpleadosPorFechaAsc {

	private static final String URL = "jdbc:mysql://localhost:3306/empresa_ordunia";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		String sql = "SELECT nombre, fecha_nacimiento FROM empleado ORDER BY fecha_nacimiento ASC";

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conexion.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			  String header = String.format("%-15s %-20s", "Nombre", "Fecha de Nacimiento");
			    System.out.println(header);
			    System.out.println("---------------------------------------------------------------");

			while (rs.next()) {

				String nombre = rs.getString("nombre");
				Date fechaNacimiento = rs.getDate("fecha_nacimiento");

				 String fila = String.format("%-15s %-20s", nombre, fechaNacimiento);
			     System.out.println(fila);

			}

		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
