package com.miempresa.tienda.sistema_gestion_tienda.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos MySQL.
 */
public class ConexionDB {

    public static Connection getConnection() {
        try {
            String url = ConfigReader.getProperty("db.url");
            String user = ConfigReader.getProperty("db.user");
            String password = ConfigReader.getProperty("db.password");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
