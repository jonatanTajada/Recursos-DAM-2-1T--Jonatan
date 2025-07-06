package barMou;

public class VasoCerveza {
	
    private static int contadorId = 0; 
    private int id;                    
    private int tipo;  // Tipo de vaso: 0 = media pinta, 1 = pinta completa

  
    public VasoCerveza(int tipo) {
        this.id = contadorId++; 
        this.tipo = tipo;
    }

 
    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

   
    @Override
    public String toString() {
    	
        String tipoVaso = (tipo == 0) ? "Media Pinta" : "Pinta Completa";
        return "Vaso ID: " + id + ", Tipo: " + tipoVaso;
    }
}
