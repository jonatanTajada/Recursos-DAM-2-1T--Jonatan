package pruebas.CrudBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Eliminar registros de la base de datos Objetivo: Eliminar un registro de la
 * tabla personas. Descripción: Este ejercicio consistirá en eliminar un
 * registro de la tabla personas usando JDBC.
 */

public class EliminarDatos {

	private static final String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		eliminarPersona(3);

	}

	private static void eliminarPersona(int idEliminar) {

		String sql = "DELETE FROM personas WHERE id = ?";
		int id = idEliminar;

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);

			int filasAfectadas = ps.executeUpdate();
			

			if (filasAfectadas > 0) {
				System.out.println("Registro eliminado correctamente!");
			}

		} catch (SQLException e) {
			System.err.println("Error al eliminar registro: " + e.getMessage());
		}
	}

}
