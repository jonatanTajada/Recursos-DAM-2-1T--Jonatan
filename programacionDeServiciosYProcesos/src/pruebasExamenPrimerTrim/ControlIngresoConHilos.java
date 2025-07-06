package pruebasExamenPrimerTrim;

import java.util.Scanner;

public class ControlIngresoConHilos {

	
	
	public static void main(String[] args) {
		
		 Scanner scanner = new Scanner(System.in);

	        // Solicitar datos del primer usuario
	        System.out.println("Introduce el nombre del primer usuario:");
	        String usuario1 = scanner.nextLine();

	        System.out.println("Introduce el día de ingreso del primer usuario:");
	        String dia1 = scanner.nextLine();

	        System.out.println("Introduce la hora de ingreso del primer usuario (formato 24h, ej. 7:45 o 8:15):");
	        String hora1 = scanner.nextLine();

	        // Solicitar datos del segundo usuario
	        System.out.println("Introduce el nombre del segundo usuario:");
	        String usuario2 = scanner.nextLine();

	        System.out.println("Introduce el día de ingreso del segundo usuario:");
	        String dia2 = scanner.nextLine();

	        System.out.println("Introduce la hora de ingreso del segundo usuario (formato 24h, ej. 7:45 o 8:15):");
	        String hora2 = scanner.nextLine();
		
		
	        Evaluador evaluador1 = new Evaluador(usuario1, dia1, hora1);
	        Evaluador evaluador2 = new Evaluador(usuario2, dia2, hora2);
	        
	        evaluador1.start();
	        evaluador2.start();
	        
	        try {
				evaluador1.join();
				evaluador2.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
		
	}
	
}

//---------------------------------------------------------------

class Evaluador extends Thread{
	
	private String usuario;
	private String dia;
	private String hora;

	public Evaluador(String usuario, String dia, String hora) {
		this.usuario = usuario;
		this.dia = dia;
		this.hora = hora;
	}
	
	@Override
	public void run() {

		
		String[] partesHora = hora.split(":");
		
		int horaIngresada = Integer.parseInt(partesHora[0]);
		int minutosIngresados = Integer.parseInt(partesHora[1]);
		
		int horaLimite = 8;
		int minutosLimite = 0;
		
		boolean llegoTemprano = (horaIngresada < horaLimite) ||
                (horaIngresada == horaLimite && minutosIngresados <= minutosLimite);
		
	
		if (llegoTemprano) {
            System.out.println(usuario + " llegó temprano el día " + dia + " a las " + hora + ".");
        } else {
            System.out.println(usuario + " llegó tarde el día " + dia + " a las " + hora + ".");
        }
	
	
	}
	
	
}