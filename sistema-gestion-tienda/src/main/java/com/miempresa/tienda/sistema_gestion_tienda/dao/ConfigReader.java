package com.miempresa.tienda.sistema_gestion_tienda.dao;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase para leer las configuraciones desde el archivo config.properties.
 */
public class ConfigReader {

    private static Properties properties = new Properties();

    static {
    	
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
        	
            if (input == null) {
            	
                throw new RuntimeException("No se encontró el archivo config.properties");
            }
            
            properties.load(input);
        } catch (IOException e) {
        	
            throw new RuntimeException("Error al cargar el archivo config.properties: " + e.getMessage());
        }
    }

    /**
     * Obtiene el valor de una propiedad.
     *
     * @param key Clave de la propiedad.
     * @return Valor de la propiedad.
     */
    public static String getProperty(String key) {
    	
        return properties.getProperty(key);
    }
}

