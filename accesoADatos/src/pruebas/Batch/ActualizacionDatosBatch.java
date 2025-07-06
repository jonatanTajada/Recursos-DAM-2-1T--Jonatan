package pruebas.Batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Actualización en lote (batch update) Objetivo: Realizar actualizaciones en
 * múltiples registros en una única operación utilizando batch updates.
 * Descripción: Actualizar la edad de varias personas utilizando addBatch() y
 * executeBatch().
 */

public class ActualizacionDatosBatch {

	private static final String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		String sqlActualizarEdad = "UPDATE personas SET edad = ? WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conexion.prepareStatement(sqlActualizarEdad)) {

			conexion.setAutoCommit(false);

			ps.setInt(1, 66);
			ps.setInt(2, 1);
			ps.addBatch();

			ps.setInt(1, 33);
			ps.setInt(2, 2);
			ps.addBatch();

			ps.setInt(1, 79);
			ps.setInt(2, 5);
			ps.addBatch();

			int[] resultados = ps.executeBatch();

			conexion.commit();

			System.out.println("Inserciones en lote completadas. Filas afectadas: " + resultados.length);

		} catch (SQLException e) {
			System.err.println("Error en la actualizacion de datos BATCH: " + e.getMessage());
		}

	}

}
