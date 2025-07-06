package com.miempresa.tienda.sistema_gestion_tienda.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;

/**
 * Clase que gestiona las operaciones CRUD de la tabla categorías.
 */
public class CategoriaDAO {

	/**
	 * Obtiene todas las categorías de la base de datos.
	 * 
	 * @return Lista de categorías.
	 */
	public List<Categoria> obtenerTodas() {
	    List<Categoria> categorias = new ArrayList<>();
	    String sql = "SELECT * FROM categorias";

	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement pstmt = crearPreparedStatement(con, sql, new Object[0]); // Array vacío
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            Categoria categoria = new Categoria();
	            categoria.setId(rs.getInt("id"));
	            categoria.setNombre(rs.getString("nombre"));
	            categoria.setDescripcion(rs.getString("descripcion"));
	            categoria.setActivo(rs.getBoolean("activo")); 
	            categorias.add(categoria);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener categorías: " + e.getMessage());
	    }

	    return categorias;
	}


	/**
	 * Inserta una nueva categoría en la base de datos.
	 * 
	 * @param categoria La categoría a insertar.
	 * @return true si la inserción fue exitosa, false en caso contrario.
	 */
	public boolean insertar(Categoria categoria) {
	    String sql = "INSERT INTO categorias (nombre, descripcion, activo) VALUES (?, ?, ?)";
	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setString(1, categoria.getNombre());
	        pstmt.setString(2, categoria.getDescripcion());
	        pstmt.setBoolean(3, categoria.isActivo());  // Debería pasar true por defecto al insertar
	        int filasAfectadas = pstmt.executeUpdate();
	        return filasAfectadas > 0;
	    } catch (SQLException e) {
	        System.err.println("Error al insertar categoría: " + e.getMessage());
	        return false;
	    }
	}





	/**
	 * Actualiza una categoría existente en la base de datos.
	 * 
	 * @param categoria La categoría con los datos actualizados.
	 * @return true si la actualización fue exitosa, false en caso contrario.
	 */
	public boolean actualizar(Categoria categoria) {
	    String sql = "UPDATE categorias SET nombre = ?, descripcion = ?, activo = ? WHERE id = ?";
	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setString(1, categoria.getNombre());
	        pstmt.setString(2, categoria.getDescripcion());
	        pstmt.setBoolean(3, categoria.isActivo());  // Esto debería actualizar correctamente el estado activo
	        pstmt.setInt(4, categoria.getId());
	        int filasAfectadas = pstmt.executeUpdate();
	        return filasAfectadas > 0;
	    } catch (SQLException e) {
	        System.err.println("Error al actualizar categoría: " + e.getMessage());
	        return false;
	    }
	}





	/**
	 * Elimina una categoría de la base de datos por su ID.
	 * 
	 * @param id ID de la categoría a eliminar.
	 * @return true si la eliminación fue exitosa, false en caso contrario.
	 */
	public boolean eliminar(int id) {
		String sql = "DELETE FROM categorias WHERE id = ?";
		return ejecutarActualizacion(sql, id);
	}

	/**
	 * Verifica si una categoría ya existe en la base de datos.
	 *
	 * @param nombre El nombre de la categoría a verificar.
	 * @return true si ya existe, false en caso contrario.
	 */
	public boolean existeCategoria(String nombre) {
		String sql = "SELECT 1 FROM categorias WHERE nombre = ?";
		try (Connection con = ConexionDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			manejarError("Error al verificar existencia de categoría", e);
		}
		return false;
	}

	/**
	 * Obtiene una categoría de la base de datos por su ID.
	 *
	 * @param id ID de la categoría a obtener.
	 * @return La categoría encontrada o null si no existe.
	 */
	public Categoria obtenerPorId(int id) {
		String sql = "SELECT * FROM categorias WHERE id = ?";
		List<Categoria> resultado = ejecutarConsultaLista(sql, id);
		return resultado.isEmpty() ? null : resultado.get(0);
	}

	// Métodos privados

	/**
	 * Ejecuta una consulta que devuelve una lista de categorías.
	 *
	 * @param sql        Consulta SQL.
	 * @param parametros Parámetros para la consulta.
	 * @return Lista de categorías.
	 */
	private List<Categoria> ejecutarConsultaLista(String sql, Object... parametros) {
		List<Categoria> categorias = new ArrayList<>();

		try (Connection con = ConexionDB.getConnection();
				PreparedStatement pstmt = crearPreparedStatement(con, sql, parametros);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
			    Categoria categoria = new Categoria(
			        rs.getInt("id"),
			        rs.getString("nombre"),
			        rs.getString("descripcion")
			    );
			    categorias.add(categoria);
			}

		} catch (SQLException e) {
			manejarError("Error al ejecutar consulta", e);
		}

		return categorias;
	}

	/**
	 * Ejecuta una operación de inserción, actualización o eliminación.
	 *
	 * @param sql        Consulta SQL.
	 * @param parametros Parámetros para la consulta.
	 * @return true si la operación afectó al menos una fila, false en caso
	 *         contrario.
	 */
	private boolean ejecutarActualizacion(String sql, Object... parametros) {
		try (Connection con = ConexionDB.getConnection();
				PreparedStatement pstmt = crearPreparedStatement(con, sql, parametros)) {

			int filasAfectadas = pstmt.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			manejarError("Error al ejecutar actualización", e);
		}
		return false;
	}

	/**
	 * Crea un objeto PreparedStatement con los parámetros especificados.
	 *
	 * @param con        La conexión a la base de datos.
	 * @param sql        La consulta SQL a ejecutar.
	 * @param parametros Un array de objetos que representan los valores a asignar a
	 *                   los parámetros de la consulta. Puede estar vacío si no hay
	 *                   parámetros.
	 * @return Un PreparedStatement listo para ser ejecutado.
	 * @throws SQLException Si ocurre un error al preparar la consulta.
	 */
	private PreparedStatement crearPreparedStatement(Connection con, String sql, Object[] parametros)
			throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(sql);

		// Si se proporcionan parámetros, asignarlos al PreparedStatement
		if (parametros != null) {
			for (int i = 0; i < parametros.length; i++) {
				pstmt.setObject(i + 1, parametros[i]); // Los índices de los parámetros comienzan en 1
			}
		}

		return pstmt;
	}

	/**
	 * Maneja un error SQL.
	 *
	 * @param mensaje Mensaje descriptivo del error.
	 * @param e       Excepción SQL.
	 */
	private void manejarError(String mensaje, SQLException e) {
		System.err.println(mensaje + ": " + e.getMessage());
	}
	
	/**
	 * Verifica si una categoría tiene productos asociados en la base de datos.
	 * 
	 * Este método consulta la base de datos para verificar si existen productos 
	 * asociados a la categoría especificada mediante su ID. Si hay al menos un 
	 * producto con el `categoria_id` correspondiente, el método devuelve `true`, 
	 * indicando que la categoría tiene productos asociados. Si no hay productos 
	 * asociados o si ocurre un error, el método devuelve `false`.
	 * 
	 * @param categoriaId El ID de la categoría que se desea verificar.
	 * @return `true` si la categoría tiene productos asociados, `false` en caso contrario.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta.
	 */
	public boolean tieneProductosAsociados(int categoriaId) {
	    String sql = "SELECT COUNT(*) FROM productos WHERE categoria_id = ?";
	    
	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setInt(1, categoriaId);  // Establece el parámetro en la consulta
	        try (ResultSet rs = pstmt.executeQuery()) { // Ejecuta la consulta y maneja el ResultSet
	            if (rs.next()) {
	                // Si el conteo es mayor que 0, significa que hay productos asociados
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al verificar productos asociados para la categoría con ID " + categoriaId + ": " + e.getMessage());
	    }
	    
	    return false; // Si no se encuentra ningún producto asociado o hay un error, devuelve false
	}



	
}
