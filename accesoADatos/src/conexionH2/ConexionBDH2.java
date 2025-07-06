package conexionH2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBDH2 {

	

		private static final String URL = "jdbc:h2:~/testdb"; // Base de datos persistente en un archivo
	    //private static final String URL = "jdbc:h2:mem:testdb"; // Base de datos en memoria

	    private static final String USER = "sa";
	    private static final String PASS = "";

	    public static void main(String[] args) {

	        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
	            // Crear tablas departamento y empleado
	            String crearTablaDepartamento = "CREATE TABLE departamento (" +
	                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
	                    "nombre VARCHAR(255), " +
	                    "ubicacion VARCHAR(255)" +
	                    ")";

	            String crearTablaEmpleado = "CREATE TABLE empleado (" +
	                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
	                    "nombre VARCHAR(255), " +
	                    "fecha_nacimiento DATE, " +
	                    "genero INT, " +
	                    "departamento_id INT, " +
	                    "FOREIGN KEY (departamento_id) REFERENCES departamento(id)" +
	                    ")";

	            try (PreparedStatement psCrearDepartamento = conexion.prepareStatement(crearTablaDepartamento);
	                 PreparedStatement psCrearEmpleado = conexion.prepareStatement(crearTablaEmpleado)) {
	                psCrearDepartamento.execute();
	                psCrearEmpleado.execute();
	                System.out.println("Tablas creadas correctamente.");
	            }

	            // Insertar datos en la tabla departamento
	            String sqlInsertarDepartamento = "INSERT INTO departamento (nombre, ubicacion) VALUES  (?, ?)";
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

	            // Insertar datos en la tabla empleado
	            String sqlInsertarEmpleado = "INSERT INTO empleado (nombre, fecha_nacimiento, genero, departamento_id) VALUES (?, ?, ?, ?)";
	            try (PreparedStatement psEmpleado = conexion.prepareStatement(sqlInsertarEmpleado)) {

	                psEmpleado.setString(1, "Alvaro");
	                psEmpleado.setDate(2, Date.valueOf("1979-11-25"));
	                psEmpleado.setInt(3, 1); // 1 para masculino
	                psEmpleado.setInt(4, 1); // Departamento Contabilidad
	                psEmpleado.executeUpdate();

	                psEmpleado.setString(1, "Juan");
	                psEmpleado.setDate(2, Date.valueOf("2001-07-07"));
	                psEmpleado.setInt(3, 1); // 1 para masculino
	                psEmpleado.setInt(4, 2); // Departamento Marketing
	                psEmpleado.executeUpdate();

	                psEmpleado.setString(1, "Marta");
	                psEmpleado.setDate(2, Date.valueOf("1997-12-25"));
	                psEmpleado.setInt(3, 0); // 0 para femenino
	                psEmpleado.setInt(4, 3); // Departamento RRHH
	                psEmpleado.executeUpdate();

	                psEmpleado.setString(1, "Silvia");
	                psEmpleado.setDate(2, Date.valueOf("1992-04-01"));
	                psEmpleado.setInt(3, 0); // 0 para femenino
	                psEmpleado.setInt(4, 2); // Departamento Marketing
	                psEmpleado.executeUpdate();

	                System.out.println("Datos insertados correctamente en la tabla Empleado.");
	            }

	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	        }
	    
	}


}
