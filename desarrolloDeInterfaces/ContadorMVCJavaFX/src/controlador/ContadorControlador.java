package controlador;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.Contador;

public class ContadorControlador {
	
    @FXML private Button botonIncrementar;
    @FXML private Label labelContador;
    private Contador contador;

    @FXML
    public void initialize() {
        // Inicializamos el modelo
        contador = new Contador();

        // Configuramos el evento del boton
        botonIncrementar.setOnAction(e -> {
            contador.incrementar(); 
            labelContador.setText("Contador: " + contador.get()); // Actualización de la vista
        });
    }
}

