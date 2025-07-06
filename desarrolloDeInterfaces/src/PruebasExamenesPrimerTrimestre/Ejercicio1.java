package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ejercicio1 extends JFrame {
	
	
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JButton btnSaludar;
	

	public Ejercicio1() {
		
		//configurar vetnna
		setTitle("Saludador");
		setSize(650, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		
		//creo panel
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		//crear label
		lblNombre = new JLabel("Escriba su nombre:");
		panel.add(lblNombre);
		
		//crear txtdfield
		txtNombre = new JTextField(15);
		panel.add(txtNombre);
		
		//crear boton
		btnSaludar = new JButton("¡Saludar!");
		panel.add(btnSaludar);
		
		add(panel, BorderLayout.CENTER);
		
		btnSaludar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = txtNombre.getText();
				JOptionPane.showMessageDialog(null, "Hola " + nombre);
				
				
			}
		});
		
		
		
		
		
		
		setVisible(true);		
		
		
	}
	
	public static void main(String[] args) {
		
		new Ejercicio1();
		
	}
	
}
