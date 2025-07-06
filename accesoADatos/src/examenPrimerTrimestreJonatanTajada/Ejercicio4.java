package examenPrimerTrimestreJonatanTajada;

/**
 * Ejercicio de conexion y consulta a bdd
 */


import java.sql.*;

public class Ejercicio4 {

    // Conexion con la bbdd
    private static final String URL = "jdbc:mysql://localhost:3306/Escuela";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";

    // Metodo para insertar un nuevo alumno
    public void insertarAlumno(String nombre, int edad, String email) {
    	
        String sqlInsert = "INSERT INTO Alumnos (nombre, edad, email) VALUES (?, ?, ?)";
        
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement ps = conexion.prepareStatement(sqlInsert)) {

            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setString(3, email);

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
            	
				System.out.println("Registro realizado correctamente!");
			}else {
				
				System.err.println("No se pudo realizar el registro para el nuevo alumno");
			}
            
            System.out.println("Total filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.err.println("Error al insertar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Metodo para mostrar todos los alumnos
    public void mostrarTodosLosAlumnos() {
    	
        String sqlSelect = "SELECT id, nombre, edad, email FROM Alumnos";
        
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement ps = conexion.prepareStatement(sqlSelect);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Lista de alumnos:");
            System.out.println("----------------------------");
            
            while (rs.next()) {
            	
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String email = rs.getString("email");

                System.out.printf("ID: %d, Nombre: %s, Edad: %d, Email: %s%n", id, nombre, edad, email);
            }

        } catch (SQLException e) {
            System.err.println("Error al mostrar alumnos: " + e.getMessage());
        }
    }

    // Metodo para actualizar la edad de un alumno
    public void actualizarEdad(int id, int nuevaEdad) {
    	
        String sql = "UPDATE Alumnos SET edad = ? WHERE id = ?";
        
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, nuevaEdad);
            ps.setInt(2, id);

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
				System.out.println("Actualizacion realizada con exito");
				System.out.println("Total filas afectadas: " + filasAfectadas);
			}
            
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar edad: " + e.getMessage());
        }
    }

  
    public static void main(String[] args) {
        Ejercicio4 escuelaDB = new Ejercicio4();

        escuelaDB.insertarAlumno("Jonatan", 36, "jonatan@gmail.com");

        escuelaDB.mostrarTodosLosAlumnos();

        // Actualizar la edad de un alumno con ID 1
        escuelaDB.actualizarEdad(1, 22);

        escuelaDB.mostrarTodosLosAlumnos();
    }
}

