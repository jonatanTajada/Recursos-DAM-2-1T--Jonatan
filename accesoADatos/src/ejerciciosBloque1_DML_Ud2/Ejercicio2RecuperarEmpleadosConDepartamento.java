package ejerciciosBloque1_DML_Ud2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio2RecuperarEmpleadosConDepartamento {

	private static final String URL = "jdbc:mysql://localhost:3306/empresa_ordunia";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static void main(String[] args) {
		
		String sqlObtenerEmpleadoConDepartamento = "SELECT e.nombre, e.fecha_nacimiento, e.genero, d.nombre AS departamento "
		        + "FROM empleado e "
		        + "JOIN departamento d ON e.departamento_id = d.id "
		        + "ORDER BY e.fecha_nacimiento";

		try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
		     PreparedStatement ps = conexion.prepareStatement(sqlObtenerEmpleadoConDepartamento);
		     ResultSet rs = ps.executeQuery()) {

		    // Formato de cabecera
		    String header = String.format("%-15s %-20s %-10s %-15s", "Nombre", "Fecha de Nacimiento", "Género", "Departamento");
		    System.out.println(header);
		    System.out.println("---------------------------------------------------------------");

		    while (rs.next()) {
		    	
		        String nombre = rs.getString("nombre");
		        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
		        boolean genero = rs.getBoolean("genero");
		        String departamento = rs.getString("departamento");

		        String generoStr = genero ? "Masculino" : "Femenino";

		        // Formato de salida
		        String fila = String.format("%-15s %-20s %-10s %-15s", nombre, fechaNacimiento, generoStr, departamento);
		        System.out.println(fila);
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
}
