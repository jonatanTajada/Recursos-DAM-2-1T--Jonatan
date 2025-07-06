package componentesJavaSwingEjercicios2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class FormMatricula implements ActionListener {

	private final int ANCHO = 550;
	private final int ALTO = 450;
	
	private JFrame ventana;
	private JLabel lMatricula;
	private JTextField tMatricula;
	private JButton matricular;
	private JPanel panelNorte;
	private JTextArea areaTexto;
	private JScrollPane scrollPane;

	private ArrayList<Barco> barcosRegistrados;

	public static void main(String[] args) {
		new FormMatricula();
	}

	public FormMatricula() {

		barcosRegistrados = new ArrayList<>();

		// Crear la ventana
		ventana = new JFrame("Matriculación barco");
		ventana.setSize(ANCHO, ALTO);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout());

		// Crear el panel superior
		panelNorte = new JPanel();
		panelNorte.setLayout(new GridLayout(2, 1, 5, 5));

		// Crear un sub-panel para la matrícula y añadir la etiqueta y el campo de texto
		JPanel panelMatrícula = new JPanel(new BorderLayout());
		lMatricula = new JLabel("Matrícula:");
		tMatricula = new JTextField("7º-PM-1-01-11", 15);
		panelMatrícula.add(lMatricula, BorderLayout.WEST);
		panelMatrícula.add(tMatricula, BorderLayout.CENTER);

		panelNorte.add(panelMatrícula);

		// Crear el boton Matricular
		matricular = new JButton("Matricular");
		panelNorte.add(matricular);

		// Añadir el panel al JFrame
		ventana.add(panelNorte, BorderLayout.NORTH);

		// Crear el area de texto para mostrar los datos de los barcos registrados
		areaTexto = new JTextArea();
		areaTexto.setEditable(false); 
		scrollPane = new JScrollPane(areaTexto);

		// Asegurarse de que el JScrollPane se expanda al maximo
		scrollPane.setPreferredSize(new Dimension(ANCHO, ALTO));
		ventana.add(scrollPane, BorderLayout.CENTER);

		// Añadir listener al boton
		añadirListenerBotones();

		// Hacer visible la ventana
		ventana.setVisible(true);
	}

	public void añadirListenerBotones() {
		matricular.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		String matricula = tMatricula.getText();

		// Verificar si la matrícula ya está registrada
		if (existeBarcoPorMatricula(matricula)) {

			JOptionPane.showMessageDialog(ventana, "Error: La matricula '" + matricula + "' ya esta registrada. Introduce una nueva.", "Matricula duplicada", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Mostrar ventanas emergentes para recoger los datos adicionales
		String nombre = JOptionPane.showInputDialog(ventana, "Introduce el nombre del barco:");
		String esloraStr = JOptionPane.showInputDialog(ventana, "Introduce la eslora del barco (en metros):");
		String cabinasStr = JOptionPane.showInputDialog(ventana, "Introduce el número de cabinas:");

		// Validación de los datos introducidos
		try {
			float eslora = Float.parseFloat(esloraStr);
			int cabinas = Integer.parseInt(cabinasStr);

			Barco nuevoBarco = new Barco(matricula, nombre, eslora, cabinas);
			barcosRegistrados.add(nuevoBarco);

			// Actualizar el area de texto para mostrar todos los barcos matriculados
			actualizarAreaTexto();
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(ventana, "Error: Asegurate de introducir numeros validos para la eslora y las cabinas.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Metodo para verificar si ya existe un barco con la misma matricula
	private boolean existeBarcoPorMatricula(String matricula) {
		for (Barco barco : barcosRegistrados) {
			if (barco.getMatricula().equals(matricula)) {
				return true;
			}
		}
		return false;
	}

	// Metodo para actualizar el area de texto 
	private void actualizarAreaTexto() {
		areaTexto.setText(""); 
		for (Barco barco : barcosRegistrados) {
			areaTexto.append(barco.toString() + "\n\n"); 
		}
	}
}
