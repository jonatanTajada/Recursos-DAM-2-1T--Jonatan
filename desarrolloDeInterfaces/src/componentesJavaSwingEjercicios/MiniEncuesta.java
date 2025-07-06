package componentesJavaSwingEjercicios;

import javax.swing.*;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Realizado sin windowsBuilder
//Ejercicio 4/4

public class MiniEncuesta extends JFrame {

    private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MiniEncuesta();
            }
        });
    }

    // Constructor del JFrame
    public MiniEncuesta() {
        setTitle("Mini Encuesta");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para organizar los componentes

        // Crear los componentes

        // Sistema operativo (JRadioButton)
        JLabel lblSistemaOperativo = new JLabel("Elige un sistema operativo");
        JRadioButton rbtnWindows = new JRadioButton("Windows");
        JRadioButton rbtnLinux = new JRadioButton("Linux");
        JRadioButton rbtnMac = new JRadioButton("Mac");

        // Agrupamos los JRadioButtons para que solo se pueda seleccionar uno
        ButtonGroup bgSistemaOperativo = new ButtonGroup();
        bgSistemaOperativo.add(rbtnWindows);
        bgSistemaOperativo.add(rbtnLinux);
        bgSistemaOperativo.add(rbtnMac);

        // Especialidades (JCheckBox)
        JLabel lblEspecialidad = new JLabel("Elige tu especialidad");
        JCheckBox cbProgramacion = new JCheckBox("Programación");
        JCheckBox cbDisenoGrafico = new JCheckBox("Diseño gráfico");
        JCheckBox cbAdministracion = new JCheckBox("Administración");

        // Horas dedicadas (JSlider)
        JLabel lblHoras = new JLabel("Horas que dedicas en el ordenador");
        JSlider sliderHoras = new JSlider(0, 10, 0); // Valores de 0 a 10, empezando en 0
        JLabel lblValorSlider = new JLabel("0"); // Mostramos el valor del slider

        // Añadir un listener para que el JLabel se actualice con el valor del slider
        sliderHoras.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblValorSlider.setText(String.valueOf(sliderHoras.getValue()));
            }
        });

        // Botón para generar el mensaje
        JButton btnGenerar = new JButton("Generar");

        // Acción del botón
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener sistema operativo seleccionado
                String sistemaOperativo = "";
                if (rbtnWindows.isSelected()) {
                    sistemaOperativo = "Windows";
                } else if (rbtnLinux.isSelected()) {
                    sistemaOperativo = "Linux";
                } else if (rbtnMac.isSelected()) {
                    sistemaOperativo = "Mac";
                }

                // Obtener especialidades seleccionadas
                String especialidades = "";
                if (cbProgramacion.isSelected()) {
                    especialidades += "Programación ";
                }
                if (cbDisenoGrafico.isSelected()) {
                    especialidades += "Diseño gráfico ";
                }
                if (cbAdministracion.isSelected()) {
                    especialidades += "Administración ";
                }

                // Obtener valor del slider
                int horas = sliderHoras.getValue();

                // Mostrar el mensaje
                JOptionPane.showMessageDialog(null, 
                    "Tu sistema operativo preferido es " + sistemaOperativo + ",\n" +
                    "tus especialidades son " + especialidades + "\n" +
                    "y el número de horas dedicadas al ordenador son " + horas, 
                    "Muestra de datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Layout y posicionamiento de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Agregar componentes al JFrame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblSistemaOperativo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(rbtnWindows, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(rbtnLinux, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(rbtnMac, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblEspecialidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(cbProgramacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(cbDisenoGrafico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(cbAdministracion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(lblHoras, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(sliderHoras, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(lblValorSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(btnGenerar, gbc);

        // Hacer visible el JFrame
        setVisible(true);
    }

 
}

