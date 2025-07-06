package componentesJavaSwingEjercicios2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventosMouse2 extends JFrame implements MouseListener {

    private static final long serialVersionUID = -2499287175555730331L;
    private JLabel titulo;
    private JButton boton;
    private JLabel etiquetaInferior;
    private JLabel contadorLabel;

    private int contadorZonaTitulo = 0;
    private int contadorZonaBoton = 0;
    private int contadorZonaInferior = 0;
    private int contadorPresionarBoton = 0; 

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        EventosMouse2 ventana = new EventosMouse2();
        ventana.setVisible(true);
    }

    public EventosMouse2() {
        // Configuración de la ventana
        setTitle("DAM 2 Interfaces Eventos del Mouse");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear el título y añadir eventos de mouse
        titulo = new JLabel("Eventos del Ratón", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.addMouseListener(this); 
        add(titulo, BorderLayout.NORTH);

        // Crear botón con eventos de mouse
        boton = new JButton("Presioname");
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.addMouseListener(this); 
        add(boton, BorderLayout.CENTER);

        // Crear etiqueta para mostrar mensajes en la parte inferior
        etiquetaInferior = new JLabel("Zona inferior", JLabel.LEFT);
        etiquetaInferior.addMouseListener(this); 
        add(etiquetaInferior, BorderLayout.SOUTH);

        // Crear un JLabel para mostrar los contadores
        contadorLabel = new JLabel();
        actualizarContadores(); 
        add(contadorLabel, BorderLayout.EAST);
    }

    // Método para actualizar los contadores en la interfaz
    private void actualizarContadores() {
        contadorLabel.setText("<html>Contador Título: " + contadorZonaTitulo +
                "<br>Contador Botón: " + contadorZonaBoton +
                "<br>Contador Zona Inferior: " + contadorZonaInferior +
                "<br>Veces Botón Presionado: " + contadorPresionarBoton + "</html>");
    }

    // Métodos del MouseListener para gestionar los eventos del mouse
    @Override
    public void mouseClicked(MouseEvent e) {
        // Si se hace clic en el botón, mostrar una ventana emergente con las estadísticas
        if (e.getSource() == boton) {
            contadorPresionarBoton++;
            actualizarContadores();
            mostrarEstadisticas();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // No es necesario en este caso
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // No es necesario en este caso
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Aumentar el contador según donde entre el ratón
        if (e.getSource() == titulo) {
            contadorZonaTitulo++;
        } else if (e.getSource() == boton) {
            contadorZonaBoton++;
        } else if (e.getSource() == etiquetaInferior) {
            contadorZonaInferior++;
        }
        // Actualizar los contadores en pantalla
        actualizarContadores();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No hacemos nada específico cuando el ratón sale de una zona
    }

    // Método para mostrar las estadísticas en una ventana emergente
    private void mostrarEstadisticas() {
        String mensaje = "Estadísticas actuales:\n" +
                "Contador Título: " + contadorZonaTitulo + "\n" +
                "Contador Botón: " + contadorZonaBoton + "\n" +
                "Contador Zona Inferior: " + contadorZonaInferior + "\n" +
                "Veces Botón Presionado: " + contadorPresionarBoton;
        JOptionPane.showMessageDialog(this, mensaje, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }
}
