package componentesJavaSwingJFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cronometro extends JFrame {

	private static final long serialVersionUID = 5966707037555187699L;

	private JLabel labelCronometro;
	private JButton botonIniciarPausar, botonReiniciar;
	private Timer timer;
	private int centesimas = 0, segundos = 0;
	private boolean enMarcha = false;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Cronometro().setVisible(true);
			}
		});
	}

	public Cronometro() {

		setTitle("Cronómetro");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		// Crear componentes
		labelCronometro = new JLabel("00:00");
		labelCronometro.setFont(new Font("Arial", Font.BOLD, 30));
		botonIniciarPausar = new JButton("Iniciar");
		botonReiniciar = new JButton("Reiniciar");

		// Añadir componentes a la ventana
		add(labelCronometro);
		add(botonIniciarPausar);
		add(botonReiniciar);

		// Configurar eventos
		botonIniciarPausar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!enMarcha) {
					iniciarCronometro();
					botonIniciarPausar.setText("Pausar");
				} else {
					pausarCronometro();
					botonIniciarPausar.setText("Iniciar");
				}
			}
		});

		botonReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciarCronometro();
			}
		});

		// Crear el Timer
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarCronometro();
			}
		});
	}

	// Método para iniciar el cronómetro
	private void iniciarCronometro() {
		enMarcha = true;
		timer.start();
	}

	// Método para pausar el cronómetro
	private void pausarCronometro() {
		enMarcha = false;
		timer.stop();
		// Mostrar diálogo si las centésimas son 00
		if (centesimas == 0) {
			JOptionPane.showMessageDialog(this, "Pausado en las centésimas 00.");
		}
	}

	// Método para reiniciar el cronómetro
	private void reiniciarCronometro() {
		enMarcha = false;
		timer.stop();
		segundos = 0;
		centesimas = 0;
		labelCronometro.setText("00:00");
		botonIniciarPausar.setText("Iniciar");
	}

	// Método para actualizar el cronómetro
	private void actualizarCronometro() {
		centesimas++;
		if (centesimas == 100) {
			centesimas = 0;
			segundos++;
		}
		labelCronometro.setText(String.format("%02d:%02d", segundos, centesimas));
	}

}
