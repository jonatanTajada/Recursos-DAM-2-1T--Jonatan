package componentesJavaSwingEjercicios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints; // Se agregó esta importación para manejar el layout correctamente
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


//Realizado sin windowsBuilder
//Ejercicio 1/4
public class Saludador extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Saludador frame = new Saludador();
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
	public Saludador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(550, 450);
		setLocation(350, 200);
		setLayout(new BorderLayout());

		// Panel central
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Cargar imagen de fondo
				ImageIcon imageIcon = new ImageIcon("java.jpg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar los componentes

		// Crear componentes
		JLabel lblUsuario = new JLabel("Escriba su nombre y dale al boton saludar");
		JTextField txtUsuario = new JTextField(15);
		JButton btnSaludar = new JButton(""
				+ "¡Saludar!");

		// Añadir los componentes al panel
		GridBagConstraints gbc = new GridBagConstraints(); // Se añadio GridBagConstraints para gestionar el
															
		gbc.insets = new Insets(10, 10, 10, 10); // Establecemos margen entre los componentes

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblUsuario, gbc); // Agregamos el JLabel en la primera posición

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(txtUsuario, gbc); // Agregamos el JTextField debajo del JLabel

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(btnSaludar, gbc); // Agregamos el JButton debajo del JTextField

		
		btnSaludar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = txtUsuario.getText();
				JOptionPane.showMessageDialog(null, "¡Hola " + nombre + "!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Agregar el panel con componentes al JFrame
		add(panel);  
		setVisible(true); 
	}
}
