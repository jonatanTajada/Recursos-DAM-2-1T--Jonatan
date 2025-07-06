package practicasExamenPrimerTrimestre.STREAMS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


class Tarea implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descripcion;
	private String fechaLimite;


	public Tarea(String titulo, String descripcion, String fechaLimite) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaLimite = fechaLimite;
	}


	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	@Override
	public String toString() {
		return "Tarea [Título=" + titulo + ", Descripción=" + descripcion + ", Fecha Límite=" + fechaLimite + "]";
	}
}
//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------


class AplicacionTareas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Tarea> listaTareas;


	public AplicacionTareas() {
		this.listaTareas = new ArrayList<>();
	}

	
	public void agregarTarea(Tarea tarea) {
		listaTareas.add(tarea);
	}

	// Método para guardar el estado de la aplicación en un archivo
	public void guardarEstado(String nombreArchivo) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
			
			oos.writeObject(this);
			System.out.println("Estado de la aplicación guardado en " + nombreArchivo);
			
		} catch (IOException e) {
			
			System.out.println("Ocurrió un error al guardar el estado.");
			e.printStackTrace();
		}
	}

	// Método para cargar el estado de la aplicación desde un archivo
	public static AplicacionTareas cargarEstado(String nombreArchivo) {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
			
			return (AplicacionTareas) ois.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println("Ocurrió un error al cargar el estado.");
			e.printStackTrace();
			return null;
		}
	}

	// Método para mostrar todas las tareas
	public void mostrarTareas() {
		
		if (listaTareas.isEmpty()) {
			
			System.out.println("No hay tareas en la lista.");
			
		} else {
			
			System.out.println("Lista de tareas:");
			
			for (Tarea tarea : listaTareas) {
				System.out.println(tarea);
			}
		}
	}
}


//-----------------------------------------------------------------------------------------------------------------------------------------------
//-------------------*** MAIN *****----------------------------------------------------------------------------------------------------------------------------

public class SerializarDeserializarAppGestionTareas {
	
	public static void main(String[] args) {
		
		// Crear una instancia de AplicacionTareas
		AplicacionTareas appTareas = new AplicacionTareas();

		// Añadir algunas tareas
		appTareas.agregarTarea(new Tarea("Estudiar", "Estudiar para el examen de DAO", "2023-12-15"));
		appTareas.agregarTarea(new Tarea("Ejercicio", "Ir al gimnasio", "2023-11-10"));
		appTareas.agregarTarea(new Tarea("Compra", "Comprar regalos de Navidad", "2023-12-20"));

		// Guardar el estado de la aplicación
		appTareas.guardarEstado("tareas.ser");

		// Cargar el estado de la aplicación desde el archivo
		AplicacionTareas appTareasCargada = AplicacionTareas.cargarEstado("tareas.ser");

		// Mostrar las tareas cargadas
		if (appTareasCargada != null) {
			
			appTareasCargada.mostrarTareas();
		}
	}
}
