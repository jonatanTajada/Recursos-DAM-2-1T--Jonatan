package com.miempresa.tienda.sistema_gestion_tienda.pruebas;

import com.miempresa.tienda.sistema_gestion_tienda.dao.CategoriaDAO;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.CategoriaService;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.Impl.CategoriaServiceImpl;
import com.miempresa.tienda.sistema_gestion_tienda.vista.PantallaPrincipalVista;

public class PruebaPantallaPrincipal {
	public static void main(String[] args) {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		CategoriaService categoriaService = new CategoriaServiceImpl(categoriaDAO);

		new PantallaPrincipalVista(categoriaService);
	}
}
