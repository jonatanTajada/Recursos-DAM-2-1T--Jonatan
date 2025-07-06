package controlador;

import modelo.Contacto;
import vista.FormularioContacto;
import vista.VentanaGrupos;
import vista.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ControladorPrincipal {
	private VentanaPrincipal vista;
	private ArrayList<Contacto> listaContactos;
	private DefaultTableModel modeloTabla;

	public ControladorPrincipal(VentanaPrincipal vista) {
		this.vista = vista;
		this.listaContactos = new ArrayList<>();
		inicializarControlador();
		inicializarTabla();
		cargarContactosDesdeArchivo(); // Cargar contactos al iniciar la aplicación
	}

	private void inicializarControlador() {
		vista.getBotonAgregar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Abrir la ventana de agregar contacto
				FormularioContacto formulario = new FormularioContacto();
				formulario.setVisible(true);
				formulario.getBotonGuardar().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Crear un nuevo contacto y agregarlo a la lista
						Contacto nuevoContacto = new Contacto(formulario.getCampoNombre().getText(),
								formulario.getCampoTelefono().getText(), formulario.getCampoEmail().getText(),
								formulario.getCampoDireccion().getText());
						listaContactos.add(nuevoContacto);
						formulario.dispose(); // Cerrar la ventana del formulario
						actualizarTabla();
						guardarContactosEnArchivo(); // Guardar contactos después de agregar
					}
				});
			}
		});

		vista.getBotonEliminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = vista.getTablaContactos().getSelectedRow();
				if (filaSeleccionada >= 0) {
					listaContactos.remove(filaSeleccionada); // Eliminar el contacto seleccionado
					actualizarTabla();
					guardarContactosEnArchivo(); // Guardar después de eliminar
				} else {
					JOptionPane.showMessageDialog(vista, "Seleccione un contacto para eliminar.");
				}
			}
		});

		vista.getBotonEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = vista.getTablaContactos().getSelectedRow();
				if (filaSeleccionada >= 0) {
					Contacto contactoSeleccionado = listaContactos.get(filaSeleccionada);
					FormularioContacto formulario = new FormularioContacto();
					formulario.setVisible(true);

					// Rellenar el formulario con los datos del contacto
					formulario.getCampoNombre().setText(contactoSeleccionado.getNombre());
					formulario.getCampoTelefono().setText(contactoSeleccionado.getTelefono());
					formulario.getCampoEmail().setText(contactoSeleccionado.getEmail());
					formulario.getCampoDireccion().setText(contactoSeleccionado.getDireccion());

					formulario.getBotonGuardar().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Actualizar los datos del contacto
							contactoSeleccionado.setNombre(formulario.getCampoNombre().getText());
							contactoSeleccionado.setTelefono(formulario.getCampoTelefono().getText());
							contactoSeleccionado.setEmail(formulario.getCampoEmail().getText());
							contactoSeleccionado.setDireccion(formulario.getCampoDireccion().getText());
							formulario.dispose(); // Cerrar la ventana del formulario
							actualizarTabla();
							guardarContactosEnArchivo(); // Guardar cambios en el contacto
						}
					});
				} else {
					JOptionPane.showMessageDialog(vista, "Seleccione un contacto para editar.");
				}
			}
		});

		vista.getBotonActualizar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});

		vista.getGestionGrupos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Abrir la ventana de gestión de grupos
				VentanaGrupos ventanaGrupos = new VentanaGrupos();
				ventanaGrupos.setVisible(true);
			}
		});

		vista.getSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Salir de la aplicación
			}
		});
	}

	private void inicializarTabla() {
		modeloTabla = new DefaultTableModel(new Object[] { "Nombre", "Teléfono", "Email", "Dirección" }, 0);
		vista.getTablaContactos().setModel(modeloTabla);
	}

	private void actualizarTabla() {
		modeloTabla.setRowCount(0); // Limpiar la tabla
		for (Contacto contacto : listaContactos) {
			modeloTabla.addRow(new Object[] { contacto.getNombre(), contacto.getTelefono(), contacto.getEmail(),
					contacto.getDireccion() });
		}
	}

	// Método para guardar contactos en un archivo
	private void guardarContactosEnArchivo() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("contactos.txt"))) {
			for (Contacto contacto : listaContactos) {
				writer.write(contacto.getNombre() + "," + contacto.getTelefono() + "," + contacto.getEmail() + ","
						+ contacto.getDireccion() + "\n");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(vista, "Error al guardar los contactos.");
		}
	}

	// Método para cargar contactos desde un archivo
	private void cargarContactosDesdeArchivo() {
		listaContactos.clear(); // Limpiar la lista actual
		try (BufferedReader reader = new BufferedReader(new FileReader("contactos.txt"))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] datos = linea.split(",");
				Contacto contacto = new Contacto(datos[0], datos[1], datos[2], datos[3]);
				listaContactos.add(contacto);
			}
			actualizarTabla();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(vista, "Error al cargar los contactos.");
		}
	}
}
