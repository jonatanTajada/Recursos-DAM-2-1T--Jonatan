package componentesJavaSwingJTable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProductoApp extends JFrame {

	private static final long serialVersionUID = -7883039405602148981L;
	
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JButton btnAgregarProducto;
	private DefaultTableModel dtm;
	private JTable tabla;

	public ProductoApp() {

		// configurar ventana
		setTitle("Agregar productos a tabla");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// crear componenetes

		txtNombre = new JTextField(15);
		txtPrecio = new JTextField(10);

		btnAgregarProducto = new JButton("Agregar producto");

		// crear Jtable
		dtm = new DefaultTableModel();
		dtm.addColumn("Nombre");
		dtm.addColumn("Precio");

		tabla = new JTable(dtm);

		// crear layout
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(4, 1));

		// añadir componentes al panel
		JPanel panelNombre = new JPanel();
		panelNombre.add(new JLabel("Nombre:"));
		panelNombre.add(txtNombre);
		panelPrincipal.add(panelNombre);

		JPanel panelPrecio = new JPanel();
		panelPrecio.add(new JLabel("Precio:"));
		panelPrecio.add(txtPrecio);
		panelPrincipal.add(panelPrecio);

		JPanel panelBoton = new JPanel();
		panelBoton.add(btnAgregarProducto);
		panelPrincipal.add(panelBoton);

		JScrollPane scrollPane = new JScrollPane(tabla);
		panelPrincipal.add(scrollPane);

		add(panelPrincipal);

		// accion boton agregarProducto
		btnAgregarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = txtNombre.getText();
				String precioTexto = txtPrecio.getText();

				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtNombre.requestFocus();
					return;
				}

				// Validar que el precio sea un número válido
				double precio;

				try {
					precio = Double.parseDouble(precioTexto);

				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null, "El precio debe ser un número", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtPrecio.requestFocus();
					return;
				}

				// Añadir el producto a la tabla
				dtm.addRow(new Object[] { nombre, precio });

				// Limpiar los campos y devolver el foco al campo nombre
				txtNombre.setText("");
				txtPrecio.setText("");
				txtNombre.requestFocus();
			}
		});

		setVisible(true);

	}

	public static void main(String[] args) {
		new ProductoApp();
	}

}
