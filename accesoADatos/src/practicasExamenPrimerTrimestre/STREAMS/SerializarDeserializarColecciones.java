package practicasExamenPrimerTrimestre.STREAMS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Producto implements Serializable{

	private static final long serialVersionUID = -7008558214087399219L;
	
	private String nombre;
	private double precio;
	private int cantidad;
	
	
	public Producto(String nombre, double precio, int cantidad) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public int getCantidad() {
		return cantidad;
	}
		
}

//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------


public class SerializarDeserializarColecciones {

	
	
	
	public static void main(String[] args) {
		
		List<Producto> listaProductos = new ArrayList<>();
		
		listaProductos.add(new Producto("Manzanas", 1, 5));
		listaProductos.add(new Producto("Platanno", 1.55, 10));
		listaProductos.add(new Producto("Arandanos", 12, 1));
		
		//serializar
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listaCompra.txt"))){
			
			oos.writeObject(listaProductos);
			System.out.println("Lista serializada correctamente.!\n");
			
		} catch (IOException e) {
			
			System.err.println("Ocurrio algun error al serializar: " + e.getMessage());
		}
		
		//deserializar
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listaCompra.txt "))){
			
			List<Producto> listaProductosLeidos = (List<Producto>) ois.readObject();
			
            System.out.println("Productos deserializados:");
			for (Producto producto : listaProductosLeidos) {
				
				System.out.println("\tNombre: " + "\t" + producto.getNombre());
				System.out.println("\tPrecio: " + "\t" +  producto.getPrecio());
				System.out.println("\tCantidad: " + "\t" +  producto.getCantidad());
				System.out.println("-----------------------------------------");
			}
			
		} catch (Exception e) {
			System.err.println("Ocurrio algun error en la deserializacion: " + e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
