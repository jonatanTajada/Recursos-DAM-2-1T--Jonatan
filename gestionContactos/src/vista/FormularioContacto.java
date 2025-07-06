package vista;

import javax.swing.*;
import java.awt.*;

public class FormularioContacto extends JFrame {
	
	
	private static final long serialVersionUID = 3418648998703096250L;
	private JTextField campoNombre, campoTelefono, campoEmail, campoDireccion;
	private JButton botonGuardar, botonCancelar;

	public FormularioContacto() {
		setTitle("Formulario de Contacto");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Etiquetas y campos de texto
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Nombre:"), gbc);
		gbc.gridx = 1;
		campoNombre = new JTextField(20);
		add(campoNombre, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("Teléfono:"), gbc);
		gbc.gridx = 1;
		campoTelefono = new JTextField(20);
		add(campoTelefono, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(new JLabel("Email:"), gbc);
		gbc.gridx = 1;
		campoEmail = new JTextField(20);
		add(campoEmail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(new JLabel("Dirección:"), gbc);
		gbc.gridx = 1;
		campoDireccion = new JTextField(20);
		add(campoDireccion, gbc);

		// Botones
		JPanel panelBotones = new JPanel();
		botonGuardar = new JButton("Guardar");
		botonCancelar = new JButton("Cancelar");
		panelBotones.add(botonGuardar);
		panelBotones.add(botonCancelar);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		add(panelBotones, gbc);
	}

	// Getters para los campos y botones
	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public JTextField getCampoTelefono() {
		return campoTelefono;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JTextField getCampoDireccion() {
		return campoDireccion;
	}

	public JButton getBotonGuardar() {
		return botonGuardar;
	}

	public JButton getBotonCancelar() {
		return botonCancelar;
	}
}
