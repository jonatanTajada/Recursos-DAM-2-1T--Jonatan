package primerosEjercicios.tarea1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//ejercicio de la pagina 26. 2/3. Tema 2 InterfazGraficaJava
//Con windowBuilder
public class ConvertidorMonedaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtCantidad;
	private JComboBox comboBoxConversion;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConvertidorMonedaGUI frame = new ConvertidorMonedaGUI();
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
	public ConvertidorMonedaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCantidad = new JLabel("Cantidad");
		panelCenter.add(lblCantidad);
		
		txtCantidad = new JTextField();
		panelCenter.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		comboBoxConversion = new JComboBox();
		comboBoxConversion.setModel(new DefaultComboBoxModel(new String[] {"\"€ a $\"", "\"$ a €\""}));
		panelCenter.add(comboBoxConversion);
		
		JButton btnConvertir = new JButton("Convertir");
		panelCenter.add(btnConvertir);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblResultado = new JLabel("Resultado: ");
		panelSouth.add(lblResultado);
		
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					double cantidad = Double.parseDouble(txtCantidad.getText());
					double resultado;
					
					String tipoConversion = comboBoxConversion.getSelectedItem().toString();
					
					if (tipoConversion.equals("€ a $")) {
						resultado = cantidad * 0.91; 
					} else {
						resultado = cantidad / 0.91; 
					}
					
					lblResultado.setText("Resultado: " + String.format("%.2f", resultado));
				} catch (NumberFormatException ex) {
					lblResultado.setText("Introduce un valor numérico válido");
				}
				
			}
		});
	
		
	

	}

}
