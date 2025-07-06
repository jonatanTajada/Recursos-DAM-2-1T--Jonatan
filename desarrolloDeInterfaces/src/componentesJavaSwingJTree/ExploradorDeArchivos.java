package componentesJavaSwingJTree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ExploradorDeArchivos extends JFrame {

	private static final long serialVersionUID = -1083754120255121327L;

	private JTree arbolDirectorios;
	private JTable tablaArchivos;
	private DefaultTableModel modeloTabla;
	private JPopupMenu menuEmergente;

	public ExploradorDeArchivos() {
		
		// Configuración de la ventana principal
		setTitle("Explorador de Archivos");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		

		// Crear el JTree para mostrar los directorios
		DefaultMutableTreeNode nodoRaiz = new DefaultMutableTreeNode("Equipo");
		arbolDirectorios = new JTree(new DefaultTreeModel(nodoRaiz));
		arbolDirectorios.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		
		// cargar el sistema de archivos con hilos 2º PLANO
		cargarSistemaDeArchivosEnSegundoPlano(nodoRaiz, new File("C:\\"));

		arbolDirectorios.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbolDirectorios.getLastSelectedPathComponent();
				if (nodoSeleccionado != null && nodoSeleccionado.getUserObject() instanceof File) {
					
					File archivoSeleccionado = (File) nodoSeleccionado.getUserObject();
					
					if (archivoSeleccionado.isDirectory()) {
						
						cargarArchivosEnTabla(archivoSeleccionado);
					}
				}
			}
		});
		
		

		//crear modelo tabla y añadir al JTree
		modeloTabla = new DefaultTableModel(new Object[] { "Nombre del Archivo", "Tamaño (en Bytes)", "Solo Lectura", "Oculto", "Ruta" }, 0);
		tablaArchivos = new JTable(modeloTabla);
		

		// Añadir menu emergente (PopUp) con opciones: Abrir - Renombrar y Eliminar
		menuEmergente = new JPopupMenu();
		
		JMenuItem opcionAbrir = new JMenuItem("Abrir");
		JMenuItem opcionRenombrar = new JMenuItem("Renombrar");
		JMenuItem opcionEliminar = new JMenuItem("Eliminar");
		
		menuEmergente.add(opcionAbrir);
		menuEmergente.add(opcionRenombrar);
		menuEmergente.add(opcionEliminar);

		opcionAbrir.addActionListener(e -> abrirArchivo());
		opcionRenombrar.addActionListener(e -> renombrarArchivo());
		opcionEliminar.addActionListener(e -> eliminarArchivo());
		

		// Mostrar el menu emergente al hacer clic derecho sobre una fila de la tabla
		tablaArchivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (SwingUtilities.isRightMouseButton(e) && tablaArchivos.getSelectedRow() != -1) {
					
					tablaArchivos.setRowSelectionInterval(tablaArchivos.rowAtPoint(e.getPoint()),tablaArchivos.rowAtPoint(e.getPoint())); // Selecciona la fila
					menuEmergente.show(tablaArchivos, e.getX(), e.getY());
				}
			}
		});

		
		// Panel de division para el JTree y JTable
		JSplitPane panelDividido = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(arbolDirectorios), new JScrollPane(tablaArchivos));
		panelDividido.setDividerLocation(250);

		
		// Boton: Actualizar 
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.addActionListener(e -> actualizarSistemaDeArchivos());

		add(panelDividido, BorderLayout.CENTER);
		add(botonActualizar, BorderLayout.SOUTH);

		setVisible(true);
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//metodo para actualizar sistema de aarchivos
	private void actualizarSistemaDeArchivos() {
		
		DefaultMutableTreeNode nodoRaiz = (DefaultMutableTreeNode) arbolDirectorios.getModel().getRoot();
		nodoRaiz.removeAllChildren(); 
		((DefaultTreeModel) arbolDirectorios.getModel()).reload();

		cargarSistemaDeArchivosEnSegundoPlano(nodoRaiz, new File("C:\\")); 

		modeloTabla.setRowCount(0);
	}

	
	// Método para cargar el sistema de archivos en segundo plano
	private void cargarSistemaDeArchivosEnSegundoPlano(DefaultMutableTreeNode nodo, File archivo) {

	    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() {
	            cargarSistemaDeArchivos(nodo, archivo);
	            return null;
	        }

	        @Override
	        protected void done() {
	            ((DefaultTreeModel) arbolDirectorios.getModel()).reload();
	        }
	    };
	    worker.execute();
	}


	
	//metodo para cargar el sistema de archivos en el JTree
	private void cargarSistemaDeArchivos(DefaultMutableTreeNode nodo, File archivo) {
		
		File[] archivos = archivo.listFiles();
		
		if (archivos != null) {
			
			for (File archi : archivos) {
				
				DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(archi);
				nodo.add(nodoHijo);
				
				if (archi.isDirectory()) {
					
					cargarSistemaDeArchivos(nodoHijo, archi);
				}
			}
		}
	}

	
	//metodo para cargar los archivos en la JTable
	private void cargarArchivosEnTabla(File directorio) {
		
		modeloTabla.setRowCount(0);
		File[] archivos = directorio.listFiles();
		
		if (archivos != null) {
			
			for (File archivo : archivos) {
				
				modeloTabla.addRow(new Object[] { archivo.getName(), archivo.isFile() ? archivo.length() : "-",
						archivo.canWrite() ? "false" : "true", archivo.isHidden(), archivo.getAbsolutePath() });
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------	
	//boton: Abrir en el menu emergente
	private void abrirArchivo() {
		
		int filaSeleccionada = tablaArchivos.getSelectedRow();
		
		if (filaSeleccionada != -1) {
			
			String rutaArchivo = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
			File archivo = new File(rutaArchivo);
			
			if (archivo.exists() && archivo.isFile()) {
				
				try {
					
					Desktop.getDesktop().open(archivo);
				} catch (IOException ex) {
					
					JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	//boton: Renombrar en el menu emergente
	private void renombrarArchivo() {
		
		int filaSeleccionada = tablaArchivos.getSelectedRow();
		
		if (filaSeleccionada != -1) {
			
			String rutaArchivo = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
			File archivo = new File(rutaArchivo);

			String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre para el archivo:", archivo.getName());

			if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
				
				File archivoRenombrado = new File(archivo.getParentFile(), nuevoNombre);
				if (archivo.renameTo(archivoRenombrado)) {
					
					cargarArchivosEnTabla(archivo.getParentFile());
				} else {
					
					JOptionPane.showMessageDialog(this, "No se pudo renombrar el archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	//boton: Eliminar en el menu emergente
	private void eliminarArchivo() {
		
		int filaSeleccionada = tablaArchivos.getSelectedRow();
		
		if (filaSeleccionada != -1) {
			
			String rutaArchivo = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
			File archivo = new File(rutaArchivo);

			int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de que deseas eliminar este archivo?",
					"Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

			if (confirmacion == JOptionPane.YES_OPTION) {
				
				if (archivo.delete()) {
					
					cargarArchivosEnTabla(archivo.getParentFile());
				} else {
					
					JOptionPane.showMessageDialog(this, "No se pudo eliminar el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	
//-----------------------------------------------------------------------------------------------------------	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(ExploradorDeArchivos::new);
	}
}
