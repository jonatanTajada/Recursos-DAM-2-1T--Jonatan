package ficheros;

import java.io.Serializable;
import java.util.Objects;

public class Persona implements Serializable {

	
	private static final long serialVersionUID = 6333167091455238386L;
	private static int contadorId= 1;
	private int id;
	private String nombre;
	private int edad;
	private String dni;
	
	public Persona(String nombre, int edad, String dni) {
		this.id= contadorId++;
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
	}

	public int getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, edad, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni) && edad == other.edad && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", dni=" + dni + "]";
	}
	
	
	
	
	
	
	
	
}
