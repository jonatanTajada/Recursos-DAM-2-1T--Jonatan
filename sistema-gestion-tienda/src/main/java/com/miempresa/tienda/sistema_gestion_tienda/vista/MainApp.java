package com.miempresa.tienda.sistema_gestion_tienda.vista;

import javax.swing.*;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.CategoriaService;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.Impl.CategoriaServiceImpl;
import com.miempresa.tienda.sistema_gestion_tienda.dao.CategoriaDAO;

public class MainApp {
	public static void main(String[] args) {
		// Crear el servicio para las categorías
		CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaDAO());

		// Ejecutar la ventana principal
		SwingUtilities.invokeLater(() -> {
			new PantallaPrincipalVista(categoriaService);
		});
	}
}
