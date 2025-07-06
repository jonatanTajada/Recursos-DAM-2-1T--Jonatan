package primerosEjercicios.tarea1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//ejercicio de la pagina 26. 3/3. Tema 2 InterfazGraficaJava
//Con windowBuilder
public class VentanaLoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreUsuario;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordVerificar;
	private JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLoginGUI frame = new VentanaLoginGUI();
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
	public VentanaLoginGUI() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(500, 200, 450, 300);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    JPanel panelCenter = new JPanel();
	    getContentPane().add(panelCenter, BorderLayout.CENTER);
	    panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
	    
	    JLabel lblUsuario = new JLabel("Nombre usuario: ");
	    panelCenter.add(lblUsuario);
	    
	    txtNombreUsuario = new JTextField();
	    txtNombreUsuario.setMaximumSize(new Dimension(200, 25));
	    panelCenter.add(txtNombreUsuario);
	    txtNombreUsuario.setColumns(15);
	    
	    JLabel lblPassword = new JLabel("Contraseña:");
	    panelCenter.add(lblPassword);
	    
	    txtPassword = new JPasswordField();
	    txtPassword.setMaximumSize(new Dimension(200, 25));
	    txtPassword.setColumns(15);
	    panelCenter.add(txtPassword);
	    
	    JLabel lblPasswordVerificar = new JLabel("Verificar contraseña:");
	    panelCenter.add(lblPasswordVerificar);
	    
	    txtPasswordVerificar = new JPasswordField();
	    txtPasswordVerificar.setMaximumSize(new Dimension(200, 25));
	    txtPasswordVerificar.setColumns(15);
	    panelCenter.add(txtPasswordVerificar);

	  
	    lblMensaje = new JLabel("");
	    lblMensaje.setAlignmentX(CENTER_ALIGNMENT); 
	    panelCenter.add(lblMensaje);

	    JPanel panelSouth = new JPanel();
	    getContentPane().add(panelSouth, BorderLayout.SOUTH);
	    panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    JButton btnLogin = new JButton("Iniciar sesion");
	    btnLogin.addActionListener(e -> verificarLogin());
	    panelSouth.add(btnLogin);
	}
	
	private void verificarLogin() {
		
	    String usuario = txtNombreUsuario.getText();
	    String password = new String(txtPassword.getPassword());
	    String passwordVerificar = new String(txtPasswordVerificar.getPassword());

	    // Validar la contraseña
	    if (!validarPassword(password)) {
	        lblMensaje.setText("Error: La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula y un número.");
	        return;
	    }

	    // Comprobar si las contraseñas coinciden
	    if (!password.equals(passwordVerificar)) {
	        lblMensaje.setText("Error: Las contraseñas no coinciden.");
	        return;
	    }

	    // Si todo es correcto
	    lblMensaje.setText("Registro exitoso. ¡Bienvenido, " + usuario + "!");
	    txtNombreUsuario.setText("");
	    txtPassword.setText("");
	    txtPasswordVerificar.setText("");
	}
	
	
	private boolean validarPassword(String password) {
	    // Expresión regular para la validación de la contraseña
	    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
	    return Pattern.matches(regex, password);
	}


}
