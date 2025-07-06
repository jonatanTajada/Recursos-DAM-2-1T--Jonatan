package pruebas;

//Es una clase que suma los numeros entre dos valores que le pasas como argumentos desde la linea de comandos.
//comando para probar clase: abrir terminal a la altura del archivo y usar:
//                           javac Sumador.java > java Sumador.java > java Sumador.java 1 10
public class Sumador {

    public static int sumar(int n1, int n2) {
        int suma = 0;
        for (int i = n1; i <= n2; i++) {
            suma += i;
        }
        return suma;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Por favor, ingrese dos números como argumentos.");
            return;
        }

        try {
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            int resultado = sumar(n1, n2);
            System.out.println("La suma de los números entre " + n1 + " y " + n2 + " es: " + resultado);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese dos números válidos.");
        }
    }
}
