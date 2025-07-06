package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MoverCirculo extends JFrame{

	private static final long serialVersionUID = -3954623218435894154L;
	
	private JPanel panelDibujo;
	private JPanel panelBotones;
	
	private JButton btnIzquierda;
	private JButton btnDerecha;
	
	private int xCirculo = 250;
	private final int yCirculo = 200;
	private final int DIAMETRO = 50;
	

	
	public MoverCirculo() {
		
		//configurar ventana
		setTitle("Mover circulo");
		setSize(650, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		//panel dibujo
		panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar el círculo rojo
                g.setColor(Color.RED);
                g.fillOval(xCirculo, yCirculo, DIAMETRO, DIAMETRO);
            }
        };
		panelDibujo.setPreferredSize(new Dimension(600, 300));
		add(panelDibujo, BorderLayout.CENTER);
		
		
		//panel botones
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		
		btnIzquierda = new JButton("Izquierda");
		btnDerecha = new JButton("Derecha");
		
		panelBotones.add(btnIzquierda);
		panelBotones.add(btnDerecha);
		
		add(panelBotones, BorderLayout.SOUTH);
		
		
		//acciones para mover el circulo
		btnIzquierda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (xCirculo > 0) {
					xCirculo -= 50;
					panelDibujo.repaint();
				}
				
			}
		});
		
		
		btnDerecha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (xCirculo + DIAMETRO < panelDibujo.getWidth()) {
					xCirculo +=50;
					panelDibujo.repaint();
				}
			}
		});
		
		setVisible(true);
	}
	
//--------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {

				new MoverCirculo();
			}
		});
	}
	
}
