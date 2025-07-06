package serializacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializarObjeto {

	
	public static void main(String[] args) {
		
		Persona persona1 = new Persona("Jonatan", 36);
		
		try (FileOutputStream fos = new FileOutputStream("src/persona.ser");
			 ObjectOutput out = new ObjectOutputStream(fos)){
			
			//serializar el objeto
			out.writeObject(persona1);
			System.out.println("El objeto se ha serializado y guardado en persona.ser");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}


