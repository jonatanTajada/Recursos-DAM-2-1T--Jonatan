package componentesJavaSwingEjercicios;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Realizado con windowsBuilder
//Ejercicio 2/4
public class ListaPeliculasGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPelicula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaPeliculasGUI frame = new ListaPeliculasGUI();
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
	public ListaPeliculasGUI() {
		setTitle("Peliculas");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 90, 0, 294, 0 };
		gridBagLayout.rowHeights = new int[] { 58, 14, 20, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblMensaje = new JLabel("Escribe el título de la pelicula");
		GridBagConstraints gbc_lblMensaje = new GridBagConstraints();
		gbc_lblMensaje.insets = new Insets(0, 0, 5, 5);
		gbc_lblMensaje.gridx = 0;
		gbc_lblMensaje.gridy = 1;
		getContentPane().add(lblMensaje, gbc_lblMensaje);

		JLabel lblListaPeliculas = new JLabel("Películas");
		GridBagConstraints gbc_lblListaPeliculas = new GridBagConstraints();
		gbc_lblListaPeliculas.insets = new Insets(0, 0, 5, 0);
		gbc_lblListaPeliculas.gridx = 2;
		gbc_lblListaPeliculas.gridy = 1;
		getContentPane().add(lblListaPeliculas, gbc_lblListaPeliculas);

		txtPelicula = new JTextField();
		GridBagConstraints gbc_txtPelicula = new GridBagConstraints();
		gbc_txtPelicula.insets = new Insets(0, 0, 5, 5);
		gbc_txtPelicula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPelicula.gridx = 0;
		gbc_txtPelicula.gridy = 2;
		getContentPane().add(txtPelicula, gbc_txtPelicula);
		txtPelicula.setColumns(15);

		JComboBox<String> comboPeliculas = new JComboBox<>();

		comboPeliculas.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Titanic", "Hasta donde los pies me lleven", "Django" }));
		
		GridBagConstraints gbc_comboListaPeliculas = new GridBagConstraints();
		gbc_comboListaPeliculas.insets = new Insets(0, 0, 5, 0);
		gbc_comboListaPeliculas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboListaPeliculas.gridx = 2;
		gbc_comboListaPeliculas.gridy = 2;
		getContentPane().add(comboPeliculas, gbc_comboListaPeliculas);

		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String nuevaPelicula = txtPelicula.getText().trim();
				boolean exists = false;
				//comprobar si existe pelicula en la lista
				for (int i = 0; i < comboPeliculas.getItemCount(); i++) {
					if (comboPeliculas.getItemAt(i).equalsIgnoreCase(nuevaPelicula)) { 
																						
						exists = true;
						break;
					}
				}

				if (exists) {
					
					JOptionPane.showMessageDialog(null, "La pelicula '" + nuevaPelicula + "' ya esta en la lista.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
				} else if (!nuevaPelicula.isEmpty()) {
					
					comboPeliculas.addItem(nuevaPelicula);
					txtPelicula.setText(""); 
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, introduce el titulo de una pelicula", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
		gbc_btnAnadir.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnadir.gridx = 1;
		gbc_btnAnadir.gridy = 3;
		getContentPane().add(btnAnadir, gbc_btnAnadir);
		
	

	}
}