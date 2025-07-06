package ejerciciosBloque1_DML_Ud2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ejercicio4ActualizarFechaNacimiento {

	private static final String URL = "jdbc:mysql://localhost:3306/empresa_ordunia";
	private static final String USER = "root";
	private static final String PASS = "1234";
	
	
	
	public static void main(String[] args) {
		
		
		String sqlActualizarFecha = "UPDATE empleado SET fecha_nacimiento = ? WHERE nombre = ?";
		
		try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement ps = conexion.prepareStatement(sqlActualizarFecha)){
			
			ps.setDate(1, Date.valueOf("2022-03-04"));
			ps.setString(2, "Marta");
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				System.out.println("Actualizacion correcta!\n Total filas afectadas: " + filasAfectadas);
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
