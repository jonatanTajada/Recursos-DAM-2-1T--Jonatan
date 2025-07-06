package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SelectorFotos extends JFrame {

	private JButton btnSalir;
	private JLabel lblElijaOpcion;

	private JRadioButton rbPerro;
	private JRadioButton rbGato;
	private JRadioButton rbTigre;
	private JRadioButton rbLeon;

	private ButtonGroup grupoRadio;

	private JLabel lblAreaFoto;

	public SelectorFotos() {

		// configurar jframe
		setTitle("Selector de fotos");
		setSize(550, 650);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// panel norte
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblElijaOpcion = new JLabel("Elija opcion para ver la imagen");
		panelNorte.add(lblElijaOpcion);

		btnSalir = new JButton("SALIR");
		panelNorte.add(btnSalir);

		add(panelNorte, BorderLayout.NORTH);

		// panel oeste
		JPanel panelOeste = new JPanel();
		panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));

		rbPerro = new JRadioButton("Perro");
		rbGato = new JRadioButton("Gato");
		rbTigre = new JRadioButton("Tigre");
		rbLeon = new JRadioButton("Leon");

		grupoRadio = new ButtonGroup();
		grupoRadio.add(rbPerro);
		grupoRadio.add(rbGato);
		grupoRadio.add(rbTigre);
		grupoRadio.add(rbLeon);

		panelOeste.add(rbPerro);
		panelOeste.add(rbGato);
		panelOeste.add(rbTigre);
		panelOeste.add(rbLeon);

		add(panelOeste, BorderLayout.WEST);

		// panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		lblAreaFoto = new JLabel();
		lblAreaFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaFoto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panelCentral.add(lblAreaFoto);
		add(panelCentral, BorderLayout.CENTER);

		// *****crear eventos****

		// eventos radioButton

		rbPerro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cambiarImagen("perros.jpg");
			}
		});
		
		rbGato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cambiarImagen("gatos.jpg");
			}
		});
		
		rbTigre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cambiarImagen("tigre.jpg");
			}
		});
		
		rbLeon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cambiarImagen("leon.jpg");
			}
		});

	
		//boton salir
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int opcion = JOptionPane.showOptionDialog(null, "¿Desea salir?", "Cerrar programa", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, new String[] {"Claro que si!!", "Claro que no!"}, "Claro que si!!");
				
				if (opcion == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
	
		setVisible(true);
	
	}
	
	//metodo cambiar imagen
//	private void cambiarImagen(String nombreImagen) {
//		
//		ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen));
//		lblAreaFoto.setIcon(imagen);
//	}
	
	private void cambiarImagen(String nombreImagen) {
	    java.net.URL url = getClass().getResource(nombreImagen);
	    if (url != null) {
	        ImageIcon imagenOriginal = new ImageIcon(url);
	        Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(lblAreaFoto.getWidth(), lblAreaFoto.getHeight(), Image.SCALE_SMOOTH);
	        lblAreaFoto.setIcon(new ImageIcon(imagenEscalada));
	    } else {
	        System.out.println("No se pudo cargar la imagen: " + nombreImagen);
	        lblAreaFoto.setIcon(null);
	    }
	}

	
	
//----------------------------------------------------------	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new SelectorFotos();
				
			}
		});
		
	}

}
