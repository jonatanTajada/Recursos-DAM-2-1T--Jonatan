package componentesJavaSwingJTablePruebas;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 *  Crear una tabla simple
 *  Este primer ejercicio es muy básico. El objetivo es crear una tabla con datos predefinidos utilizando un JTable. 
 *  Practicaremos cómo inicializar los datos y mostrarlos en una ventana.

 *  Explicación: Utilizaremos el constructor JTable(Object[][] rowData, Object[] columnNames) para crear la tabla. 
 *  Los datos de la tabla se proporcionan directamente en un array bidimensional y los nombres de las columnas en un array simple.
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SimpleTable1 extends JFrame {

	private static final long serialVersionUID = -533159146655421333L;

	public SimpleTable1() {
		
		//datos de la tabla
		Object[][] data = { 
							{ "Mary", "Campione", "Esquiar", 5, false },
							{ "Lhucas", "Huml", "Patinar", 3, true },
							{ "Kathya", "Walrath", "Escalar", 2, false }, 
							{ "Marcus", "Andrews", "Correr", 7, true },
							{ "Angela", "Lalth", "Nadar", 4, false } 
						  };
		
		//nombres de las columnas
		  String[] columnNames = {"Nombre", "Apellido", "Pasatiempo", "Años de Práctica", "Soltero(a)"};
		  
		//crear JTable con datos y nombres
		  JTable tabla = new JTable(data, columnNames);
		  tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
		
		  
		  // Crear JScrollPane y añadir la tabla
		  JScrollPane scrollPane = new JScrollPane(tabla);
		  getContentPane().add(scrollPane, BorderLayout.CENTER);
		  
		  // Configurar ventana
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(700,600);
	        setVisible(true);
	}
	
	public static void main(String[] args) {
		new SimpleTable1();
	}

}
