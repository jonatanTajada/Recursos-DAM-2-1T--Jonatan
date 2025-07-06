package adivinarPalabra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JuegoPalabras extends JFrame {

    private String[] palabrasEsdrujulas = { "música", "rápido", "práctico", "pájaro", "títere", "teléfono", "esdrújula",
            "cáscara", "mágico", "álgebra", "plástico", "número", "lógico", "épico", "telégrafo" };
    private String[] palabrasLlanas = { "casa", "árbol", "tigre", "mesa", "amigo", "lápiz", "calle", "pájaro", "cómodo",
            "silla", "carro", "pluma", "ratón", "libro", "espejo" };
    private String[] palabrasAgudas = { "camión", "sofá", "corazón", "ratón", "compás", "reloj", "capitán", "pan",
            "canción", "mamá", "papá", "jamás", "anís", "dragón", "balón" };

    private String[] todasPalabras = new String[45];

    private JLabel labelPalabra;
    private JList<String> listaAciertos;
    private JList<String> listaFallos;
    private DefaultListModel<String> modeloAciertos;
    private DefaultListModel<String> modeloFallos;
    private String palabraActual;
    private String tipoPalabraActual;
    private int totalPalabras = 0;
    private int aciertos = 0;

    public JuegoPalabras() {
        // Inicializa el array con palabras de todos los tipos
        System.arraycopy(palabrasEsdrujulas, 0, todasPalabras, 0, palabrasEsdrujulas.length);
        System.arraycopy(palabrasLlanas, 0, todasPalabras, palabrasEsdrujulas.length, palabrasLlanas.length);
        System.arraycopy(palabrasAgudas, 0, todasPalabras, palabrasEsdrujulas.length + palabrasLlanas.length,
                palabrasAgudas.length);

        setTitle("Juego de Palabras");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con la palabra a adivinar
        labelPalabra = new JLabel("", SwingConstants.CENTER);
        labelPalabra.setFont(new Font("Arial", Font.BOLD, 24));
        add(labelPalabra, BorderLayout.NORTH);

        // Panel central con las listas de aciertos y fallos
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(1, 2));

        JPanel panelAciertos = new JPanel(new BorderLayout());
        JLabel labelAciertos = new JLabel("Pantalla de palabras acertadas", SwingConstants.CENTER);
        modeloAciertos = new DefaultListModel<>();
        listaAciertos = new JList<>(modeloAciertos);
        panelAciertos.add(labelAciertos, BorderLayout.NORTH);
        panelAciertos.add(new JScrollPane(listaAciertos), BorderLayout.CENTER);

        JPanel panelFallos = new JPanel(new BorderLayout());
        JLabel labelFallos = new JLabel("Pantalla de palabras erróneas", SwingConstants.CENTER);
        modeloFallos = new DefaultListModel<>();
        listaFallos = new JList<>(modeloFallos);
        panelFallos.add(labelFallos, BorderLayout.NORTH);
        panelFallos.add(new JScrollPane(listaFallos), BorderLayout.CENTER);

        panelCentro.add(panelAciertos);
        panelCentro.add(panelFallos);

        add(panelCentro, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1)); // Un layout para los botones y el botón Finalizar

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(1, 3));

        JButton botonEsdrujula = new JButton("Esdrújula");
        JButton botonLlano = new JButton("Llano");
        JButton botonAguda = new JButton("Aguda");

        botonEsdrujula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarRespuesta("Esdrújula");
            }
        });

        botonLlano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarRespuesta("Llano");
            }
        });

        botonAguda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarRespuesta("Aguda");
            }
        });

        panelOpciones.add(botonEsdrujula);
        panelOpciones.add(botonLlano);
        panelOpciones.add(botonAguda);

        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarJuego();
            }
        });

        panelBotones.add(panelOpciones);
        panelBotones.add(botonFinalizar);

        add(panelBotones, BorderLayout.SOUTH);

        // Muestra la primera palabra
        nuevaPalabra();
    }

    // Método para elegir una nueva palabra aleatoria
    private void nuevaPalabra() {
        Random random = new Random();
        int indice = random.nextInt(todasPalabras.length);
        palabraActual = todasPalabras[indice];

        if (indice < palabrasEsdrujulas.length) {
            tipoPalabraActual = "Esdrújula";
        } else if (indice < palabrasEsdrujulas.length + palabrasLlanas.length) {
            tipoPalabraActual = "Llano";
        } else {
            tipoPalabraActual = "Aguda";
        }

        // Cambiar el color de la palabra usando HTML
        String palabraFormateada = "<html>Tipo de palabra: <span style='color:#006400;'>" + palabraActual + "</span></html>";
        labelPalabra.setText(palabraFormateada);
    }

    // Método para comprobar si la respuesta es correcta
    private void comprobarRespuesta(String respuesta) {
        totalPalabras++;
        if (respuesta.equals(tipoPalabraActual)) {
            aciertos++;
            modeloAciertos.addElement(palabraActual + " (" + tipoPalabraActual + ")");
        } else {
            modeloFallos.addElement(palabraActual + " (" + tipoPalabraActual + ")");
        }
        nuevaPalabra(); // Mostrar nueva palabra
    }

    // Método para finalizar el juego y mostrar el resultado
    private void finalizarJuego() {
        int fallos = totalPalabras - aciertos;
        String mensaje = "Total acertadas: " + aciertos + "\nTotal erróneas: " + fallos + "\n";
        double porcentajeAciertos = ((double) aciertos / totalPalabras) * 100;

        if (porcentajeAciertos >= 50) {
            mensaje += "¡Aprobado!";
        } else {
            mensaje += "Suspendido.";
        }

        JOptionPane.showMessageDialog(this, mensaje, "Resultado Final", JOptionPane.INFORMATION_MESSAGE);

        // Reiniciar el juego automáticamente
        reiniciarJuego();
    }

    // Método para reiniciar el juego
    private void reiniciarJuego() {
        totalPalabras = 0;
        aciertos = 0;
        modeloAciertos.clear();
        modeloFallos.clear();
        nuevaPalabra(); // Empezar con una nueva palabra
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JuegoPalabras().setVisible(true);
            }
        });
    }
}
