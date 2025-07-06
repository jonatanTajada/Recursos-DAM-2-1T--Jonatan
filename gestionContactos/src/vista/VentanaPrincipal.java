package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
	private JTable tablaContactos;
	private JButton botonAgregar, botonEditar, botonEliminar, botonActualizar;
	private JMenuBar menuBar;
	private JMenu menuOpciones;
	private JMenuItem gestionGrupos, salir;

	public VentanaPrincipal() {
		setTitle("Gestión de Contactos");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel con imagen de fondo
		setContentPane(new JLabel(new ImageIcon("ruta/a/tu/imagen.jpg"))); // Añadir imagen de fondo
		setLayout(new BorderLayout());

		// Crear el menú
		menuBar = new JMenuBar();
		menuOpciones = new JMenu("Opciones");
		gestionGrupos = new JMenuItem("Gestionar Grupos");
		salir = new JMenuItem("Salir");
		menuOpciones.add(gestionGrupos);
		menuOpciones.add(salir);
		menuBar.add(menuOpciones);
		setJMenuBar(menuBar);

		// Crear la tabla de contactos
		String[] columnas = { "Nombre", "Teléfono", "Email", "Dirección" };
		tablaContactos = new JTable(new Object[0][0], columnas);
		add(new JScrollPane(tablaContactos), BorderLayout.CENTER);

		// Crear los botones CRUD
		JPanel panelBotones = new JPanel();
		botonAgregar = new JButton("Agregar");
		botonEditar = new JButton("Editar");
		botonEliminar = new JButton("Eliminar");
		botonActualizar = new JButton("Actualizar");
		panelBotones.add(botonAgregar);
		panelBotones.add(botonEditar);
		panelBotones.add(botonEliminar);
		panelBotones.add(botonActualizar);
		add(panelBotones, BorderLayout.SOUTH);
	}

	// Getters para los elementos de la interfaz
	public JTable getTablaContactos() {
		return tablaContactos;
	}

	public JButton getBotonAgregar() {
		return botonAgregar;
	}

	public JButton getBotonEditar() {
		return botonEditar;
	}

	public JButton getBotonEliminar() {
		return botonEliminar;
	}

	public JButton getBotonActualizar() {
		return botonActualizar;
	}

	public JMenuItem getGestionGrupos() {
		return gestionGrupos;
	}

	public JMenuItem getSalir() {
		return salir;
	}
}
