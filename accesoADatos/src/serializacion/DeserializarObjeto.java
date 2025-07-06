package serializacion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializarObjeto {

	public static void main(String[] args) {

		try (FileInputStream fileIn = new FileInputStream("src/persona.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn)) {

			// Deserializar el objeto
			Persona persona1 = (Persona) in.readObject();
			System.out.println("Nombre: " + persona1.getNombre());
			System.out.println("Edad: " + persona1.getEdad());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
