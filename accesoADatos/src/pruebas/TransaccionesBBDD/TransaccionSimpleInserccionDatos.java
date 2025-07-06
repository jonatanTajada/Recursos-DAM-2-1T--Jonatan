package pruebas.TransaccionesBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Transacciones simples con commit() y rollback() Objetivo: Realizar varias
 * operaciones en la base de datos como parte de una transacción y controlar si
 * se confirman (commit) o se deshacen (rollback) en caso de error. Descripción:
 * Insertar dos registros en la tabla personas. Si ambos registros se insertan
 * correctamente, se confirmará la transacción. Si alguna inserción falla, se
 * deshará toda la transacción.
 */

public class TransaccionSimpleInserccionDatos {

	private static String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		String sql = "INSERT INTO personas (nombre, edad) VALUES (?, ?)";

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {

			conexion.setAutoCommit(false);

			try (PreparedStatement ps = conexion.prepareStatement(sql);
					PreparedStatement ps2 = conexion.prepareStatement(sql)) {

				ps.setString(1, "Alvaro");
				ps.setInt(2, 45);
				ps.executeUpdate();

				ps2.setString(1, "Mari Mar");
				ps2.setInt(2, (-5 > 0) ? -5 : 0); // Esto evitará la inserción de un valor negativo
				ps2.executeUpdate();

				// confirmar transaccion
				conexion.commit();
				System.out.println("Transaccion realizada con exito!");

			} catch (Exception e) {
				// deshacer la transaccion
				conexion.rollback();
				System.err.println("Error. Se ha realizado un rollback");
				e.printStackTrace();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
