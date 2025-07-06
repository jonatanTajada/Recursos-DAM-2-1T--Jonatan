package practicasExamenPrimerTrimestre.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConsultaSelect {

    public static void main(String[] args) {
    	
        
        String url = "jdbc:mysql://localhost:3306/nombre_base_datos";
        String usuario = "tu_usuario";
        String contraseña = "tu_contraseña";

        // Consulta SQL para obtener todos los clientes
        String consultaSQL = "SELECT id, nombre, email FROM clientes";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(consultaSQL)) {

            System.out.println("Resultados de la consulta:");
            // Recorrer los resultados de la consulta
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta.");
            e.printStackTrace();
        }
    }
}

