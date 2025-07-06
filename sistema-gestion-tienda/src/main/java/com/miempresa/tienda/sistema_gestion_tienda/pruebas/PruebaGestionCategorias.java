package com.miempresa.tienda.sistema_gestion_tienda.pruebas;

import com.miempresa.tienda.sistema_gestion_tienda.dao.CategoriaDAO;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.CategoriaService;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.Impl.CategoriaServiceImpl;

import java.util.List;

public class PruebaGestionCategorias {

    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        CategoriaService categoriaService = new CategoriaServiceImpl(categoriaDAO);

        System.out.println("Obteniendo todas las categorías desde la capa DAO...");
        List<Categoria> categorias = categoriaService.obtenerTodas();
        categorias.forEach(System.out::println);

        // Intentar guardar una nueva categoría única
        System.out.println("\nGuardando una nueva categoría...");
        Categoria nuevaCategoria = new Categoria(0, "Instrumentos Musicales", "Categoría para instrumentos", true);
        try {
            boolean guardado = categoriaService.guardar(nuevaCategoria);
            System.out.println("Categoría guardada: " + guardado);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al guardar la categoría: " + e.getMessage());
        }

        // Listar categorías después del intento de guardar
        System.out.println("\nObteniendo todas las categorías después del guardado...");
        categorias = categoriaService.obtenerTodas();
        categorias.forEach(System.out::println);

        // Actualizar una categoría existente
        System.out.println("\nActualizando la categoría con ID 1...");
        Categoria categoriaAActualizar = categoriaService.obtenerPorId(1);
        if (categoriaAActualizar != null) {
            categoriaAActualizar.setDescripcion("Artículos electrónicos actualizados");
            boolean actualizado = categoriaService.actualizar(categoriaAActualizar);
            System.out.println("Categoría actualizada: " + actualizado);
        } else {
            System.err.println("La categoría con ID 1 no existe.");
        }

        // Cambiar el estado de activo/inactivo de una categoría
        System.out.println("\nCambiando el estado de la categoría con ID 2...");
        Categoria categoriaACambiar = categoriaService.obtenerPorId(2);
        if (categoriaACambiar != null) {
            categoriaACambiar.setActivo(!categoriaACambiar.isActivo());
            boolean cambiado = categoriaService.actualizar(categoriaACambiar);
            System.out.println("Estado cambiado: " + cambiado);
        } else {
            System.err.println("La categoría con ID 2 no existe.");
        }

        // Eliminar una categoría existente
        System.out.println("\nEliminando la categoría con ID 3...");
        boolean eliminado = categoriaService.eliminar(3);
        System.out.println("Categoría eliminada: " + eliminado);

        // Listar categorías después de eliminar
        System.out.println("\nObteniendo todas las categorías después de eliminar...");
        categorias = categoriaService.obtenerTodas();
        categorias.forEach(System.out::println);
    }
}
