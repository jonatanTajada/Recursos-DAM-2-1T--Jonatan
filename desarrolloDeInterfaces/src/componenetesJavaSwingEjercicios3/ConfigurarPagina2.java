package componenetesJavaSwingEjercicios3;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurarPagina2 extends JFrame {

	private static final long serialVersionUID = -673528825014244789L;
	private JComboBox<String> orientacionComboBox;
	private JSpinner margenSuperiorSpinner, margenInferiorSpinner, margenIzquierdoSpinner, margenDerechoSpinner;
	private JPanel panelHoja;
	private int margenSuperior = 10, margenInferior = 10, margenIzquierdo = 10, margenDerecho = 10;
	private boolean orientacionVertical = true;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ConfigurarPagina2 ventana = new ConfigurarPagina2();
				ventana.setVisible(true);
			}
		});
	}

	public ConfigurarPagina2() {
		// Configuración de la ventana
		setTitle("Configuración de Página");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos BorderLayout para organizar los paneles principales
		setLayout(new BorderLayout());

		// Panel para los controles superiores (Orientación)
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new TitledBorder("Orientación de Página"));
		panelSuperior.setLayout(new FlowLayout());

		String[] orientaciones = { "Vertical", "Horizontal" };
		orientacionComboBox = new JComboBox<>(orientaciones);
		orientacionComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) orientacionComboBox.getSelectedItem();
				orientacionVertical = seleccion.equals("Vertical");
				panelHoja.repaint();
			}
		});
		panelSuperior.add(orientacionComboBox);

		// Panel para representar la hoja
		panelHoja = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				dibujarHoja(g);
			}
		};
		panelHoja.setPreferredSize(new Dimension(400, 500));
		panelHoja.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

		// Panel central que contiene el panelSuperior y el panelHoja
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		panelCentral.add(panelSuperior, BorderLayout.NORTH);
		panelCentral.add(panelHoja, BorderLayout.CENTER);

		// Panel para los controles de márgenes
		JPanel panelMargenes = new JPanel();
		panelMargenes.setBorder(new TitledBorder("Configuración de Márgenes"));
		panelMargenes.setLayout(new GridLayout(4, 2, 5, 5));

		// Spinner para el margen superior
		panelMargenes.add(new JLabel("Margen Superior:"));
		margenSuperiorSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		margenSuperiorSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				margenSuperior = (int) margenSuperiorSpinner.getValue();
				panelHoja.repaint();
			}
		});
		panelMargenes.add(margenSuperiorSpinner);

		// Spinner para el margen inferior
		panelMargenes.add(new JLabel("Margen Inferior:"));
		margenInferiorSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		margenInferiorSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				margenInferior = (int) margenInferiorSpinner.getValue();
				panelHoja.repaint();
			}
		});
		panelMargenes.add(margenInferiorSpinner);

		// Spinner para el margen izquierdo
		panelMargenes.add(new JLabel("Margen Izquierdo:"));
		margenIzquierdoSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		margenIzquierdoSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				margenIzquierdo = (int) margenIzquierdoSpinner.getValue();
				panelHoja.repaint();
			}
		});
		panelMargenes.add(margenIzquierdoSpinner);

		// Spinner para el margen derecho
		panelMargenes.add(new JLabel("Margen Derecho:"));
		margenDerechoSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		margenDerechoSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				margenDerecho = (int) margenDerechoSpinner.getValue();
				panelHoja.repaint();
			}
		});
		panelMargenes.add(margenDerechoSpinner);

		// Panel inferior con el botón inicializar
		JPanel panelInferior = new JPanel();
		JButton botonInicializar = new JButton("Inicializar");
		botonInicializar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inicializar();
			}
		});
		panelInferior.add(botonInicializar);

		// Agregar los paneles al frame principal
		add(panelCentral, BorderLayout.CENTER);
		add(panelMargenes, BorderLayout.EAST);
		add(panelInferior, BorderLayout.SOUTH);
	}
	
// ------------------------------------------------------------------------------------------------------	

	// Método para dibujar la hoja con márgenes y orientación
	private void dibujarHoja(Graphics g) {
		int anchoHoja = orientacionVertical ? 300 : 400;
		int altoHoja = orientacionVertical ? 400 : 300;

		// Centro la hoja en el panel
		int inicioX = (panelHoja.getWidth() - anchoHoja) / 2;
		int inicioY = (panelHoja.getHeight() - altoHoja) / 2;

		// Dibujar la hoja
		g.setColor(Color.WHITE);
		g.fillRect(inicioX, inicioY, anchoHoja, altoHoja);
		g.setColor(Color.BLACK);
		g.drawRect(inicioX, inicioY, anchoHoja, altoHoja);

		// Dibujar los márgenes
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(inicioX + margenIzquierdo, inicioY + margenSuperior, anchoHoja - margenIzquierdo - margenDerecho,
				altoHoja - margenSuperior - margenInferior);

		// Líneas para visualizar los márgenes
		g.setColor(Color.RED);
		// Margen superior
		g.drawLine(inicioX, inicioY + margenSuperior, inicioX + anchoHoja, inicioY + margenSuperior);
		// Margen inferior
		g.drawLine(inicioX, inicioY + altoHoja - margenInferior, inicioX + anchoHoja,
				inicioY + altoHoja - margenInferior);
		// Margen izquierdo
		g.drawLine(inicioX + margenIzquierdo, inicioY, inicioX + margenIzquierdo, inicioY + altoHoja);
		// Margen derecho
		g.drawLine(inicioX + anchoHoja - margenDerecho, inicioY, inicioX + anchoHoja - margenDerecho,
				inicioY + altoHoja);
	}

	// Método para inicializar la configuración
	private void inicializar() {
		margenSuperiorSpinner.setValue(10);
		margenInferiorSpinner.setValue(10);
		margenIzquierdoSpinner.setValue(10);
		margenDerechoSpinner.setValue(10);
		orientacionComboBox.setSelectedItem("Vertical");
		margenSuperior = 10;
		margenInferior = 10;
		margenIzquierdo = 10;
		margenDerecho = 10;
		orientacionVertical = true;
		panelHoja.repaint();
	}
}
