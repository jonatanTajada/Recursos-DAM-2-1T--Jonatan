package componentesJavaSwingJTablePruebas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormularioPersonas extends JFrame{

	
	
	private DefaultTableModel dtm;
	private JTable tabla;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtPasatiempos;
	private JTextField txtAnosPracticas;
	private JCheckBox checkBoxSoltero;
	
	
	public FormularioPersonas() {
		
		setTitle("Ingreso de datos en la tabla");
		setLayout(new BorderLayout());
		
		//creamos el modelo de la tabla con los nombre de las columnas
		String[] nombreColumnas = {"Nombre", "Apellido", "Pasatiempo", "Años de Práctica", "Soltero(a)"};
		dtm = new DefaultTableModel(nombreColumnas, 0);
		tabla = new JTable(dtm);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 100));
		
		//añadir la tabla a un jscrolpane
		JScrollPane jsp = new JScrollPane(tabla);
		add(jsp, BorderLayout.CENTER);
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
