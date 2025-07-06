package pruebas.CrudBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Actualización de registros en la base de datos Objetivo: Actualizar un
 * registro existente en la tabla personas. Descripción: Este ejercicio
 * consistirá en actualizar la edad de una persona en la base de datos usando
 * JDBC.
 */

public class ActualizarDatos {

	private static String URL = "jdbc:mysql://localhost:3306/prueba";
	private static String USER = "root";
	private static String PASS = "1234";

	public static void main(String[] args) {

		actualizarEdadPorId(2,15);

	}

	private static void actualizarEdadPorId(int idPersona, int edad) {
		
		String sql = "UPDATE personas SET edad = ? WHERE id = ?";
		int id = idPersona;
		int nuevaEdad = edad;

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, nuevaEdad);
			ps.setInt(2, id);

			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
				System.out.println("Actulizacion realizada con exito! - Total filas actualizadas: " + filasAfectadas);
			}

		} catch (SQLException e) {
			System.err.println("Error en la actualizacion: " + e.getMessage());
		}
	}

}
