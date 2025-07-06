package ejerciciosBloque1_DDL_Ud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio2ActualizarDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/empresa_ordunia";
    private static final String USER = "root"; 
    private static final String PASSWORD = "1234"; 

    public static void main(String[] args) {
    	
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // 1. Añadir la columna departamento_id a la tabla Empleado
            String sqlAnadirColumna = "ALTER TABLE Empleado ADD COLUMN departamento_id INT";
            
            try (PreparedStatement psAnadirColumn = conexion.prepareStatement(sqlAnadirColumna)) {
            	psAnadirColumn.executeUpdate();
                System.out.println("La columna se ha creado correctamente en la tabla empleado!");
            }

            // 2. Crear la FK con Departamento
            String sqlAnadirFK = "ALTER TABLE Empleado "
            					+ "ADD CONSTRAINT fk_departamento "
            					+ "FOREIGN KEY (departamento_id) REFERENCES Departamento(id)";
            
            try (PreparedStatement psAnadirFK = conexion.prepareStatement(sqlAnadirFK)) {
                psAnadirFK.executeUpdate();
                System.out.println("FK creada correctamente en la tabla empleado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
