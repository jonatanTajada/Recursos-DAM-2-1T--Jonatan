package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) {
    	
        try {
        	
            // Carga el archivo FXML
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VistaContador.fxml"));
            primaryStage.setTitle("Contador MVC");
            primaryStage.setScene(new Scene(root, 300, 200));
            primaryStage.show();
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
    	
        launch(args); // Método para iniciar la aplicación JavaFX
    }
    
}

