package PrimerosEjerciciosTarea1Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioLoginStreams extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioLoginStreams frame = new FormularioLoginStreams();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioLoginStreams() {
		setTitle("Formulario Login");
		setSize(850, 450);
		setLocation(290, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblUsuario = new JLabel("Usuario: ");
		panelCenter.add(lblUsuario);

		txtUsuario = new JTextField();
		panelCenter.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblPassword = new JLabel("Contraseña: ");
		panelCenter.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		panelCenter.add(txtPassword);

		// botonInciarSesion
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.addActionListener(e -> {

			String nombre = txtUsuario.getText();
			String password = new String(txtPassword.getPassword());

			if (validarUsuario(nombre, password)) {
				lblMensaje.setText("Bienvenido " + nombre + "!");
			} else {
				
				// Preguntar si el usuario desea registrarse
				int respuesta = JOptionPane.showConfirmDialog(null, "Usuario no encontrado. ¿Quieres registrarte?",
						"Registro", JOptionPane.YES_NO_OPTION);

				// Si elige "Sí", registrar al usuario
				if (respuesta == JOptionPane.YES_OPTION) {
					registrarUsuario(nombre, password);
					lblMensaje.setText("Registro realizado con éxito. ¡Bienvenido, " + nombre + "!");
				}
				// Si elige "No", mostrar mensaje de agradecimiento y cerrar la aplicación
				else if (respuesta == JOptionPane.NO_OPTION) {
					lblMensaje.setText("Gracias. Cerrando aplicación...");
					JOptionPane.showMessageDialog(null, "Gracias por usar nuestra aplicación.");
					System.exit(0); // Cierra la aplicación
				}
			}
		});
		panelCenter.add(btnIniciarSesion);

		// botonRegistrarUsuario
		JButton btnRegistrarUsuario = new JButton("Regístrarse");
		btnRegistrarUsuario.addActionListener(e -> {

			String nombre = txtUsuario.getText();
			String password = new String(txtPassword.getPassword());

			if (nombre.isBlank() || password.isBlank()) {
				lblMensaje.setText("Los campos nombre y contraseña no pueden estar vacios!");
				return;
			}

			if (validarUsuario(nombre, password)) {
				lblMensaje.setText("El usuario ya existe. Inicia sesion");

			} else {
				registrarUsuario(nombre, password);
				lblMensaje.setText("Registro realizado con exito. Bienvenido, " + nombre + "!");
			}

		});
		panelCenter.add(btnRegistrarUsuario);

		lblMensaje = new JLabel("");
		panelCenter.add(lblMensaje);

	}

	// --------------metodos-----------------------

	private boolean validarUsuario(String nombre, String password) {
		try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(",");
				if (datos.length == 2) {
					String usuarioArchivo = datos[0];
					String passwordArchivo = datos[1];
					if (usuarioArchivo.equals(nombre) && passwordArchivo.equals(password)) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void registrarUsuario(String nombre, String password) {
		try (PrintWriter pw = new PrintWriter(new FileWriter("usuarios.txt", true))) {
			pw.println(nombre + "," + password);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
