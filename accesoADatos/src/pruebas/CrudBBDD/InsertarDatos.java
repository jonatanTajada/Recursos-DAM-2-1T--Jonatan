package pruebas.CrudBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserción de datos en la base de datos
 * Objetivo: Insertar datos en la tabla personas utilizando JDBC.
 * Descripción: Este ejercicio permitirá insertar un nuevo registro en la tabla personas usando PreparedStatement.
 */



public class InsertarDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/prueba";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
    	
        insertarPersonaNueva("Bill Gates", 72);
    }

	private static void insertarPersonaNueva(String nombrePersona, int edadPersona) {
		
		String sql = "INSERT INTO personas (nombre, edad) VALUES (?, ?)";
        String nombre = nombrePersona;
        int edad = edadPersona;

        
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, edad);

            int filasInsertadas = ps.executeUpdate();
            
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa: " + filasInsertadas + " fila(s) insertada(s).");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
