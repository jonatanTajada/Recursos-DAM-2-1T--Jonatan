package componentesJavaSwingEjercicios2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VentanaEventosTeclado extends JFrame implements KeyListener, ActionListener {

	
	private static final long serialVersionUID = -431715382133276967L;
	
	private JTextArea areaEntradaTexto, areaSalidaTexto;
	private JLabel lblContadorVocales, lblContadorPalabras;
	private int contadorVocales = 0;
	private JButton btnGuardar, btnCambiarColor;

	public VentanaEventosTeclado() {
		
		setTitle("Eventos del Teclado");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Etiqueta del título
		JLabel lblTitulo = new JLabel("Eventos del Teclado", JLabel.CENTER);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 24));
		add(lblTitulo, BorderLayout.NORTH);

		// Panel central con áreas de texto
		JPanel panelCentral = new JPanel(new GridLayout(2, 1, 10, 10));

		areaEntradaTexto = new JTextArea("Escribe aquí para contar vocales...");
		areaEntradaTexto.addKeyListener(this);
		panelCentral.add(new JScrollPane(areaEntradaTexto));

		areaSalidaTexto = new JTextArea();
		areaSalidaTexto.setEditable(false);
		panelCentral.add(new JScrollPane(areaSalidaTexto));

		add(panelCentral, BorderLayout.CENTER);

		// Panel inferior con etiquetas y botones
		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		lblContadorVocales = new JLabel("Número de Vocales: 0");
		panelInferior.add(lblContadorVocales);

		lblContadorPalabras = new JLabel("Número de Palabras: 0");
		panelInferior.add(lblContadorPalabras);

		btnGuardar = new JButton("Guardar Texto");
		btnGuardar.addActionListener(this);
		panelInferior.add(btnGuardar);

		btnCambiarColor = new JButton("Cambiar Color");
		btnCambiarColor.addActionListener(this);
		panelInferior.add(btnCambiarColor);

		add(panelInferior, BorderLayout.SOUTH);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		if (esVocal(keyChar)) {
			contadorVocales++;
			areaSalidaTexto.append(String.valueOf(keyChar));
		}
		actualizarContadores();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No se necesita implementar nada aquí.
	}

	private boolean esVocal(char c) {
		return "aeiouáéíóúAEIOUÁÉÍÓÚ".indexOf(c) != -1;
	}

	private void actualizarContadores() {
		lblContadorVocales.setText("Número de Vocales: " + contadorVocales);
		lblContadorPalabras.setText("Número de Palabras: " + contarPalabras(areaEntradaTexto.getText()));
	}

	// Método corregido para contar correctamente las palabras
	private int contarPalabras(String texto) {
		// Eliminar espacios en blanco al inicio y al final
		texto = texto.trim();

		// Si el texto está vacío, no hay palabras
		if (texto.isEmpty()) {
			return 0;
		}

		// Dividir el texto usando uno o más espacios como separadores
		String[] palabras = texto.split("\\s+");

		return palabras.length;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			guardarArchivo();
		} else if (e.getSource() == btnCambiarColor) {
			cambiarColorTexto();
		}
	}

	private void guardarArchivo() {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showSaveDialog(this);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File archivo = fileChooser.getSelectedFile();
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
				writer.write(areaEntradaTexto.getText());
				JOptionPane.showMessageDialog(this, "Texto guardado correctamente.");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void cambiarColorTexto() {
		Color nuevoColor = JColorChooser.showDialog(this, "Seleccionar Color de Texto",
				areaSalidaTexto.getForeground());
		if (nuevoColor != null) {
			areaSalidaTexto.setForeground(nuevoColor);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			VentanaEventosTeclado ventana = new VentanaEventosTeclado();
			ventana.setVisible(true);
		});
	}
}
