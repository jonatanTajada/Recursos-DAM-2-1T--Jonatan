package componentesJavaSwingEjercicios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//Realizado sin windowsBuilder
//Ejercicio 3/4

public class SelectorFotos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblImage;
	private JLabel lblAnimalSeleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectorFotos frame = new SelectorFotos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectorFotos() {
		
		setTitle("Selector Fotos");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelOpciones = new JPanel();
		contentPane.add(panelOpciones, BorderLayout.NORTH);

		JLabel lblInstrucciones = new JLabel("Elija una opcion para ver la imagen");
		panelOpciones.add(lblInstrucciones);

		// Panel para la imagen 
		JPanel panelImagen = new JPanel();
		contentPane.add(panelImagen, BorderLayout.CENTER);

		lblImage = new JLabel();
		panelImagen.add(lblImage);

		lblAnimalSeleccionado = new JLabel();
		panelImagen.add(lblAnimalSeleccionado);

		JPanel panelSalir = new JPanel();
		contentPane.add(panelSalir, BorderLayout.SOUTH);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea salir?", "Cerrar Programa",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Claro, que SI", "Claro, que NO" }, "Claro, que NO");

				if (opcion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		panelSalir.add(btnSalir);

		// Crear un ButtonGroup para agrupar los JRadioButtons
		ButtonGroup grupoOpciones = new ButtonGroup();

		JRadioButton rbtnPerro = new JRadioButton("Perro");
		rbtnPerro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarImagen("perros.jpg");
				lblAnimalSeleccionado.setText("Has elegido: Perro");
			}
		});
		panelOpciones.add(rbtnPerro);
		grupoOpciones.add(rbtnPerro); // Añadir al ButtonGroup

		JRadioButton rbtnGato = new JRadioButton("Gato");
		
		rbtnGato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarImagen("gatos.jpg");
				lblAnimalSeleccionado.setText("Has elegido: Gato");
			}
		});
		panelOpciones.add(rbtnGato);
		grupoOpciones.add(rbtnGato); // Añadir al ButtonGroup

		JRadioButton rbtnLeon = new JRadioButton("Leon");
		
		rbtnLeon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarImagen("leon.jpg");
				lblAnimalSeleccionado.setText("Has elegido: León"); 
			}
		});
		panelOpciones.add(rbtnLeon);
		grupoOpciones.add(rbtnLeon); // Añadir al ButtonGroup

		JRadioButton rbtnTigre = new JRadioButton("Tigre");
		
		rbtnTigre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarImagen("tigre.jpg");
				lblAnimalSeleccionado.setText("Has elegido: Tigre"); 
			}
		});
		panelOpciones.add(rbtnTigre);
		grupoOpciones.add(rbtnTigre); // Añadir al ButtonGroup
	}

	// Método para mostrar una imagen en el JLabel
	private void mostrarImagen(String nombreImagen) {
		
		ImageIcon icono = new ImageIcon(nombreImagen);
		Image imagen = icono.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Escalar imagen
		lblImage.setIcon(new ImageIcon(imagen)); // Cambiar el icono del JLabel
	}

}
