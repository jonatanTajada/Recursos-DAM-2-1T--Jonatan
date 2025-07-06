package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.miempresa.tienda.sistema_gestion_tienda.dao.UsuarioDAO;
import com.miempresa.tienda.sistema_gestion_tienda.estilos.EstiloUI;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Usuario;

public class LoginVista {
	public static void main(String[] args) {
		JFrame ventana = new JFrame("Sistema Gestión Tienda - Login");
		ventana.setSize(700, 700);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// Barra superior
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

		// Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		panelPrincipal.setBackground(EstiloUI.COLOR_SECUNDARIO);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUsuario.setFont(EstiloUI.FUENTE_TEXTO);

		JTextField txtUsuario = new JTextField();
		txtUsuario.setMaximumSize(new Dimension(300, 30));

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPassword.setFont(EstiloUI.FUENTE_TEXTO);

		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setMaximumSize(new Dimension(300, 30));

		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setFont(EstiloUI.FUENTE_BOTON);
		btnLogin.setBackground(EstiloUI.COLOR_BOTON);
		btnLogin.setForeground(Color.WHITE);

		panelPrincipal.add(lblUsuario);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPrincipal.add(txtUsuario);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPrincipal.add(lblPassword);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPrincipal.add(txtPassword);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		panelPrincipal.add(btnLogin);

		ventana.add(panelPrincipal, BorderLayout.CENTER);

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				String password = new String(txtPassword.getPassword());

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				Usuario user = usuarioDAO.verificarCredenciales(usuario, password);

				if (user != null) {
					JOptionPane.showMessageDialog(ventana,
							"Inicio de sesión exitoso. Bienvenido " + user.getUsername());
					ventana.dispose();
				} else {
					JOptionPane.showMessageDialog(ventana, "Credenciales incorrectas. Inténtalo de nuevo.");
				}
			}
		});

		ventana.setVisible(true);
	}
}
