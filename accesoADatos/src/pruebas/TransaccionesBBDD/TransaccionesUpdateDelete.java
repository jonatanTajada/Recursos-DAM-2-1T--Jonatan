package pruebas.TransaccionesBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Actualización y eliminación en la misma transacción Objetivo: Realizar una
 * actualización y eliminación en la misma transacción. Si cualquiera de las dos
 * operaciones falla, se realiza un rollback. Descripción: Actualizar la edad de
 * una persona y eliminar otro registro. Si ambas operaciones son exitosas, la
 * transacción se confirma, de lo contrario, se deshace.
 */

public class TransaccionesUpdateDelete {

	private static final String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		String sqlActualizarRegistro = "UPDATE personas SET edad = ? WHERE id = ?";
		String sqlEliminarRegistro = "DELETE FROM personas WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {

			conexion.setAutoCommit(false);

			try (PreparedStatement psActualizar = conexion.prepareStatement(sqlActualizarRegistro);
					PreparedStatement psEliminar = conexion.prepareStatement(sqlEliminarRegistro)) {

				psActualizar.setInt(1, 84);
				psActualizar.setInt(2, 2);
				psActualizar.executeUpdate();

				psEliminar.setInt(1, 4);
				psEliminar.executeUpdate();

				// confirmar commit
				conexion.commit();
				System.out.println("Se han realizado ambas operaciones de actualizacion y eliminacion correctamente!");

			} catch (Exception e) {
				// confirmar rollback
				conexion.rollback();
				System.err.println("Hubo algun error, realizo rollback.");
			}

		} catch (SQLException e) {
			System.err.println("Error al intentar conectar a la bbdd: " + e.getMessage());
		}

	}

}
