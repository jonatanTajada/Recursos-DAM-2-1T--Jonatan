package practicasExamenPrimerTrimestre.EjercicioRepasoAD1Trimestre;

import java.io.Serializable;

public class Alumno implements Serializable {
	
	private static final long serialVersionUID = 3090514443196657719L;
	
	private int id;
    private String nombre;
    private int edad;
    private boolean activo;

    public Alumno(int id, String nombre, int edad, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.activo = activo;
    }

   
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public boolean isActivo() { return activo; }

    @Override
    public String toString() {
        return "Alumno [ID=" + id + ", Nombre=" + nombre + ", Edad=" + edad + ", Activo=" + activo + "]";
    }
}
