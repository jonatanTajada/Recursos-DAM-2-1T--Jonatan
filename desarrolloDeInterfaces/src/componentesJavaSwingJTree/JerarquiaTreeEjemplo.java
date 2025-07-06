package componentesJavaSwingJTree;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class JerarquiaTreeEjemplo extends JFrame {

  
	private static final long serialVersionUID = -9173466814828427344L;

	public JerarquiaTreeEjemplo() {
    	
		// Configuramos la ventana
        setTitle("JTree Example");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        configurarArbol(); 
    }

	//Configurar arbol
    private void configurarArbol() {
    	
        //Crear la estructura de nodos
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Categorias");
        DefaultMutableTreeNode vegetales = new DefaultMutableTreeNode("Vegetales");
        DefaultMutableTreeNode frutas = new DefaultMutableTreeNode("Frutas");

        raiz.add(vegetales);
        raiz.add(frutas);

        // Añadir nodos hijos de Vegetales
        vegetales.add(new DefaultMutableTreeNode("Capsicum"));
        vegetales.add(new DefaultMutableTreeNode("Carrot"));
        vegetales.add(new DefaultMutableTreeNode("Tomato"));
        vegetales.add(new DefaultMutableTreeNode("Potato12"));

        // Añadir nodos hijos de Frutas
        frutas.add(new DefaultMutableTreeNode("Banana"));
        frutas.add(new DefaultMutableTreeNode("Mango"));
        frutas.add(new DefaultMutableTreeNode("Apple"));
        frutas.add(new DefaultMutableTreeNode("Grapes"));
        frutas.add(new DefaultMutableTreeNode("Orange"));

        //Crear el modelo de arbol
        DefaultTreeModel modeloArbol = new DefaultTreeModel(raiz);
        JTree arbol = new JTree(modeloArbol);

        //Agregar el JTree a JScrollPane
        JScrollPane scrollPane = new JScrollPane(arbol);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> {
        	
        	JerarquiaTreeEjemplo app = new JerarquiaTreeEjemplo();
            app.setVisible(true);
        });
    }
}

