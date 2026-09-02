package ni.edu.uam.pae_eventos_javafx_pcinalambrica.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.pae_eventos_javafx_pcinalambrica.modelos.Pulperia;

import java.util.ArrayList;
import java.util.List;

public class PulperiaController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtBuscarCodigo;

    @FXML
    private Label lblResultado;

    private List<Pulperia> productos = new ArrayList<>();


    @FXML
    private void OnClickAgregar(ActionEvent event) {

        // Validar campos vacíos
        if (txtCodigo.getText().isBlank()
                || txtNombre.getText().isBlank()
                || txtPrecio.getText().isBlank()
                || txtCantidad.getText().isBlank()) {

            mostrarAlerta(
                    "Campos vacíos",
                    "Debe llenar todos los campos."
            );

            return;
        }

        double precio;
        int cantidad;

        // Validar precio
        try {
            precio = Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Precio inválido",
                    "El precio debe ser un valor numérico."
            );

            return;
        }

        // Validar cantidad
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Cantidad inválida",
                    "La cantidad debe ser un número entero."
            );

            return;
        }

        // Validar valores negativos
        if (precio < 0 || cantidad < 0) {

            mostrarAlerta(
                    "Valores inválidos",
                    "El precio y la cantidad no pueden ser negativos."
            );

            return;
        }

        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();

        // Crear producto
        Pulperia producto = new Pulperia(
                codigo,
                nombre,
                precio,
                cantidad
        );

        // Agregar producto a la lista
        productos.add(producto);

        mostrarAlerta(
                "Producto agregado",
                "El producto se agregó correctamente."
        );

        limpiarCampos();
    }


    @FXML
    private void OnKeyEnter(KeyEvent event) {

        if (event.getCode() != KeyCode.ENTER) {
            return;
        }

        // Si ENTER se presionó en el campo de búsqueda
        if (event.getSource() == txtBuscarCodigo) {
            buscarProducto();
            return;
        }

        // Si ENTER se presionó en uno de los campos para agregar
        if (!txtCodigo.getText().isBlank()
                && !txtNombre.getText().isBlank()
                && !txtPrecio.getText().isBlank()
                && !txtCantidad.getText().isBlank()) {

            OnClickAgregar(new ActionEvent());
        }
    }


    private void buscarProducto() {

        String codigoBuscado = txtBuscarCodigo.getText().trim();

        if (codigoBuscado.isBlank()) {
            lblResultado.setText("Ingrese un código para buscar.");
            return;
        }

        // Buscar el producto por código
        for (Pulperia producto : productos) {

            if (producto.getCodigo().equalsIgnoreCase(codigoBuscado)) {

                lblResultado.setText(
                        "Codigo: " + producto.getCodigo()
                                + ", Nombre: " + producto.getNombre()
                                + ", Precio: " + producto.getPrecio()
                                + ", Cantidad: " + producto.getCantidad()
                );

                return;
            }
        }

        // Si no se encontró
        lblResultado.setText(
                "No se encontró ningún producto con el código: "
                        + codigoBuscado
        );
    }


    private void mostrarAlerta(String titulo, String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }


    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }
}