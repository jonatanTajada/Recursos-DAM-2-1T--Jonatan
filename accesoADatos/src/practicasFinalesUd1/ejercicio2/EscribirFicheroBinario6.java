package practicasFinalesUd1.ejercicio2;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Ejercicio 6: Escribir un fichero binario de empleados (.dat)

public class EscribirFicheroBinario6 {

	public static void main(String[] args) {
		
		String[] departamentos = { "Contabilidad", "Informática", "Dirección", "Análisis", "Finanzas", "Hardware" };
		int[] empleados = { 3, 10, 2, 5, 4, 8 };

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("empleados.dat"))) {
			for (int i = 0; i < departamentos.length; i++) {
				dos.writeUTF(departamentos[i]);
				dos.writeInt(empleados[i]);
			}
			System.out.println("Fichero binario empleados.dat creado con exito.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
