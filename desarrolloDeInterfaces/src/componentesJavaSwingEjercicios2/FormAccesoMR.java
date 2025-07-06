package componentesJavaSwingEjercicios2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormAccesoMR implements ActionListener {

	private final int ANCHO = 400;
	private final int ALTURA = 200;

	private JFrame ventana;
	private JLabel pregunta;
	private JButton si;
	private JButton no;
	private boolean respuesta;

	public static void main(String[] args) {
		
		new FormAccesoMR();
	}
	
	
	public FormAccesoMR() {
		
		// Crear la ventana y establecer layout BorderLayout
		ventana = new JFrame("Acceso de movilidad reducida.");
		ventana.setSize(ANCHO, ALTURA);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// Crear los componentes
		pregunta = new JLabel("La atracción tiene acceso de movilidad reducida?", JLabel.CENTER);
		si = new JButton("Sí");
		no = new JButton("No");

		// Crear un panel para los botones 
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(1, 2, 10, 20)); 
		panelBotones.add(si);
		panelBotones.add(no);

		// Añadir los componentes a la ventana
		ventana.add(pregunta, BorderLayout.NORTH); 
		ventana.add(panelBotones, BorderLayout.SOUTH); 

		// Añadir los listeners a los botones
		anadirListenerBotones();

		// Hacer visible la ventana
		ventana.setVisible(true);
	}

	public void anadirListenerBotones() {
		si.addActionListener(this);
		no.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == si) {
			respuesta = true;
			System.out.println("Respuesta: Sí");
		} else if (evento.getSource() == no) {
			respuesta = false;
			System.out.println("Respuesta: No");
		}
	}

}
