package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ejercicio2_JCombox extends JFrame {

	
	
	private JLabel lblEscribeTitulo;
	private JTextField txtTitulo;
	private JButton btnAnadir;
	
	private JLabel lblPeliculas;
 
	private JComboBox<String> comboBox;
	
	
	
	public Ejercicio2_JCombox() {
		
		//configurar frame
		setTitle("Peliculas");
		setSize(650, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		
		//crear panel oeste
		JPanel panelOeste = new JPanel();
		//panelOeste.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelOeste.setLayout(new GridBagLayout());
		add(panelOeste ,BorderLayout.CENTER);
		
		lblEscribeTitulo = new JLabel("Escribe el titulo de una pellicula");
		txtTitulo = new JTextField(10);
		btnAnadir = new JButton("Añadir");
		
		panelOeste.add(lblEscribeTitulo);
		panelOeste.add(txtTitulo);
		panelOeste.add(btnAnadir);
		
		
		//crear panelEste
		JPanel panelEste = new JPanel();
		//panelEste.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelEste.setLayout(new GridBagLayout());
		add(panelEste, BorderLayout.NORTH);
		
		lblPeliculas = new JLabel("Peliculas");
		comboBox = new JComboBox<>(new String[] {"Top Gun", "Forest Gump", "Mar a dentro"});
		comboBox.setBounds(100, 200, 200, 50);
		

		
		
		panelEste.add(lblPeliculas);
		panelEste.add(comboBox);
		
		//accionar el boton
		btnAnadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String pelicula = txtTitulo.getText().trim();
				
				if (!pelicula.isEmpty()) {
					comboBox.addItem(pelicula);
					txtTitulo.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Por favor escriba un titulo de una pelicula");
				}
			}
		});
		
		
		setVisible(true);
		//pack();
		
	}
	
	
	public static void main(String[] args) {
		
		new Ejercicio2_JCombox();
	}
	
	
}
