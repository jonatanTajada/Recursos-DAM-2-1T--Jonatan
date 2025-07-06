package practicasExamenPrimerTrimestre.STREAMS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Persona implements Serializable {

	private static final long serialVersionUID = -3344654563796280357L;

	private String nombre;
	private int edad;

	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}
}

//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------

public class SerializarDeserializar {

	public static void main(String[] args) {

		Persona persona = new Persona("Jonatan", 36);

		// Serializar
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persona.ser"))) {

			oos.writeObject(persona);
			System.out.println("Objeto persona serialziado correctamente.");
		} catch (IOException e) {

			System.err.println("Ocurrio algun error en la serializacion: " + e.getMessage());
		}

		// Deserializar
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persona.ser"))) {
			
			Persona personaLeida = (Persona) ois.readObject();
			
			System.out.println("Nombre deserializado: " + personaLeida.getNombre());
			System.out.println("Edad deserializada: " + personaLeida.getEdad());
			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println("Ocurrió un error durante la deserialización.");
			e.printStackTrace();
		}

	}

}
