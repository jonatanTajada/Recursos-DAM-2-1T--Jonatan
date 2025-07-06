package com.miempresa.tienda.sistema_gestion_tienda.dao;

import com.miempresa.tienda.sistema_gestion_tienda.modelo.Producto;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la gestión de productos en la base de datos.
 */
public class ProductoDAO {

	/**
	 * Obtiene todos los productos de la base de datos.
	 *
	 * @return Lista de productos.
	 */
	public List<Producto> obtenerTodos() {
	    List<Producto> productos = new ArrayList<>();

	    String sql = """
	            SELECT p.id, p.nombre, p.descripcion, p.precio, p.stock, p.activo,
	                   c.id AS id_categoria, c.nombre AS nombre_categoria
	            FROM productos p
	            JOIN categorias c ON p.categoria_id = c.id
	            """;

	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            Categoria categoria = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
	            Producto producto = new Producto(
	                rs.getInt("id"),
	                rs.getString("nombre"),
	                rs.getString("descripcion"),
	                rs.getInt("stock"),
	                rs.getDouble("precio"),
	                categoria,
	                rs.getBoolean("activo") // Campo añadido
	            );
	            productos.add(producto);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener productos: " + e.getMessage());
	    }

	    return productos;
	}


	/**
	 * Inserta un nuevo producto en la base de datos.
	 *
	 * @param producto Producto a insertar.
	 * @return true si la operación fue exitosa, false en caso contrario.
	 */
	public boolean insertar(Producto producto) {
		String sql = "INSERT INTO productos (nombre, descripcion, stock, precio, categoria_id) VALUES (?, ?, ?, ?, ?)";

		try (Connection con = ConexionDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, producto.getNombre());
			pstmt.setString(2, producto.getDescripcion());
			pstmt.setInt(3, producto.getStock());
			pstmt.setDouble(4, producto.getPrecio());
			pstmt.setInt(5, producto.getCategoria().getId());

			int filasAfectadas = pstmt.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar producto: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Actualiza un producto existente en la base de datos.
	 *
	 * @param producto Producto con los datos actualizados.
	 * @return true si la operación fue exitosa, false en caso contrario.
	 */
	public boolean actualizar(Producto producto) {
		String sql = """
				UPDATE productos
				SET nombre = ?, descripcion = ?, stock = ?, precio = ?, categoria_id = ?
				WHERE id = ?
				""";

		try (Connection con = ConexionDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, producto.getNombre());
			pstmt.setString(2, producto.getDescripcion());
			pstmt.setInt(3, producto.getStock());
			pstmt.setDouble(4, producto.getPrecio());
			pstmt.setInt(5, producto.getCategoria().getId());
			pstmt.setInt(6, producto.getId());

			int filasAfectadas = pstmt.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar producto: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un producto de la base de datos por su ID.
	 *
	 * @param id ID del producto a eliminar.
	 * @return true si la operación fue exitosa, false en caso contrario.
	 */
	public boolean eliminar(int id) {
		String sql = "DELETE FROM productos WHERE id = ?";

		try (Connection con = ConexionDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			int filasAfectadas = pstmt.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar producto: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene un producto de la base de datos por su ID.
	 *
	 * @param id ID del producto a obtener.
	 * @return El producto correspondiente al ID o null si no se encuentra.
	 */
	public Producto obtenerPorId(int id) {
	    String sql = """
	                SELECT p.id, p.nombre, p.descripcion, p.stock, p.precio, p.activo,
	                       c.id AS id_categoria, c.nombre AS nombre_categoria
	                FROM productos p
	                JOIN categorias c ON p.categoria_id = c.id
	                WHERE p.id = ?
	            """;

	    try (Connection con = ConexionDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setInt(1, id);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                Categoria categoria = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));

	                return new Producto(
	                    rs.getInt("id"),
	                    rs.getString("nombre"),
	                    rs.getString("descripcion"),
	                    rs.getInt("stock"),
	                    rs.getDouble("precio"),
	                    categoria,
	                    rs.getBoolean("activo") // Campo añadido
	                );
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener producto por ID: " + e.getMessage());
	    }

	    return null; // Si no se encuentra el producto
	}


	/**
	 * Verifica si ya existe un producto con el mismo nombre, excluyendo el producto
	 * actual.
	 *
	 * @param nombre   Nombre del producto a verificar.
	 * @param idActual ID del producto actual (se excluye de la verificación).
	 * @return true si el nombre ya está en uso, false en caso contrario.
	 */
	public boolean existeNombreDuplicado(String nombre, int idActual) {
		String sql = "SELECT COUNT(*) FROM productos WHERE nombre = ? AND id != ?";

		try (Connection con = ConexionDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, nombre);
			pstmt.setInt(2, idActual);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0; // Retorna true si hay duplicados
			}
		} catch (SQLException e) {
			System.err.println("Error al verificar duplicados: " + e.getMessage());
		}

		return false;
	}
	
	
	
	/**
	 * Cambia el estado de un producto en la base de datos.
	 *
	 * @param id    ID del producto.
	 * @param activo Nuevo estado del producto (true para activo, false para inactivo).
	 * @return true si la operación fue exitosa, false en caso contrario.
	 */
	public boolean cambiarEstado(int id, boolean activo) {
	    String sql = "UPDATE productos SET activo = ? WHERE id = ?";

	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setInt(1, activo ? 1 : 0); // Convertir booleano a 1 o 0
	        pstmt.setInt(2, id);

	        return pstmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Error al cambiar el estado del producto: " + e.getMessage());
	        return false;
	    }
	}



}
