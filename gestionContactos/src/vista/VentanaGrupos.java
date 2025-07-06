package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaGrupos extends JFrame {
	
	
	private static final long serialVersionUID = -5393149704368844465L;
	private JTextField campoNombreGrupo;
	private JButton botonAgregarGrupo, botonEliminarGrupo;
	private JList<String> listaGrupos;
	private DefaultListModel<String> modeloLista;

	public VentanaGrupos() {
		setTitle("Gestión de Grupos");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		// Panel para agregar grupos
		JPanel panelAgregar = new JPanel();
		panelAgregar.setLayout(new FlowLayout());
		campoNombreGrupo = new JTextField(15);
		botonAgregarGrupo = new JButton("Agregar Grupo");
		panelAgregar.add(new JLabel("Nombre del Grupo:"));
		panelAgregar.add(campoNombreGrupo);
		panelAgregar.add(botonAgregarGrupo);
		add(panelAgregar, BorderLayout.NORTH);

		// Lista de grupos
		modeloLista = new DefaultListModel<>();
		listaGrupos = new JList<>(modeloLista);
		add(new JScrollPane(listaGrupos), BorderLayout.CENTER);

		// Botón para eliminar grupo
		botonEliminarGrupo = new JButton("Eliminar Grupo");
		add(botonEliminarGrupo, BorderLayout.SOUTH);
	}

	// Getters para los componentes
	public JTextField getCampoNombreGrupo() {
		return campoNombreGrupo;
	}

	public JButton getBotonAgregarGrupo() {
		return botonAgregarGrupo;
	}

	public JButton getBotonEliminarGrupo() {
		return botonEliminarGrupo;
	}

	public DefaultListModel<String> getModeloLista() {
		return modeloLista;
	}

	public JList<String> getListaGrupos() {
		return listaGrupos;
	}
}
