package ni.edu.uam.pae_eventos_javafx_pcinalambrica.modelos;

public class Cafe {

    private String codigo;
    private String productor;
    private String tipoCafe;
    private double peso;

    public Cafe(String codigo, String productor, String tipoCafe, double peso) {
        this.codigo = codigo;
        this.productor = productor;
        this.tipoCafe = tipoCafe;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getProductor() {
        return productor;
    }

    public String getTipoCafe() {
        return tipoCafe;
    }

    public double getPeso() {
        return peso;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public void setTipoCafe(String tipoCafe) {
        this.tipoCafe = tipoCafe;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
