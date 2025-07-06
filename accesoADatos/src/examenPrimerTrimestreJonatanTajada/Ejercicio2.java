package examenPrimerTrimestreJonatanTajada;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio de escritura en archivo binario
 */

//implementa Serializable
public class Ejercicio2 implements Serializable {

    private static final long serialVersionUID = -8276984455618976434L;

    private String nombre;
    private int edad;
    private String curso;

    // Constructor
    public Ejercicio2(String nombre, int edad, String curso) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre='" + nombre + '\'' + ", edad=" + edad + ", curso='" + curso + '\'' + '}';
    }


    public static void main(String[] args) {

        // Crear una lista de alumnos
        List<Ejercicio2> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Ejercicio2("Pedro", 21, "DAM2"));
        listaAlumnos.add(new Ejercicio2("Laura", 23, "DAM2"));
        listaAlumnos.add(new Ejercicio2("Marta", 20, "DAM2"));

        // Llamar al método para escribir en el archivo binario
        escribirAlumnosEnArchivo(listaAlumnos, "alumnos.dat");
    }

    // Metodo para escribir los alumnos en un archivo
    public static void escribirAlumnosEnArchivo(List<Ejercicio2> listaAlumnos, String nombreArchivo) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {

            oos.writeObject(listaAlumnos);
            System.out.println("Lista de alumnos escrita correctamente en el archivo: " + nombreArchivo);
        } catch (IOException e) {

            System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}
