package ni.edu.uam.pae_eventos_javafx_pcinalambrica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class Reto3Controller {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cmbCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtRutaImagen;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<ProductoArtesanal> tblProductos;

    @FXML
    private TableColumn<ProductoArtesanal, String> colCodigo;

    @FXML
    private TableColumn<ProductoArtesanal, String> colNombre;

    @FXML
    private TableColumn<ProductoArtesanal, String> colCategoria;

    @FXML
    private TableColumn<ProductoArtesanal, Double> colPrecio;

    @FXML
    private TableColumn<ProductoArtesanal, Integer> colCantidad;

    @FXML
    private TableColumn<ProductoArtesanal, ImageView> colImagen;

    @FXML
    private Label lblEstado;

    private final ObservableList<ProductoArtesanal> productos =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbCategoria.getItems().addAll(
                "Cerámica", "Textil", "Cuero", "Madera", "Decoración"
        );

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));

        tblProductos.setItems(productos);
        tblProductos.setPlaceholder(new Label("No hay productos registrados"));
        tblProductos.setFixedCellSize(64);

        cargarProductosDeEjemplo();
    }

    private void cargarProductosDeEjemplo() {
        productos.add(new ProductoArtesanal(
                "ART-001", "Jarrón pintado", "Cerámica",
                450.00, 8, "imagenes/jarron.jpg"
        ));
        productos.add(new ProductoArtesanal(
                "ART-002", "Hamaca tradicional", "Textil",
                1250.00, 5, "imagenes/hamaca.jpg"
        ));
        productos.add(new ProductoArtesanal(
                "ART-003", "Monedero de tela", "Cuero",
                320.00, 10, "imagenes/monedero.jpg"
        ));
    }

    @FXML
    private void nuevoProducto(ActionEvent event) {
        limpiarCampos();
        lblEstado.setText("Formulario listo para un producto nuevo");
        txtCodigo.requestFocus();
    }

    @FXML
    private void guardarProducto(ActionEvent event) {
        if (hayCamposVacios()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos",
                    "Complete código, nombre, categoría, precio y cantidad.");
            return;
        }

        try {
            double precio = Double.parseDouble(txtPrecio.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());

            if (precio <= 0 || cantidad < 0) {
                mostrarAlerta(Alert.AlertType.WARNING, "Valores incorrectos",
                        "El precio debe ser mayor que cero y la cantidad no puede ser negativa.");
                return;
            }

            if (existeCodigo(txtCodigo.getText())) {
                mostrarAlerta(Alert.AlertType.WARNING, "Código repetido",
                        "Ya existe un producto con ese código.");
                return;
            }

            String rutaImagen = txtRutaImagen.getText().trim();

            ProductoArtesanal producto = new ProductoArtesanal(
                    txtCodigo.getText().trim(),
                    txtNombre.getText().trim(),
                    cmbCategoria.getValue(),
                    precio,
                    cantidad,
                    rutaImagen
            );

            productos.add(producto);
            tblProductos.setItems(productos);
            limpiarCampos();
            lblEstado.setText("Producto guardado correctamente");

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Datos no válidos",
                    "Precio debe ser decimal y cantidad debe ser un número entero.");
        }
    }

    @FXML
    private void buscarProducto(ActionEvent event) {
        String texto = txtBuscar.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            mostrarTodos(event);
            return;
        }

        ObservableList<ProductoArtesanal> encontrados =
                FXCollections.observableArrayList();

        for (ProductoArtesanal producto : productos) {
            if (producto.getCodigo().toLowerCase().contains(texto)
                    || producto.getNombre().toLowerCase().contains(texto)) {
                encontrados.add(producto);
            }
        }

        tblProductos.setItems(encontrados);
        lblEstado.setText("Resultados encontrados: " + encontrados.size());
    }

    @FXML
    private void mostrarTodos(ActionEvent event) {
        txtBuscar.clear();
        tblProductos.setItems(productos);
        lblEstado.setText("Se muestran todos los productos");
    }

    @FXML
    private void registrarVenta(ActionEvent event) {
        ProductoArtesanal seleccionado = tblProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Producto no seleccionado",
                    "Seleccione un producto de la tabla para registrar la venta.");
            return;
        }

        if (seleccionado.getCantidad() == 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Sin existencias",
                    "No hay unidades disponibles de este producto.");
            return;
        }

        seleccionado.setCantidad(seleccionado.getCantidad() - 1);
        tblProductos.refresh();
        lblEstado.setText("Venta registrada: " + seleccionado.getNombre());

        mostrarAlerta(Alert.AlertType.INFORMATION, "Venta registrada",
                "Se vendió una unidad de " + seleccionado.getNombre()
                        + ". Existencia actual: " + seleccionado.getCantidad());
    }

    @FXML
    private void mostrarAcercaDe(ActionEvent event) {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Acerca de",
                "Reto 3 - Catálogo y ventas de una tienda de artesanías nicaragüenses.");
    }

    private boolean hayCamposVacios() {
        return txtCodigo.getText().isBlank()
                || txtNombre.getText().isBlank()
                || cmbCategoria.getValue() == null
                || txtPrecio.getText().isBlank()
                || txtCantidad.getText().isBlank();
    }

    private boolean existeCodigo(String codigo) {
        for (ProductoArtesanal producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return true;
            }
        }
        return false;
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        cmbCategoria.getSelectionModel().clearSelection();
        txtPrecio.clear();
        txtCantidad.clear();
        txtRutaImagen.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
