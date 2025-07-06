package com.miempresa.tienda.sistema_gestion_tienda.modelo;

/**
 * Clase que representa una categoría en el sistema.
 */
public class Categoria {
	private int id;
	private String nombre;
	private String descripcion; // Si tienes la descripción
	private boolean activo; // Nueva propiedad

	/**
	 * Constructor vacío.
	 */
	public Categoria() {
	}

	/**
	 * Constructor con ID y nombre.
	 *
	 * @param id     ID de la categoría.
	 * @param nombre Nombre de la categoría.
	 */
	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.activo = true; // Por defecto, una categoría nueva es activa
	}

	/**
	 * Constructor que inicializa una categoría con su ID, nombre, descripción y estado de actividad.
	 *
	 * @param id          Identificador único de la categoría.
	 * @param nombre      Nombre de la categoría.
	 * @param descripcion Descripción de la categoría.
	 * @param activo      Indica si la categoría está activa (true) o inactiva (false).
	 */
	public Categoria(int id, String nombre, String descripcion, boolean activo) {
	    this.id = id;
	    this.nombre = nombre;
	    this.descripcion = descripcion;
	    this.activo = true;
	}


	/**
	 * Constructor que inicializa una categoría con su ID, nombre y descripción.
	 *
	 * @param id         Identificador único de la categoría.
	 * @param nombre     Nombre de la categoría.
	 * @param descripcion Descripción de la categoría.
	 */
	public Categoria(int id, String nombre, String descripcion) {
	    this.id = id;
	    this.nombre = nombre;
	    this.descripcion = descripcion;
	}

	// Getters y setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Categoria{" + "id=" + id + ", nombre='" + nombre + '\'' + ", descripcion='" + descripcion + '\''
				+ ", activo=" + activo + '}';
	}
}
