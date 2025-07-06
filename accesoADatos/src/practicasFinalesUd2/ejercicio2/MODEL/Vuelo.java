package practicasFinalesUd2.ejercicio2.MODEL;


public class Vuelo {
	
	private String codigo;
	private String horaSalida;
	private String destino;
	private String procedencia;
	private int plazasFumador;
	private int plazasNoFumador;
	private int plazasTurista;
	private int plazasPrimera;

	public Vuelo(String codigo, String horaSalida, String destino, String procedencia, int plazasFumador,
			int plazasNoFumador, int plazasTurista, int plazasPrimera) {
		this.codigo = codigo;
		this.horaSalida = horaSalida;
		this.destino = destino;
		this.procedencia = procedencia;
		this.plazasFumador = plazasFumador;
		this.plazasNoFumador = plazasNoFumador;
		this.plazasTurista = plazasTurista;
		this.plazasPrimera = plazasPrimera;
	}
	
	// Constructor con solo COD_VUELO, HORA_SALIDA, DESTINO
    public Vuelo(String codigo, String horaSalida, String destino) {
        this.codigo = codigo;
        this.horaSalida = horaSalida;
        this.destino = destino;
    }

	// Getters y Setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public int getPlazasFumador() {
		return plazasFumador;
	}

	public void setPlazasFumador(int plazasFumador) {
		this.plazasFumador = plazasFumador;
	}

	public int getPlazasNoFumador() {
		return plazasNoFumador;
	}

	public void setPlazasNoFumador(int plazasNoFumador) {
		this.plazasNoFumador = plazasNoFumador;
	}

	public int getPlazasTurista() {
		return plazasTurista;
	}

	public void setPlazasTurista(int plazasTurista) {
		this.plazasTurista = plazasTurista;
	}

	public int getPlazasPrimera() {
		return plazasPrimera;
	}

	public void setPlazasPrimera(int plazasPrimera) {
		this.plazasPrimera = plazasPrimera;
	}
}
