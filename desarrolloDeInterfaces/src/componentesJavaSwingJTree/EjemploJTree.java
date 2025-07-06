package componentesJavaSwingJTree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class EjemploJTree extends JFrame {

	private static final long serialVersionUID = -7561219354315489108L;
	
	private JTree tree;
    private JPanel panelDerecho;
    private JTable rutaTabla;
    private DefaultTableModel tableModel;

    public EjemploJTree() {
    	
        // Configuración de la ventana principal
        setTitle("DAM: Ejemplo JTree");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los nodos del árbol con nombres similares a los de la imagen
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Carpeta");
        DefaultMutableTreeNode subCarpeta = new DefaultMutableTreeNode("SubCarpeta");
        
        
        // Nodos hoja
        DefaultMutableTreeNode archivo1 = new DefaultMutableTreeNode("Archivo1");
        DefaultMutableTreeNode archivo2 = new DefaultMutableTreeNode("Archivo2");
        DefaultMutableTreeNode archivo3 = new DefaultMutableTreeNode("Archivo3");
        DefaultMutableTreeNode archivo4 = new DefaultMutableTreeNode("Archivo4");
        DefaultMutableTreeNode archivo5 = new DefaultMutableTreeNode("Archivo5");
        DefaultMutableTreeNode archivo6 = new DefaultMutableTreeNode("Archivo6");

        
        // Construir la jerarquia del arbol
        root.add(subCarpeta);
        subCarpeta.add(archivo1);
        subCarpeta.add(archivo2);
        subCarpeta.add(archivo3);
        root.add(archivo4);
        root.add(archivo5);
        root.add(archivo6);

        // Crear el modelo del arbol
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree = new JTree(model);

        
        // Cargar y redimensionar el icono para los nodos hoja
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        ImageIcon leafIcon = new ImageIcon("imgNodoHoja.jpg");
        
        // Verificar el tamaño de la imagen y redimensionarla
        if (leafIcon.getIconWidth() > 16 || leafIcon.getIconHeight() > 16) {
            Image scaledImage = leafIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            leafIcon = new ImageIcon(scaledImage);
        }
        
        renderer.setLeafIcon(leafIcon);
        tree.setCellRenderer(renderer);
        

        // Panel derecho para mostrar el nodo seleccionado
        panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.GREEN);
        JLabel tituloPanel = new JLabel("Titulo Panel3");
        panelDerecho.add(tituloPanel);

        // Crear  tabla para almacenar rutas seleccionadas
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Ruta");
        rutaTabla = new JTable(tableModel);
        JScrollPane tablaScrollPane = new JScrollPane(rutaTabla);

        
        // Escuchar la seleccion de nodos en el arbol
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    String selectedNodeName = selectedNode.toString();
                    tituloPanel.setText("Nodo seleccionado: " + selectedNodeName);

                    // Obtener la ruta con separadores "/"
                    String ruta = e.getPath().toString().replace(", ", "/").replace("[", "").replace("]", "");
                    
                    // Añadir la ruta a la tabla
                    tableModel.addRow(new Object[]{ruta});
                }
            }
        });
        

        // Crear el SplitPane que divide el arbol y el panel derecho
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree), panelDerecho);
        splitPane.setDividerLocation(300); 

        // Añadir los componentes al JFrame
        add(new JLabel("Componente JTree", JLabel.CENTER), BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER); 
        add(tablaScrollPane, BorderLayout.SOUTH); 

      
        setVisible(true);
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> new EjemploJTree());
    }
}
