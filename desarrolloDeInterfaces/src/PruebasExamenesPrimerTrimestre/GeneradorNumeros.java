package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GeneradorNumeros {

	private Random random;
	private JFrame ventana;
	private JLabel lblNumero1;
	private JLabel lblNumero2;
	private JLabel lblNumeroGenerado;
	private JTextField txtNumeroGenerado;
	private JSpinner js1;
	private JSpinner js2;
	private JButton btnGenerar;

	public GeneradorNumeros() {
		random = new Random();

		// Configuración de la ventana
		ventana = new JFrame();
		ventana.setTitle("Generador de números");
		ventana.setSize(300, 250);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// Crear panel central con GridLayout
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(4, 2, 3, 3)); 

		lblNumero1 = new JLabel("Número 1:", SwingConstants.CENTER);
		js1 = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));

		lblNumero2 = new JLabel("Número 2", SwingConstants.CENTER);
		js2 = new JSpinner(new SpinnerNumberModel(10, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));

		lblNumeroGenerado = new JLabel("Número generado", SwingConstants.CENTER);
		txtNumeroGenerado = new JTextField();
		txtNumeroGenerado.setEditable(false);

		btnGenerar = new JButton("Generar");

		// Añadir componentes al panel
		panelCentral.add(lblNumero1);
		panelCentral.add(js1);
		panelCentral.add(lblNumero2);
		panelCentral.add(js2);
		panelCentral.add(lblNumeroGenerado);
		panelCentral.add(txtNumeroGenerado);
		panelCentral.add(new JLabel()); // Espacio vacío para alinear el botón
		panelCentral.add(btnGenerar);

		// Añadir panel central a la ventana
		ventana.add(panelCentral);

		// Acción del botón
		btnGenerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generarNumeroAleatorio();
			}
		});

		ventana.setVisible(true);
	}

	// Método para generar un número aleatorio entre los valores de los spinners
	private void generarNumeroAleatorio() {
		int numero1 = (int) js1.getValue();
		int numero2 = (int) js2.getValue();

		int min = Math.min(numero1, numero2);
		int max = Math.max(numero1, numero2);

		int numeroGenerado = min + random.nextInt(max - min + 1);
		txtNumeroGenerado.setText(String.valueOf(numeroGenerado));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GeneradorNumeros();
			}
		});
	}
}
