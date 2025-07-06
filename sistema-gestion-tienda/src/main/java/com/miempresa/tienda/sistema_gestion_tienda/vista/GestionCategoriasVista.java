package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.miempresa.tienda.sistema_gestion_tienda.estilos.EstiloUI;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;
import com.miempresa.tienda.sistema_gestion_tienda.servicios.CategoriaService;

/**
 * Clase que gestiona la interfaz gráfica para la administración de categorías.
 * Incluye funcionalidades para agregar, editar, activar/inactivar y filtrar
 * categorías.
 */
public class GestionCategoriasVista extends JFrame {

	private JTable tablaCategorias;
	private DefaultTableModel modeloTabla;
	private CategoriaService categoriaService;
	private JTextField txtBuscar;
	private JComboBox<String> cmbEstado;

	/**
	 * Constructor de la clase. Inicializa la interfaz gráfica y sus componentes.
	 *
	 * @param categoriaService Servicio para gestionar las categorías.
	 */
	public GestionCategoriasVista(CategoriaService categoriaService) {
		super("Gestión de Categorías");
		this.categoriaService = categoriaService;

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		// Crear el panel de filtros
		JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelSuperior.setBackground(EstiloUI.COLOR_PRIMARIO);

		JLabel lblBuscar = new JLabel("Buscar categoría:");
		lblBuscar.setForeground(Color.WHITE);
		txtBuscar = new JTextField(15);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.WHITE);
		cmbEstado = new JComboBox<>(new String[] { "Todos", "Activo", "Inactivo" });

		panelSuperior.add(lblBuscar);
		panelSuperior.add(txtBuscar);
		panelSuperior.add(lblEstado);
		panelSuperior.add(cmbEstado);

		add(panelSuperior, BorderLayout.NORTH);

		// Configurar la tabla
		String[] columnas = { "ID", "Nombre", "Estado" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		tablaCategorias = new JTable(modeloTabla);

		// Aplicar el estilo a la tabla
		EstiloUI.configurarTabla(tablaCategorias);

		// Añadir la tabla a la ventana
		add(new JScrollPane(tablaCategorias), BorderLayout.CENTER);


		// Crear botones
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnAgregar = new JButton("Agregar");
		JButton btnEditar = new JButton("Editar");
		JButton btnActivarInactivar = new JButton("Activar/Inactivar");

		panelBotones.add(btnAgregar);
		panelBotones.add(btnEditar);
		panelBotones.add(btnActivarInactivar);

		add(panelBotones, BorderLayout.SOUTH);

		// Llenar la tabla inicial
		llenarTabla();

		// Eventos
		txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				aplicarFiltros();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				aplicarFiltros();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				aplicarFiltros();
			}
		});

		cmbEstado.addActionListener(e -> aplicarFiltros());

		btnAgregar.addActionListener(e -> agregarCategoria());
		btnEditar.addActionListener(e -> editarCategoria());
		btnActivarInactivar.addActionListener(e -> cambiarEstadoCategoria());

		setVisible(true);
	}

	/**
	 * Llena la tabla con los datos de las categorías obtenidos del servicio.
	 */
	private void llenarTabla() {
		// Limpiar la tabla antes de agregar los datos actualizados
		modeloTabla.setRowCount(0);

		// Obtener las categorías desde la base de datos
		List<Categoria> categorias = categoriaService.obtenerTodas();

		// Agregar cada categoría a la tabla
		for (Categoria categoria : categorias) {
			modeloTabla.addRow(new Object[] { categoria.getId(), categoria.getNombre(),
					categoria.isActivo() ? "Activo" : "Inactivo" });
		}
	}

	/**
	 * Aplica los filtros de búsqueda y estado a la tabla.
	 */
	private void aplicarFiltros() {
		String texto = txtBuscar.getText().toLowerCase();
		String estadoSeleccionado = (String) cmbEstado.getSelectedItem();

		modeloTabla.setRowCount(0); // Limpiar tabla

		// Obtener las categorías desde la base de datos
		List<Categoria> categorias = categoriaService.obtenerTodas();
		for (Categoria categoria : categorias) {
			boolean coincideTexto = categoria.getNombre().toLowerCase().contains(texto);
			boolean coincideEstado = estadoSeleccionado.equals("Todos")
					|| (estadoSeleccionado.equals("Activo") && categoria.isActivo())
					|| (estadoSeleccionado.equals("Inactivo") && !categoria.isActivo());

			// Si pasa los filtros, agregarla a la tabla
			if (coincideTexto && coincideEstado) {
				modeloTabla.addRow(new Object[] { categoria.getId(), categoria.getNombre(),
						categoria.isActivo() ? "Activo" : "Inactivo" });
			}
		}
	}

	/**
	 * Abre un cuadro de diálogo para agregar una nueva categoría.
	 */
	private void agregarCategoria() {
		String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la categoría:");
		if (nombre != null && !nombre.trim().isEmpty()) {
			Categoria categoria = new Categoria(0, nombre.trim());
			if (categoriaService.guardar(categoria)) {
				JOptionPane.showMessageDialog(this, "Categoría agregada correctamente.");
				llenarTabla(); // Refrescar la tabla
			} else {
				JOptionPane.showMessageDialog(this, "Error al agregar la categoría.");
			}
		}
	}

	/**
	 * Abre un cuadro de diálogo para editar la categoría seleccionada.
	 */
	private void editarCategoria() {
		int filaSeleccionada = tablaCategorias.getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Seleccione una categoría para editar.");
			return;
		}

		int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
		Categoria categoria = categoriaService.obtenerPorId(id);
		if (categoria != null) {
			String nuevoNombre = JOptionPane.showInputDialog(this, "Edite el nombre de la categoría:",
					categoria.getNombre());
			if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
				categoria.setNombre(nuevoNombre.trim());
				if (categoriaService.actualizar(categoria)) {
					JOptionPane.showMessageDialog(this, "Categoría actualizada correctamente.");
					llenarTabla(); // Refrescar la tabla
				} else {
					JOptionPane.showMessageDialog(this, "Error al actualizar la categoría.");
				}
			}
		}
	}

	/**
	 * Cambia el estado (activo/inactivo) de la categoría seleccionada.
	 */
