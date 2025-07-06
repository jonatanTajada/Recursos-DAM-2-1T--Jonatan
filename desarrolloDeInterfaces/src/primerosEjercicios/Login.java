package primerosEjercicios;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame{

	
	
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JButton btnRegistrar;
	private JLabel lblMensaje;
	
	
	public Login() {
		
		//Configuracion simple de la ventana
		setTitle("Formulario registro");
		setSize(550, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Crear el panel principal
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		
		//Titulo
		JLabel lblTitulo = new JLabel("Formulario de Registro", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblTitulo, BorderLayout.NORTH);
		// -------------------------------------------------------------------------
		
		//Panel central para los campos de texto
		JPanel panelCentral = new JPanel(new GridLayout(4, 1, 5, 5));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Campo de nombre
		txtNombre = new JTextField();
		txtNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
		panelCentral.add(txtNombre);
		
		//Campo apellido
		txtApellido = new JTextField();
		txtApellido.setBorder(BorderFactory.createTitledBorder("Apellido"));
		panelCentral.add(txtApellido);
		
		//Boton de registro
		btnRegistrar = new JButton("Registrar");
		panelCentral.add(btnRegistrar);
		
		//Mensaje de confirmacion
		lblMensaje = new JLabel("", JLabel.CENTER);
		panelCentral.add(lblMensaje);
		
		panel.add(panelCentral, BorderLayout.CENTER);

		//-----------------------------------------
		
		//Añadir funcionalidad al boton
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				
				if (!nombre.isEmpty() && !apellido.isEmpty()) {
					lblMensaje.setText("Registrado correctamente");
				} else {
					lblMensaje.setText("Tienen que estar todos los campos rellenados.");	
				}
			}
		});
		
		//Añadir panel a la ventana
		add(panel);

	}
	
	public static void main(String[] args) {
		
		//Crear y mostrar la ventana
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Login().setVisible(true);
			}
		});
		
		
	}
	

}
