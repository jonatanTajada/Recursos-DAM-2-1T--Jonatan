package modelo;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
	
	private String nombre;
	private List<Contacto> contactos;

	public Grupo(String nombre) {
		this.nombre = nombre;
		this.contactos = new ArrayList<>();
	}

	// Método para añadir contacto al grupo
	public void agregarContacto(Contacto contacto) {
		contactos.add(contacto);
	}

	// Método para eliminar contacto del grupo
	public void eliminarContacto(Contacto contacto) {
		contactos.remove(contacto);
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre + " - " + contactos.size() + " contactos";
	}
}
