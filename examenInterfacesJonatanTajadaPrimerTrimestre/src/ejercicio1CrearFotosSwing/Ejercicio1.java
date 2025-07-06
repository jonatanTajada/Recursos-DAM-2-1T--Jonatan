package ejercicio1CrearFotosSwing;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.File;

public class Ejercicio1 {

    public static void main(String[] args) {

        // Configurar ventana
        JFrame ventana = new JFrame();
        ventana.setTitle("Crear fotos Swing");
        ventana.setSize(600, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new BorderLayout());

        // Panel norte con menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(menuSalir);
        menuBar.add(menuArchivo);
        ventana.setJMenuBar(menuBar);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        ventana.add(panelCentral, BorderLayout.CENTER);

        // Subpanel para los datos
        JPanel panelDatos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNombre = new JLabel("Nombre:");
        JTextField campoNombre = new JTextField(20);

        JLabel labelCarpeta = new JLabel("Carpeta:");
        JComboBox<String> campoCarpeta = new JComboBox<>(new String[] { "Familia", "Trabajo", "Amigos" });

        JLabel labelDescripcion = new JLabel("Descripcion:");
        JTextField campoDescripcion = new JTextField(20);

        JLabel labelRuta = new JLabel("Ruta:");
        JTextField campoRuta = new JTextField(20);
        JButton btnBuscarRuta = new JButton("Buscar");

        // Añadir elementos al panel de datos
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelDatos.add(labelNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelDatos.add(campoNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDatos.add(labelCarpeta, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelDatos.add(campoCarpeta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelDatos.add(labelDescripcion, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelDatos.add(campoDescripcion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelDatos.add(labelRuta, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelDatos.add(campoRuta, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        panelDatos.add(btnBuscarRuta, gbc);

        // Añadir borde con titulo
        Border bordeConTitulo = BorderFactory.createTitledBorder("Datos de la foto");
        panelDatos.setBorder(bordeConTitulo);
        panelCentral.add(panelDatos, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.add(Box.createHorizontalGlue());
        JButton btnVerFoto = new JButton("Ver Foto");
        JButton btnBorrar = new JButton("Borrar");
        JButton btnGuardar = new JButton("Guardar");
        panelBotones.add(btnVerFoto);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnBorrar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnGuardar);
        panelBotones.add(Box.createHorizontalGlue());
        panelCentral.add(panelBotones, BorderLayout.SOUTH);

        // Panel sur con JTextArea
        JPanel panelSur = new JPanel(new BorderLayout());
        JTextArea areaTexto = new JTextArea(8, 50);
        areaTexto.setEditable(false);
        JScrollPane scrollTexto = new JScrollPane(areaTexto);
        panelSur.add(scrollTexto, BorderLayout.CENTER);
        ventana.add(panelSur, BorderLayout.SOUTH);

        // Eventos
        btnBuscarRuta.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int opcion = fileChooser.showOpenDialog(ventana);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                campoRuta.setText(archivoSeleccionado.getAbsolutePath());
                areaTexto.append("Seleccionada la ruta: " + archivoSeleccionado.getAbsolutePath() + "\n");
            }
        });

        btnVerFoto.addActionListener(e -> areaTexto.append("Pulsó Ver Foto\n"));

        btnBorrar.addActionListener(e -> {
            campoNombre.setText("");
            campoDescripcion.setText("");
            campoRuta.setText("");
            areaTexto.append("Pulsó Borrar: Campos limpiados\n");
        });

        btnGuardar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String carpeta = (String) campoCarpeta.getSelectedItem();
            String descripcion = campoDescripcion.getText();
            String ruta = campoRuta.getText();

            if (nombre.isEmpty() || ruta.isEmpty()) {
                areaTexto.append("Error: Debe completar los campos 'Nombre' y 'Ruta'\n");
            } else {
                areaTexto.append("Archivo guardado con los siguientes datos:\n" +
                        "Nombre: " + nombre + "\n" +
                        "Carpeta: " + carpeta + "\n" +
                        "Descripción: " + descripcion + "\n" +
                        "Ruta: " + ruta + "\n");
            }
        });

        // Mostrar ventana
        ventana.setVisible(true);
    }
}
