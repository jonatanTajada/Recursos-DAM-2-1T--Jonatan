package PruebasExamenesPrimerTrimestre.MIAS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ListaDeTareas {

	public static void main(String[] args) {

		// Crear la ventana
		JFrame ventana = new JFrame("Lista de Tareas");
		ventana.setSize(400, 500);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// Modelo de lista y JList para mostrar las tareas
		DefaultListModel<String> modeloLista = new DefaultListModel<>();
		JList<String> listaTareas = new JList<>(modeloLista);
		listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ventana.add(new JScrollPane(listaTareas), BorderLayout.CENTER);

		// Panel superior para entrada de tarea y botón de añadir
		JPanel panelSuperior = new JPanel(new BorderLayout());
		JTextField campoTarea = new JTextField();
		JButton botonAñadir = new JButton("Añadir Tarea");
		panelSuperior.add(campoTarea, BorderLayout.CENTER);
		panelSuperior.add(botonAñadir, BorderLayout.EAST);
		ventana.add(panelSuperior, BorderLayout.NORTH);

		// Panel inferior con botones de "Marcar como Completada" y "Eliminar"
		JPanel panelInferior = new JPanel();
		JButton botonCompletar = new JButton("Marcar como Completada");
		JButton botonEliminar = new JButton("Eliminar Tarea");
		panelInferior.add(botonCompletar);
		panelInferior.add(botonEliminar);
		ventana.add(panelInferior, BorderLayout.SOUTH);

		// Lista para llevar el estado de las tareas (completadas o no)
		List<Boolean> tareasCompletadas = new ArrayList<>();

		// Acción para añadir una nueva tarea
		botonAñadir.addActionListener((ActionEvent e) -> {
			String tarea = campoTarea.getText().trim();
			if (!tarea.isEmpty()) {
				modeloLista.addElement(tarea);
				tareasCompletadas.add(false); // Añadir como no completada
				campoTarea.setText("");
			}
		});

		// Acción para marcar una tarea como completada
		botonCompletar.addActionListener((ActionEvent e) -> {
			int index = listaTareas.getSelectedIndex();
			if (index != -1 && !tareasCompletadas.get(index)) {
				String tarea = modeloLista.get(index) + " (Completada)";
				modeloLista.set(index, tarea);
				tareasCompletadas.set(index, true);
			}
		});

		// Acción para eliminar una tarea
		botonEliminar.addActionListener((ActionEvent e) -> {
			int index = listaTareas.getSelectedIndex();
			if (index != -1) {
				modeloLista.remove(index);
				tareasCompletadas.remove(index);
			}
		});

		// Mostrar la ventana
		ventana.setVisible(true);
	}
}
