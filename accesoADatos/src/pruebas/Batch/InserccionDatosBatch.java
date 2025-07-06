package pruebas.Batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Inserciones en lote (batch insert) Objetivo: Insertar varios registros en la
 * base de datos en una única operación utilizando batch updates para mejorar el
 * rendimiento. Descripción: Insertar tres registros en la tabla personas
 * utilizando addBatch() y executeBatch().
 */

public class InserccionDatosBatch {

	private static final String URL = "jdbc:mysql://localhost:3306/prueba";
	private static final String USER = "root";
	private static final String PASS = "1234";

	
	public static void main(String[] args) {
		
		String sqlInsertarPersona = "INSERT INTO personas (nombre, edad) VALUES (?, ?)";
		
		try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement ps = conexion.prepareStatement(sqlInsertarPersona)){
			
			conexion.setAutoCommit(false);
			
			ps.setString(1, "Mari Carmen");
			ps.setInt(2, 68);
			ps.addBatch();
			
			ps.setString(1, "Angel Maria");
			ps.setInt(2, 70);
			ps.addBatch();
			
			ps.setString(1, "Michael");
			ps.setInt(2, 2);
			ps.addBatch();
			
			//ejecutar el batch
			int[] resultados = ps.executeBatch();
			
			conexion.commit();
			
			System.out.println("Inserciones en lote completadas. Filas afectadas: " + resultados.length);
			
			conexion.setAutoCommit(true);
			
		} catch (Exception e) {
			System.err.println("Error en la ejecuccion BATCH: " + e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