//	private void cambiarEstadoCategoria() {
//	    int filaSeleccionada = tablaCategorias.getSelectedRow();
//	    if (filaSeleccionada == -1) {
//	        JOptionPane.showMessageDialog(this, "Seleccione una categoría.");
//	        return;
//	    }
//
//	    int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
//	    Categoria categoria = categoriaService.obtenerPorId(id);
//	    if (categoria != null) {
//	        boolean nuevoEstado = !categoria.isActivo();  // Cambiar el estado
//	        categoria.setActivo(nuevoEstado);
//	        if (categoriaService.actualizar(categoria)) {
//	            JOptionPane.showMessageDialog(this,
//	                    "La categoría ha sido " + (nuevoEstado ? "activada" : "inactivada") + " correctamente.");
//	            llenarTabla();  // Asegúrate de llamar a este método después de actualizar
//	        } else {
//	            JOptionPane.showMessageDialog(this, "Error al cambiar el estado de la categoría.");
//	        }
//	    }
//	}
	
	/**
	 * Cambia el estado (activo/inactivo) de la categoría seleccionada.
	 */
	private void cambiarEstadoCategoria() {
	    // Obtiene la fila seleccionada en la tabla
	    int filaSeleccionada = tablaCategorias.getSelectedRow();
	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(this, "Seleccione una categoría.");
	        return;
	    }

	    // Obtiene el ID de la categoría seleccionada
	    int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
	    Categoria categoria = categoriaService.obtenerPorId(id);
	    
	    if (categoria != null) {
	        // Cambia el estado de la categoría al contrario de su estado actual
	        boolean nuevoEstado = !categoria.isActivo();  // Si estaba activa, será inactiva y viceversa
	        categoria.setActivo(nuevoEstado);

	        // Ahora actualizamos la categoría en la base de datos
	        if (categoriaService.actualizar(categoria)) {
	            JOptionPane.showMessageDialog(this,
	                    "La categoría ha sido " + (nuevoEstado ? "activada" : "inactivada") + " correctamente.");
	            llenarTabla();  // Actualiza la tabla para reflejar el nuevo estado
	        } else {
	            JOptionPane.showMessageDialog(this, "Error al cambiar el estado de la categoría.");
	        }
	    }
	}


	
	
	
	


}
