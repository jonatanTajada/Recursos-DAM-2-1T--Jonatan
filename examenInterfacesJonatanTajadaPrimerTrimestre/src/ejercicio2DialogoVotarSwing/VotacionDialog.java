package ejercicio2DialogoVotarSwing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VotacionDialog {

	public static void main(String[] args) {
		
		// Crear el JDialog
		JDialog ventanaVotacion = new JDialog();
		ventanaVotacion.setTitle("Emitir Voto");
		ventanaVotacion.setSize(700, 600);
		ventanaVotacion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ventanaVotacion.setLocationRelativeTo(null);
		ventanaVotacion.setLayout(new BorderLayout(10, 10));

		// Etiqueta de instrucciones
		JLabel etiquetaInstrucciones = new JLabel("Selecciona tu candidato y haz clic en 'Votar'.", JLabel.CENTER);
		etiquetaInstrucciones.setFont(new Font("Arial", Font.BOLD, 14));
		ventanaVotacion.add(etiquetaInstrucciones, BorderLayout.NORTH);

		// Panel central para los JRadioButton
		JPanel panelCandidatos = new JPanel();
		panelCandidatos.setLayout(new BoxLayout(panelCandidatos, BoxLayout.Y_AXIS));
		ButtonGroup grupoCandidatos = new ButtonGroup();

		// Crear 4 opciones de candidatos
		JRadioButton candidato1 = new JRadioButton("Candidato 1: Goku");
		JRadioButton candidato2 = new JRadioButton("Candidato 2: Sady Sadie");
		JRadioButton candidato3 = new JRadioButton("Candidato 3: RIP McDaniel");
		JRadioButton candidato4 = new JRadioButton("Candidato 4: Duke de Java");

		// Añadir los botones al grupo y al panel
		grupoCandidatos.add(candidato1);
		grupoCandidatos.add(candidato2);
		grupoCandidatos.add(candidato3);
		grupoCandidatos.add(candidato4);

		panelCandidatos.add(candidato1);
		panelCandidatos.add(candidato2);
		panelCandidatos.add(candidato3);
		panelCandidatos.add(candidato4);

		// Espaciado y borde visual
		panelCandidatos.setBorder(BorderFactory.createTitledBorder("Candidatos"));
		ventanaVotacion.add(panelCandidatos, BorderLayout.CENTER);

		// Panel inferior para el boton de votar y la etiqueta de resultado
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));

		JButton botonVotar = new JButton("Votar");
		JLabel etiquetaAntesDeVotar = new JLabel("¡Vota ahora!", JLabel.CENTER); 
		JLabel etiquetaResultado = new JLabel("", JLabel.CENTER); // 

		etiquetaAntesDeVotar.setFont(new Font("Arial", Font.ITALIC, 12));
		etiquetaResultado.setFont(new Font("Arial", Font.ITALIC, 12));

		// Añadir los componentes al panel inferior
		panelInferior.add(botonVotar);
		panelInferior.add(Box.createVerticalStrut(10)); 
		panelInferior.add(etiquetaAntesDeVotar); 
		panelInferior.add(Box.createVerticalStrut(5)); 
		panelInferior.add(etiquetaResultado); 

		ventanaVotacion.add(panelInferior, BorderLayout.SOUTH);

		// Evento del boton: Votar
		botonVotar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (candidato1.isSelected()) {
					
					JOptionPane.showMessageDialog(ventanaVotacion,
							"Este candidato es un dibujo animado. Voto inválido.", "Mensaje",
							JOptionPane.ERROR_MESSAGE);
					etiquetaResultado.setText("Voto inválido para Goku.");
				} else if (candidato2.isSelected()) {
					
					int respuesta = JOptionPane.showConfirmDialog(ventanaVotacion,
							"Esta candidata es una delincuente convicta. ¿Está seguro que vota por ella?",
							"Una pregunta de seguimiento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (respuesta == JOptionPane.YES_OPTION) {
						
						etiquetaResultado.setText("Has votado por Sady Sadie.");
					} else {
						
						etiquetaResultado.setText("Voto cancelado para Sady Sadie.");
					}
				} else if (candidato3.isSelected()) {
					
					Object[] opciones = { "Sí, por favor", "No, gracias" };
					int respuesta = JOptionPane.showOptionDialog(ventanaVotacion,
							"Este candidato está muerto. ¿Todavía quiere votar por él?", "Una pregunta de seguimiento",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
					
					if (respuesta == JOptionPane.YES_OPTION) {
						
						etiquetaResultado.setText("Has votado por RIP McDaniel.");
					} else {
						
						etiquetaResultado.setText("Voto cancelado para RIP McDaniel.");
					}
					
				} else if (candidato4.isSelected()) {
					
					int respuesta = JOptionPane.showConfirmDialog(ventanaVotacion,
							"Duke es una mascota de cartón. ¿Todavía quieres emitir tu voto?",
							"Una pregunta de seguimiento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (respuesta == JOptionPane.YES_OPTION) {
						
						etiquetaResultado.setText("Has votado por Duke de Java.");
					} else {
						
						etiquetaResultado.setText("Voto cancelado para Duke de Java.");
					}
				} else {
					JOptionPane.showMessageDialog(ventanaVotacion, "Por favor, selecciona un candidato antes de votar.",
							"Error", JOptionPane.WARNING_MESSAGE);
					etiquetaResultado.setText("");
				}

				etiquetaAntesDeVotar.setText("Voto emitido.");
			}
		});

		ventanaVotacion.setVisible(true);
	}
}
