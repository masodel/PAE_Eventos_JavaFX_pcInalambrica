package ni.edu.uam.pae_eventos_javafx_pcinalambrica;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class ProductoArtesanal {

    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidad;
    private String rutaImagen;

    public ProductoArtesanal(String codigo, String nombre, String categoria,
                             double precio, int cantidad, String rutaImagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.rutaImagen = rutaImagen;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    /*
     * PropertyValueFactory llama a getImagen() para colocar un ImageView
     * dentro de la columna Imagen del TableView.
     */
    public ImageView getImagen() {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(64);
        imageView.setFitHeight(48);
        imageView.setPreserveRatio(true);

        if (rutaImagen != null && !rutaImagen.isBlank()) {
            InputStream archivo = getClass().getResourceAsStream(rutaImagen);

            if (archivo != null) {
                imageView.setImage(new Image(archivo));
            }
        }

        /* Si aún no se agregó el archivo PNG, se muestra un ícono de respaldo. */
        if (imageView.getImage() == null) {
            imageView.setImage(crearImagenRespaldo());
        }

        return imageView;
    }

    private Image crearImagenRespaldo() {
        int ancho = 64;
        int alto = 48;
        WritableImage imagen = new WritableImage(ancho, alto);
        PixelWriter pixel = imagen.getPixelWriter();

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                pixel.setColor(x, y, Color.web("#F1E3CF"));
            }
        }

        /* Silueta sencilla de una vasija artesanal. */
        for (int y = 8; y < 42; y++) {
            int mitad = ancho / 2;
            int radio = y < 16 ? 7 : 11 + (y - 16) / 3;

            if (radio > 18) {
                radio = 18;
            }

            for (int x = mitad - radio; x <= mitad + radio; x++) {
                pixel.setColor(x, y, Color.web("#A94F35"));
            }
        }

        return imagen;
    }
}
