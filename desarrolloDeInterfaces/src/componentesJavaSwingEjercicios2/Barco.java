package componentesJavaSwingEjercicios2;

public class Barco {

	private String matricula;
	private String nombre;
	private float eslora;
	private int cabinas;

	public Barco(String matricula, String nombre, float eslora, int cabinas) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.eslora = eslora;
		this.cabinas = cabinas;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getEslora() {
		return eslora;
	}

	public void setEslora(float eslora) {
		this.eslora = eslora;
	}

	public int getCabinas() {
		return cabinas;
	}

	public void setCabinas(int cabinas) {
		this.cabinas = cabinas;
	}

	@Override
	public String toString() {
		return "Matrícula=" + matricula + "\nNombre=" + nombre + "\nEslora=" + eslora + " metros\nCabinas=" + cabinas;
	}

}
