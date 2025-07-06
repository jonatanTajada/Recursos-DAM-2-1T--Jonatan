package componentesJavaSwingJFileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EjemploJFileChooser extends JFrame {

	private JLabel labelImagen;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			EjemploJFileChooser ventana = new EjemploJFileChooser();
			ventana.setVisible(true);
		});
	}
	
	public EjemploJFileChooser() {
		
		// Configuración básica del JFrame
		setTitle("Ejemplo de JFileChooser");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// JLabel para mostrar la imagen seleccionada
		labelImagen = new JLabel();
		labelImagen.setHorizontalAlignment(JLabel.CENTER);
		add(labelImagen, BorderLayout.CENTER);

		// Botón para seleccionar una imagen
		JButton botonSeleccionarImagen = new JButton("Selecciona una Imagen");
		add(botonSeleccionarImagen, BorderLayout.SOUTH);

		// Evento al pulsar el botón
		botonSeleccionarImagen.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			int opcion = chooser.showOpenDialog(this);
			if (opcion == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				cargarImagen(file); // Método que carga la imagen en el JLabel
			}
		});
	}

	// Método que carga y muestra la imagen en el JLabel
	private void cargarImagen(File file) {
		ImageIcon icon = new ImageIcon(file.getPath());
		Image image = icon.getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),
				Image.SCALE_SMOOTH);
		labelImagen.setIcon(new ImageIcon(image));
	}

}
