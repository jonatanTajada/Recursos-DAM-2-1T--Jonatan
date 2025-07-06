package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MostrarRutaFichero extends JFrame {

	private static final long serialVersionUID = -7580123364618357845L;
	
	private JLabel lblMensaje;
	private JTextField txtRuta;
	private JButton btnSeleccionarArchivo;

	public MostrarRutaFichero() {

		// configurar ventana

		setTitle("Mostrar ruta fichero");
		setSize(650, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// panel norte
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);

		lblMensaje = new JLabel("Pulsa en el boton y elige una ruta");
		panelNorte.add(lblMensaje, BorderLayout.CENTER);

		// panel center
		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);

		txtRuta = new JTextField();
		txtRuta.setPreferredSize(new Dimension(400,25));
		txtRuta.setEditable(false);
		panelCenter.add(txtRuta, BorderLayout.CENTER);

		btnSeleccionarArchivo = new JButton("...");
		panelCenter.add(btnSeleccionarArchivo, BorderLayout.EAST);

		// accion del bton para abrir el jfilechooser
		btnSeleccionarArchivo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				abrirFileChooser();
			}

		});

		setVisible(true);
	}

	// metodo abriFileChooser
	private void abrirFileChooser() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecciona un archivo .txt");

		FileNameExtensionFilter filtroTxt = new FileNameExtensionFilter("Archivos solo *txt", "txt");
		fileChooser.setFileFilter(filtroTxt);

		int seleccion = fileChooser.showOpenDialog(this);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = fileChooser.getSelectedFile();
			txtRuta.setText(archivoSeleccionado.getAbsolutePath());
		}

	}
//-----------------------------------------------------------------------------------------------------------------------	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> new MostrarRutaFichero());
	}

}
