package modelo;

public class Tarea {

	private String descripcion;
	private boolean completada;

	public Tarea(String descripcion) {
		this.descripcion = descripcion;
		this.completada = false;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void completar() {
		this.completada = true;
	}

	// metodo para cambiar el estado de la tarea
	public void cambiarEstado() {
		this.completada = !this.completada;
	}

	@Override
	public String toString() {
		return descripcion + " - " + (completada ? "Completada" : "Pendiente");
	}
}
