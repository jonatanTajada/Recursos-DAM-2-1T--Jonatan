package practicasExamenPrimerTrimestre.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CrearBaseDeDatos {

	public static void main(String[] args) {
		
		// Conexión al servidor MySQL sin especificar base de datos
		String url = "jdbc:mysql://localhost:3306/";
		String usuario = "root";
		String contraseña = "1234";

	
		String crearBaseDeDatosSQL = "CREATE DATABASE IF NOT EXISTS practica_jdbc";
		
		String crearTablaSQL = "CREATE TABLE IF NOT EXISTS practica_jdbc.clientes ("
				+ "id INT AUTO_INCREMENT PRIMARY KEY, " + "nombre VARCHAR(50), " + "email VARCHAR(100)" + ")";

		try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
				Statement statement = conexion.createStatement()) {

			// Crear la base de datos
			statement.executeUpdate(crearBaseDeDatosSQL);
			System.out.println("Base de datos 'practica_jdbc' creada (o ya existía).");

			// Crear la tabla en la base de datos
			statement.executeUpdate(crearTablaSQL);
			System.out.println("Tabla 'clientes' creada en la base de datos 'practica_jdbc'.");

		} catch (SQLException e) {
			System.out.println("Error al crear la base de datos o la tabla.");
			e.printStackTrace();
		}
	}
}
