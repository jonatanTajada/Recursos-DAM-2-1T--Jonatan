package primerosEjercicios.tarea1;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;


//ejercicio de la pagina 26. 3/3. Tema 2 InterfazGraficaJava
//Sin windowBuilder
public class VentanaLogin extends JFrame {
	
	   public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            VentanaLogin ventana = new VentanaLogin();
	            ventana.setVisible(true);
	        });
	    }
	
	
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JPasswordField txtPasswordVerificar;
    private JLabel lblMensaje;

    public VentanaLogin() {
        // Configuración de la ventana
        setTitle("Ventana de Login");
        setSize(750, 550);
        setLocation(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        // Crear componentes
        JLabel lblUsuario = new JLabel("Nombre de usuario:");
        txtUsuario = new JTextField(15);
        txtUsuario.setMaximumSize(new Dimension(200, 25)); 

        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(15);
        txtPassword.setMaximumSize(new Dimension(200, 25)); 

        JLabel lblPasswordVerificar = new JLabel("Verificar contraseña:");
        txtPasswordVerificar = new JPasswordField(15);
        txtPasswordVerificar.setMaximumSize(new Dimension(200, 25)); 

        JButton btnLogin = new JButton("Iniciar Sesión");
        
        lblMensaje = new JLabel("");
	    lblMensaje.setAlignmentX(CENTER_ALIGNMENT); 

        // Añadir ActionListener al botón
        btnLogin.addActionListener(e -> verificarLogin());

        // Añadir componentes al panel central con espacios
        panelCentral.add(lblUsuario);
        panelCentral.add(txtUsuario);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio
        panelCentral.add(lblPassword);
        panelCentral.add(txtPassword);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); 
        panelCentral.add(lblPasswordVerificar);
        panelCentral.add(txtPasswordVerificar);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(lblMensaje);

        // Añadir el botón al panel
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnLogin);

        // Añadir los paneles al BorderLayout
        add(panelCentral, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }

    // Método para verificar el login
    private void verificarLogin() {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());
        String passwordVerificar = new String(txtPasswordVerificar.getPassword());

        // Validar la contraseña
        if (!validarPassword(password)) {
            lblMensaje.setText("Error: La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula y un número.");
        	System.out.println("Error: La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula y un número.");
            return;
        }

        // Comprobar si las contraseñas coinciden
        if (!password.equals(passwordVerificar)) {
        	lblMensaje.setText("Error: Las contraseñas no coinciden.");
            System.out.println("Error: Las contraseñas no coinciden.");
            return;
        }

        // Si todo es correcto
        lblMensaje.setText("Registro exitoso. ¡Bienvenido, " + usuario + "!");
        System.out.println("Registro exitoso. ¡Bienvenido, " + usuario + "!");
        txtUsuario.setText("");
        txtPassword.setText("");
        txtPasswordVerificar.setText("");
    }

    // Método para validar la contraseña
    private boolean validarPassword(String password) {
        // Expresión regular para la validación de la contraseña
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return Pattern.matches(regex, password);
    }

 
}
