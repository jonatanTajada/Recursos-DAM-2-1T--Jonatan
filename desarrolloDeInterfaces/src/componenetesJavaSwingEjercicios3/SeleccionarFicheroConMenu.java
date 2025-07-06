package componenetesJavaSwingEjercicios3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

public class SeleccionarFicheroConMenu extends JFrame {

	private static final long serialVersionUID = -7090890786421600138L;
	private JTextField rutaTextField;

	public SeleccionarFicheroConMenu() {
		setTitle("Menu");
		setSize(500, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Crear componentes
		rutaTextField = new JTextField();

		// Configurar el menú
		JMenuBar menuBar = new JMenuBar();
		JMenu menuArchivo = new JMenu("File");
		JMenuItem menuItemAbrir = new JMenuItem("Abrir...");
		JMenuItem menuItemSalir = new JMenuItem("Salir");

		menuArchivo.add(menuItemAbrir);
		menuArchivo.add(menuItemSalir);
		menuBar.add(menuArchivo);
		setJMenuBar(menuBar);

		// Añadir JTextField en la parte central con BorderLayout
		add(rutaTextField, BorderLayout.CENTER);

		// Evento para abrir JFileChooser
		menuItemAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				// Filtro para solo permitir archivos .txt
				fileChooser.setFileFilter(new FileFilter() {
					@Override
					public boolean accept(File f) {
						return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
					}

					@Override
					public String getDescription() {
						return "Archivos de Texto (.txt)";
					}
				});

				int seleccion = fileChooser.showOpenDialog(SeleccionarFicheroConMenu.this);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File archivoSeleccionado = fileChooser.getSelectedFile();
					rutaTextField.setText(archivoSeleccionado.getAbsolutePath());
				}
			}
		});

		// Evento para salir del programa en "Salir"
		menuItemSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SeleccionarFicheroConMenu ventana = new SeleccionarFicheroConMenu();
			ventana.setVisible(true);
		});
	}
}
