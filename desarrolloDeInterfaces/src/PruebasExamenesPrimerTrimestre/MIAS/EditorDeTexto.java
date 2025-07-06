package PruebasExamenesPrimerTrimestre.MIAS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class EditorDeTexto {

	public static void main(String[] args) {

		// Crear la ventana
		JFrame ventana = new JFrame("Editor de Texto Básico");
		ventana.setSize(600, 400);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Crear el área de texto
		JTextArea areaTexto = new JTextArea();
		areaTexto.setFont(new Font("Arial", Font.PLAIN, 16));
		ventana.add(new JScrollPane(areaTexto), BorderLayout.CENTER);

		// Crear la barra de menú
		JMenuBar menuBar = new JMenuBar();
		ventana.setJMenuBar(menuBar);

		// Menú "Archivo"
		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);

		// Opción "Abrir"
		JMenuItem abrirItem = new JMenuItem("Abrir");
		abrirItem.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser();
			int seleccion = fileChooser.showOpenDialog(ventana);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File archivo = fileChooser.getSelectedFile();
				try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
					areaTexto.setText("");
					String linea;
					while ((linea = br.readLine()) != null) {
						areaTexto.append(linea + "\n");
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(ventana, "Error al abrir el archivo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuArchivo.add(abrirItem);

		// Opción "Guardar"
		JMenuItem guardarItem = new JMenuItem("Guardar");
		guardarItem.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser();
			int seleccion = fileChooser.showSaveDialog(ventana);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File archivo = fileChooser.getSelectedFile();
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
					bw.write(areaTexto.getText());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(ventana, "Error al guardar el archivo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuArchivo.add(guardarItem);

		// Opción "Limpiar"
		JMenuItem limpiarItem = new JMenuItem("Limpiar");
		limpiarItem.addActionListener((ActionEvent e) -> areaTexto.setText(""));
		menuArchivo.add(limpiarItem);

		// Mostrar la ventana
		ventana.setVisible(true);
	}
}
