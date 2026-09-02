package ni.edu.uam.pae_eventos_javafx_pcinalambrica.modelos;

public class Pulperia {

    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public Pulperia(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
}