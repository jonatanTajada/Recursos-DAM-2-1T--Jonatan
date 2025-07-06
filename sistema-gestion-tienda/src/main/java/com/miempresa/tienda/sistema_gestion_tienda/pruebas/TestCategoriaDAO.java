package com.miempresa.tienda.sistema_gestion_tienda.pruebas;

import java.util.List;

import com.miempresa.tienda.sistema_gestion_tienda.dao.CategoriaDAO;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;

public class TestCategoriaDAO {
    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        // Probar inserción
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre("Electrónica");
        if (categoriaDAO.insertar(nuevaCategoria)) {
            System.out.println("Categoría insertada correctamente.");
        } else {
            System.out.println("Error al insertar categoría.");
        }

        // Probar obtención de categorías
        try {
            List<Categoria> categorias = categoriaDAO.obtenerTodas();
            System.out.println("Categorías actuales:");
            if (categorias.isEmpty()) {
                System.out.println("La tabla está vacía.");
            } else {
                for (Categoria categoria : categorias) {
                    System.out.println(categoria);
                }
            }
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

