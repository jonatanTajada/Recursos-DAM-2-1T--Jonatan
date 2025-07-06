package componentesJavaSwingJTablePruebas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Introducir datos manualmente en la tabla
 * En este ejercicio, vamos a crear un formulario que permita al usuario ingresar los datos: 
 * (nombre, apellido, pasatiempo, años de práctica, soltero/a). 
 * Cada vez que el usuario ingrese los datos y presione un botón, se añadirá una nueva fila a la tabla.
 */



public class FormularioJTable extends JFrame {

	private static final long serialVersionUID = 3198290575537282610L;
	
	private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nombreField, apellidoField, pasatiempoField, añosField;
    private JCheckBox solteroCheckBox;

    public FormularioJTable() {
        // Configurar la ventana
        setTitle("Ingreso de datos en la tabla");
        setLayout(new BorderLayout());

        // Crear el modelo de la tabla con los nombres de las columnas
        String[] columnNames = {"Nombre", "Apellido", "Pasatiempo", "Años de Práctica", "Soltero(a)"};
        tableModel = new DefaultTableModel(columnNames, 0); // 0 es para indicar que comienza sin filas
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));

        // Añadir la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear panel de formulario para agregar nuevos datos
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));

        // Campos de texto para el formulario
        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);

        formPanel.add(new JLabel("Pasatiempo:"));
        pasatiempoField = new JTextField();
        formPanel.add(pasatiempoField);

        formPanel.add(new JLabel("Años de Práctica:"));
        añosField = new JTextField();
        formPanel.add(añosField);

        formPanel.add(new JLabel("Soltero(a):"));
        solteroCheckBox = new JCheckBox();
        formPanel.add(solteroCheckBox);

        // Botón para agregar datos
        JButton addButton = new JButton("Agregar a la Tabla");
        formPanel.add(addButton);

        // Añadir el panel del formulario a la ventana
        add(formPanel, BorderLayout.SOUTH);

        // Acción del botón para añadir una fila a la tabla
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos del formulario
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String pasatiempo = pasatiempoField.getText();
                String años = añosField.getText();
                boolean soltero = solteroCheckBox.isSelected();

                // Validar que el campo "años de práctica" sea un número
                try {
                    int añosPractica = Integer.parseInt(años);

                    // Añadir una nueva fila a la tabla
                    Object[] rowData = {nombre, apellido, pasatiempo, añosPractica, soltero};
                    tableModel.addRow(rowData);

                    // Limpiar los campos del formulario
                    limpiarFormulario();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormularioJTable.this, "El campo 'Años de Práctica' debe ser un número.");
                }
            }
        });

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Método para limpiar los campos del formulario
    private void limpiarFormulario() {
        nombreField.setText("");
        apellidoField.setText("");
        pasatiempoField.setText("");
        añosField.setText("");
        solteroCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        new FormularioJTable();
    }
}

