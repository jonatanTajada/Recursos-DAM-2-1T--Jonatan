package componentesJavaSwingJFileChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TresEnRaya extends JFrame {

	private static final long serialVersionUID = -5677803627950085081L;
	

	private JButton[] botones = new JButton[9]; 
	private boolean turnoJugador1 = true; // Bandera para alternar entre los jugadores
	private int jugadas = 0; 
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new TresEnRaya().setVisible(true);
		});
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	public TresEnRaya() {
		configurarVentana();
		inicializarComponentes();
	}

	private void configurarVentana() {
		setTitle("Tres en Raya");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 3)); 
	}

	private void inicializarComponentes() {
		// Crear los 9 botones y añadirlos al JFrame
		for (int i = 0; i < 9; i++) {
			botones[i] = new JButton();
			botones[i].setFont(new Font("Arial", Font.BOLD, 60)); 
			botones[i].setFocusPainted(false); // Para evitar que se vea la selección en el botón
			add(botones[i]); 

			final int indice = i; // Guardamos el índice del botón en una variable final
			botones[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					realizarJugada(indice); // Llamamos al método para manejar la jugada
				}
			});
		}
	}

	private void realizarJugada(int indice) {
		if (!botones[indice].isEnabled()) {
			return; // Si el botón ya está deshabilitado, no hacemos nada
		}

		// Alternar entre jugadores
		if (turnoJugador1) {
			botones[indice].setBackground(Color.BLUE); 
			botones[indice].setText("X");
		} else {
			botones[indice].setBackground(Color.RED); 
			botones[indice].setText("O");
		}

		// Deshabilitamos el botón para que no se vuelva a usar
		botones[indice].setEnabled(false); 
		turnoJugador1 = !turnoJugador1; // Cambiamos de turno
		jugadas++; // Aumentamos el contador de jugadas

		verificarGanador(); // Verificamos si hay un ganador
	}

	private void verificarGanador() {
		// Combinaciones ganadoras posibles
		int[][] combinacionesGanadoras = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // Filas
				{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } // Diagonales
				
		};

		for (int[] combinacion : combinacionesGanadoras) {
			if (botones[combinacion[0]].getText().equals(botones[combinacion[1]].getText())
					&& botones[combinacion[1]].getText().equals(botones[combinacion[2]].getText())
					&& !botones[combinacion[0]].getText().isEmpty()) {

				String ganador = botones[combinacion[0]].getText().equals("X") ? "Jugador 1" : "Jugador 2";
				mostrarMensaje("¡" + ganador + " ha ganado!");
				reiniciarJuego();
				return;
			}
		}

		if (jugadas == 9) {
			mostrarMensaje("¡Empate!");
			reiniciarJuego();
		}
	}

	private void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	private void reiniciarJuego() {
		for (JButton boton : botones) {
			boton.setText("");
			boton.setBackground(null);
			boton.setEnabled(true);
		}
		turnoJugador1 = true;
		jugadas = 0;
	}

	
}
