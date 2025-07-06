package ejerciciosBloque1_DDL_Ud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Ejercicio1CrearBaseDeDatos {

	// URL del servidor MySQL (sin especificar la base de datos aún)
	private static final String URL = "jdbc:mysql://localhost:3306?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public static void main(String[] args) {

		// 1. Crear conexion con el servidor MySQL (todavia sin bd)
		try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement ps = conexion.createStatement()) {

			// 2. Crear la base de datos empresa_ordunia
			String sqlCrearBD = "CREATE DATABASE IF NOT EXISTS empresa_ordunia";
			ps.executeUpdate(sqlCrearBD);
			System.out.println("Base de datos empresa_ordunia creada con exito!.");

			// 3. Usar la base de datos empresa_ordunia
			String sqlUseDB = "USE empresa_ordunia";
			ps.executeUpdate(sqlUseDB);

			// 4. Crear la tabla Departamento
			String sqlCrearTablaDepartamento = "CREATE TABLE IF NOT EXISTS Departamento ("
					+ "id INT AUTO_INCREMENT PRIMARY KEY, " + "nombre VARCHAR(20) NOT NULL, "
					+ "ubicacion VARCHAR(100) NOT NULL" + ")";
			ps.executeUpdate(sqlCrearTablaDepartamento);
			System.out.println("Tabla Departamento creada con exito!.");

			// 5. Crear la tabla Empleado con clave foránea a Departamento
			String sqlCrearTablaEmpleado = "CREATE TABLE IF NOT EXISTS Empleado ("
					+ "id INT AUTO_INCREMENT PRIMARY KEY, " + "nombre VARCHAR(20) NOT NULL, "
					+ "fecha_nacimiento DATE NOT NULL, " + "genero TINYINT(1) NOT NULL" + ")";
			ps.executeUpdate(sqlCrearTablaEmpleado);
			System.out.println("Tabla Empleado creada con exito!.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
