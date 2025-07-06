package componentesJavaSwingJTable;

import javax.swing.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoAppV2 extends JFrame {


	private static final long serialVersionUID = -7462770252020791634L;
	
	private JTextField txtNombre, txtPrecio;
    private JButton btnAgregar;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public ProductoAppV2() {
    	
       
        setTitle("Agregar productos a tabla");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes
        
        // Crear los campos de texto (Nombre y Precio)
        txtNombre = new JTextField(15);
        txtPrecio = new JTextField(10);

        // Crear el botón
        btnAgregar = new JButton("Agregar Producto");

        // Crear el JTable con el modelo
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        tablaProductos = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);

       
        // Etiqueta y campo de Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtNombre, gbc);

        // Etiqueta y campo de Precio
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtPrecio, gbc);

        //agregar producto
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAgregar, gbc);

        // Añadir la tabla en la siguiente fila y expandirla para llenar el espacio disponible
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

       
        btnAgregar.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String precioTexto = txtPrecio.getText();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNombre.requestFocus();
                    return;
                }

                double precio;
                try {
                    precio = Double.parseDouble(precioTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El precio debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                    txtPrecio.requestFocus();
                    return;
                }

                // Añadir producto a la tabla
                modeloTabla.addRow(new Object[]{nombre, precio});

                txtNombre.setText("");
                txtPrecio.setText("");
                txtNombre.requestFocus();
            }
        });

   
        setVisible(true);
    }

    public static void main(String[] args) {
        new ProductoAppV2();
    }
}

