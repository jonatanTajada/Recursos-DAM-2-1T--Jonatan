package com.miempresa.tienda.sistema_gestion_tienda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.miempresa.tienda.sistema_gestion_tienda.modelo.Usuario;

/**
 * Clase que gestiona las operaciones relacionadas con los usuarios.
 */
public class UsuarioDAO {

	/**
	 * Verifica si un usuario existe en la base de datos con las credenciales
	 * proporcionadas.
	 *
	 * @param username Nombre de usuario.
	 * @param password Contraseña.
	 * @return El usuario si las credenciales son correctas, null en caso contrario.
	 */
	public Usuario verificarCredenciales(String username, String password) {
		String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
		Usuario usuario = null;

		try (Connection con = ConexionDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setUsername(rs.getString("username"));
					usuario.setPassword(rs.getString("password"));
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al verificar credenciales: " + e.getMessage());
		}

		return usuario;
	}
}
