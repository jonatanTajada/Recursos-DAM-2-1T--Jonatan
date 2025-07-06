package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.miempresa.tienda.sistema_gestion_tienda.estilos.EstiloUI;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.CategoriaService;

/**
 * Clase que representa la pantalla principal del sistema de gestión de tienda.
 * Incluye el menú de navegación para acceder a los diferentes módulos.
 */
public class PantallaPrincipalVista {

	private CategoriaService categoriaService;

	/**
	 * Constructor de la clase que inicializa la vista principal.
	 *
	 * @param categoriaService Servicio para la gestión de categorías.
	 */
	public PantallaPrincipalVista(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;

		// Crear la ventana principal
		JFrame ventana = new JFrame("Sistema Gestión Tienda - Pantalla Principal");
		ventana.setSize(800, 700);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// -----------------------
		// Barra superior (nombre + logo)
		// -----------------------
		JPanel barraSuperior = new JPanel();
		barraSuperior.setLayout(new BorderLayout());
		barraSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		barraSuperior.setBackground(EstiloUI.COLOR_PRIMARIO);

		JLabel lblNombreEmpresa = new JLabel("Logistica Basque");
		lblNombreEmpresa.setFont(EstiloUI.FUENTE_TITULO);
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreEmpresa.setForeground(Color.WHITE);

		ImageIcon icono = EstiloUI.cargarIcono("logo.png");
		JLabel lblImagen = new JLabel(icono);

		barraSuperior.add(lblNombreEmpresa, BorderLayout.CENTER);
		barraSuperior.add(lblImagen, BorderLayout.EAST);

		ventana.add(barraSuperior, BorderLayout.NORTH);

		// -----------------------
		// Barra de menú
		// -----------------------
		JMenuBar barraMenu = new JMenuBar();

		// Menú de gestión
		JMenu menuGestion = new JMenu("Gestión");
		menuGestion.setFont(EstiloUI.FUENTE_TEXTO);

		JMenuItem itemCategorias = new JMenuItem("Categorías");
		JMenuItem itemProductos = new JMenuItem("Productos");
		JMenuItem itemUsuarios = new JMenuItem("Usuarios");
		JMenuItem itemPedidos = new JMenuItem("Pedidos");
		JMenuItem itemProveedores = new JMenuItem("Proveedores");
		JMenuItem itemReportes = new JMenuItem("Reportes");
		JMenuItem itemConfiguracion = new JMenuItem("Configuración");

		// Agregar ítems al menú de gestión
		menuGestion.add(itemCategorias);
		menuGestion.add(itemProductos);
		menuGestion.add(itemUsuarios);
		menuGestion.add(itemPedidos);
		menuGestion.add(itemProveedores);
		menuGestion.add(itemReportes);
		menuGestion.add(itemConfiguracion);

		// Menú de ayuda
		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setFont(EstiloUI.FUENTE_TEXTO);

		JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
		menuAyuda.add(itemAcercaDe);

		// Agregar menús a la barra
		barraMenu.add(menuGestion);
		barraMenu.add(menuAyuda);

		ventana.setJMenuBar(barraMenu);

		// -----------------------
		// Panel central (contenido principal)
		// -----------------------
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		panelCentral.setBackground(EstiloUI.COLOR_SECUNDARIO);

		JLabel lblBienvenida = new JLabel("Bienvenido al Sistema de Gestión de Tienda");
		lblBienvenida.setFont(EstiloUI.FUENTE_TITULO);
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

		panelCentral.add(lblBienvenida, BorderLayout.CENTER);
		ventana.add(panelCentral, BorderLayout.CENTER);

		// -----------------------
		// Eventos del menú
		// -----------------------
		itemCategorias.addActionListener(e -> {
		    new GestionCategoriasVista(categoriaService); // Abre la ventana de gestión de categorías
		});


		itemProductos.addActionListener(e -> {
			new GestionProductosVista(); // Abrir la ventana de gestión de productos
		});

		itemUsuarios.addActionListener(e -> {
			JOptionPane.showMessageDialog(ventana, "Módulo de Usuarios en construcción.");
		});

		itemPedidos.addActionListener(e -> {
			JOptionPane.showMessageDialog(ventana, "Módulo de Pedidos en construcción.");
		});

		itemProveedores.addActionListener(e -> {
			JOptionPane.showMessageDialog(ventana, "Módulo de Proveedores en construcción.");
		});

		itemReportes.addActionListener(e -> {
			JOptionPane.showMessageDialog(ventana, "Módulo de Reportes en construcción.");
		});

		itemConfiguracion.addActionListener(e -> {
			JOptionPane.showMessageDialog(ventana, "Módulo de Configuración en construcción.");
		});

		itemAcercaDe.addActionListener(e -> {
			JOptionPane.showMessageDialog(ventana, "Sistema Gestión Tienda\nVersión 1.0");
		});

		// Mostrar la ventana
		ventana.setVisible(true);
	}
}
