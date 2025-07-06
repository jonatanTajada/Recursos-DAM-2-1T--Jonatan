package pruebasExamenPrimerTrimestre;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InformacionSistema {

	public static void main(String[] args) {

		String[] comando = { "cmd", "/c", "systeminfo" };

		ProcessBuilder pb = new ProcessBuilder(comando);

		try {

			Process proceso = pb.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String linea;

			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
			int codigoSalida = proceso.waitFor();
			
			if (codigoSalida == 0) {
				System.out.println("El proceso termino correctamente");
			}else {
				System.err.println("El proceso termino con errores: " + codigoSalida);
			}

		} catch (Exception e) {
			System.err.println("Error en la ejecucion del comando: " + e.getMessage());
		}

	}

}
