package ni.edu.uam.pae_eventos_javafx_pcinalambrica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FormularioGeneralController {

    private static final String RUTA_RETO_1 = "pulperia-view.fxml";
    private static final String RUTA_RETO_2 = "reto2-recepcion-cafe.fxml";
    private static final String RUTA_RETO_3 = "reto3-artesanias.fxml";

    @FXML
    private Label lblEstado;

    @FXML
    private void abrirReto1(ActionEvent event) {
        abrirFormulario(RUTA_RETO_1, "Reto 1 - Inventario de pulpería");
    }

    @FXML
    private void abrirReto2(ActionEvent event) {
        abrirFormulario(RUTA_RETO_2, "Reto 2 - Recepción de café");
    }

    @FXML
    private void abrirReto3(ActionEvent event) {
        abrirFormulario(RUTA_RETO_3, "Reto 3 - Tienda de artesanías");
    }

    private void abrirFormulario(String nombreFXML, String titulo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource(nombreFXML)
            );

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

            lblEstado.setText("Formulario abierto: " + titulo);
        } catch (IOException | NullPointerException e) {
            lblEstado.setText("No se pudo abrir " + titulo);

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de navegación");
            alerta.setHeaderText("No se encontró el formulario");
            alerta.setContentText(
                    "Verifique que el archivo " + nombreFXML
                            + " esté en la misma carpeta de recursos."
            );
            alerta.showAndWait();
        }
    }
}
