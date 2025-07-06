package pruebaConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public static void main(String[] args) {
		
		// Crear una instancia de Persona
		Persona persona = new Persona("Jonatan", 36);

		// Guardar la persona en la base de datos
		PersonaDAO personaDAO = new PersonaDAO();
		personaDAO.guardarPersona(persona);
	}

	//-------------------------------------------------------------------------------------------------------
	
	public void guardarPersona(Persona persona) {
		String insertQuery = "INSERT INTO personas (nombre, edad) VALUES (?, ?)";
		String selectQuery = "SELECT * FROM personas";

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			     PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
			     PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

				
				insertStatement.setString(1, persona.getNombre());
				insertStatement.setInt(2, persona.getEdad());

				// Ejecutar la insercion
				int filasInsertadas = insertStatement.executeUpdate();
				if (filasInsertadas > 0) {
					System.out.println("Persona guardada correctamente.");
				}

			
				try (ResultSet resultSet = selectStatement.executeQuery()) {
					System.out.println("Lista de personas en la base de datos:");
					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						String nombre = resultSet.getString("nombre");
						int edad = resultSet.getInt("edad");
						System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
					}
				}
				
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error: No se pudo cargar el driver de MySQL.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al guardar la persona en la base de datos.");
			e.printStackTrace();
		}
	}
}
