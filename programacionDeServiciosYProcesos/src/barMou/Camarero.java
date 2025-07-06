package barMou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Camarero {
	
	private String nombreCamarero; 
	private List<VasoCerveza> listaVasos; 

	
	public Camarero(String nombre) {
		
		this.nombreCamarero = nombre;
		this.listaVasos = new ArrayList<>();

		//crear 3 vasos con su tipo de forma aleatoria
		Random random = new Random();
		
		for (int i = 0; i < 3; i++) {
			
			int tipoAleatorio = random.nextInt(2); // 0 para media pinta, 1 para pinta completa
			listaVasos.add(new VasoCerveza(tipoAleatorio));
		}

		System.out.println("Camarero " + nombre + " listo con " + listaVasos.size() + " vasos.");
	}

	
	// metodo para servir cerveza: selecciona un vaso aleatorio de la lista y lo elimina
	public synchronized VasoCerveza servirCerveza() {
		
		if (listaVasos.isEmpty()) {
			
			System.out.println("El camarero " + nombreCamarero + " no tiene vasos disponibles.");
			return null;
		}

		Random random = new Random();
		int indice = random.nextInt(listaVasos.size()); // Selecciona un vaso aleatorio
		
		VasoCerveza vaso = listaVasos.remove(indice); // Lo saca de la lista
		
		System.out.println("Camarero " + nombreCamarero + " ha servido " + vaso + "vasos.");
		
		return vaso;
	}

	
	// metodo para devolver un vaso: agrega el vaso de nuevo a la lista
	public synchronized void devolverVasoCerveza(VasoCerveza vaso) {
		
		listaVasos.add(vaso);
		System.out.println("Camarero " + nombreCamarero + " ha recibido de vuelta " + vaso);
		
	}

	
	// metodo para contar los vasos disponibles
	public synchronized void contarVasosDisponibles() {
		System.out.println("Vasos disponibles en el bar: " + listaVasos.size());
	}
}
