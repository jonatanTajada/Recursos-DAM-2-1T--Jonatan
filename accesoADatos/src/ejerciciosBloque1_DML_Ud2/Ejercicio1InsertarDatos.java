package ejerciciosBloque1_DML_Ud2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio1InsertarDatos {

	private static final String URL = "jdbc:mysql://localhost:3306/empresa_ordunia";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {

			String sqlInsertarDepartamento = "INSERT INTO departamento (nombre, ubicacion) VALUES  (?, ?)";

			// 1. insertar datos en la tabla departamento
			try (PreparedStatement psDepartamento = conexion.prepareStatement(sqlInsertarDepartamento)) {

				psDepartamento.setString(1, "Contabilidad");
				psDepartamento.setString(2, "PB0");
				psDepartamento.executeUpdate();

				psDepartamento.setString(1, "Marketing");
				psDepartamento.setString(2, "PB0");
				psDepartamento.executeUpdate();

				psDepartamento.setString(1, "RRHH");
				psDepartamento.setString(2, "PB1");
				psDepartamento.executeUpdate();

				System.out.println("Datos insertados correctamente en la tabla Departamento.");
			}

			// 2. insertar datos en la tabla empleado
			String sqlInsertarEmpleado = "INSERT INTO empleado (nombre, fecha_nacimiento, genero, departamento_id) VALUES (?, ?, ?, ?)";

			try (PreparedStatement psEmpleado = conexion.prepareStatement(sqlInsertarEmpleado)) {

				psEmpleado.setString(1, "Alvaro");
				psEmpleado.setDate(2, Date.valueOf("1979-11-25"));
				psEmpleado.setInt(3, 1); 
				psEmpleado.setInt(4, 1); //Departamento Contabilidad
				psEmpleado.executeUpdate();

				psEmpleado.setString(1, "Juan");
				psEmpleado.setDate(2, Date.valueOf("2001-07-07"));
				psEmpleado.setInt(3, 1); 
				psEmpleado.setInt(4, 2); // Departamento Marketing
				psEmpleado.executeUpdate();

				psEmpleado.setString(1, "Marta");
				psEmpleado.setDate(2, Date.valueOf("1997-12-25"));
				psEmpleado.setInt(3, 0); 
				psEmpleado.setInt(4, 3); // Departamento RRHH
				psEmpleado.executeUpdate();

				psEmpleado.setString(1, "Silvia");
				psEmpleado.setDate(2, Date.valueOf("1992-04-01"));
				psEmpleado.setInt(3, 0); 
				psEmpleado.setInt(4, 2); 
				psEmpleado.executeUpdate();

				System.out.println("Datos insertados correctamente en la tabla Empleado.");

			}

		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}















