package com.miempresa.tienda.sistema_gestion_tienda.modelo;

/**
 * Clase que representa un producto en el sistema.
 */
public class Producto {

	private int id;
	private String nombre;
	private String descripcion; 
	private int stock;
	private double precio;
	private Categoria categoria;
	private boolean activo;

	// Constructor vacío
	public Producto() {
	}

	// Constructor completo
	public Producto(int id, String nombre, String descripcion, int stock, double precio, Categoria categoria, boolean activo) {
	    this.id = id;
	    this.nombre = nombre;
	    this.descripcion = descripcion;
	    this.stock = stock;
	    this.precio = precio;
	    this.categoria = categoria;
	    this.activo = activo;
	}


	// Getters y Setters
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public boolean isActivo() {
	    return activo;
	}

	public void setActivo(boolean activo) {
	    this.activo = activo;
	}

	@Override
	public String toString() {
	    return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", stock=" + stock
	            + ", precio=" + precio + ", categoria=" + categoria.getNombre() + ", activo=" + (activo ? "Activo" : "Inactivo") + "]";
	}

}
