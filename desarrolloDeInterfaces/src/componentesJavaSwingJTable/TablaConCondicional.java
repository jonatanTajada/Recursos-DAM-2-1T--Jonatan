package componentesJavaSwingJTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TablaConCondicional {

    public static void main(String[] args) {
        
        // Configuración de la ventana
        JFrame ventana = new JFrame("Tabla avanzada de empleados");
        ventana.setSize(800, 400);
        ventana.setLayout(new BorderLayout());

        // Modelo de datos para la tabla
        String[] columnas = {"Nombre", "Apellido", "Edad", "Puesto", "Activo"};
        Object[][] datos = {
            {"Jonatan", "Tajada", 36, "Desarrollador", true},
            {"Janire", "Lalal", 28, "Diseñadora", false},
            {"Andrew", "Lala", 45, "Estudiante", true},
            {"Michael", "Dada", 20, "Asistente", false}
        };
        
        // Modelo de tabla personalizado
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo permitir edición en la columna "Activo" (índice 4)
                return column == 4;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Especificar el tipo de datos de cada columna
                if (columnIndex == 2) return Integer.class; // Edad como Integer
                if (columnIndex == 4) return Boolean.class; // Activo como Boolean
                return String.class;
            }
        };

        // Crear la JTable con el modelo de datos personalizado
        JTable tablaEmpleados = new JTable(modelo);

        // Renderizador condicional para la columna de Edad
        tablaEmpleados.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = table.getDefaultRenderer(Integer.class).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int edad = (int) value;
                // Cambiar color si la edad es mayor a 30
                if (edad > 30) {
                    cell.setBackground(Color.PINK);
                } else {
                    cell.setBackground(Color.WHITE);
                }
                return cell;
            }
        });

        // Añadir la tabla a un JScrollPane y agregarla a la ventana
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        ventana.add(scrollPane, BorderLayout.CENTER);

        // Configurar cierre y visibilidad de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}
