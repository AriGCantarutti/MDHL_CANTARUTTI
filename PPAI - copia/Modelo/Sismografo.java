package Modelo;

public class Sismografo {
    private int id;
    private String fabricante;
    private String modelo;
    private String fechaAdquisicion;

    //Constructor
    public Sismografo(int id, String fabricante, String modelo, String fechaAdquisicion) {
        this.id = id;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    //Métodos get y set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    //Métodos de sismógrafo
    public void enviarAReparar(){};
}
