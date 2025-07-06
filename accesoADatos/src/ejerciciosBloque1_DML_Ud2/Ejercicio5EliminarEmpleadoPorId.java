package ejerciciosBloque1_DML_Ud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ejercicio5EliminarEmpleadoPorId {

	private static final String URL = "jdbc:mysql://localhost:3306/empresa_ordunia";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		String sqlEliminarEmpleadoPorId = "DELETE FROM empleado WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conexion.prepareStatement(sqlEliminarEmpleadoPorId)) {

			ps.setInt(1, 1);
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				System.out.println("Total de filas afectadas: " + filasAfectadas);
				System.out.println("Empleado eliminado correctamente!");
			}
			

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
