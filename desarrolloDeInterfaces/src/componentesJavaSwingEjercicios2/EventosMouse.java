package componentesJavaSwingEjercicios2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventosMouse extends JFrame implements MouseListener {

	private static final long serialVersionUID = -2499287175555730331L;
	private JLabel titulo;
	private JButton boton;
	private JLabel etiquetaInferior;

	public static void main(String[] args) {
		
		// Crear y mostrar la ventana
		EventosMouse ventana = new EventosMouse();
		ventana.setVisible(true);
	}

	public EventosMouse() {
		
		// Configuración de la ventana
		setTitle("DAM 2 Interfaces Eventos del Mouse");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Crear el título y añadir eventos de mouse
		titulo = new JLabel("Eventos del Ratón", JLabel.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.addMouseListener(this); // Añadir listener al título
		add(titulo, BorderLayout.NORTH);

		// Crear botón con eventos de mouse
		boton = new JButton("Presioname");
		boton.setFont(new Font("Arial", Font.PLAIN, 16));
		boton.addMouseListener(this); // Añadir listener al botón
		add(boton, BorderLayout.CENTER);

		// Crear etiqueta para mostrar mensajes en la parte inferior
		etiquetaInferior = new JLabel("Salio del Titulo", JLabel.LEFT);
		etiquetaInferior.addMouseListener(this); // Añadir listener a la etiqueta inferior
		add(etiquetaInferior, BorderLayout.SOUTH);
	}

	// Métodos del MouseListener para gestionar los eventos del mouse

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == boton) {
			JOptionPane.showMessageDialog(this, "Has hecho clic en el botón.");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == boton) {
			boton.setText("Botón presionado");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == boton) {
			boton.setText("Presioname");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == titulo) {
			titulo.setText("Entró al Título");
		} else if (e.getSource() == boton) {
			boton.setText("Entró al Botón");
		} else if (e.getSource() == etiquetaInferior) {
			etiquetaInferior.setText("Entró al Pie");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == titulo) {
			titulo.setText("Salio del Titulo");
		} else if (e.getSource() == boton) {
			boton.setText("Presioname");
		} else if (e.getSource() == etiquetaInferior) {
			etiquetaInferior.setText("Salio del Pie");
		}
	}
}
