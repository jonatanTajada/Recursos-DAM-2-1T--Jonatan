package com.miempresa.tienda.sistema_gestion_tienda.servicios.Impl;

import com.miempresa.tienda.sistema_gestion_tienda.dao.CategoriaDAO;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.CategoriaService;

import java.util.List;

/**
 * Implementación de la capa de servicios para gestionar las categorías.
 * Proporciona la lógica de negocio necesaria antes de delegar operaciones al
 * DAO.
 */
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaDAO categoriaDAO;

	/**
	 * Constructor que inyecta el DAO de categorías.
	 * 
	 * @param categoriaDAO DAO para realizar operaciones en la base de datos.
	 */
	public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	/**
	 * Obtiene todas las categorías.
	 * 
	 * @return Lista de todas las categorías.
	 */
	@Override
	public List<Categoria> obtenerTodas() {
		return categoriaDAO.obtenerTodas();
	}

	/**
	 * Obtiene una categoría por su ID.
	 * 
	 * @param id ID de la categoría a obtener.
	 * @return La categoría encontrada o null si no existe.
	 */
	@Override
	public Categoria obtenerPorId(int id) {
		return categoriaDAO.obtenerPorId(id);
	}

	/**
	 * Guarda una nueva categoría después de verificar que no exista otra con el
	 * mismo nombre.
	 * 
	 * @param categoria La categoría a guardar.
	 * @return true si la categoría fue guardada correctamente, false en caso
	 *         contrario.
	 * @throws IllegalArgumentException si la categoría ya existe.
	 */
	   @Override
	   public boolean guardar(Categoria categoria) {
	       if (categoriaDAO.existeCategoria(categoria.getNombre())) {
	           throw new IllegalArgumentException("La categoría ya existe");
	       }
	       categoria.setActivo(true); // Asegura que la categoría nueva esté activa
	       return categoriaDAO.insertar(categoria);
	   }



	/**
	 * Actualiza una categoría existente después de verificar que el nuevo nombre no
	 * esté duplicado.
	 * 
	 * @param categoria La categoría con los datos actualizados.
	 * @return true si la categoría fue actualizada correctamente, false en caso
	 *         contrario.
	 * @throws IllegalArgumentException si el nuevo nombre ya está en uso por otra
	 *                                  categoría.
	 */
	   @Override
	   public boolean actualizar(Categoria categoria) {
	       Categoria categoriaActual = categoriaDAO.obtenerPorId(categoria.getId());
	       if (categoriaActual == null) {
	           throw new IllegalArgumentException("La categoría no existe");
	       }

	       if (!categoria.getNombre().equals(categoriaActual.getNombre()) && categoriaDAO.existeCategoria(categoria.getNombre())) {
	           throw new IllegalArgumentException("El nombre de la categoría ya está en uso");
	       }

	       return categoriaDAO.actualizar(categoria);
	   }



	/**
	 * Elimina una categoría por su ID.
	 * 
	 * @param id ID de la categoría a eliminar.
	 * @return true si la categoría fue eliminada correctamente, false en caso
	 *         contrario.
	 */
	   @Override
	   public boolean eliminar(int id) {
	       // Verificar si la categoría está referenciada en productos
	       if (categoriaDAO.tieneProductosAsociados(id)) {
	           throw new IllegalStateException("No se puede eliminar la categoría porque tiene productos asociados.");
	       }
	       return categoriaDAO.eliminar(id);
	   }


}
