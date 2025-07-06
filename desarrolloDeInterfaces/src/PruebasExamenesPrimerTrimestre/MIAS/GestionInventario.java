package PruebasExamenesPrimerTrimestre.MIAS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GestionInventario {

	public static void main(String[] args) {

		// Configurar ventana

		JFrame ventana = new JFrame();
		ventana.setTitle("Gestion de Inventario");
		ventana.setSize(900, 700);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// configurar panelNorte: Registro de datos
		JPanel panelInformacionProducto = new JPanel(new BorderLayout());
		panelInformacionProducto.setBorder(BorderFactory.createTitledBorder("Informacion del producto"));
		ventana.add(panelInformacionProducto, BorderLayout.NORTH);

		// subPanel: Informacion Producto
		JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
		panelInformacionProducto.add(panelCampos, BorderLayout.CENTER);

		JTextField campoNombre = new JTextField();
		JTextField campoCategoria = new JTextField();
		JTextField campoCantidad = new JTextField();
		JTextField campoPrecio = new JTextField();

		panelCampos.add(new JLabel("Nombre"));
		panelCampos.add(campoNombre);

		panelCampos.add(new JLabel("Categoria"));
		panelCampos.add(campoCategoria);

		panelCampos.add(new JLabel("Cantidad"));
		panelCampos.add(campoCantidad);

		panelCampos.add(new JLabel("Precio"));
		panelCampos.add(campoPrecio);

		// subPanel: botones
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panelInformacionProducto.add(panelBotones, BorderLayout.SOUTH);

		JButton btnAgregar = new JButton("Agregar Producto");
		JButton btnEliminar = new JButton("Eliminar Producto");
		JButton btnEditar = new JButton("Editar Producto");

		panelBotones.add(btnAgregar);
		panelBotones.add(btnEliminar);
		panelBotones.add(btnEditar);

		// Tabla de productos
		String[] columnas = { "Nombre", "Categoria", "Cantidad", "Precio" };
		DefaultTableModel dtm = new DefaultTableModel(columnas, 0);
		JTable tablaProductos = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(tablaProductos);
		ventana.add(scrollPane, BorderLayout.CENTER);

		// Accion boton: Agregar Producto
		btnAgregar.addActionListener((ActionEvent e) -> {

			String nombre = campoNombre.getText().trim();
			String categoria = campoCategoria.getText().trim();
			String cantidadString = campoCantidad.getText().trim();
			String precioString = campoPrecio.getText().trim();

			if (!nombre.isBlank() && !categoria.isBlank() && !cantidadString.isBlank() && !precioString.isBlank()) {

				try {

					double cantidad = Double.parseDouble(cantidadString);
					double precio = Double.parseDouble(precioString);

					if (cantidad < 0) {
						System.err.println("No puede ser una cantidad negativa");
					} else if (precio < 0) {
						System.err.println("Precio no puede ser negativo");
					}

					dtm.addRow(new Object[] { nombre, categoria, cantidad, precio });

					campoNombre.setText("");
					campoCategoria.setText("");
					campoCantidad.setText("");
					campoPrecio.setText("");

				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(ventana, "Cantidad y precio deben ser valores numericos",
							"Error de formato", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(ventana, "Todos los campos deben estar cumplimentados",
						"Error de datos incompletos", JOptionPane.ERROR_MESSAGE);
			}

		});

		// Accion boton: Eliminar Producto
		btnEliminar.addActionListener((ActionEvent e) -> {

			int filaSeleccionada = tablaProductos.getSelectedRow();

			if (filaSeleccionada != -1) {

				int confirmar = JOptionPane.showConfirmDialog(ventana,
						"¿Estas seguro de eliminar la fila seleccionada?", "Confirmacion", JOptionPane.YES_NO_OPTION);

				if (confirmar == JOptionPane.YES_OPTION) {
					dtm.removeRow(filaSeleccionada);
				}

			} else {
				JOptionPane.showMessageDialog(ventana, "Seleccione alguna fila", "Error de no elemento no seleccionado",
						JOptionPane.WARNING_MESSAGE);
			}

		});

		// Accion boton: Editar Producto
		btnEditar.addActionListener((ActionEvent e) -> {

			int filaSeleccionada = tablaProductos.getSelectedRow();

			if (filaSeleccionada != -1) {

				campoNombre.setText(dtm.getValueAt(filaSeleccionada, 0).toString());
				campoCategoria.setText(dtm.getValueAt(filaSeleccionada, 1).toString());
				campoCantidad.setText(dtm.getValueAt(filaSeleccionada, 2).toString());
				campoPrecio.setText(dtm.getValueAt(filaSeleccionada, 3).toString());

				btnAgregar.setText("Guardar cambios");

				// Remover el ActionListener existente para evitar acumulaciones
				for (ActionListener al : btnAgregar.getActionListeners()) {
					btnAgregar.removeActionListener(al);
				}

				btnAgregar.addActionListener((ActionEvent e2) -> {

					try {
						int cantidad = Integer.parseInt(campoCantidad.getText().trim());
						double precio = Double.parseDouble(campoPrecio.getText().trim());

						dtm.setValueAt(campoNombre.getText().trim(), filaSeleccionada, 0);
						dtm.setValueAt(campoCategoria.getText().trim(), filaSeleccionada, 1);
						dtm.setValueAt(cantidad, filaSeleccionada, 2);
						dtm.setValueAt(precio, filaSeleccionada, 3);

						campoNombre.setText("");
						campoCategoria.setText("");
						campoCantidad.setText("");
						campoPrecio.setText("");

						btnAgregar.setText("Agregar Producto");

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(ventana, "Cantidad y Precio deben ser valores numéricos.",
								"Error de formato", JOptionPane.ERROR_MESSAGE);
					}

				});

			} else {
				JOptionPane.showMessageDialog(ventana, "Seleccione un registro", "Aviso", JOptionPane.WARNING_MESSAGE);
			}

		});

		ventana.setVisible(true);

	}

}
