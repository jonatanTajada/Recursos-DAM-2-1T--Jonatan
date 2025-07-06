package practicasExamenPrimerTrimestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MiniCRUD {

    private static final String URL = "jdbc:mysql://localhost:3306/escuela";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n*** MENU CRUD ***");
            System.out.println(" 1. Mostrar todos los alumnos");
            System.out.println(" 2. Actualizar un alumno");
            System.out.println(" 3. Borrar un alumno");
            System.out.println(" 4. Insertar nuevo alumno");
            System.out.println(" 5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> mostrarAlumnos();
                case 2 -> {
                    System.out.print("Ingrese el ID del alumno a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nombreActualizar = scanner.nextLine();
                    System.out.print("Ingrese la nueva edad: ");
                    int edadActualizar = scanner.nextInt();
                    System.out.print("¿El alumno está activo? (true/false): ");
                    boolean activoActualizar = scanner.nextBoolean();
                    actualizarAlumnoPorID(idActualizar, nombreActualizar, edadActualizar, activoActualizar);
                }
                case 3 -> {
                    System.out.print("Ingrese el ID del alumno a borrar: ");
                    int idBorrar = scanner.nextInt();
                    borrarAlumno(idBorrar);
                }
                case 4 -> insertarNuevoAlumno(scanner);
                case 5 -> {
                    salir = true;
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción no válida, intente nuevamente.");
            }
        }

        scanner.close();
    }

    private static void insertarNuevoAlumno(Scanner scanner) {

        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Introduce nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduce edad: ");
        int edad = scanner.nextInt();

        System.out.print("Introduce si está activo (true/false): ");
        boolean activo = scanner.nextBoolean();

        String sqlInsertarAlumno = "INSERT INTO alumnos (nombre, edad, activo) VALUES (?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement ps = conexion.prepareStatement(sqlInsertarAlumno)) {

            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setBoolean(3, activo);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Alumno registrado correctamente, total de filas afectadas: " + filasAfectadas);
            }

        } catch (Exception e) {
            System.err.println("Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void mostrarAlumnos() {

        String sqlInsertar = "SELECT * FROM alumnos";
        System.out.println("Conexión establecida con éxito!");
        System.out.println("-----------------------------------");

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement ps = conexion.prepareStatement(sqlInsertar)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                boolean activo = rs.getBoolean("activo");

                System.out.println("\t" + id + "\t" + nombre + "\t" + edad + "\t" + activo);
            }

        } catch (Exception e) {
            System.err.println("Ocurrió algún error al mostrar tabla alumnos: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void actualizarAlumnoPorID(int id, String nombre, int edad, boolean activo) {

        String sqlActualizar = "UPDATE alumnos SET nombre = ?, edad = ?, activo = ? WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement ps = conexion.prepareStatement(sqlActualizar)) {

            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setBoolean(3, activo);
            ps.setInt(4, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Actualización realizada correctamente, total filas afectadas: " + filasAfectadas);
            } else {
                System.err.println("No se encontró un registro con el ID especificado.");
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void borrarAlumno(int id) {

        String sqlEliminar = "DELETE FROM alumnos WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement ps = conexion.prepareStatement(sqlEliminar)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Eliminación realizada con éxito!");
            } else {
                System.err.println("No se pudo eliminar el registro deseado.");
            }

        } catch (Exception e) {
            System.out.println("Ocurrió algún error y no pudo eliminarse el alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
