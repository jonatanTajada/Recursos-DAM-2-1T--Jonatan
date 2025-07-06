package ejercicio3LayoutDemo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EtiquetaInterfaz {

	public static void main(String[] args) {
		
		
		// Crear la ventana principal
		JFrame ventana = new JFrame("Configurador de Etiqueta");
		ventana.setSize(500, 400);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new GridLayout(3, 2, 10, 10)); 

		// Crear la etiqueta
		JLabel etiqueta = new JLabel("HOLA MUNDO!!", JLabel.CENTER);
		etiqueta.setFont(new Font("Arial", Font.PLAIN, 20));
		etiqueta.setOpaque(true); 
		etiqueta.setBackground(Color.LIGHT_GRAY); 
		etiqueta.setForeground(Color.BLACK); 

		// Slider para cambiar el fondo de la etiqueta
		JSlider sliderFondo = new JSlider(0, 255, 127); 
		sliderFondo.setMajorTickSpacing(50);
		sliderFondo.setPaintTicks(true);
		sliderFondo.setPaintLabels(true);

		// Slider para cambiar el color del texto de la etiqueta
		JSlider sliderTexto = new JSlider(0, 255, 0); 
		sliderTexto.setMajorTickSpacing(50);
		sliderTexto.setPaintTicks(true);
		sliderTexto.setPaintLabels(true);

		// Botones para los estilos de fuente
		JButton btnPlainFont = new JButton("Plain Font");
		JButton btnItalicFont = new JButton("Italic Font");
		JButton btnBoldFont = new JButton("Bold Font");

		// Añadir componentes a la ventana 
		ventana.add(etiqueta); 
		ventana.add(btnPlainFont); 

		ventana.add(sliderFondo); 
		ventana.add(btnItalicFont); 

		ventana.add(sliderTexto); 
		ventana.add(btnBoldFont); 

		// Eventos para los sliders
		sliderFondo.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int valorFondo = sliderFondo.getValue();
				etiqueta.setBackground(new Color(valorFondo, valorFondo, valorFondo)); 
			}
		});

		sliderTexto.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int valorTexto = sliderTexto.getValue();
				etiqueta.setForeground(new Color(valorTexto, 0, 255 - valorTexto)); 
			}
		});
		

		
		// Eventos para los botones
		btnPlainFont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				etiqueta.setFont(new Font("Arial", Font.PLAIN, 20));
			}
		});

		btnItalicFont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				etiqueta.setFont(new Font("Arial", Font.ITALIC, 20));
			}
		});

		btnBoldFont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				etiqueta.setFont(new Font("Arial", Font.BOLD, 20));
			}
		});

	
		ventana.setVisible(true);
	}
}
