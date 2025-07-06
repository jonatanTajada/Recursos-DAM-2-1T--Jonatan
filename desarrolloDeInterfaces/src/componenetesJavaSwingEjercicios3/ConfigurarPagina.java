package componenetesJavaSwingEjercicios3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurarPagina extends JFrame {

    private static final long serialVersionUID = -673528825014244789L;
    private JComboBox<String> orientacionComboBox;
    private JSpinner margenSuperiorSpinner, margenInferiorSpinner;
    private JPanel panelHoja, panelAzul;
    private JLabel labelHoja;
    private int margenSuperior = 10, margenInferior = 10;
    private boolean orientacionVertical = true;

    public static void main(String[] args) {
        ConfigurarPagina ventana = new ConfigurarPagina();
        ventana.setVisible(true);
    }

    public ConfigurarPagina() {
    	
        // Configuración de la ventana
        setTitle("Configuración de Página");
        setSize(600, 450);  // Ajustar tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Label Hoja
        labelHoja = new JLabel("Hoja");
        labelHoja.setBounds(80, 30, 50, 20); // Posicionar más arriba
        add(labelHoja);

        // Panel para representar la hoja (sin recuadro azul)
        panelHoja = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarHoja(g);
            }
        };
        panelHoja.setBounds(50, 50, 120, 200);  // Ajustar el tamaño y posición del panel de la hoja
        panelHoja.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        add(panelHoja);

        // Panel azul para representar el cambio de orientación
        panelAzul = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPanelAzul(g);
            }
        };
        panelAzul.setBounds(330, 120, 200, 200); 
        panelAzul.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        add(panelAzul);

        // Spinner para el margen superior
        JLabel labelMargenSuperior = new JLabel("Margen superior");
        labelMargenSuperior.setBounds(200, 50, 150, 20);
        add(labelMargenSuperior);

        margenSuperiorSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 50, 1));
        margenSuperiorSpinner.setBounds(200, 70, 50, 30);
        margenSuperiorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margenSuperior = (int) margenSuperiorSpinner.getValue();
                panelHoja.repaint();
            }
        });
        add(margenSuperiorSpinner);

        // Spinner para el margen inferior
        JLabel labelMargenInferior = new JLabel("Margen inferior");
        labelMargenInferior.setBounds(200, 120, 150, 20);
        add(labelMargenInferior);

        margenInferiorSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 50, 1));
        margenInferiorSpinner.setBounds(200, 140, 50, 30);
        margenInferiorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margenInferior = (int) margenInferiorSpinner.getValue();
                panelHoja.repaint();
            }
        });
        add(margenInferiorSpinner);

        // ComboBox para la orientación del panel azul
        JLabel labelOrientacion = new JLabel("Orientación de página:");
        labelOrientacion.setBounds(330, 50, 150, 20);
        add(labelOrientacion);

        String[] orientaciones = {"Vertical", "Horizontal"};
        orientacionComboBox = new JComboBox<>(orientaciones);
        orientacionComboBox.setBounds(330, 70, 120, 30);
        orientacionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) orientacionComboBox.getSelectedItem();
                orientacionVertical = seleccion.equals("Vertical");
                panelAzul.setBounds(330, 120, orientacionVertical ? 100 : 200, orientacionVertical ? 200 : 100);  // Cambiar tamaño y forma
                panelAzul.repaint();
            }
        });
        add(orientacionComboBox);

        // Botón Inicializar
        JButton botonInicializar = new JButton("Inicializar");
        botonInicializar.setBounds(200, 300, 100, 30);
        botonInicializar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializar();
            }
        });
        add(botonInicializar);
    }

    // Método para dibujar la hoja con márgenes
    private void dibujarHoja(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(10, 10, 100, 150); // Dibujar la hoja en el panel de la hoja
        // Dibujar márgenes
        g.setColor(Color.BLUE);
        g.fillRect(10, 10 + margenSuperior, 100, 5);
        g.fillRect(10, 160 - margenInferior, 100, 5); 
    }

    // Método para dibujar el panel azul con la orientación seleccionada
    private void dibujarPanelAzul(Graphics g) {
        g.setColor(Color.BLUE);
        if (orientacionVertical) {
            g.fillRect(20, 10, 50, 100); // Dibujar en vertical
        } else {
            g.fillRect(20, 10, 100, 50); // Dibujar en horizontal
        }
    }

    // Método para inicializar la configuración
    private void inicializar() {
        margenSuperiorSpinner.setValue(10);
        margenInferiorSpinner.setValue(10);
        orientacionComboBox.setSelectedItem("Vertical");
        panelAzul.setBounds(330, 120, 100, 200);  // Inicializar en vertical
        panelAzul.repaint();
    }
}
