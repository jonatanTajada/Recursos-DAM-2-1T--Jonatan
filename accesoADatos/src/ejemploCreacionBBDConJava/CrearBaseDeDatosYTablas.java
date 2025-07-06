package ejemploCreacionBBDConJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBaseDeDatosYTablas {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        try {
            // Conectar a MySQL sin especificar una base de datos (nivel de servidor)
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
            
            stmt = con.createStatement();

         
            String sqlCreateDB = "CREATE DATABASE  MiNuevaBaseDeDatos";
            stmt.executeUpdate(sqlCreateDB);
            System.out.println("Base de datos 'MiNuevaBaseDeDatos' creada.");

            // Cambiar la conexión a la nueva base de datos
            con.setCatalog("MiNuevaBaseDeDatos");

            // Crear la primera tabla (Personas)
            String sqlCreateTable1 = "CREATE TABLE Personas (" +
                                     "id INT PRIMARY KEY AUTO_INCREMENT, " +
                                     "nombre VARCHAR(100), " +
                                     "edad INT)";
            stmt.executeUpdate(sqlCreateTable1);
            System.out.println("Tabla 'Personas' creada.");

            // Crear la segunda tabla (Direcciones)
            String sqlCreateTable2 = "CREATE TABLE Direcciones (" +
                                     "id INT PRIMARY KEY AUTO_INCREMENT, " +
                                     "persona_id INT, " +
                                     "direccion VARCHAR(255), " +
                                     "FOREIGN KEY (persona_id) REFERENCES Personas(id))";
            stmt.executeUpdate(sqlCreateTable2);
            System.out.println("Tabla 'Direcciones' creada.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrar los recursos
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
