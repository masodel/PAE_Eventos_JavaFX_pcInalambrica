package ni.edu.uam.pae_eventos_javafx_pcinalambrica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CafeApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                CafeApplication.class.getResource("cafe-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Recepción de Café");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}