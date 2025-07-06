package PrimerosEjerciciosTarea1Vista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Prueba {

	
	public static void main(String[] args) {
		
		
		JFrame ventana = new JFrame("Ejemplo JLabel");
		
		JLabel label = new JLabel("Intenta pulsar el boton");
		JButton boton = new JButton("Es aqui");
		
		boton.addActionListener(e -> label.setText("Enorabuena has pulsado el boton"));
		
		ventana.setLayout(new FlowLayout());
		ventana.add(label);
		ventana.add(boton);
		
		ventana.setVisible(true);
		ventana.setSize(300, 400);
		ventana.setLocation(300, 100);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
