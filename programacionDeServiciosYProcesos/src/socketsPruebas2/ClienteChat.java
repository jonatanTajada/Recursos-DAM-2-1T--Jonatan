package socketsPruebas2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClienteChat extends JFrame {
	
	
	private JTextArea areaMensajes;
	private JTextField campoEntrada;
	private JButton botonEnviar;
	private PrintWriter salida;
	private BufferedReader entrada;
	private String nombreUsuario;
	private Socket socket;

	public ClienteChat(String nombreUsuario) {
		super("Chat - " + nombreUsuario);
		this.nombreUsuario = nombreUsuario;
		inicializarUI();
		conectarAlServidor();

		// Listener para cerrar el programa correctamente
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				desconectar();
				System.exit(0);
			}
		});
	}

	private void inicializarUI() {
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		areaMensajes = new JTextArea();
		areaMensajes.setEditable(false);
		JScrollPane scroll = new JScrollPane(areaMensajes);
		add(scroll, BorderLayout.CENTER);

		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());
		campoEntrada = new JTextField();
		botonEnviar = new JButton("Enviar");
		panelInferior.add(campoEntrada, BorderLayout.CENTER);
		panelInferior.add(botonEnviar, BorderLayout.EAST);
		add(panelInferior, BorderLayout.SOUTH);

		botonEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enviarMensaje();
			}
		});

		setVisible(true);
	}

	private void conectarAlServidor() {
		try {
			socket = new Socket("localhost", 12345);
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			salida = new PrintWriter(socket.getOutputStream(), true);

			salida.println(nombreUsuario + " se ha unido al chat.");

			new Thread(() -> {
				try {
					String mensaje;
					while ((mensaje = entrada.readLine()) != null) {
						if (mensaje.startsWith(nombreUsuario + ":")) {
							mensaje = mensaje.replace(nombreUsuario + ":", "Tú:");
						}
						areaMensajes.append(mensaje + "\n");
					}
				} catch (IOException e) {
					areaMensajes.append("Desconexión del servidor.\n");
				}
			}).start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor.");
			System.exit(0);
		}
	}

	private void enviarMensaje() {
		String mensaje = campoEntrada.getText();
		if (!mensaje.isEmpty()) {
			if (mensaje.equalsIgnoreCase("salir")) {
				desconectar();
				System.exit(0);
			} else {
				salida.println(nombreUsuario + ": " + mensaje);
				campoEntrada.setText("");
			}
		}
	}

	private void desconectar() {
		try {
			if (salida != null) {
				salida.println(nombreUsuario + " ha salido del chat.");
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String nombreUsuario = JOptionPane.showInputDialog("Introduce tu nombre de usuario:");
		if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
			new ClienteChat(nombreUsuario);
		}
	}
}
