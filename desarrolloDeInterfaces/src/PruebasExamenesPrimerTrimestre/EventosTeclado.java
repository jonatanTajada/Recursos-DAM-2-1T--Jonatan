package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class EventosTeclado {

	private JFrame ventana;

	private JLabel lblEncabezado;

	private JLabel lblEntradaTexto;
	private JLabel lblSalidaTexto;
	private JTextArea txtAreaEntrada;
	private JTextArea txtAreaSalida;

	private JLabel lblMensaje;
	private JLabel lblVocales;
	private int contadorVocales;

	public EventosTeclado() {

		// configurar ventana
		ventana = new JFrame();
		ventana.setTitle("Diseño de interfaces: Ventana eventos de teclado");
		ventana.setSize(650, 550);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout());

		// crear panelNorte
		JPanel panelNorte = new JPanel();
		lblEncabezado = new JLabel("Eventos del Teclado", SwingConstants.CENTER);
		lblEncabezado.setFont(new Font("Serif", Font.BOLD, 24));
		panelNorte.add(lblEncabezado);
		ventana.add(panelNorte, BorderLayout.NORTH);

		// crear panelCenter
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(4, 1, 2, 2));

		lblEntradaTexto = new JLabel("Area Entrada de Texto");
		txtAreaEntrada = new JTextArea(4, 20);
		panelCenter.add(lblEntradaTexto);
		panelCenter.add(new JScrollPane(txtAreaEntrada));

		lblSalidaTexto = new JLabel("Area Salida de Texto");
		txtAreaSalida = new JTextArea(4, 20);
		txtAreaSalida.setEditable(false);
		panelCenter.add(lblSalidaTexto);
		panelCenter.add(new JScrollPane(txtAreaSalida));

		ventana.add(panelCenter, BorderLayout.CENTER);

		 // Crear panel sur
        JPanel panelSur = new JPanel();
        panelSur.setLayout(new FlowLayout());

        lblMensaje = new JLabel("Para salir presione la tecla Escape", SwingConstants.LEFT);
        lblVocales = new JLabel("Numero Vocales: 0", SwingConstants.RIGHT);
        panelSur.add(lblMensaje, BorderLayout.WEST);
        panelSur.add(lblVocales, BorderLayout.EAST);

        ventana.add(panelSur, BorderLayout.SOUTH);

		// eventos teclado
		txtAreaEntrada.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				if ("aeiouAEIOU".indexOf(c) != -1) {
					txtAreaSalida.append(String.valueOf(c) + " ");
					contadorVocales++;
					lblVocales.setText("Numero Vocales: " + contadorVocales);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		});

		ventana.setVisible(true);

	}

//-------------------------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new EventosTeclado());
	}

}
