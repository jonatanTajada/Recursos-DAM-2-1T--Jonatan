package pruebas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// -------------CLASE PERSONA - SUSCEPTIBLE A SERIALIZACION--------------------------------------

class Persona implements Serializable{

	private static final long serialVersionUID = -2447089020307931885L;
	private String nombre;
	private int edad;
	
	public Persona(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	
	  @Override
	    public String toString() {
	        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
	    }
	
	
}

// ----------------------------------------------------------------------------------------------

public class AccesoAFicheros_Object {
	

	//1. metodo para escribir objeto en archivo
	public void escribirObjeto(String ruta, Persona persona) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))){
			oos.writeObject(persona);
			System.out.println("Objeto escrito en el archivo.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2. metodo para leer el objeto en archivo
	public void leerObjeto(String ruta) {
		
		 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
	            Persona persona = (Persona) ois.readObject();
	            System.out.println("Objeto leído del archivo: " + persona);
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		AccesoAFicheros_Object ejemplo = new AccesoAFicheros_Object();
        String ruta = "archivoObjeto.dat";
        Persona persona = new Persona("Jonatan", 36);
        ejemplo.escribirObjeto(ruta, persona);
        ejemplo.leerObjeto(ruta);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
