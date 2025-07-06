package componentesJavaSwingJTablePruebas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Añadir y modificar filas y columnas En este ejercicio vamos a usar
 * DefaultTableModel para manipular dinámicamente las filas y columnas.
 * Aprenderás a añadir una columna extra y modificar una fila existente.
 * 
 * Explicación: DefaultTableModel permite agregar y manipular el contenido de
 * una tabla más fácilmente que JTable. Veremos cómo añadir columnas y filas
 * después de que la tabla esté creada.
 */
public class SimpleTable2 extends JFrame {

	private static final long serialVersionUID = 5407869482386456345L;

	public SimpleTable2() {

		// datos de la tabla
		Object[][] data = { { "Mary", "Campione", "Esquiar", 5, false }, { "Lhucas", "Huml", "Patinar", 3, true },
				{ "Kathya", "Walrath", "Escalar", 2, false }, { "Marcus", "Andrews", "Correr", 7, true },
				{ "Angela", "Lalth", "Nadar", 4, false } };

		// nombre de las columnas
		String[] columnNames = { "Nombre", "Apellido", "Pasatiempo", "Años de Practica", "Soltero(a)" };

		// crear Jtable
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		JTable tabla = new JTable(dtm);

		// añadir una nueva columna
		String[] nuevaColumna = { "Flan", "Pastel", "Helado", "Barquillo", "Manzana" };
		dtm.addColumn("Postre", nuevaColumna);

		 // Añadir una nueva fila
        Object[] newRow = {"Pepe", "Grillo", "Tenis", 5, false, "Pera"};
        dtm.addRow(newRow);

        // Modificar una celda
        dtm.setValueAt("Catherine", 1, 1);

        // Configurar tabla
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
		
		
	}

}
