package pruebas;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class RegistroProducto {

	
	public static void main(String[] args) {
		
		
		JFrame ventana = new JFrame();
		ventana.setSize(700,700);
		ventana.setLayout(new BorderLayout());
		
		//pabel norete
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new GridLayout(3, 2, 1, 1));
		ventana.add(panelNorte, BorderLayout.NORTH);
		
		
		JTextField campoNombre = new JTextField();
		campoNombre.setPreferredSize(new Dimension());
		panelNorte.add(new JLabel("Nombre"));
		panelNorte.add(campoNombre);
		
		JTextField campoCantidad = new JTextField();
		campoCantidad.setPreferredSize(new Dimension());
		panelNorte.add(new JLabel("Cantidad"));
		panelNorte.add(campoCantidad);
		
		JButton btnAgregar = new JButton("Agregar");
		panelNorte.add(btnAgregar);
		
		
		//panel central: jtable
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		ventana.add(panelCentral, BorderLayout.CENTER);
		
		
		String[] columnas = {"Nombre", "Cantidad"};
		DefaultTableModel dtm = new DefaultTableModel(columnas, 0);
		JTable tablaProductos = new JTable(dtm);
		JScrollPane scroolPane = new JScrollPane(tablaProductos);
		panelCentral.add(scroolPane, BorderLayout.CENTER);
		
		
		
		
		
		
		//actionlistener boton: agregar
		btnAgregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nombre = campoNombre.getText().trim();
				String cantidadStr = campoCantidad.getText().trim();
				
				try {
					
					int cantidad = Integer.parseInt(cantidadStr);
					
					if (cantidad < 0) {
						JOptionPane.showMessageDialog(ventana, "Error: la cantidad no puede ser numeros negativos");
					}
					
					dtm.addRow(new Object[] {nombre, cantidad});
					
					campoNombre.setText("");
					campoCantidad.setText("");
					
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
				
			}
		});
		
		
		
		
		
		
		ventana.setVisible(true);
	}
	
	
	
	
	
	
	
	
}
