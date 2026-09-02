package ni.edu.uam.pae_eventos_javafx_pcinalambrica.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ni.edu.uam.pae_eventos_javafx_pcinalambrica.modelos.Cafe;

import java.util.Optional;

public class CafeController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtProductor;

    @FXML
    private TextField txtTipoCafe;

    @FXML
    private TextField txtPeso;

    @FXML
    private Button btnAgregar;

    @FXML
    private Label lblDetalles;

    @FXML
    private TableView<Cafe> tablaLotes;

    @FXML
    private TableColumn<Cafe, String> colCodigo;

    @FXML
    private TableColumn<Cafe, String> colProductor;

    @FXML
    private TableColumn<Cafe, String> colTipoCafe;

    @FXML
    private TableColumn<Cafe, Double> colPeso;

    private ObservableList<Cafe> lotes =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        colProductor.setCellValueFactory(
                new PropertyValueFactory<>("productor")
        );

        colTipoCafe.setCellValueFactory(
                new PropertyValueFactory<>("tipoCafe")
        );

        colPeso.setCellValueFactory(
                new PropertyValueFactory<>("peso")
        );

        tablaLotes.setItems(lotes);

        agregarContextMenu();
    }

    @FXML
    private void OnClickAgregar(ActionEvent event) {

        if (txtCodigo.getText().isBlank()
                || txtProductor.getText().isBlank()
                || txtTipoCafe.getText().isBlank()
                || txtPeso.getText().isBlank()) {

            mostrarAlerta(
                    "Campos vacíos",
                    "Debe llenar todos los campos."
            );

            return;
        }

        double peso;

        try {
            peso = Double.parseDouble(txtPeso.getText());
        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Peso inválido",
                    "El peso debe ser numérico."
            );

            return;
        }

        if (peso <= 0) {

            mostrarAlerta(
                    "Peso inválido",
                    "El peso debe ser mayor que cero."
            );

            return;
        }

        Cafe lote = new Cafe(
                txtCodigo.getText(),
                txtProductor.getText(),
                txtTipoCafe.getText(),
                peso
        );

        lotes.add(lote);

        limpiarCampos();
    }

    @FXML
    private void OnMouseClicked(MouseEvent event) {

        if (event.getButton() == MouseButton.PRIMARY) {

            Cafe lote = tablaLotes.getSelectionModel()
                    .getSelectedItem();

            if (lote != null) {

                lblDetalles.setText(
                        "Código: " + lote.getCodigo()
                                + ", Productor: " + lote.getProductor()
                                + ", Tipo de café: " + lote.getTipoCafe()
                                + ", Peso: " + lote.getPeso() + " kg"
                );
            }
        }
    }

    private void agregarContextMenu() {

        ContextMenu contextMenu = new ContextMenu();

        MenuItem editar = new MenuItem("Editar");
        MenuItem eliminar = new MenuItem("Eliminar");

        editar.setOnAction(event -> editarLote());

        eliminar.setOnAction(event -> eliminarLote());

        contextMenu.getItems().addAll(
                editar,
                eliminar
        );

        tablaLotes.setContextMenu(contextMenu);
    }

    private void editarLote() {

        Cafe lote = tablaLotes.getSelectionModel()
                .getSelectedItem();

        if (lote == null) {
            return;
        }

        txtCodigo.setText(lote.getCodigo());
        txtProductor.setText(lote.getProductor());
        txtTipoCafe.setText(lote.getTipoCafe());
        txtPeso.setText(String.valueOf(lote.getPeso()));

        lotes.remove(lote);
    }

    private void eliminarLote() {

        Cafe lote = tablaLotes.getSelectionModel()
                .getSelectedItem();

        if (lote == null) {
            return;
        }

        Alert alerta = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alerta.setTitle("Eliminar lote");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "¿Está seguro de que desea eliminar el lote "
                        + lote.getCodigo() + "?"
        );

        Optional<ButtonType> resultado =
                alerta.showAndWait();

        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {

            lotes.remove(lote);

            lblDetalles.setText(
                    "Lote eliminado correctamente."
            );
        }
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtProductor.clear();
        txtTipoCafe.clear();
        txtPeso.clear();
    }

    private void mostrarAlerta(
            String titulo,
            String mensaje) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}