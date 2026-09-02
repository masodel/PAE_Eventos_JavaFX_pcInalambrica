package ni.edu.uam.pae_eventos_javafx_pcinalambrica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionEventos extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                AplicacionEventos.class.getResource("formulario-general.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Eventos y controles JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
