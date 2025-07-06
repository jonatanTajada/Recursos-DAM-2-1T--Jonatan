package practicasExamenPrimerTrimestre.EjercicioRepasoAD1Trimestre;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnosApp {

	private static final String URL = "jdbc:mysql://localhost:3306/escuela";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "1234";

	public static void insertarAlumnos() {
		
		String sql = "INSERT INTO alumnos (nombre, edad, activo) VALUES (?, ?, ?)";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, "Jonatan");
			ps.setInt(2, 22);
			ps.setBoolean(3, true);
			ps.executeUpdate();

			ps.setString(1, "Rosa");
			ps.setInt(2, 25);
			ps.setBoolean(3, true);
			ps.executeUpdate();

			ps.setString(1, "Pedro");
			ps.setInt(2, 20);
			ps.setBoolean(3, false);
			ps.executeUpdate();

			System.out.println("Alumnos insertados correctamente!");

		} catch (SQLException e) {
			
			System.err.println("Error al insertar alumno/s: " + e.getMessage());
		}
	}

	// metodo para guardar alumnos activos en archivo .dat
	public static void guardarAlumnosActivos() {
		
		String sql = "SELECT * FROM alumnos WHERE activo = true";
		
		List<Alumno> alumnosActivos = new ArrayList<>();

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			 PreparedStatement ps = conexion.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int edad = rs.getInt("edad");
				boolean activo = rs.getBoolean("activo");

				Alumno alumno = new Alumno(id, nombre, edad, activo);
				alumnosActivos.add(alumno);
			}

			
			// Serializar alumnos activos en archivo alumnos_activos.dat
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("alumnos_activos.dat"))) {
				
				oos.writeObject(alumnosActivos);
				System.out.println("Alumno/s registrado/s!");
			} catch (IOException e) {
				
				System.out.println("Error al serializar los alumnos activos: " + e.getMessage());
			}

			
		} catch (SQLException e) {
			
			System.out.println("Ocurrió algun error al consultar alumnos activos: " + e.getMessage());
		}
	}
	

	// Método para deserializar y mostrar los alumnos
	public static void leerAlumnosActivos() {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("alumnos_activos.dat"))) {
			
			List<Alumno> alumnosActivos = (List<Alumno>) ois.readObject();

			System.out.println("\n***Alumnos activos***");
			System.out.println("-----------------------------");
			for (Alumno alumno : alumnosActivos) {
				System.out.println(alumno);
			}

		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Ocurrio algun error en el proceso: " + e.getMessage());
		}
	}

	// ------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		insertarAlumnos();
		guardarAlumnosActivos();
		leerAlumnosActivos();
		
	}
}
