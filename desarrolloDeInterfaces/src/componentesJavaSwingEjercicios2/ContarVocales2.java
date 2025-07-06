package componentesJavaSwingEjercicios2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ContarVocales2 extends JFrame implements KeyListener {

   
	private static final long serialVersionUID = 3831701947052329616L;
	// Declaración de componentes
    private JTextArea areaEntrada, areaSalida;
    private JLabel lblVocales;
    private int contadorVocales;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContarVocales2 ventana = new ContarVocales2();
            ventana.setVisible(true);
        });
    }

    public ContarVocales2() {
    	
        // Configuración de la ventana
        setTitle("Diseño de interfaces : Ventana Eventos del Teclado");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Etiqueta principal
        JLabel titulo = new JLabel("Eventos del Teclado", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Panel de texto para entrada y salida
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(2, 1));

        // Área de entrada de texto
        areaEntrada = new JTextArea("-Esto es una prueba para contar vocales:");
        areaEntrada.addKeyListener(this);  // Añadir el KeyListener para capturar teclas
        panelTexto.add(new JScrollPane(areaEntrada));

        // Área de salida de texto (solo para mostrar las vocales)
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);  // No editable
        panelTexto.add(new JScrollPane(areaSalida));

        // Añadir el panel de áreas de texto a la ventana
        add(panelTexto, BorderLayout.CENTER);

        // Panel inferior con información
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());

        // Etiqueta de información para salir y contar vocales
        JLabel info = new JLabel("Para salir presione la tecla Escape", JLabel.LEFT);
        lblVocales = new JLabel("Numero Vocales: 0", JLabel.RIGHT);

        panelInferior.add(info, BorderLayout.WEST);
        panelInferior.add(lblVocales, BorderLayout.EAST);

        // Añadir el panel inferior a la ventana
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Implementación del KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        // Obtener el carácter presionado
        char keyChar = e.getKeyChar();

        // Si el carácter es una vocal, mostrarla en el área de salida
        if (esVocal(keyChar)) {
            areaSalida.append(keyChar + " ");
            contadorVocales++;
            lblVocales.setText("Numero Vocales: " + contadorVocales);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
      
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);  
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // Método para verificar si un carácter es una vocal
    private boolean esVocal(char c) {
        return "aeiouáéíóúAEIOUÁÉÍÓÚ".indexOf(c) != -1;
    }


}
