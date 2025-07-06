package barMou;

import java.util.Random;

public class HiloCliente extends Thread {
	
	private Camarero camarero;
	private int totalCervezaBebida;
	private boolean activo; 

	
	public HiloCliente(String nombre, Camarero camarero) {
		
		super(nombre); //setName(nombre); establezco el nombre del hilo directamente
		this.camarero = camarero;
		this.totalCervezaBebida = 0;
		this.activo = true; 
	}

	// metodo para detener el cliente de forma controlada
	public void detener() {
		activo = false;
	}

	@Override
	public void run() {
		
		System.out.println(getName() + " esta en el bar y comienza a pedir cerveza.");
		Random random = new Random();

		while (activo) {
			
			VasoCerveza vaso = camarero.servirCerveza();
			
			if (vaso != null) {
				
				int cantidad = (vaso.getTipo() == 0) ? 500 : 1000; //500 o 1000 ml
				totalCervezaBebida += cantidad;

				System.out.println(getName() + " ha bebido " + cantidad + " ml de cerveza. Total bebido: " + totalCervezaBebida + " ml");

				camarero.devolverVasoCerveza(vaso);

				try {
					Thread.sleep(250 + random.nextInt(750));
					
				} catch (InterruptedException e) {
					
					System.out.println(getName() + " ha sido interrumpido mientras esperaba.");
					break;
				}
				
			} else {
				
				System.out.println(getName() + " espera porque no hay vasos disponibles.");
				
				try {
					
					Thread.sleep(500);
				} catch (InterruptedException e) {
					
					System.out.println(getName() + " ha sido interrumpido mientras esperaba.");
					break;
				}
			}
		}
	}
}
