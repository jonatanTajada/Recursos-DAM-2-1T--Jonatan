package ejericioEditorTexto;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class EditorDeTexto {

	private JFrame ventana;
	private JTextArea txtArea;
	private File archivoActual;

	public EditorDeTexto() {
		
		// Configurar la ventana
		ventana = new JFrame();
		ventana.setTitle("Bloc de notas");
		ventana.setSize(650, 550);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		txtArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txtArea); 
		ventana.add(scrollPane, BorderLayout.CENTER); 

		// Añadir el menú
		ventana.setJMenuBar(crearMenu());

		ventana.setVisible(true);
	}

	
	//metodo para crear: Menu
	private JMenuBar crearMenu() {
		
		JMenuBar menuBar = new JMenuBar();

		//menu Archivo
		JMenu menuArchivo = new JMenu("Archivo");

		JMenuItem opcionNuevo = new JMenuItem("Nuevo");
		JMenuItem opcionAbrir = new JMenuItem("Abrir");
		JMenuItem opcionGuardar = new JMenuItem("Guardar");
		JMenuItem opcionGuardarComo = new JMenuItem("Guardar como");
		JMenuItem opcionSalir = new JMenuItem("Salir");

		menuArchivo.add(opcionNuevo);
		menuArchivo.add(opcionAbrir);
		menuArchivo.add(opcionGuardar);
		menuArchivo.add(opcionGuardarComo);
		menuArchivo.add(opcionSalir);

		opcionNuevo.addActionListener(e -> nuevoArchivo());
		opcionAbrir.addActionListener(e -> abrirArchivo());
		opcionGuardar.addActionListener(e -> guardarArchivo());
		opcionGuardarComo.addActionListener(e -> guardarArchivoComo());
		opcionSalir.addActionListener(e -> salirAplicacion());

		menuBar.add(menuArchivo);

		//menu: Editar
		JMenu menuEditar = new JMenu("Editar");

		JMenuItem opcionCortar = new JMenuItem("Cortar");
		JMenuItem opcionCopiar = new JMenuItem("Copiar");
		JMenuItem opcionPegar = new JMenuItem("Pegar");

		menuEditar.add(opcionCortar);
		menuEditar.add(opcionCopiar);
		menuEditar.add(opcionPegar);

		opcionCortar.addActionListener(e -> txtArea.cut());
		opcionCopiar.addActionListener(e -> txtArea.copy());
		opcionPegar.addActionListener(e -> txtArea.paste());

		menuBar.add(menuEditar);

		return menuBar;
	}

	//metodo para la opción "Nuevo"
	private void nuevoArchivo() {
		
		txtArea.setText("");
		archivoActual = null;
		ventana.setTitle("Bloc de notas");
	}

	//metodo para la opcion: Abrir
	private void abrirArchivo() {
		
		JFileChooser fileChooser = new JFileChooser();
		int opcion = fileChooser.showOpenDialog(ventana);

		if (opcion == JFileChooser.APPROVE_OPTION) {
			
			archivoActual = fileChooser.getSelectedFile();

			try (BufferedReader br = new BufferedReader(new FileReader(archivoActual))) {
				
				txtArea.read(br, null);
				ventana.setTitle("Bloc de notas - " + archivoActual.getName());
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(ventana, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	//metodo para la opcion: Guardar
	private void guardarArchivo() {
		
		if (archivoActual != null) {
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoActual))) {
				
				txtArea.write(bw);
				JOptionPane.showMessageDialog(ventana, "Archivo guardado con exito.", "Guardar",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(ventana, "Error al guardar el archivo", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			
			guardarArchivoComo();
		}
	}

	//metodo para la opcion: Guardar como
	private void guardarArchivoComo() {
		
		JFileChooser fileChooser = new JFileChooser();
		int opcion = fileChooser.showSaveDialog(ventana);

		if (opcion == JFileChooser.APPROVE_OPTION) {
			
			archivoActual = fileChooser.getSelectedFile();
			guardarArchivo();
			ventana.setTitle("Bloc de notas - " + archivoActual.getName());
		}
	}

	//metodo para la opcion: Salir
	private void salirAplicacion() {
		
		int opcion = JOptionPane.showConfirmDialog(ventana, "¿Desea guardar los cambios antes de salir?",
				"Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION);

		if (opcion == JOptionPane.YES_OPTION) {
			
			guardarArchivo();
			System.exit(0);
		} else if (opcion == JOptionPane.NO_OPTION) {
			
			System.exit(0);
		}
	}

//---------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> new EditorDeTexto());
	}
	
}
