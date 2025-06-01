package Modelo;

public class Sismografo {
    private int identificadorSismografo;
    private String fechaAdquisicion;
    private int nroDeSerie;
    private CambioEstado cambioEstado;
    private Estado estadoActual;
    private EstacionSismologica estacionSismologica;

    // Constructor
    public Sismografo(int identificadorSismografo, String fechaAdquisicion, int nroDeSerie,
                      CambioEstado cambioEstado, Estado estadoActual, EstacionSismologica estacionSismologica) {
        this.identificadorSismografo = identificadorSismografo;
        this.fechaAdquisicion = fechaAdquisicion;
        this.nroDeSerie = nroDeSerie;
        this.cambioEstado = cambioEstado;
        this.estadoActual = estadoActual;
        this.estacionSismologica = estacionSismologica;
    }

    // Métodos Get y Set
    public int getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setIdentificadorSismografo(int identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public int getNroDeSerie() {
        return nroDeSerie;
    }

    public void setNroDeSerie(int nroDeSerie) {
        this.nroDeSerie = nroDeSerie;
    }

    public CambioEstado getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(CambioEstado cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }
    
    // Métodos de Sismógrafo
    public void enviarAReparar() {}
}
