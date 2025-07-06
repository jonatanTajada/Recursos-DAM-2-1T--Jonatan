package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Tarea;
import vista.VentanaPrincipal;

public class ControladorTareas {
	private VentanaPrincipal vista;
	private ArrayList<Tarea> listaTareas;

	public ControladorTareas(VentanaPrincipal vista) {
		this.vista = vista;
		this.listaTareas = new ArrayList<>();
		inicializarControlador();
	}

	private void inicializarControlador() {
		// Agregar tarea
		vista.getBotonAgregar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion = vista.getCampoTarea().getText();
				if (!descripcion.isEmpty()) {
					Tarea nuevaTarea = new Tarea(descripcion);
					listaTareas.add(nuevaTarea);
					actualizarVista();
					vista.getCampoTarea().setText(""); // Limpiar campo
				}
			}
		});

		// Guardar tareas en archivo
		vista.getBotonGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarTareasEnArchivo();
			}
		});

		// Alternar estado de la tarea seleccionada
		vista.getBotonCambiarEstado().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alternarEstadoTarea();
			}
		});

		// Eliminar tarea seleccionada
		vista.getBotonEliminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarTarea();
			}
		});
	}

	private void actualizarVista() {
		vista.getAreaTareas().setText("");
		for (int i = 0; i < listaTareas.size(); i++) {
			Tarea tarea = listaTareas.get(i);
			vista.getAreaTareas().append((i + 1) + ". " + tarea.toString() + "\n");
		}
	}

	private void alternarEstadoTarea() {
		String input = JOptionPane.showInputDialog(vista, "Introduce el número de la tarea para alternar el estado:");
		try {
			int indice = Integer.parseInt(input) - 1;
			if (indice >= 0 && indice < listaTareas.size()) {
				listaTareas.get(indice).cambiarEstado();
				actualizarVista();
			} else {
				JOptionPane.showMessageDialog(vista, "Número de tarea inválido.");
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vista, "Entrada inválida.");
		}
	}

	private void eliminarTarea() {
		String input = JOptionPane.showInputDialog(vista, "Introduce el número de la tarea para eliminar:");
		try {
			int indice = Integer.parseInt(input) - 1;
			if (indice >= 0 && indice < listaTareas.size()) {
				listaTareas.remove(indice);
				actualizarVista();
			} else {
				JOptionPane.showMessageDialog(vista, "Número de tarea inválido.");
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vista, "Entrada inválida.");
		}
	}

	private void guardarTareasEnArchivo() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("tareas.txt"))) {
			for (Tarea tarea : listaTareas) {
				writer.write(tarea.toString() + "\n");
			}
			JOptionPane.showMessageDialog(vista, "Tareas guardadas correctamente.");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(vista, "Error al guardar las tareas.");
		}
	}
}
