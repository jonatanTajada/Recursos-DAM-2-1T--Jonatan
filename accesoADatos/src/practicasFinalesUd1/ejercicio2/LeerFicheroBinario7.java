package practicasFinalesUd1.ejercicio2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

// Ejercicio 7: Mostrar la información del fichero binario (.dat) de forma secuencial

public class LeerFicheroBinario7 {
	
    public static void main(String[] args) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("empleados.dat"))) {
            while (dis.available() > 0) {
                String departamento = dis.readUTF();
                int numeroEmpleados = dis.readInt();
                System.out.println("Departamento: " + departamento + ", Número de empleados: " + numeroEmpleados);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
