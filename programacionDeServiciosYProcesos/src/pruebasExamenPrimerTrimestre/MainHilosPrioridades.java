package pruebasExamenPrimerTrimestre;

public class MainHilosPrioridades {

	public static void main(String[] args) {

		Thread hiloPrioridadAlta = new Thread(new HiloPrioridadAlta());
		Thread hiloPrioridadBaja = new Thread(new HiloPrioridadBaja());

		hiloPrioridadAlta.setPriority(Thread.MAX_PRIORITY);
		hiloPrioridadBaja.setPriority(Thread.MIN_PRIORITY);

		hiloPrioridadAlta.start();
		hiloPrioridadBaja.start();

		System.out.println("\t\tHilos con diferentes prioridades han sido iniciados.");

	}

}
