package PruebasExamenesPrimerTrimestre.MIAS;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RegistroEmpleado {

	public static void main(String[] args) {

		// Configurar ventana
		JFrame ventana = new JFrame();
		ventana.setTitle("Registro empleados");
		ventana.setSize(650, 550);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout());

		// Panel norte: Formulario de ingreso de datos
		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(5, 2, 5, 5));
		ventana.add(panelFormulario, BorderLayout.NORTH);

		JTextField campoNombre = new JTextField();
		JTextField campoApellido = new JTextField();
		JTextField campoPosicion = new JTextField();
		JTextField campoSalario = new JTextField();

		panelFormulario.add(new JLabel("Nombre:"));
		panelFormulario.add(campoNombre);

		panelFormulario.add(new JLabel("Apellido:"));
		panelFormulario.add(campoApellido);

		panelFormulario.add(new JLabel("Posición:"));
		panelFormulario.add(campoPosicion);

		panelFormulario.add(new JLabel("Salario"));
		panelFormulario.add(campoSalario);

		JButton btnRegistrar = new JButton("Registrar");
		panelFormulario.add(btnRegistrar);
		
		JButton btnEliminar = new JButton("Eliminar registro");
		panelFormulario.add(btnEliminar);
		
		

		// Panel para la tabla de empleados
		JPanel panelTabla = new JPanel(new BorderLayout());
		ventana.add(panelTabla, BorderLayout.CENTER);

		String[] columnas = { "Nombre", "Apellido", "Posición", "Salario" };
		DefaultTableModel dtm = new DefaultTableModel(columnas, 0);
		JTable tablaEmpleados = new JTable(dtm);
		JScrollPane scrollTabla = new JScrollPane(tablaEmpleados);
		panelTabla.add(scrollTabla, BorderLayout.CENTER);

		// ActionListener del botón: Registrar
		btnRegistrar.addActionListener((ActionEvent e) -> {
			
			String nombre = campoNombre.getText().trim();
			String apellido = campoApellido.getText().trim();
			String posicion = campoPosicion.getText().trim();
			String salario = campoSalario.getText().trim();

			if (!nombre.isEmpty() && !apellido.isEmpty() && !posicion.isEmpty() && !salario.isEmpty()) {
				try {
					double salarioDouble = Double.parseDouble(salario);

					if (salarioDouble >= 0 && salarioDouble <= 10000) {
						// Agregar datos a la tabla
						dtm.addRow(new Object[] { nombre, apellido, posicion, salario });
						// Limpiar campos después de agregar
						campoNombre.setText("");
						campoApellido.setText("");
						campoPosicion.setText("");
						campoSalario.setText("");
					} else {
						JOptionPane.showMessageDialog(ventana, "Introduce una cantidad entre 0 y 10000",
								"Valor incorrecto", JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(ventana, "El salario debe ser un número válido", "Error de formato",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		
		// ActionListener del botón: Eliminar registro
		btnEliminar.addActionListener((ActionEvent e) -> {
			
		    int selectedRow = tablaEmpleados.getSelectedRow();
		    
		    if (selectedRow != -1) { 
		    	
		        int confirm = JOptionPane.showConfirmDialog(ventana, "¿Estás seguro de que quieres eliminar este registro?", 
		                                                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            dtm.removeRow(selectedRow); 
		        }
		        
		    } else {
		    	
		        JOptionPane.showMessageDialog(ventana, "Seleccione un registro para eliminar.", "No hay selección", JOptionPane.WARNING_MESSAGE);
		    }
		});

		
		ventana.setVisible(true);
	}
}
