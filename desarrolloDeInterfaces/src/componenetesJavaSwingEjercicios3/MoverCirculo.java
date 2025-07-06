package componenetesJavaSwingEjercicios3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoverCirculo extends JFrame {

  
	private static final long serialVersionUID = 5396493112054784517L;
	private int x = 200; // Posición inicial en el eje X
    private final int y = 150; // Posición fija en el eje Y
    private final int diametro = 100; // Diámetro del círculo

    public static void main(String[] args) {
        MoverCirculo ventana = new MoverCirculo();
        ventana.setVisible(true);
    }

    public MoverCirculo() {
        // Configuración de la ventana
        setTitle("Mover Círculo");
        setSize(500, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        // Botón para mover a la izquierda
        JButton botonIzquierda = new JButton("Izquierda");
        botonIzquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverIzquierda(); // Mover el círculo a la izquierda
            }
        });
        panelBotones.add(botonIzquierda);

        // Botón para mover a la derecha
        JButton botonDerecha = new JButton("Derecha");
        botonDerecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverDerecha(); // Mover el círculo a la derecha
            }
        });
        panelBotones.add(botonDerecha);

        add(panelBotones, BorderLayout.SOUTH); // Añadir el panel de botones en la parte inferior
    }

    // Método para mover el círculo a la izquierda
    public void moverIzquierda() {
        if (x > 0) { // Asegurarse de que el círculo no salga de la ventana por la izquierda
            x -= 100; // Mover 10 píxeles a la izquierda
            repaint(); // Repintar la pantalla
        }
    }

    // Método para mover el círculo a la derecha
    public void moverDerecha() {
        if (x < getWidth() - diametro) { // Asegurarse de que el círculo no salga de la ventana por la derecha
            x += 100; // Mover 10 píxeles a la derecha
            repaint(); // Repintar la pantalla
        }
    }

    // Sobrescribir el método paint para dibujar el círculo
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Llamar al método paint original
        g.setColor(Color.RED); // Definir el color del círculo
        g.fillOval(x, y, diametro, diametro); // Dibujar el círculo en la posición (x, y)
    }
}
