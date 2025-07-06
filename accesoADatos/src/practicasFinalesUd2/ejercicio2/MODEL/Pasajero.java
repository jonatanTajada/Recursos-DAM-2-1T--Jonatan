package practicasFinalesUd2.ejercicio2.MODEL;

public class Pasajero {

	private int num;
	private String codVuelo;
	private TipoPlaza tipoPlaza;
	private Fumador fumador;

	public Pasajero(int num, String codVuelo, TipoPlaza tipoPlaza, Fumador fumador) {
		this.num = num;
		this.codVuelo = codVuelo;
		this.tipoPlaza = tipoPlaza;
		this.fumador = fumador;
	}

	// Getters y Setters
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCodVuelo() {
		return codVuelo;
	}

	public void setCodVuelo(String codVuelo) {
		this.codVuelo = codVuelo;
	}

	public TipoPlaza getTipoPlaza() {
		return tipoPlaza;
	}

	public void setTipoPlaza(TipoPlaza tipoPlaza) {
		this.tipoPlaza = tipoPlaza;
	}

	public Fumador getFumador() {
		return fumador;
	}

	public void setFumador(Fumador fumador) {
		this.fumador = fumador;
	}
}
