package Modelo;

public class EstacionSismografica {
     private String nombre;
    private String ubicacion;
    private String coordenada;

    // Atributos adicionales
    private Sismografo sismografo;

    //Constructor
    public EstacionSismografica(String nombre, String ubicacion, String coordenada) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.coordenada = coordenada;
    }

    //Método get y set
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String getCoordenada() {
        return coordenada;
    }
    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    // Métodos de Estación Sismográfica
    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

    public void enviarAReparar() {
        // Marcar la estación como fuera de servicio y enviar su sismógrafo a reparar
        if (sismografo != null) {
            sismografo.enviarAReparar();
        }
    }
}
