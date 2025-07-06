package socketsPruebas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//servidor simula como si fuera usuario


public abstract class VentanaChat extends JFrame {
	
    protected JTextArea areaMensajes;
    protected JTextField campoEntrada;
    protected JButton botonEnviar;

    public VentanaChat(String titulo) {
        super(titulo);
        inicializarUI();
    }

    private void inicializarUI() {
    	
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Área de mensajes
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaMensajes);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior: entrada y botón
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());
        campoEntrada = new JTextField();
        botonEnviar = new JButton("Enviar");
        panelInferior.add(campoEntrada, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método abstracto para definir la acción al enviar un mensaje
    protected abstract void enviarMensaje();
}
