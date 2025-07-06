
package componenetesJavaSwingEjercicios3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

public class SeleccionarFichero extends JFrame {

	private static final long serialVersionUID = 670078702198285721L;
	private JTextField rutaTextField;
	private JButton seleccionarButton;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SeleccionarFichero ventana = new SeleccionarFichero();
			ventana.setVisible(true);
		});
	}

	public SeleccionarFichero() {
		
		setTitle("Mostrar ruta fichero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 300);

		// Crear panel principal
		JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Crear componentes
		JLabel label = new JLabel("Pulsa en el boton y elige una ruta");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		rutaTextField = new JTextField();
		seleccionarButton = new JButton("Seleccionar Archivo");

		rutaTextField.setEditable(false);

		// Panel para el botón y el JTextField
		JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
		panelInferior.add(rutaTextField, BorderLayout.CENTER);
		panelInferior.add(seleccionarButton, BorderLayout.EAST);

		// Añadir componentes al panel principal
		panelPrincipal.add(label, BorderLayout.NORTH);
		panelPrincipal.add(panelInferior, BorderLayout.CENTER);

		// Añadir panel principal al frame
		add(panelPrincipal);

		// Evento para abrir JFileChooser y seleccionar fichero .txt
		seleccionarButton.addActionListener(new ActionListener() {
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

				int seleccion = fileChooser.showOpenDialog(SeleccionarFichero.this);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File archivoSeleccionado = fileChooser.getSelectedFile();
					rutaTextField.setText(archivoSeleccionado.getAbsolutePath());
				}
			}
		});
	}
}
