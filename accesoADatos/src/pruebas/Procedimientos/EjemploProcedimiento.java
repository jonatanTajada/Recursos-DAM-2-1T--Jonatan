package pruebas.Procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EjemploProcedimiento {

	private static final String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		procedimientoPersonaPorEdad(25);

	}

	private static void procedimientoPersonaPorEdad(int edadABuscar) {
		
		String procedimiento = "{CALL getPersonaPorEdad(?)}";
		int edadParametro = edadABuscar;

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				CallableStatement callStatement = conn.prepareCall(procedimiento)) {

			callStatement.setInt(1, edadParametro);

			try (ResultSet rs = callStatement.executeQuery()) {

				while (rs.next()) {
					String nombre = rs.getString("nombre");
					System.out.println("Nombre: " + nombre);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
