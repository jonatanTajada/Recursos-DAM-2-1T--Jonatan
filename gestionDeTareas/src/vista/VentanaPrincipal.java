package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
	private JTextField campoTarea;
	private JButton botonAgregar;
	private JTextArea areaTareas;
	private JButton botonGuardar;
	private JButton botonCambiarEstado;
	private JButton botonEliminar;

	public VentanaPrincipal() {
		setTitle("Gestión de Tareas");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Panel superior con el campo para agregar tareas
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout());
		campoTarea = new JTextField(20);
		botonAgregar = new JButton("Agregar Tarea");
		panelSuperior.add(campoTarea);
		panelSuperior.add(botonAgregar);

		// Área para mostrar las tareas
		areaTareas = new JTextArea();
		areaTareas.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(areaTareas);

		// Botones para guardar, cambiar estado y eliminar tareas
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		botonGuardar = new JButton("Guardar Tareas");
		botonCambiarEstado = new JButton("Cambiar estado a la Tarea");
		botonEliminar = new JButton("Eliminar Tarea");
		panelBotones.add(botonCambiarEstado);
		panelBotones.add(botonEliminar);
		panelBotones.add(botonGuardar);

		// Añadir componentes a la ventana
		add(panelSuperior, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
	}

	public JTextField getCampoTarea() {
		return campoTarea;
	}

	public JButton getBotonAgregar() {
		return botonAgregar;
	}

	public JTextArea getAreaTareas() {
		return areaTareas;
	}

	public JButton getBotonGuardar() {
		return botonGuardar;
	}

	public JButton getBotonCambiarEstado() {
		return botonCambiarEstado;
	}

	public JButton getBotonEliminar() {
		return botonEliminar;
	}
}
