package ni.edu.uam.pae_eventos_javafx_pcinalambrica;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PulperiaApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                PulperiaApplication.class.getResource("pulperia-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Pulpería");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}