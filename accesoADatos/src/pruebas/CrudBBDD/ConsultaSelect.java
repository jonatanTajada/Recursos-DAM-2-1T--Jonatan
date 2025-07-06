package pruebas.CrudBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Conectar a la base de datos y realizar una consulta simple Objetivo:
 * Establecer una conexión con la base de datos MySQL y realizar una consulta
 * simple para obtener y mostrar datos de una tabla. Descripción: Vamos a crear
 * una conexión simple con MySQL y realizar una consulta SELECT que recupera
 * todos los registros de una tabla llamada personas.
 */

public class ConsultaSelect {

	private static final String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		String sql = "SELECT * FROM personas";

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conexion.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			System.out.println("***Lista de la tabla Personas***\n ");
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String edad = rs.getString("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad );

			}

		} catch (SQLException e) {
			System.err.println("Ocurrio error al realizar la consulta: " + e.getMessage());
		}

	}

}
