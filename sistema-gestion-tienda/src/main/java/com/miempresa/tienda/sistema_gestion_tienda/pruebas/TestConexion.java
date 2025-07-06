package com.miempresa.tienda.sistema_gestion_tienda.pruebas;


import java.sql.Connection;

import com.miempresa.tienda.sistema_gestion_tienda.dao.ConexionDB;

public class TestConexion {
	
    public static void main(String[] args) {
    	
        try (Connection connection = ConexionDB.getConnection()) {
        	
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (Exception e) {
        	
            System.err.println("Error: " + e.getMessage());
        }
    }
}
