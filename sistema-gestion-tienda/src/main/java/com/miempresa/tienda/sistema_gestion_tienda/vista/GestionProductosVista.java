package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.miempresa.tienda.sistema_gestion_tienda.dao.CategoriaDAO;
import com.miempresa.tienda.sistema_gestion_tienda.dao.ProductoDAO;
import com.miempresa.tienda.sistema_gestion_tienda.estilos.EstiloUI;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Producto;

/**
 * Clase para gestionar la interfaz gráfica de los productos, incluyendo filtros
 * y acciones como agregar, editar y cambiar el estado.
 */
public class GestionProductosVista extends VentanaBase {

	private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscar;
    private JComboBox<Categoria> cmbFiltroCategoria;
    private JComboBox<String> cmbFiltroEstado;
    private JLabel lblNotificacion;

    public GestionProductosVista() {
        super("Gestión de Productos");

        // Panel superior (Filtros y Búsqueda)
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(EstiloUI.COLOR_PRIMARIO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(EstiloUI.FUENTE_TITULO);
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        // Panel de filtros
        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelFiltros.setBackground(EstiloUI.COLOR_PRIMARIO);

        gbc.insets = new java.awt.Insets(5, 5, 5, 5);  // Espacio entre componentes

        JLabel lblBuscar = new JLabel("Buscar producto:");
        lblBuscar.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFiltros.add(lblBuscar, gbc);

        txtBuscar = new JTextField(15);
        gbc.gridx = 1;
        panelFiltros.add(txtBuscar, gbc);

        JLabel lblFiltroCategoria = new JLabel("Categoría:");
        lblFiltroCategoria.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFiltros.add(lblFiltroCategoria, gbc);

        cmbFiltroCategoria = new JComboBox<>();
        cmbFiltroCategoria.addItem(new Categoria(0, "Todas"));

        // Obtener categorías y agregarlas al JComboBox
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.obtenerTodas();
        for (Categoria categoria : categorias) {
            cmbFiltroCategoria.addItem(categoria);
        }
        
        // Usar un ListCellRenderer para mostrar solo el nombre de la categoría
        cmbFiltroCategoria.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);

                if (value instanceof Categoria) {
                    label.setText(((Categoria) value).getNombre()); // Mostrar solo el nombre
                }
                return label;
            }
        });

        gbc.gridx = 1;
        panelFiltros.add(cmbFiltroCategoria, gbc);

        JLabel lblFiltroEstado = new JLabel("Estado:");
        lblFiltroEstado.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFiltros.add(lblFiltroEstado, gbc);

        cmbFiltroEstado = new JComboBox<>(new String[] { "Todos", "Activo", "Inactivo" });
        gbc.gridx = 1;
        panelFiltros.add(cmbFiltroEstado, gbc);

        JButton btnLimpiarFiltros = new JButton("Limpiar Filtros");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelFiltros.add(btnLimpiarFiltros, gbc);

        panelSuperior.add(panelFiltros, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central (Tabla)
        String[] columnas = { "ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría", "Estado" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setAutoCreateRowSorter(true);
        EstiloUI.configurarTabla(tablaProductos);

        JScrollPane scrollTabla = new JScrollPane(tablaProductos);
        add(scrollTabla, BorderLayout.CENTER);

        llenarTabla();

        // Eventos de Filtros y Búsqueda
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

        cmbFiltroCategoria.addActionListener(e -> aplicarFiltros());
        cmbFiltroEstado.addActionListener(e -> aplicarFiltros());
        btnLimpiarFiltros.addActionListener(e -> limpiarFiltros());

        // Panel inferior (Botones y Notificación)
        JPanel panelInferior = new JPanel(new BorderLayout());
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnCambiarEstado = new JButton("Activar/Inactivar");
        JButton btnExportarCSV = new JButton("Exportar a CSV");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnCambiarEstado);
        panelBotones.add(btnExportarCSV);

        panelInferior.add(panelBotones, BorderLayout.NORTH);

        lblNotificacion = new JLabel(" ");
        lblNotificacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblNotificacion.setFont(EstiloUI.FUENTE_GENERAL);
        lblNotificacion.setForeground(Color.DARK_GRAY); // Cambiar color del mensaje a más visible
        panelInferior.add(lblNotificacion, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        btnAgregar.addActionListener(e -> abrirFormularioAgregar());
        btnEditar.addActionListener(e -> abrirFormularioEditar());
        btnCambiarEstado.addActionListener(e -> cambiarEstadoProducto());
        btnExportarCSV.addActionListener(e -> exportarTablaACSV());

        setVisible(true);
    }

    private void aplicarFiltros() {
        String texto = txtBuscar.getText().toLowerCase();
        Categoria categoriaSeleccionada = (Categoria) cmbFiltroCategoria.getSelectedItem();
        String estadoSeleccionado = (String) cmbFiltroEstado.getSelectedItem();

        modeloTabla.setRowCount(0);
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.obtenerTodos();

        for (Producto producto : productos) {
            boolean coincideTexto = producto.getNombre().toLowerCase().contains(texto)
                    || producto.getDescripcion().toLowerCase().contains(texto);

            boolean coincideCategoria = categoriaSeleccionada == null || categoriaSeleccionada.getId() == 0
                    || producto.getCategoria().getId() == categoriaSeleccionada.getId();

            boolean coincideEstado = estadoSeleccionado.equals("Todos")
                    || (estadoSeleccionado.equals("Activo") && producto.isActivo())
                    || (estadoSeleccionado.equals("Inactivo") && !producto.isActivo());

            if (coincideTexto && coincideCategoria && coincideEstado) {
                modeloTabla.addRow(new Object[] { producto.getId(), producto.getNombre(), producto.getDescripcion(),
                        producto.getPrecio(), producto.getStock(), producto.getCategoria().getNombre(),
                        producto.isActivo() ? "Activo" : "Inactivo" });
            }
        }
    }

    private void llenarTabla() {
        aplicarFiltros();
    }

    private void limpiarFiltros() {
        txtBuscar.setText("");
        cmbFiltroCategoria.setSelectedIndex(0);
        cmbFiltroEstado.setSelectedIndex(0);
        aplicarFiltros();
    }

    private void exportarTablaACSV() {
        if (modeloTabla.getRowCount() == 0) {
            mostrarNotificacion("No hay datos para exportar.", Color.RED);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como CSV");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            // Asegurarse de que el archivo tenga extensión ".csv"
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                // Escribir encabezados
                for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                    writer.write(modeloTabla.getColumnName(i));
                    if (i < modeloTabla.getColumnCount() - 1) {
                        writer.write(","); // Separador
                    }
                }
                writer.write("\n");

                // Escribir datos
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                        writer.write(String.valueOf(modeloTabla.getValueAt(i, j)));
                        if (j < modeloTabla.getColumnCount() - 1) {
                            writer.write(","); // Separador
                        }
                    }
                    writer.write("\n");
                }

                mostrarNotificacion("Archivo CSV exportado correctamente.", new Color(0, 128, 255)); // Azul legible
            } catch (IOException ex) {
                mostrarNotificacion("Error al guardar el archivo CSV.", Color.RED);
            }
        }
    }

	private void abrirFormularioAgregar() {
		// Crear un panel con diseño de tipo GridBagLayout para un mejor control de los
		// componentes
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new java.awt.Insets(10, 10, 10, 10); // Espacio entre componentes

		// Alineación a la izquierda para las etiquetas
		gbc.anchor = GridBagConstraints.WEST;

		// Campo Nombre
		JLabel lblNombre = new JLabel("Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombre, gbc);
		JTextField txtNombre = new JTextField(20);
		gbc.gridx = 1;
		panel.add(txtNombre, gbc);

		// Campo Descripción
		JLabel lblDescripcion = new JLabel("Descripción:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(lblDescripcion, gbc);
		JTextField txtDescripcion = new JTextField(20);
		gbc.gridx = 1;
		panel.add(txtDescripcion, gbc);

		// Campo Precio
		JLabel lblPrecio = new JLabel("Precio:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lblPrecio, gbc);
		JTextField txtPrecio = new JTextField(20);
		gbc.gridx = 1;
		panel.add(txtPrecio, gbc);

		// Campo Stock
		JLabel lblStock = new JLabel("Stock:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblStock, gbc);
		JTextField txtStock = new JTextField(20);
		gbc.gridx = 1;
		panel.add(txtStock, gbc);

		// Campo Categoría
		JLabel lblCategoria = new JLabel("Categoría:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblCategoria, gbc);

		JComboBox<Categoria> cmbCategorias = new JComboBox<>();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorias = categoriaDAO.obtenerTodas();

		// Usar un ListCellRenderer para mostrar solo el nombre de la categoría
		cmbCategorias.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);

				if (value instanceof Categoria) {
					label.setText(((Categoria) value).getNombre()); // Mostrar solo el nombre
				}
				return label;
			}
		});

		// Añadir las categorías al JComboBox
		for (Categoria categoria : categorias) {
			cmbCategorias.addItem(categoria);
		}

		gbc.gridx = 1;
		panel.add(cmbCategorias, gbc);

		// Campo Estado
		JLabel lblEstado = new JLabel("Estado:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblEstado, gbc);
		JCheckBox chkActivo = new JCheckBox("Activo");
		chkActivo.setSelected(true); // El producto estará activo por defecto
		gbc.gridx = 1;
		panel.add(chkActivo, gbc);

		// Crear el panel de botones con un layout de FlowLayout (para alinear los
		// botones)
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centrado y espacio entre botones

		// Crear los botones
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2; // Hacer que los botones ocupen dos columnas
		panel.add(panelBotones, gbc);

		// Mostrar el formulario en un cuadro de diálogo
		int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);

		// Si el usuario presiona OK, procesamos la información ingresada
		if (result == JOptionPane.OK_OPTION) {
			try {
				// Validar y obtener los valores ingresados
				String nombre = txtNombre.getText().trim();
				String descripcion = txtDescripcion.getText().trim();
				double precio = Double.parseDouble(txtPrecio.getText().trim());
				int stock = Integer.parseInt(txtStock.getText().trim());
				Categoria categoriaSeleccionada = (Categoria) cmbCategorias.getSelectedItem();
				boolean activo = chkActivo.isSelected();

				// Verificar si los campos obligatorios no están vacíos
				if (nombre.isEmpty() || descripcion.isEmpty()) {
					throw new IllegalArgumentException("Los campos Nombre y Descripción son obligatorios.");
				}

				// Verificar que no exista un producto con el mismo nombre
				ProductoDAO productoDAO = new ProductoDAO();
				if (productoDAO.existeNombreDuplicado(nombre, -1)) {
					throw new IllegalArgumentException("El nombre del producto ya está en uso.");
				}

				// Crear el nuevo producto
				Producto nuevoProducto = new Producto();
				nuevoProducto.setNombre(nombre);
				nuevoProducto.setDescripcion(descripcion);
				nuevoProducto.setPrecio(precio);
				nuevoProducto.setStock(stock);
				nuevoProducto.setCategoria(categoriaSeleccionada);
				nuevoProducto.setActivo(activo);

				// Insertar el nuevo producto en la base de datos
				if (productoDAO.insertar(nuevoProducto)) {
					mostrarNotificacion("Producto agregado correctamente.", new Color(34, 139, 34)); // Verde más
																										// legible
					llenarTabla(); // Refrescar la tabla con los nuevos datos
				} else {
					mostrarNotificacion("Error al agregar producto.", Color.RED);
				}
			} catch (NumberFormatException ex) {
				mostrarNotificacion("Verifique que los campos Precio y Stock sean numéricos.", Color.RED);
			} catch (IllegalArgumentException ex) {
				mostrarNotificacion(ex.getMessage(), Color.RED);
			}
		}
	}

	private void abrirFormularioEditar() {
		// Obtener la fila seleccionada
		int filaSeleccionada = tablaProductos.getSelectedRow();
		if (filaSeleccionada == -1) {
			mostrarNotificacion("Debe seleccionar un producto para editar.", Color.RED);
			return;
		}

		// Obtener los datos del producto seleccionado
		int filaModelo = tablaProductos.convertRowIndexToModel(filaSeleccionada);
		int idProducto = (int) modeloTabla.getValueAt(filaModelo, 0);

		ProductoDAO productoDAO = new ProductoDAO();
		Producto producto = productoDAO.obtenerPorId(idProducto);

		if (producto == null) {
			mostrarNotificacion("Error al cargar los datos del producto.", Color.RED);
			return;
		}

		// Crear el panel con GridBagLayout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new java.awt.Insets(10, 10, 10, 10); // Espacio entre componentes

		// Alineación a la izquierda para las etiquetas
		gbc.anchor = GridBagConstraints.WEST;

		// Campo Nombre
		JLabel lblNombre = new JLabel("Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombre, gbc);
		JTextField txtNombre = new JTextField(producto.getNombre(), 20);
		gbc.gridx = 1;
		panel.add(txtNombre, gbc);

		// Campo Descripción
		JLabel lblDescripcion = new JLabel("Descripción:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(lblDescripcion, gbc);
		JTextField txtDescripcion = new JTextField(producto.getDescripcion(), 20);
		gbc.gridx = 1;
		panel.add(txtDescripcion, gbc);

		// Campo Precio
		JLabel lblPrecio = new JLabel("Precio:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lblPrecio, gbc);
		JTextField txtPrecio = new JTextField(String.valueOf(producto.getPrecio()), 20);
		gbc.gridx = 1;
		panel.add(txtPrecio, gbc);

		// Campo Stock
		JLabel lblStock = new JLabel("Stock:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblStock, gbc);
		JTextField txtStock = new JTextField(String.valueOf(producto.getStock()), 20);
		gbc.gridx = 1;
		panel.add(txtStock, gbc);

		// Campo Categoría
		JLabel lblCategoria = new JLabel("Categoría:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblCategoria, gbc);
		JComboBox<Categoria> cmbCategorias = new JComboBox<>();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorias = categoriaDAO.obtenerTodas();

		// Usar un ListCellRenderer para mostrar solo el nombre de la categoría
		cmbCategorias.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);

				if (value instanceof Categoria) {
					label.setText(((Categoria) value).getNombre()); // Mostrar solo el nombre
				}
				return label;
			}
		});

		// Añadir las categorías al JComboBox
		for (Categoria categoria : categorias) {
			cmbCategorias.addItem(categoria);
		}

		cmbCategorias.setSelectedItem(producto.getCategoria()); // Establecer la categoría seleccionada
		gbc.gridx = 1;
		panel.add(cmbCategorias, gbc);

		// Campo Estado
		JLabel lblEstado = new JLabel("Estado:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblEstado, gbc);
		JCheckBox chkActivo = new JCheckBox("Activo");
		chkActivo.setSelected(producto.isActivo()); // Establecer si el producto está activo o no
		gbc.gridx = 1;
		panel.add(chkActivo, gbc);

		// Crear el panel de botones con un layout de FlowLayout
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centrado y espacio entre botones

		// Crear los botones
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2; // Hacer que los botones ocupen dos columnas
		panel.add(panelBotones, gbc);

		// Mostrar el formulario en un cuadro de diálogo
		int result = JOptionPane.showConfirmDialog(this, panel, "Editar Producto", JOptionPane.OK_CANCEL_OPTION);

		// Si el usuario presiona OK, procesamos la información ingresada
		if (result == JOptionPane.OK_OPTION) {
			try {
				// Validar y obtener los valores ingresados
				String nombre = txtNombre.getText().trim();
				String descripcion = txtDescripcion.getText().trim();
				double precio = Double.parseDouble(txtPrecio.getText().trim());
				int stock = Integer.parseInt(txtStock.getText().trim());
				Categoria categoriaSeleccionada = (Categoria) cmbCategorias.getSelectedItem();
				boolean activo = chkActivo.isSelected();

				// Verificar si los campos obligatorios no están vacíos
				if (nombre.isEmpty() || descripcion.isEmpty()) {
					throw new IllegalArgumentException("Los campos Nombre y Descripción son obligatorios.");
				}

				// Verificar que no exista un producto con el mismo nombre
				if (productoDAO.existeNombreDuplicado(nombre, producto.getId())) {
					throw new IllegalArgumentException("El nombre del producto ya está en uso.");
				}

				// Actualizar los datos del producto
				producto.setNombre(nombre);
				producto.setDescripcion(descripcion);
				producto.setPrecio(precio);
				producto.setStock(stock);
				producto.setCategoria(categoriaSeleccionada);
				producto.setActivo(activo);

				// Actualizar el producto en la base de datos
				if (productoDAO.actualizar(producto)) {
					mostrarNotificacion("Producto actualizado correctamente.", new Color(34, 139, 34)); // Verde más
																										// legible
					llenarTabla(); // Refrescar la tabla con los nuevos datos
				} else {
					mostrarNotificacion("Error al actualizar el producto.", Color.RED);
				}
			} catch (NumberFormatException ex) {
				mostrarNotificacion("Verifique que los campos Precio y Stock sean numéricos.", Color.RED);
			} catch (IllegalArgumentException ex) {
				mostrarNotificacion(ex.getMessage(), Color.RED);
			}
		}
	}

	private void cambiarEstadoProducto() {
		int[] filasSeleccionadas = tablaProductos.getSelectedRows();

		if (filasSeleccionadas.length == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un producto.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Está seguro de cambiar el estado de los productos seleccionados?", "Confirmar acción",
				JOptionPane.YES_NO_OPTION);

		if (confirmacion != JOptionPane.YES_OPTION) {
			return; // Si el usuario cancela, no hacer nada
		}

		ProductoDAO productoDAO = new ProductoDAO();
		boolean exito = true;

		for (int fila : filasSeleccionadas) {
			int filaModelo = tablaProductos.convertRowIndexToModel(fila);
			int idProducto = (int) modeloTabla.getValueAt(filaModelo, 0);
			String estadoActual = (String) modeloTabla.getValueAt(filaModelo, 6);

			boolean nuevoEstado = estadoActual.equals("Inactivo");

			if (!productoDAO.cambiarEstado(idProducto, nuevoEstado)) {
				exito = false; // Si falla un cambio, marcamos el error
			}
		}

		if (exito) {
			mostrarNotificacion("Estado de los productos cambiado correctamente.", Color.ORANGE);
		} else {
			mostrarNotificacion("Hubo un error al cambiar el estado de algunos productos.", Color.RED);
		}

		llenarTabla(); // Actualizar la tabla
	}

	private void mostrarNotificacion(String mensaje, Color color) {
		lblNotificacion.setText(mensaje);
		lblNotificacion.setForeground(color);
		Timer timer = new Timer(3000, e -> lblNotificacion.setText(" "));
		timer.setRepeats(false);
		timer.start();
	}
}
