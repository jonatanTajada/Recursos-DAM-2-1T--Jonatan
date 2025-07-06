package componentesJavaSwingEjercicios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Realizado sin windowsBuilder
//Ejercicio 2/4
public class ListaPeliculas extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	 public static void main(String[] args) {
	        // Ejecutar la aplicación en el hilo de eventos de Swing
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new ListaPeliculas();
	            }
	        });
	    }

    
    
    // Constructor del JFrame
    public ListaPeliculas() {
        // Configuracion de la ventana
        setTitle("Películas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para centrar los componentes

        // Crear los componentes
        JLabel lblMensaje = new JLabel("Escribe el título de una película");
        JTextField txtPelicula = new JTextField(15);
        JButton btnAnadir = new JButton("Añadir");
        JLabel lblListaPeliculas = new JLabel("Películas");
        
        // Crear JComboBox con algunas películas iniciales
        JComboBox<String> comboListaPeliculas = new JComboBox<>(new String[]{"Top Gun", "Forest Gump", "Mar Adentro"});
        
        // Layout y posicionamiento de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMensaje, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(lblListaPeliculas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(txtPelicula, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(comboListaPeliculas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnAnadir, gbc);

        // Añadir funcionalidad al botón "Añadir"
        btnAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaPelicula = txtPelicula.getText().trim();
                
                // Comprobamos que el campo no esté vacío antes de añadir
                if (!nuevaPelicula.isEmpty()) {
                    comboListaPeliculas.addItem(nuevaPelicula); // Añadir nueva película al JComboBox
                    txtPelicula.setText(""); // Limpiar el campo de texto
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce el título de una película", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

   
}
