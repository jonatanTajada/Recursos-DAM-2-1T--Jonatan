package primerosEjercicios.tarea1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//ejercicio de la pagina 26. 1/3. Tema 2 InterfazGraficaJava
//Con windowBuilder
public class SumaDosNumerosGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNum1;
	private JTextField txtNum2;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SumaDosNumerosGUI frame = new SumaDosNumerosGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}

	
	public SumaDosNumerosGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMensaje = new JLabel("Introduce numeros enteros para sumar:");
		contentPane.add(lblMensaje, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNum1 = new JLabel("Número 1");
		panelCentral.add(lblNum1);
		
		txtNum1 = new JTextField();
		panelCentral.add(txtNum1);
		txtNum1.setColumns(10);
		
		JLabel lblNum2 = new JLabel("Número 2");
		panelCentral.add(lblNum2);
		
		txtNum2 = new JTextField();
		panelCentral.add(txtNum2);
		txtNum2.setColumns(10);
		
		JButton btnSumar = new JButton("Sumar");
		panelCentral.add(btnSumar);
		
		JButton btnCerrar = new JButton("Cerrar");
	
		panelCentral.add(btnCerrar);
		
		JPanel panel_Sur = new JPanel();
		contentPane.add(panel_Sur, BorderLayout.SOUTH);
		
		JLabel lblResultado = new JLabel("Resultado: ");
		panel_Sur.add(lblResultado);
		
		
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int num1= Integer.parseInt(txtNum1.getText());
					int num2= Integer.parseInt(txtNum2.getText());
					
					int suma= num1 + num2;
					lblResultado.setText("El Resultado es: " + suma);
				}catch(NumberFormatException ex) {
					lblResultado.setText("Introduce valores numericos enteros.");
				}
				
			}
		});
		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}

}
