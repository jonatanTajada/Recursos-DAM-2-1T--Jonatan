package PruebasExamenesPrimerTrimestre.MIAS;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraSimple {

	public static void main(String[] args) {

		// Crear la ventana
		JFrame ventana = new JFrame();
		ventana.setTitle("Calculadora Mejorada");
		ventana.setSize(400, 500);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// Pantalla de la calculadora
		JTextField pantalla = new JTextField("0", 40);
		pantalla.setEditable(false);
		pantalla.setFont(new Font("Arial", Font.BOLD, 24));
		ventana.add(pantalla, BorderLayout.NORTH);

		// Variables para el cálculo
		final double[] acumulado = { 0 }; // Almacena el resultado acumulado
		final char[] operacionActual = { ' ' }; // Guarda la operación actual
		final boolean[] esNuevoNumero = { true }; // Controla si estamos iniciando un nuevo número
		final boolean[] errorEnPantalla = { false }; // Controla si hay un error en pantalla

		// Panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(5, 4, 5, 5));
		ventana.add(panelBotones, BorderLayout.CENTER);

		// Botones de la calculadora
		String[] botones = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+", "C" };

		// Agregar los botones y configurar los ActionListeners
		for (String texto : botones) {
			JButton boton = new JButton(texto);
			panelBotones.add(boton);

			boton.addActionListener((ActionEvent e) -> {
				String comando = e.getActionCommand();

				// Limpieza de pantalla en caso de error
				if (errorEnPantalla[0]) {
					pantalla.setText("0");
					errorEnPantalla[0] = false;
				}

				try {
					switch (comando) {
					case "+":
					case "-":
					case "*":
					case "/":
						realizarOperacion(acumulado, pantalla, operacionActual[0]); // Realiza la operación previa
						operacionActual[0] = comando.charAt(0);
						esNuevoNumero[0] = true;
						break;
					case "=":
						realizarOperacion(acumulado, pantalla, operacionActual[0]); // Realiza la operación final
						operacionActual[0] = ' '; // Resetea la operación actual
						break;
					case "C":
						pantalla.setText("0");
						acumulado[0] = 0;
						operacionActual[0] = ' ';
						esNuevoNumero[0] = true;
						break;
					case ".":
						if (esNuevoNumero[0]) {
							pantalla.setText("0.");
							esNuevoNumero[0] = false;
						} else if (!pantalla.getText().contains(".")) {
							pantalla.setText(pantalla.getText() + ".");
						}
						break;
					default: // Manejo de números
						if (esNuevoNumero[0] || pantalla.getText().equals("0")) {
							pantalla.setText(comando);
						} else {
							pantalla.setText(pantalla.getText() + comando);
						}
						esNuevoNumero[0] = false;
						break;
					}
				} catch (Exception ex) {
					pantalla.setText("Error");
					errorEnPantalla[0] = true;
				}
			});
		}

		ventana.setVisible(true);
	}

	/**
	 * Realiza la operación aritmética basada en el operador actual y actualiza la
	 * pantalla.
	 */
	private static void realizarOperacion(double[] acumulado, JTextField pantalla, char operacion) {
		try {
			double numeroActual = Double.parseDouble(pantalla.getText());

			// Calcula según la operación actual
			switch (operacion) {
			case '+':
				acumulado[0] += numeroActual;
				break;
			case '-':
				acumulado[0] -= numeroActual;
				break;
			case '*':
				acumulado[0] *= numeroActual;
				break;
			case '/':
				if (numeroActual == 0)
					throw new ArithmeticException("División por cero");
				acumulado[0] /= numeroActual;
				break;
			case ' ':
				acumulado[0] = numeroActual;
				break;
			}

			// Muestra el resultado en pantalla
			pantalla.setText(String.valueOf(acumulado[0]));
		} catch (Exception e) {
			pantalla.setText("Error");
		}
	}
}
